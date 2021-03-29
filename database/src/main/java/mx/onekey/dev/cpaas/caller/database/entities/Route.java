package mx.onekey.dev.cpaas.caller.database.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routes_id_gen")
    @SequenceGenerator(name = "routes_id_gen", sequenceName = "routes_id_seq")
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    private HandlerServer server;

    @NotEmpty
    private String handlerUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
