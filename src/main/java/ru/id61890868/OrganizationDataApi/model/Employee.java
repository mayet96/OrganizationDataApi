package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.*;

/**
 * Сотрудник
 */
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "second_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "phone", length = 15, nullable = true)
    private String phone;


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
    @JoinColumn(name = "country_id")
    private Country country;

    /**
     * Документ
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Document document;

    public Employee() {

    }

    public Employee(String firstName, String lastName,
                    String middleName, String position,
                    String phone, Office office, Country country,
                    Document document) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.office = office;
        this.country = country;
        this.document = document;
    }

    public Employee(Long id, String firstName, String lastName,
                    String middleName, String position, String phone,
                    Office office, Country country, Document document) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.office = office;
        this.country = country;
        this.document = document;
    }

    public Long getId() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "{id: %s, name: %s, pos: %s, office: %s, doc: %s, country: %s, phone: %s}",
                id.toString(), firstName, position, office.getName(),
                document.getDocType().getName(), country.getName(), phone
        );
    }
}
