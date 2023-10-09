package h2r.dev.pocglueschemaregistry.infra.message

import h2r.dev.pocglueschemaregistry.domain.entity.Payment
import h2r.dev.pocglueschemaregistry.domain.port.PaymentMessagePort
import h2r.dev.pocglueschemaregistry.infra.message.entity.PaymentAvro
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PaymentKafkaMessage(
    private val kafkaTemplate: KafkaTemplate<String, GenericRecord>
) : PaymentMessagePort {

    companion object {
        const val TOPIC_NAME = "payment_topic"
    }

    override fun sendPaymentMessage(payment: Payment) {
        val record = PaymentAvro.newBuilder()
            .setPaymentId(payment.paymentId.toString())
            .setOriginAccount(payment.originAccount)
            .setDestinationAccount(payment.destinationAccount)
            .setAmount(payment.amount)
            .build()

        kafkaTemplate.send(ProducerRecord(TOPIC_NAME, record))
    }

    @KafkaListener(
        topics = [TOPIC_NAME],
        groupId = "h2r.dev.pocglueschemaregistry"
    )
    fun messageListener(paymentAvro: PaymentAvro) {
        println("Received Message: $paymentAvro")
    }

}