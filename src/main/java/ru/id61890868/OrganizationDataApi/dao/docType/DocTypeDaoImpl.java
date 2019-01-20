package ru.id61890868.OrganizationDataApi.dao.docType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DocTypeDaoImpl implements DocTypeDao {


    private final EntityManager em;

    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> getAll() throws Exception {

        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
            Root<DocType> root = criteriaQuery.from(DocType.class);

            criteriaQuery.select(root);

            TypedQuery<DocType> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception(" DocTypeDao: error", e);
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getById(long id) throws NotFoundException {
        DocType o = em.find(DocType.class, id);
        if (o == null) {
            throw new NotFoundException("DocTypeDao: type not found");
        }
        return o;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getByCode(String filter) throws NotFoundException {

        if (filter == null || filter.isEmpty()) {
            throw new NullPointerException();
        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DocType> cq = cb.createQuery(DocType.class);
        Root<DocType> root = cq.from(DocType.class);
        cq.where(cb.equal(root.get("code"), filter));
        TypedQuery<DocType> query = em.createQuery(cq);
        DocType result = query.getSingleResult();
        if (result == null) {
            throw new NotFoundException("DocTypeDao: DocTypeNotFound");
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(DocType o) throws Exception {

        try {
            em.persist(o);
        } catch (Exception e) {
            throw new Exception("docTypeDao: on save error", e);
        }
    }
}
