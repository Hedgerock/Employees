package com.hedgerock.spring.mvc_hibernate_aop.entity.places;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Audited
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "min_salary")
    private Integer minSalary = 0;

    @Column(name = "max_salary")
    private Integer maxSalary = 0;

    @Column(name = "total_employees")
    private Integer totalEmployees;

    @OneToMany(mappedBy = "department")
    @Audited
    private List<Employee> employee;

    @Column(name="last_operator")
    private String lastOperator;

    public Department() {
        this.minSalary = 0;
        this.maxSalary = 0;
        this.totalEmployees = 0;
    }

    public Long getId() {
        return id;
    }

    public Department(String name, Integer minSalary, Integer maxSalary, Integer totalEmployees) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.totalEmployees = totalEmployees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
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

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public Integer getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public String getLastOperator() {
        return lastOperator;
    }

    public void setLastOperator(String lastOperator) {
        this.lastOperator = lastOperator;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
