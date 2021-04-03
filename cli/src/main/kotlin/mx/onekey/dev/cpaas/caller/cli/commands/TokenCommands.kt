package mx.onekey.dev.cpaas.caller.cli.commands

import mx.onekey.dev.cpaas.caller.database.repositories.AccessTokenRepository
import mx.onekey.dev.cpaas.caller.database.services.AccessTokenService
import org.springframework.data.domain.PageRequest
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import org.springframework.transaction.annotation.Transactional

@ShellComponent
class TokenCommands(private val tokenService: AccessTokenService, private val tokenRepository: AccessTokenRepository) {

    @ShellMethod(value = "Create a new access token.", key = ["tok-create"])
    fun createToken(@ShellOption(help = "Size of the token in bytes. It is encoded in HEX so the resulting token will " +
            "have twice the size in characters") size: Int,
                    uses: Int): String {
        val token = tokenService.createToken(size, uses)
        return "New token with ${token.usesLeft} uses left has been created: ${token.token}"
    }

    @ShellMethod(value = "Set the number of uses left for a specific token.", key = ["tok-uses"])
    fun setUses(token: String, uses: Int): String {
        val accessToken = tokenRepository.findByToken(token)
        return if (accessToken.isEmpty) {
            "ERROR: Token $token was not found"
        }
        else {
            val dbToken = accessToken.get()
            dbToken.usesLeft = uses
            tokenRepository.save(dbToken)

            "Token ${dbToken.token} now has ${dbToken.usesLeft} uses left"
        }
    }

    @ShellMethod(value = "Disable the specified token. Does not delete it.", key = ["tok-dis"])
    fun disableToken(token: String): String {
        tokenService.invalidate(token).apply {
            return if (this == null) {
                "ERROR: Token $token not found"
            }
            else {
                "Token ${this.token} is no longer valid"
            }
        }
    }

    @ShellMethod(value = "Delete the specified token.", key = ["tok-del"])
    @Transactional
    fun deleteToken(token: String): String {
        tokenRepository.deleteByToken(token)
        return "Token $token has been deleted"
    }

    @ShellMethod(value = "Enable the specified token.", key = ["tok-en"])
    fun enableToken(token: String): String {
        tokenService.enable(token).apply {
            return if (this.isValid) {
                "Token ${this.token} has been enabled"
            }
            else {
                "ERROR: Token ${this.token} could not be enabled"
            }
        }
    }

    @ShellMethod(value = "List information about a token.", key = ["tok-info"])
    fun tokenInfo(token: String): String {
        val accessToken = tokenRepository.findByToken(token)
        return if (accessToken.isPresent) {
            accessToken.get().toString()
        }
        else {
            "ERROR: $token not found"
        }
    }

    @ShellMethod(value = "List n amount of tokens.", key = ["tok-list"])
    fun listToken(@ShellOption(help = "Max amount of tokens that will be shown", defaultValue = "10") size: Int) {
        for (token in tokenRepository.findAll(PageRequest.of(0, size)).content) {
            print("ID: ${token.id} ---> ${token.token}\n")
        }
    }

}