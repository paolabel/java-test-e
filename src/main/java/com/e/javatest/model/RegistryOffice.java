package com.e.javatest.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Cartorios")
public class RegistryOffice {
    @Id private int id;

    @Column(name = "nome", nullable = false, length = 50)
    private String name;

    @Column(name = "observacao", nullable = true, length = 250)
    private String observation;

    @OneToOne(optional = false)
    @JoinColumn(name = "situacao", referencedColumnName = "id", nullable = false)
    private RegistryOfficeSituation situation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "atribuicoes", nullable = false)
    private List<RegistryOfficeAttribution> attributions;

    public RegistryOffice(
            int id,
            String name,
            String observation,
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
        this.observation = observation;
        this.situation = situation;
        this.attributions = attributions;
    }
}
