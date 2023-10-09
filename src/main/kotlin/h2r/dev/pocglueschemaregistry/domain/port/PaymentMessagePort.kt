package h2r.dev.pocglueschemaregistry.domain.port

import h2r.dev.pocglueschemaregistry.domain.entity.Payment

interface PaymentMessagePort {

    fun sendPaymentMessage(payment: Payment)

}