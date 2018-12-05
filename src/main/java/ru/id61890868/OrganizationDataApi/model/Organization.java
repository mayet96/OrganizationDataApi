package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.*;
import java.util.List;

/**
 * Организация
 */
@Entity(name = "organization")
public class Organization {


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;


    /**
     * Служебное поле hibernate
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
    @Column(name = "inn", nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(name = "kpp", nullable = false)
    private String kpp;


    /**
     * Адрес
     */
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(name = "phone", length = 15, nullable = true)
    private String phone;

    /**
     * Действительность
     */
    @Column(name = "is_active", nullable = true)
    private Boolean isActive;

    public Organization(){

    }

    public Organization(String name, String fullName, String inn, String kpp,
                        String address, String phone, Boolean isActive){
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

    public Boolean getActive() {
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

    public void setActive(Boolean active) {
        isActive = active;
    }

}
