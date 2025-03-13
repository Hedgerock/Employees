package com.hedgerock.spring.mvc_hibernate_aop.entity;

import com.hedgerock.spring.mvc_hibernate_aop.entity.employee_details.EmployeeDetails;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.City;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Department;
import com.hedgerock.spring.mvc_hibernate_aop.entity.places.Nationality;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.SelectionCollector;
import com.hedgerock.spring.mvc_hibernate_aop.utils.dto.id_collectors.IdFinder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;


@Audited
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Please specify first name")
    @NotBlank(message = "First name can't be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please specify last name")
    @NotBlank(message = "Last name can't be empty")
    private String lastName;

    @Column(name = "middle_name")
    @NotEmpty(message = "Please specify middle name")
    @NotBlank(message = "Middle name can't be empty")
    private String middleName;

    @Column(name = "department_id", insertable = false, updatable = false)
    private Long depId;

    @Column(name = "city_id", insertable = false, updatable = false)
    private Long cityId;

    @Column(name = "nationality_id", insertable = false, updatable = false)
    private Long nationalityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Audited
    private Department department;

    @Column(name = "salary")
    @Min(value = 500, message = "Min salary should be " + 500 + "$")
    private int salary;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "fire_date")
    private LocalDate fireDate;

    @Transient
    private Long currentDepartmentId;

    @Column(
            name = "employee_details_id",
            insertable = false,
            updatable = false
    )
    private Long employeeDetailsId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_details_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private EmployeeDetails employeeDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id")
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Nationality nationality;

    @Transient
    private SelectionCollector selectionCollector = new SelectionCollector();

    @Transient
    private IdFinder idFinder = new IdFinder();

    public Employee() {
        this.employeeDetails = new EmployeeDetails();
    }

    public Employee(
            String firstName,
            String lastName,
            int salary,
            Long employeeDetailsId,
            EmployeeDetails employeeDetails,
            String middleName,
            LocalDate hireDate
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.salary = salary;
        this.employeeDetailsId = employeeDetailsId;
        this.employeeDetails = employeeDetails;
        this.hireDate = hireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getCurrentDepartmentId() {
        return currentDepartmentId;
    }

    public void setCurrentDepartmentId(Long currentDepartmentId) {
        this.currentDepartmentId = currentDepartmentId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public SelectionCollector getSelectionCollector() {
        return selectionCollector;
    }

    public void setSelectionCollector(SelectionCollector selectionCollector) {
        this.selectionCollector = selectionCollector;
    }

    public IdFinder getIdFinder() {
        return idFinder;
    }

    public void setIdFinder(IdFinder idFinder) {
        this.idFinder = idFinder;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public Long getEmployeeDetailsId() {
        return employeeDetailsId;
    }

    public void setEmployeeDetailsId(Long employeeDetailsId) {
        this.employeeDetailsId = employeeDetailsId;
    }

    public LocalDate getFireDate() {
        return fireDate;
    }

    public void setFireDate(LocalDate fireDate) {
        this.fireDate = fireDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", employeeDetailsId=" + employeeDetailsId +
                ", employeeDetails=" + employeeDetails +
                '}';
    }
}
