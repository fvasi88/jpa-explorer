/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fvsware.jpaexplorer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fvasi
 */
@Entity
public class Address {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) 
    private int id;
    private String street;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String city, String state,String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address() {
        
    }
            
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + ", city=" + city + ", state=" + state + '}';
    }
    
    
    
}
