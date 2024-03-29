package com.learning.spring.currencies.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Currency {

    @Id
    private String name;
    private BigDecimal ratio;

    public Currency() {
    }

    public Currency(String name, BigDecimal ratio) {
        this.name = name;
        this.ratio = ratio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) &&
                Objects.equals(ratio, currency.ratio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ratio);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", ratio=" + ratio +
                '}';
    }
}
