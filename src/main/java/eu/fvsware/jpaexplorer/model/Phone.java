/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fvsware.jpaexplorer.model;

import javax.persistence.*;

/**
 *
 * @author fvasi
 */
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    private String number;
    
    @ManyToOne
    private Employee employee;
    
    private String type;

    public Phone(String number, String phoneType, Employee emp) {
        this.number = number;
        this.type = phoneType;
        this.employee = emp;
    }



    public Phone() {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return number;
    }

    public void setPhone(String phone) {
        this.number = phone;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee emp) {
        this.employee = emp;
    }

    public String getType() {
        return type;
    }

    public void setType(String phoneType) {
        this.type = phoneType;
    }
    
    
    
}
