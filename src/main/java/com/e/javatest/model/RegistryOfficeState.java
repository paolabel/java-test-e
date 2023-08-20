package com.e.javatest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "situacoes_cartorio")
public class RegistryOfficeState {
    @Id
    @Column(length = 20)
    private String id;

    @Column(name = "nome", nullable = false, length = 50)
    @JsonProperty("nome")
    private String name;

    public RegistryOfficeState(String id, String name) {
        this.id = id;
        this.name = name;
    }
}