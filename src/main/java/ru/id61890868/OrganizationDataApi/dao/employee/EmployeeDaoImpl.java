package ru.id61890868.OrganizationDataApi.dao.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.dao.country.CountryDao;
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.dao.document.DocDao;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.DocType;
import ru.id61890868.OrganizationDataApi.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
    public void update(Employee o, Long id) throws Exception {
        if (id == null) {
            throw new Exception("EmployeeDao: id can not be null");
        }
        Employee upEmp = getById(id);

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

        CriteriaQuery<Employee> empQuery = cb.createQuery(Employee.class);
        Root<Employee> emp = empQuery.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        try {
            if (filter.getId() != null) {
                predicates.add(cb.equal(emp.get("id"), filter.getId()));
            }
            if (filter.getFirstName() != null) {
                predicates.add(cb.equal(emp.get("firstName"), filter.getFirstName()));
            }
            if (filter.getLastName() != null) {
                predicates.add(cb.equal(emp.get("lastName"), filter.getLastName()));
            }
            if (filter.getMiddleName() != null) {
                predicates.add(cb.equal(emp.get("middleName"), filter.getMiddleName()));
            }
            if (filter.getPosition() != null) {
                predicates.add(cb.equal(emp.get("position"), filter.getPosition()));
            }
            if (filter.getPhone() != null) {
                predicates.add(cb.equal(emp.get("phone"), filter.getPhone()));
            }
            //если документ есть в фильтре
            if (filter.getDocument() != null) {
                // TODO: здесь можно реализовать фильтр по полям документа

                //и у документа есть типДокумента то искать будем по соответсвиям в типах
                if (filter.getDocument().getDocType() != null) {

                    Subquery<DocType> docQuery = empQuery.subquery(DocType.class);
                    Root<Employee> doc = docQuery.correlate(emp);

                    Join docTable = doc.join("document");
                    Join docTypeTable = docTable.join("docType");

                    docQuery.select(docTypeTable);
                    //параметры поиска по типуДокумента по документу сотрудника из фильтра
                    DocType _docType = filter.getDocument().getDocType();
                    docQuery.where(
                            cb.or(
                                    cb.equal(
                                            docTypeTable.get("code"),
                                            _docType.getCode()
                                    ),
                                    cb.equal(
                                            docTypeTable.get("name"),
                                            _docType.getName()
                                    )

                            )
                    );
                    predicates.add(cb.exists(docQuery));
                }
            }
            if (filter.getOffice() != null) {
                // TODO: здесь можно реализовать фильтр полям офиса
                predicates.add(cb.equal(emp.get("office"), filter.getOffice().getId()));
            }
            if (filter.getCountry() != null) {
                Subquery<Country> countryQuery = empQuery.subquery(Country.class);
                Root<Employee> country = countryQuery.correlate(emp);
                Join countryTable = country.join("country");

                countryQuery.select(countryTable);
                //параметры поиска по гражданству(стране) из фильтра
                Country _country = filter.getCountry();
                countryQuery.where(
                        cb.or(
                                cb.equal(
                                        countryTable.get("code"),
                                        _country.getCode()
                                ),
                                cb.equal(
                                        countryTable.get("name"),
                                        _country.getName()
                                )
                        )
                );
                predicates.add(cb.exists(countryQuery));
            }
            empQuery.where(predicates.toArray(new Predicate[predicates.size()]));

            TypedQuery<Employee> query = em.createQuery(empQuery);
            List<Employee> result = query.getResultList();
            if (result.isEmpty()) {
                throw new NotFoundException();
            }
            return result;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
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
