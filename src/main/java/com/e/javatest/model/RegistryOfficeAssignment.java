package com.e.javatest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "atribuicoes_cartorio")
public class RegistryOfficeAssignment {
    @Id
    @Column(length = 20)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    @JsonProperty("nome")
    private String name;

    @Column(name = "situacao", nullable = false)
    @JsonProperty("situacao")
    private Boolean state = true;

    public RegistryOfficeAssignment(String id, String name, Optional<Boolean> state) {
        this.id = id;
        this.name = name;
        if (state.isPresent()) {
            this.state = state.get();
        }
    }
}
