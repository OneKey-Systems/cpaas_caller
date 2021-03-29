package mx.onekey.dev.cpaas.caller.database.repositories;

import mx.onekey.dev.cpaas.caller.database.entities.AccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {
    Optional<AccessToken> findByToken(String token);
}