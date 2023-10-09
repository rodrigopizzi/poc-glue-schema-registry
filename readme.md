# Aws Glue schema registry
This is a project for try on aws glue schema registry. 
The main idea is create a spring web project that receive a POST of payment request and send to a kafka topic. The schema registry will validate the request and send to the topic if the request is valid. The schema registry will be used to validate the request and send to the topic if the request is valid.

## Table of contents
* [Technologies](#technologies)
* [Setup](#setup)
* [Usage](#usage)
* [References](#references)

## Technologies
* Spring boot web
* Spring boot kafka
* Kotlin
* Avro

## Setup
To run this project, gradle is required. You can use the gradle wrapper to run the project.

In the src/main/avro folder, there is a schema file. This file is used to generate the avro classes. To generate the classes, run the command below:
```shell
./gradlew clean build
```
Look for the generated classes in the build/avro-generated folder. The [build.gradle.kts](build.gradle.kts) file is configured to generate the classes in this folder.

See [application.yaml](src/main/resources/application.yaml) file to configure the kafka connection and the schema registry for producer and consumer.

Take a look in [PaymentKafkaMessage.kt](src/main/kotlin/h2r/dev/pocglueschemaregistry/infra/message/PaymentKafkaMessage.kt) to see how to send and consume a message of kafka topic.

The AWS Glue Schema register needs a schema registry with name registryName1. See [application.yaml](src/main/resources/application.yaml) file to configure the schema registry in a producer and consumer.

For test this project a http sample request is available in [payment-request.http](src/main/http/payment-request.http) file. You can use the [vscode rest client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) to send the request.

## Usage
To run the project, run the command below:
```shell
./gradlew bootRun
``` 

## References
* [Spring boot kafka](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/#messaging.kafka)
* [AWS Glue Schema Registry](https://github.com/awslabs/aws-glue-schema-registry/blob/master/README.md)
* [Schema Registry Compatibility](https://docs.aws.amazon.com/glue/latest/dg/schema-registry.html#schema-registry-compatibility)
* [Avro](https://avro.apache.org/docs/1.11.1/getting-started-java/)
