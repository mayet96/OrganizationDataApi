package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.*;

@Entity(name = "office")
public class Office {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

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
     * id организации владельца
     */
    @Column(name = "org_id", nullable = false)
    private Long orgId;

    public Office(){

    }

    public Office( String name, String address,
                  String phone, Boolean isActive, Long orgId) {
        this.orgId = orgId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

}
