package mx.onekey.dev.cpaas.caller.database.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "access_tokens")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_tokens_id_gen")
    @SequenceGenerator(name = "access_tokens_id_gen", sequenceName = "access_tokens_id_seq")
    private Long id;

    @NotEmpty
    private String token;

    private Integer usesLeft;

    @Column(name = "is_valid")
    private boolean isValid;

    public AccessToken() {}

    public AccessToken(String token, int usesLeft) {
        this.token = token;
        this.usesLeft = usesLeft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUsesLeft() {
        return usesLeft;
    }

    public void setUsesLeft(Integer usesLeft) {
        this.usesLeft = usesLeft;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
