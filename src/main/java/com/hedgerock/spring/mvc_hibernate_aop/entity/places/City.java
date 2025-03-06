package com.hedgerock.spring.mvc_hibernate_aop.entity.places;

import com.hedgerock.spring.mvc_hibernate_aop.entity.Employee;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Audited
@Entity
@Table(name="cities")
public class City {

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

    @OneToMany(mappedBy = "city")
    @Audited
    private List<Employee> employee;

    public City() {
        this.minSalary = 0;
        this.maxSalary = 0;
        this.totalEmployees = 0;
    }

    public City(String name, Integer minSalary, Integer maxSalary, Integer totalEmployees) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.totalEmployees = totalEmployees;
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
        this.name = improveValue(name);
    }

    private String improveValue(String name) {
        return name.isEmpty()
                ? name
                : name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
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

    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", totalEmployees=" + totalEmployees +
                '}';
    }
}
