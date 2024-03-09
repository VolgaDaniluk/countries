package com.danliuk.countries.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class City extends BaseEntity {

    private String name;

    private long area;

    private long population;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
