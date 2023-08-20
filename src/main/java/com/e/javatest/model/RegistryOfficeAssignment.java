package com.e.javatest.model;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "atribuicoes_cartorio")
public class RegistryOfficeAssignment {
    @Id
    @Column(length = 20)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "situacao", nullable = false)
    private Boolean situation = true;

    public RegistryOfficeAssignment(String id, String name, Optional<Boolean> situation) {
        this.id = id;
        this.name = name;
        if (situation.isPresent()) {
            this.situation = situation.get();
        }
    }
}
