package mx.onekey.dev.cpaas.caller.cli

import mx.onekey.dev.cpaas.api.configuration.PropertiesFileZangConfiguration
import mx.onekey.dev.cpaas.api.configuration.ZangConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun zangConfiguration(): ZangConfiguration {
        return PropertiesFileZangConfiguration()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

