package mx.onekey.dev.cpaas.caller.database.services;

import mx.onekey.dev.cpaas.caller.database.codec.HexEncoder;
import mx.onekey.dev.cpaas.caller.database.entities.AccessToken;
import mx.onekey.dev.cpaas.caller.database.repositories.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    public AccessTokenServiceImpl(AccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
    }

    @Override
    public AccessToken createToken(int bytes, int uses) {
        AccessToken accessToken = new AccessToken(randomBytesToHexString(bytes), uses);
        accessToken.setValid(true);
        accessTokenRepository.save(accessToken);

        return accessToken;
    }

    @Override
    public boolean isValid(String token) {
        Optional<AccessToken> accessTokenOptional = accessTokenRepository.findByToken(token);
        return accessTokenOptional.filter(this::isValid).isPresent();
    }

    @Override
    public boolean isValid(AccessToken accessToken) {
        return (accessToken.getUsesLeft() > 0) && accessToken.isValid();
    }

    @Override
    public AccessToken invalidate(String token) {
        Optional<AccessToken> accessTokenOptional = accessTokenRepository.findByToken(token);
        return accessTokenOptional.map(this::invalidate).orElse(null);
    }

    @Override
    public AccessToken invalidate(AccessToken accessToken) {
        accessToken.setValid(false);
        accessTokenRepository.save(accessToken);

        return accessToken;
    }

    @Override
    public AccessToken enable(String token) {
        Optional<AccessToken> accessTokenOptional = accessTokenRepository.findByToken(token);
        return accessTokenOptional.map(this::enable).orElse(null);
    }

    @Override
    public AccessToken enable(AccessToken accessToken) {
        accessToken.setValid(true);
        accessTokenRepository.save(accessToken);

        return accessToken;
    }

    @Override
    public void use(AccessToken accessToken) {
        accessToken.setUsesLeft(accessToken.getUsesLeft() - 1);
        accessTokenRepository.save(accessToken);
    }

    private String randomBytesToHexString(int size) {
        byte[] bytes = new byte[size];
        secureRandom.nextBytes(bytes);

        return HexEncoder.bytesToHex(bytes);
    }
}
