package h2r.dev.pocglueschemaregistry

import com.amazonaws.services.schemaregistry.utils.AWSSchemaRegistryConstants
import com.amazonaws.services.schemaregistry.utils.AvroRecordType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class PocGlueSchemaRegistryApplication

fun main(args: Array<String>) {
    runApplication<PocGlueSchemaRegistryApplication>(*args)
    AWSSchemaRegistryConstants.AVRO_RECORD_TYPE
    AvroRecordType.GENERIC_RECORD
}

