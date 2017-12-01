/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fvsware.jpaexplorer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author fvasi
 */
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    private String nume;
    
    @OneToMany(mappedBy="dept",cascade= CascadeType.ALL)
    private Collection<Employee> employees = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", nume=" + nume + '}';
    }
    
    
    
    
}
