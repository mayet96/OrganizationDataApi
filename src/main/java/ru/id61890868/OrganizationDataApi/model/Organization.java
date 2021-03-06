package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Организация
 */
@Entity
@Table(name = "organization")
public class Organization {


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
     * Наименование
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Полное наименование
     */
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(name = "inn", length = 50, nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", length = 50, nullable = false)
    private String kpp;


    /**
     * Адрес
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(name = "phone", length = 15)
    private String phone;

    /**
     * Действительность
     */
    @Column(name = "is_active")
    private Boolean isActive;


    public Organization() {

    }

    public Organization(String name, String fullName, String inn, String kpp,
                        String address, String phone, Boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Organization(Long id, String name, String fullName,
                        String inn, String kpp, String address, String phone,
                        Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
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

    public String getFullName() {
        return fullName;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }


}
