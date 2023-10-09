import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.apache.avro.tool.SpecificCompilerTool

plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "h2r.dev"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // kafka
    implementation("org.springframework.kafka:spring-kafka")

    // Installing SerDe Libraries
    implementation("software.amazon.glue:schema-registry-serde:1.1.17")
    implementation("org.apache.avro:avro:1.11.1")

    // spring doc springdoc-openapi-starter-webmvc-ui
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
}

// Added avro-tools dependency to buildscript
buildscript {
    dependencies {
        classpath("org.apache.avro:avro-tools:1.11.1")
    }
}

// Avro generation source set
sourceSets {
    main {
        java {
            srcDirs("build/avro-generated")
        }
    }
}

// Task for generating Avro Java classes
tasks.create("avroGenerated") {
    doLast {
        SpecificCompilerTool().run(
            System.`in`, System.out, System.err, listOf(
                "-encoding", "UTF-8",
                "-string",
                "-fieldVisibility", "private",
                "-noSetters",
                "-bigDecimal",
                "schema", "$projectDir/src/main/avro", "$projectDir/build/avro-generated"
            )
        )
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
    // configure kotlin-compile task to use avro-generated as an additional source directory
    dependsOn("avroGenerated")
}

tasks.withType<Test> {
    useJUnitPlatform()
}










