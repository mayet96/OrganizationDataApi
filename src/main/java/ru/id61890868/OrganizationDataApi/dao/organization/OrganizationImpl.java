package ru.id61890868.OrganizationDataApi.dao.organization;

import org.springframework.beans.factory.annotation.Autowired;
import ru.id61890868.OrganizationDataApi.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrganizationImpl implements OrganizationDao {

    private final EntityManager em;


    @Autowired
    public OrganizationImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Organization loadById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> all() {
        TypedQuery<Organization> query = em.createQuery(
                "SELECT id, name, full_name FROM organization", Organization.class
        );
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }
}
