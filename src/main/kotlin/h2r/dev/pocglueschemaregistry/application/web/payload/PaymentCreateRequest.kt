package h2r.dev.pocglueschemaregistry.application.web.payload

import h2r.dev.pocglueschemaregistry.domain.entity.Payment
import java.util.UUID

data class PaymentCreateRequest(
    val paymentId: String,
    val originAccount: String,
    val destinationAccount: String,
    val amount: Double
) {
    fun toDomain(): Payment {
        return Payment(UUID.fromString(paymentId), originAccount, destinationAccount, amount)
    }
}
