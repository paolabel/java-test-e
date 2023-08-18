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
@Entity(name = "AtribuicoesDoCartorio")
public class RegistryOfficeAttribution {
    @Id
    @Column(length = 20)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "situacao", nullable = false)
    private Boolean situation = true;

    public RegistryOfficeAttribution(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RegistryOfficeAttribution(String id, String name, Boolean situation) {
        this.id = id;
        this.name = name;
        this.situation = situation;
    }
}
