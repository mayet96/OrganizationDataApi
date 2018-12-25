package ru.id61890868.OrganizationDataApi.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "country")
public class Country {


    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Код страны
     */
    @Column(nullable = false)
    private Integer code;

    /**
     * Название страны
     */
    @Column(length = 255, nullable = false)
    private String name;

    public Country() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
