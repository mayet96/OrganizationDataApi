package ru.id61890868.OrganizationDataApi.dao.office;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office loadById(Long id) throws Exception {
        Office o = em.find(Office.class, id);
        if (o == null) {
            throw new Exception("OfficeDao: not found");
        }
        return o;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> all() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Office> c = qb.createQuery(Office.class);
        Root<Office> p = c.from(Office.class);
        TypedQuery<Office> q = em.createQuery(c);
        return q.getResultList();
    }

    @Override
    public List<Office> list(Office filter, Long orgId) throws Exception {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Office> cq = cb.createQuery(Office.class);
        Root<Office> root = cq.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();

        try {
            if (orgId != null) {
                predicates.add(cb.equal(root.get("organization"), orgId));
            }
            if (filter.getAddress() != null) {
                predicates.add(cb.equal(root.get("address"), filter.getAddress()));
            }
            if (filter.getIsActive() != null) {
                predicates.add(cb.equal(root.get("isActive"), filter.getIsActive()));
            }
            if (filter.getName() != null) {
                predicates.add(cb.equal(root.get("name"), filter.getName()));
            }
            if (filter.getPhone() != null) {
                predicates.add(cb.equal(root.get("phone"), filter.getPhone()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            TypedQuery<Office> query = em.createQuery(cq);
            List<Office> result = query.getResultList();
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
    public void save(Office office) throws Exception {
        try {
            em.persist(office);
        } catch (Exception e) {
            throw new Exception("OfficeDao: on save error");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) throws Exception {
        if (office.getId() == null) {
            throw new Exception("OfficeDao: id can not be null");
        }
        Office upOffice = loadById(office.getId());
        if (upOffice == null) {
            throw new Exception("OfficeDao: office not found");
        }
        if (office.getIsActive() != null) {
            upOffice.setIsActive(office.getIsActive());
        }
        if (office.getAddress() != null) {
            upOffice.setAddress(office.getAddress());
        }
        if (office.getName() != null) {
            upOffice.setName(office.getName());
        }
        if (office.getPhone() != null) {
            upOffice.setPhone(office.getPhone());
        }
        em.flush();
    }

    @Override
    public void removeById(long officeId) throws Exception {

        Office o = em.find(Office.class, officeId);
        if (o == null) {
            throw new Exception("OfficeDao: not found");
        }
        em.remove(o);
        em.flush();
    }

}
