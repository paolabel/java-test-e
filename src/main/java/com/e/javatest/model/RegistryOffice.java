package com.e.javatest.model;

import java.util.List;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "cartorios")
public class RegistryOffice {
    @Id private int id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "observacao", nullable = true, length = 250)
    private String observation;

    @OneToOne(optional = false)
    @JoinColumn(name = "situacao", referencedColumnName = "id", nullable = false)
    private RegistryOfficeSituation situation;

    @ManyToMany
    @JoinTable(
            name = "relacao_cartorio_atribuicoes",
            joinColumns = {
                @JoinColumn(
                        name = "cartorio_id",
                        referencedColumnName = "id",
                        table = "cartorios",
                        nullable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(
                        name = "atribuicao_id",
                        referencedColumnName = "id",
                        table = "atribuicoes_cartorio",
                        nullable = false)
            },
            uniqueConstraints =
                    @UniqueConstraint(
                            name = "UNIQUE_cartorio_atribuicao",
                            columnNames = {"cartorio_id", "atribuicao_id"}))
    private List<RegistryOfficeAttribution> attributions;

    public RegistryOffice(
            int id,
            String name,
            Optional<String> observation,
            RegistryOfficeSituation situation,
            List<RegistryOfficeAttribution> attributions) {
        if (id <= 0) {
            throw new IllegalArgumentException("Valor do ID inválido para cadastro de cartório");
        }
        if (attributions.size() < 1) {
            throw new IllegalArgumentException(
                    "É necessária pelo menos 1 atribuição do cartório no registro");
        }
        this.id = id;
        this.name = name;
        if (observation.isPresent()) {
            this.observation = observation.get();
        }
        this.situation = situation;
        this.attributions = attributions;
    }
}
