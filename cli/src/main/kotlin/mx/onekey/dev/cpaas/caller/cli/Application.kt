package mx.onekey.dev.cpaas.caller.cli

import mx.onekey.dev.cpaas.api.configuration.PropertiesFileZangConfiguration
import mx.onekey.dev.cpaas.api.configuration.ZangConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackages = ["mx.onekey.dev.cpaas.caller.database"])
@EnableJpaRepositories(basePackages = ["mx.onekey.dev.cpaas.caller.database"])
@SpringBootApplication(scanBasePackages = ["mx.onekey.dev.cpaas.caller.database", "mx.onekey.dev.cpaas.caller.cli"])
class Application {
    @Bean
    fun zangConfiguration(): ZangConfiguration {
        return PropertiesFileZangConfiguration()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

