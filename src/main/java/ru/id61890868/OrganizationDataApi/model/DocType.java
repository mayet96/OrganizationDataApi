package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

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

    @Version
    private Integer version;

    /**
     * Наименование документа
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    public DocType(String name, String code) {
        this.name = name;
        this.code = code;
    }


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
