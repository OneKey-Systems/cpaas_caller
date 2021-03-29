package mx.onekey.dev.cpaas.caller.cli.commands

import mx.onekey.dev.cpaas.api.configuration.ZangConfiguration
import mx.onekey.dev.cpaas.api.connectors.CallsConnector
import mx.onekey.dev.cpaas.api.connectors.ZangConnectorFactory
import mx.onekey.dev.cpaas.api.params.MakeCallParams
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod

@ShellComponent
class CallCommands(zangConfiguration: ZangConfiguration) {

    val callsConnector: CallsConnector = ZangConnectorFactory.getCallsConnector(zangConfiguration)

    @ShellMethod(key = ["call-link"], value = "Call a phone number with a set URL to be POSTED once the call connects")
    fun callWithLink(phone: String, link: String) {
        callsConnector.makeCall(MakeCallParams.builder()
                .setTo(phone)
                .setFrom("+16178125467")
                .setUrl(link)
                .build()
        )
    }
}