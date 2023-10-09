package h2r.dev.pocglueschemaregistry.application.config

import h2r.dev.pocglueschemaregistry.domain.port.PaymentMessagePort
import h2r.dev.pocglueschemaregistry.domain.usecase.PaymentTransactionUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {

    @Bean
    fun paymentTransactionUseCase(paymentMessagePort: PaymentMessagePort) =
        PaymentTransactionUseCase(paymentMessagePort)

}