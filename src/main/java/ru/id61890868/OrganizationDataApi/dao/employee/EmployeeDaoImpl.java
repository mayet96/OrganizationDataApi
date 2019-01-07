package ru.id61890868.OrganizationDataApi.dao.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.dao.country.CountryDao;
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.dao.document.DocDao;
import ru.id61890868.OrganizationDataApi.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {


    private DocDao docDao;

    private CountryDao countryDao;

    private DocTypeDao docTypeDao;

    private EntityManager em;

    @Autowired
    public EmployeeDaoImpl(DocDao docDao, CountryDao countryDao, DocTypeDao docTypeDao, EntityManager em) {
        this.docDao = docDao;
        this.countryDao = countryDao;
        this.docTypeDao = docTypeDao;
        this.em = em;
    }


    @Override
    public void save(Employee o) throws Exception {
        try {
            em.persist(o);
        } catch (Exception e) {
            throw new Exception("EmployeeDao: on save error");
        }
    }

    @Override
    public Employee getById(Long id) throws NotFoundException {
        Employee o = em.find(Employee.class, id);
        if (o == null) {
            throw new NotFoundException("EmployeeDao: employee not found");
        }
        return o;
    }

    @Override
    public void update(Employee o) throws Exception {
        if (o.getId() == null) {
            throw new Exception("EmployeeDao: id can not be null");
        }
        Employee upEmp = getById(o.getId());

        if (o.getFirstName() != null) {
            upEmp.setFirstName(o.getFirstName());
        }
        if (o.getLastName() != null) {
            upEmp.setLastName(o.getLastName());
        }
        if (o.getMiddleName() != null) {
            upEmp.setMiddleName(o.getMiddleName());
        }
        if (o.getPosition() != null) {
            upEmp.setPosition(o.getPosition());
        }
        if (o.getPhone() != null) {
            upEmp.setPhone(o.getPhone());
        }
        if (o.getDocument() != null) {
            upEmp.setDocument(o.getDocument());
        }
        if (o.getOffice() != null) {
            upEmp.setOffice(o.getOffice());
        }
        if (o.getCountry() != null) {
            upEmp.setCountry(o.getCountry());
        }

        em.flush();
    }

    @Override
    public List<Employee> getByFilter(Employee filter) throws Exception {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        try {
            if (filter.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filter.getId()));
            }
            if (filter.getFirstName() != null) {
                predicates.add(cb.equal(root.get("firstName"), filter.getFirstName()));
            }
            if (filter.getLastName() != null) {
                predicates.add(cb.equal(root.get("lastName"), filter.getLastName()));
            }
            if (filter.getMiddleName() != null) {
                predicates.add(cb.equal(root.get("middleName"), filter.getMiddleName()));
            }
            if (filter.getPosition() != null) {
                predicates.add(cb.equal(root.get("position"), filter.getPosition()));
            }
            if (filter.getPhone() != null) {
                predicates.add(cb.equal(root.get("phone"), filter.getPhone()));
            }
            if (filter.getDocument() != null) {
                predicates.add(cb.equal(root.get("document"), filter.getDocument().getId()));
            }
            if (filter.getOffice() != null) {
                predicates.add(cb.equal(root.get("office"), filter.getOffice().getId()));
            }
            if (filter.getCountry() != null) {
                predicates.add(cb.equal(root.get("country"), filter.getCountry().getId()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<Employee> query = em.createQuery(cq);
            List<Employee> result = query.getResultList();
            if (result.isEmpty()) {
                throw new NotFoundException();
            }
            return result;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {

            throw new Exception("incorrect filter");
        }
    }


    @Override
    public void removeById(Long id) throws NotFoundException {

        Employee o = getById(id);
        em.remove(o);
        em.flush();
    }

}
