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
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    private String name;
    
    @OneToMany(mappedBy="employee",cascade= CascadeType.ALL)
    private Collection<Phone> phones = new ArrayList<>();
    
    @OneToOne(cascade= CascadeType.ALL)
    private Address address;
    
    private double salary;
    
    @ManyToOne 
    private Department dept;
    
    @ManyToOne
    private Employee manager;
    
    @ManyToMany(cascade= CascadeType.ALL)
    private Collection<Project> projects = new ArrayList<>();
    
    @OneToMany(mappedBy="manager")
    private Collection<Employee> directs = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nume) {
        this.name = nume;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department department) {
        this.dept = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", nume=" + name + '}';
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Collection<Employee> getDirects() {
        return directs;
    }

    public void setDirects(Collection<Employee> directs) {
        this.directs = directs;
    }

    public Collection<Project> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Project> projects) {
        this.projects = projects;
    }
    
    
}
