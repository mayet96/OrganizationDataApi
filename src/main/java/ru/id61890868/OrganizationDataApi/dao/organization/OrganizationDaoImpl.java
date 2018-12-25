package ru.id61890868.OrganizationDataApi.dao.organization;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public Organization loadById(Long id) throws Exception {
        Organization o = em.find(Organization.class, id);
        if (o == null) {
            throw new Exception("OrgDao: not found");
        }
        return o;
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

    @Override
    public List<Organization> list(Organization filter) throws Exception {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
        Root<Organization> root = cq.from(Organization.class);
        List<Predicate> predicates = new ArrayList<>();

        try {
            if (filter.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filter.getId()));
            }
            if (filter.getName() != null) {
                predicates.add(cb.equal(root.get("name"), filter.getName()));
            }
            if (filter.getFullName() != null) {
                predicates.add(cb.equal(root.get("fullName"), filter.getFullName()));
            }
            if (filter.getInn() != null) {
                predicates.add(cb.equal(root.get("inn"), filter.getInn()));
            }
            if (filter.getKpp() != null) {
                predicates.add(cb.equal(root.get("kpp"), filter.getKpp()));
            }
            if (filter.getAddress() != null) {
                predicates.add(cb.equal(root.get("address"), filter.getAddress()));
            }
            if (filter.getIsActive() != null) {
                predicates.add(cb.equal(root.get("isActive"), filter.getIsActive()));
            }
            if (filter.getPhone() != null) {
                predicates.add(cb.equal(root.get("phone"), filter.getPhone()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Organization> query = em.createQuery(cq);
            List<Organization> result = query.getResultList();
            if (result.isEmpty()) {
                throw new NotFoundException();
            }
            return result;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            Logger.getLogger(this.getClass()).error(e.getStackTrace());
            throw new Exception("incorrect filter");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) throws Exception {
        try {
            em.persist(organization);
        } catch (Exception e) {
            throw new Exception("OrgDao: on save error");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization org) throws Exception {
        if (org.getId() == null) {
            throw new Exception("OrgDao: id can not be null");
        }
        Organization upOrg = loadById(org.getId());
        if (upOrg == null) {
            throw new Exception("OrgDao: office not found");
        }
        if (org.getIsActive() != null) {
            upOrg.setIsActive(org.getIsActive());
        }
        if (org.getAddress() != null) {
            upOrg.setAddress(org.getAddress());
        }
        if (org.getName() != null) {
            upOrg.setName(org.getName());
        }
        if (org.getFullName() != null) {
            upOrg.setFullName(org.getFullName());
        }
        if (org.getPhone() != null) {
            upOrg.setPhone(org.getPhone());
        }
        if (org.getInn() != null) {
            upOrg.setInn(org.getInn());
        }
        if (org.getKpp() != null) {
            upOrg.setKpp(org.getKpp());
        }

        em.flush();
    }

    @Override
    public void removeById(long orgId) throws Exception {

        Organization o = em.find(Organization.class, orgId);
        if (o == null) {
            throw new Exception("OrgDao: not found");
        }
        em.remove(o);
        em.flush();
    }

}
