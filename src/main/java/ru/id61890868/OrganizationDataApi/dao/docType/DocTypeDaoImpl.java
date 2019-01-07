package ru.id61890868.OrganizationDataApi.dao.docType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Country;
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


    @Override
    public List<DocType> getAll() throws Exception {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> root = criteriaQuery.from(DocType.class);

        criteriaQuery.select(root);

        TypedQuery<DocType> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }


    @Override
    public DocType getById(long id) throws NotFoundException {
        DocType o = em.find(DocType.class, id);
        if (o == null) {
            throw new NotFoundException("DocTypeDao: type not found");
        }
        return o;
    }

    @Override
    public void save(DocType o) throws Exception {

        try {
            em.persist(o);
        } catch (Exception e) {
            throw new Exception("docTypeDao: on save error");
        }
    }
}
