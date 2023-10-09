package h2r.dev.pocglueschemaregistry.domain.entity

import java.util.UUID

data class Payment(
    val paymentId: UUID,
    val originAccount: String,
    val destinationAccount: String,
    val amount: Double
)
