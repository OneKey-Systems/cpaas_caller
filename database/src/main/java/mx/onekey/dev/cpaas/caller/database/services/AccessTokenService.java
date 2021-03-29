package mx.onekey.dev.cpaas.caller.database.services;

import mx.onekey.dev.cpaas.caller.database.entities.AccessToken;

public interface AccessTokenService {
    AccessToken createToken(int bytes, int uses);
    boolean isValid(String token);
    boolean isValid(AccessToken accessToken);
    AccessToken invalidate(String token);
    AccessToken invalidate(AccessToken accessToken);
    void use(AccessToken accessToken);
}
