package com.hedgerock.spring.mvc_hibernate_aop.entity.places;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Audited
@Entity
@Table(name="nationalities")
public class Nationality {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_salary")
    private Integer maxSalary;

    @Column(name = "total_employees")
    private Integer totalEmployees;

    @OneToMany(mappedBy = "nationality")
    @Audited
    private List<Employee> employee;

    @Column(name="last_operator")
    private String lastOperator;


    public Nationality() {
        this.minSalary = 0;
        this.maxSalary = 0;
        this.totalEmployees = 0;
    }

    public Nationality(String name, Integer minSalary, Integer maxSalary, Integer totalEmployees) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.totalEmployees = totalEmployees;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getTotalEmployees() {
        return totalEmployees;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}
