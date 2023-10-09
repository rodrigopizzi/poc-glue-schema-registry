package h2r.dev.pocglueschemaregistry.application.web

import h2r.dev.pocglueschemaregistry.application.web.payload.PaymentCreateRequest
import h2r.dev.pocglueschemaregistry.domain.usecase.PaymentTransactionUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/payment")
class PaymentController(
    private val paymentTransactionUseCase: PaymentTransactionUseCase
) {

    @PostMapping
    fun createPayment(@RequestBody paymentCreateRequest: PaymentCreateRequest): ResponseEntity<Unit> {
        paymentTransactionUseCase.createPayment(paymentCreateRequest.toDomain())
        return ResponseEntity.created(URI("/paymnet/${paymentCreateRequest.paymentId}")).build()
    }

}