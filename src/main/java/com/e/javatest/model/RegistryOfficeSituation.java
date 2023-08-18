package com.e.javatest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "SituacoesDoCartorio")
public class RegistryOfficeSituation {
    @Id
    @Column(length = 20)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    public RegistryOfficeSituation(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
