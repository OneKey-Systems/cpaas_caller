package mx.onekey.dev.cpaas.caller.database.services;

import mx.onekey.dev.cpaas.caller.database.entities.AccessToken;
import mx.onekey.dev.cpaas.caller.database.repositories.AccessTokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccessTokenRepositoryTest {

    private final AccessTokenService accessTokenService;
    private final AccessTokenRepository accessTokenRepository;

    @Autowired
    public AccessTokenRepositoryTest(AccessTokenService accessTokenService,
                                     AccessTokenRepository accessTokenRepository) {
        this.accessTokenService = accessTokenService;
        this.accessTokenRepository = accessTokenRepository;
    }

    @Test
    @Transactional
    void deleteByTokenTest() {
        AccessToken token = accessTokenService.createToken(5, 4);
        assert accessTokenRepository.findByToken(token.getToken()).isPresent();

        accessTokenRepository.deleteByToken(token.getToken());
        assert accessTokenRepository.findByToken(token.getToken()).isEmpty();
    }

}