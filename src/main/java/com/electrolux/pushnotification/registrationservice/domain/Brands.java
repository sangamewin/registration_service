package com.electrolux.pushnotification.registrationservice.domain;

//import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
//@Entity
//@Table(name = "brands", uniqueConstraints = {
 //       @UniqueConstraint(columnNames = "externalId")})
public class Brands implements Serializable {
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(name = "name")
    private String name;

   // @Column(name = "externalid")
    private String externalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brands brands = (Brands) o;
        return Objects.equals(id, brands.id) && Objects.equals(name, brands.name) && Objects.equals(externalId, brands.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, externalId);
    }
}
