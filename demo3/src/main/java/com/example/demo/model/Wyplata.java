package com.example.demo.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class Wyplata{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ilosc;
    private String data;


    @ManyToMany(mappedBy = "wyplatas", cascade = CascadeType.ALL)
    private Set<Wplata> wplatas = new HashSet<>();

    public Wyplata(String ilosc, String data) {
        this.ilosc = ilosc;
        this.data = data;
    }

    public Wyplata() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Set<Wplata> getWplatas() {
        return wplatas;
    }

    public void setWplatas(Set<Wplata> wplatas) {
        this.wplatas = wplatas;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", ilosc='" + ilosc + '\'' +
                ", data='" + data + '\'' +
                ", wplata=" + wplatas +
                '}';
    }
}