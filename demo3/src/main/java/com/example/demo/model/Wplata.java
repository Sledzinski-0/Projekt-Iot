package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Wplata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String volume;
    private String paymentDay;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wplata_wyplata",
            joinColumns = @JoinColumn(name = "wplata_id"),
            inverseJoinColumns = @JoinColumn(name = "wyplata_id")
    )
    private Set<Wyplata> wyplatas = new HashSet<>();

    public Wplata(String volume, String paymentDay) {
        this.volume = volume;
        this.paymentDay = paymentDay;
    }

    public Wplata() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(String paymentDay) {
        this.paymentDay = paymentDay;
    }

    public Set<Wyplata> getWyplatas() {
        return wyplatas;
    }

    public void setWyplatas(Set<Wyplata> wyplatas) {
        this.wyplatas = wyplatas;
    }

    @Override
    public String toString() {
        return "Wplata{" +
                "id=" + id +
                ", volume='" + volume + '\'' +
                ", paymentDay='" + paymentDay + '\'' +
                ", wyplatas=" + wyplatas +
                '}';
    }
}
