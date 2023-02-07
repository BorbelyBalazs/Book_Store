package com.progmatic.hibernate.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;


@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long store_id;

    private String name;
    private String address;

    private boolean isActive;


    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookConnStore> bcs;

    public Store(String name, String address, boolean isActive) {
        this.name = name;
        this.address = address;
        this.isActive = isActive;
    }

    public Store() {

    }

    public long getStore_id() {
        return store_id;
    }

    public void setStore_id(long store_id) {
        this.store_id = store_id;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<BookConnStore> getBcs() {
        return bcs;
    }

    public void setBcs(List<BookConnStore> bcs) {
        this.bcs = bcs;
    }

    @Override
    public String toString() {
        return "store_id=" + store_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                ", bcs=" + bcs + "\n";
    }
}
