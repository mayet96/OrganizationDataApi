package ru.id61890868.OrganizationDataApi.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doc")
public class Document {

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
     * Номер документа
     */
    @Column(name = "doc_number", nullable = false, length = 20)
    private String docNumber;

    /**
     * Дата документа
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    /**
     * Тип документа
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_type", nullable = false)
    private DocType docType;

    public Document() {

    }

    public Long getId() {
        return id;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }
}
