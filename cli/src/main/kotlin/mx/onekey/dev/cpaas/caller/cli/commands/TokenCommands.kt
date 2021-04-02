package mx.onekey.dev.cpaas.caller.cli.commands

import mx.onekey.dev.cpaas.caller.database.repositories.AccessTokenRepository
import mx.onekey.dev.cpaas.caller.database.services.AccessTokenService
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption

@ShellComponent
class TokenCommands(private val tokenService: AccessTokenService, private val tokenRepository: AccessTokenRepository) {

    @ShellMethod(value = "Create a new access token")
    fun createToken(@ShellOption(help = "Size of the token in bytes. It is encoded in HEX so the resulting token will " +
            "have twice the size in characters") size: Int,
                    uses: Int): String {
        val token = tokenService.createToken(size, uses)
        return "New token with ${token.usesLeft} has been created: \n${token.token}"
    }

    @ShellMethod(value = "Set the number of uses left for a specific token")
    fun setUses(token: String, uses: Int): String {
        val accessToken = tokenRepository.findByToken(token)
        return if(accessToken.isEmpty) {
            "ERROR: Token $token was not found"
        }
        else {
            val dbToken = accessToken.get()
            dbToken.usesLeft = uses
            tokenRepository.save(dbToken)

            "Token ${dbToken.token} now has ${dbToken.usesLeft} uses left"
        }
    }

    @ShellMethod(value = "Disable the specified token. Does not delete it.")
    fun disableToken(token: String): String {
        tokenService.invalidate(token).apply {
            return if(this == null) {
                "ERROR: Token $token not found"
            }
            else {
                "Token ${this.token} is no longer valid"
            }
        }
    }

    @ShellMethod(value = "Delete the specified token")
    fun deleteToken(token: String): String {
        TODO("Implement deleteByToken in database library")
    }

    fun enableToken(token: String): String {
        TODO("Code method in database library")
    }

    fun tokenInfo(token: String): String {
        TODO("Code toString in database library")
    }

}