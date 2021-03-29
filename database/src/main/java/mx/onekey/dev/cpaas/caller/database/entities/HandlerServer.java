package mx.onekey.dev.cpaas.caller.database.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "handler_servers")
public class HandlerServer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "handler_servers_id_gen")
    @SequenceGenerator(name = "handler_servers_id_gen", sequenceName = "handler_servers_id_seq")
    private Long id;

    @NotEmpty
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
