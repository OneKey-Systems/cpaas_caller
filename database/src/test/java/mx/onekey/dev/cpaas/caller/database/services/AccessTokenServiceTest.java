package mx.onekey.dev.cpaas.caller.database.services;

import mx.onekey.dev.cpaas.caller.database.entities.AccessToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccessTokenServiceTest {

    private final AccessTokenService accessTokenService;

    @Autowired
    public AccessTokenServiceTest(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Test
    public void createTest() {
        AccessToken accessToken = accessTokenService.createToken(6, 10);
        assert accessToken.getId() != null;
        assert accessToken.getToken().length() == 12;
        assert accessToken.getUsesLeft() == 10;
    }

    @Test
    public void isValidTest() {
        AccessToken validToken = accessTokenService.createToken(5, 7);
        assert accessTokenService.isValid(validToken.getToken());

        AccessToken invalidToken = accessTokenService.createToken(12, 0);
        assert !accessTokenService.isValid(invalidToken.getToken());

        accessTokenService.invalidate(validToken);
        assert !accessTokenService.isValid(validToken.getToken());
    }

    @Test
    public void useTest() {
        AccessToken accessToken = accessTokenService.createToken(5, 1);
        accessTokenService.use(accessToken);

        assert accessToken.getUsesLeft() == 0;
        assert !accessTokenService.isValid(accessToken);
    }

}
