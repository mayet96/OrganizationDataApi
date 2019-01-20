package ru.id61890868.OrganizationDataApi.dao.document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Document;

import javax.persistence.EntityManager;

@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager em;

    @Autowired
    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document getById(long id) throws NotFoundException {
        Document o = em.find(Document.class, id);
        if (o == null) {
            throw new NotFoundException("DocumentDao: document not found");
        }
        return o;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Document o) throws Exception {
        try {
            em.persist(o);
        } catch (Exception e) {
            throw new Exception("docDao: on save error", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Document o) throws Exception {
        if (o.getId() == null) {
            throw new Exception("DocDao: id can not be null");
        }
        Document upDoc = getById(o.getId());

        if (o.getDocDate() != null) {
            upDoc.setDocDate(o.getDocDate());
        }
        if (o.getDocNumber() != null) {
            upDoc.setDocNumber(o.getDocNumber());
        }
        if (o.getDocType() != null) {
            upDoc.setDocType(o.getDocType());
        }
        if (o.getIsIdentified() != null) {
            upDoc.setIsIdentified(o.getIsIdentified());
        }
        em.flush();
    }
}
