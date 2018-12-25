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
     * Код документа
     */
    @Column(name = "code")
    private String docCode;

    /**
     * Наименование документа
     */
    @Column(name = "name")
    private String docName;


    public DocType() {

    }

    public Long getId() {
        return id;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

}
