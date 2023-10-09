package h2r.dev.pocglueschemaregistry.domain.usecase

import h2r.dev.pocglueschemaregistry.domain.entity.Payment
import h2r.dev.pocglueschemaregistry.domain.port.PaymentMessagePort

class PaymentTransactionUseCase(
    private val paymentMessagePort: PaymentMessagePort
) {

    fun createPayment(payment: Payment) {
        if (payment.amount < 0) {
            throw IllegalArgumentException("Amount must be greater than zero")
        }

        if (payment.amount > 1000) {
            throw IllegalArgumentException("Amount must be less than 1000")
        }

        if (payment.originAccount.isBlank()) {
            throw IllegalArgumentException("Name must not be blank")
        }

        if (payment.destinationAccount.isBlank()) {
            throw IllegalArgumentException("Name must not be blank")
        }

        paymentMessagePort.sendPaymentMessage(payment)
    }

}