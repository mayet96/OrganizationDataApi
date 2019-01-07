package ru.id61890868.OrganizationDataApiTest.DaoTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.id61890868.OrganizationDataApi.OrganizationDataApi;
import ru.id61890868.OrganizationDataApi.dao.country.CountryDao;
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.dao.document.DocDao;
import ru.id61890868.OrganizationDataApi.dao.employee.EmployeeDao;
import ru.id61890868.OrganizationDataApi.dao.office.OfficeDao;
import ru.id61890868.OrganizationDataApi.model.Employee;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * При проведении данных тестов предполагается что в БД есть сотрудник
 * с id: 1
 * Employee(
 * "firstName",
 * "lastName",
 * "position",
 * "7778889996",
 * oDao.getById(1L),
 * cDao.getById(1L),
 * dDao.getById(1L)
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
//@Transactional
public class EmployeeDaoTest {

    @Autowired
    private DocDao dDao;

    @Autowired
    private DocTypeDao tDao;

    @Autowired
    private EmployeeDao empDao;

    @Autowired
    private CountryDao cDao;

    @Autowired
    private OfficeDao oDao;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Test
    public void testSave() throws Exception {

        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW
        ));

        Employee e = new Employee(
                "firstName",
                "lastName",
                "middleName",
                "position",
                "7778889996",
                oDao.getById(1L),
                cDao.getById(1L),
                dDao.getById(1L)
        );

        empDao.save(e);


        transactionManager.rollback(transaction);
    }

    @Test
    public void testGetById() throws Exception {

        Employee o = empDao.getById(1L);

        System.out.printf("\n--------------\nEmployee:\n\t" +
                        "name: %s, lastName: %s, middleName: %s,\n\t" +
                        "position: %s, officeName: %s, \n\t" +
                        "country: %s, docTypeName: %s\n\n",
                o.getFirstName(), o.getLastName(), o.getMiddleName(),
                o.getPosition(), o.getOffice().getName(),
                o.getCountry().getName(), o.getDocument().getDocType().getName()
        );
    }

    @Test
    public void testUpdate() throws Exception {

        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW
        ));

        Employee e = new Employee(
                1L,
                "firstName*",
                "lastName*",
                "middleName*",
                "position*",
                "7778889996",
                oDao.getById(5L),
                cDao.getById(2L),
                dDao.getById(1L)
        );

        empDao.update(e);


        transactionManager.rollback(transaction);
    }

    @Test
    public void testGetByFilter() throws Exception {


        Employee e = new Employee(
                null,
                "lastName",
                "middleName",
                "position",
                "7778889996",
                null,
                cDao.getById(1L),
                dDao.getById(1L)
        );

        List<Employee> l = empDao.getByFilter(e);
        assertNotNull(l);
        assertTrue(l.size() > 0);
        System.out.println("\n result" + l + "\n");

    }

}
