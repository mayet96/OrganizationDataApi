package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.*;

/**
 * Офис
 */
@Entity(name = "office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;


    /**
     * Наименование офиса
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * Телефон офиса
     */
    @Column(name = "phone", length = 15, nullable = true)
    private String phone;

    /**
     * Действительность
     */
    @Column(name = "is_active", nullable = true)
    private Boolean isActive;

    /**
     * Связь с таблицей организаций
     */
    @ManyToOne
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    public Office() {

    }

    public Office(String name, String address,
                  String phone, Boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Office(Long id, String name, String address,
                  String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
