package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.*;

/**
 * Сотрудник
 */
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Связь с таблицей офисов
     */
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    /**
     * Гражданство
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "citizenship_id")
    private Country country;

    /**
     * Документ
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", unique = true)
    private Document document;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String position, Boolean isIdentified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.isIdentified = isIdentified;
    }

    public Integer getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
