package ru.id61890868.OrganizationDataApi.model;

import javax.persistence.*;

@Entity(name = "doc")
public class Document {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    public Long getId() {
        return id;
    }
}
