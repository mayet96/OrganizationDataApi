package ru.id61890868.OrganizationDataApi.model;

import javax.persistence.*;

@Entity
@Table(name = "doc_type")
public class DocType {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    /**
     * Наименование документа
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "code", nullable = false)
    private String code;

    public DocType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Version
    private Integer version;

    public DocType() {

    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String docCode) {
        this.code = docCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String docName) {
        this.name = docName;
    }
}
