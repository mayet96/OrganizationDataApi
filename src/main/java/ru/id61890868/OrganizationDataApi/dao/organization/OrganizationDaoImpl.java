package ru.id61890868.OrganizationDataApi.dao.organization;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager em;


    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
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

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> c = qb.createQuery(Organization.class);
        Root<Organization> p = c.from(Organization.class);
        TypedQuery<Organization> q = em.createQuery(c);
        List<Organization> l = q.getResultList();
        return l;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization org) throws Exception {
        if(org.getId() == null){
            throw new Exception("OrgDao: id can not be null");
        }
        Organization upOrg = loadById(org.getId());
        if(upOrg == null){
            throw new Exception("OrgDao: office not found");
        }
        if(org.getIsActive() != null){
            upOrg.setIsActive(org.getIsActive());
        }
        if(org.getAddress() != null){
            upOrg.setAddress(org.getAddress());
        }
        if(org.getName() != null){
            upOrg.setName(org.getName());
        }
        if(org.getFullName() != null){
            upOrg.setFullName(org.getFullName());
        }
        if(org.getPhone() != null){
            upOrg.setPhone(org.getPhone());
        }
        if(org.getInn() != null){
            upOrg.setInn(org.getInn());
        }
        if(org.getKpp() != null){
            upOrg.setKpp(org.getKpp());
        }

        em.flush();
    }

    /**
     * {@inheritDoc}
     * не используется
     */
    @Override
    public void override(Organization org) throws Exception {
        if(org.getId() == null){
            throw new Exception("OrgDao: id can not be null");
        }
        em.merge(org);
    }

}
