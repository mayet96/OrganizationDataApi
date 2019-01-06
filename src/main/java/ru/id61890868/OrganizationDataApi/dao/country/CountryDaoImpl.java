package ru.id61890868.OrganizationDataApi.dao.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao {


    private final EntityManager em;

    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Country> getAll() throws Exception {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> root = criteriaQuery.from(Country.class);

        criteriaQuery.select(root);

        TypedQuery<Country> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }


    @Override
    public Country getById(long id) throws NotFoundException {
        Country c = em.find(Country.class, id);
        if (c == null) {
            throw new NotFoundException();
        }
        return c;
    }

    @Override
    public void save(Country o) throws Exception {
        try {
            em.persist(o);
        } catch (Exception e) {
            throw new Exception("country dao: on save error");
        }
    }
}
