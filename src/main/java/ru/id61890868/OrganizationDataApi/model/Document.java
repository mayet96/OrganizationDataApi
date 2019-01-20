package ru.id61890868.OrganizationDataApi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
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
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Тип документа
     */
    @ManyToOne
    @JoinColumn(name = "doc_type", nullable = false)
    private DocType docType;

    public Document() {

    }

    public Document(String docNumber, Date docDate, Boolean isIdentified, DocType docType) {
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.isIdentified = isIdentified;
        this.docType = docType;
    }

    public Document(Long id, String docNumber, Date docDate, Boolean isIdentified, DocType docType) {
        this(docNumber, docDate, isIdentified, docType);
        this.id = id;
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

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
