package com.e.javatest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "situacoes_cartorio")
@JsonPropertyOrder(alphabetic = true)
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
