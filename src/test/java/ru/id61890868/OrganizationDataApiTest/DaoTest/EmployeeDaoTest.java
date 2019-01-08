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
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.dao.country.CountryDao;
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.dao.document.DocDao;
import ru.id61890868.OrganizationDataApi.dao.employee.EmployeeDao;
import ru.id61890868.OrganizationDataApi.dao.office.OfficeDao;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.DocType;
import ru.id61890868.OrganizationDataApi.model.Document;
import ru.id61890868.OrganizationDataApi.model.Employee;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListFilterView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeView;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;


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
 * <p>
 * а так же еще хотя бы 1 запись сотрудника у которого документ с типом
 * "Паспорт гражданина Российской Федерации"
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

    @Autowired
    private MapperFacade mapperFacade;


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

        empDao.update(e, e.getId());


        transactionManager.rollback(transaction);
    }

    @Test
    public void testGetByFilter() throws Exception {

        //поиск по полному фильтру
        List<Employee> l;
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

        l = empDao.getByFilter(e);

        assertNotNull(l);
        assertTrue(l.size() > 0);
        assertThat(l.toString(), containsString("Военный билет"));
        System.out.println("\n result" + l + "\n");


        //поиск по фильтру в котором есть только документ с
        //одним(единственным заполненым полем) типом документа(и то, только одним параметром)
        Document d = new Document();
        d.setDocType(new DocType("Паспорт гражданина Российской Федерации", null));


        e = new Employee(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                d
        );

        l = empDao.getByFilter(e);
        assertNotNull(l);
        assertTrue(l.size() > 0);
        assertThat(l.toString(), containsString("Паспорт гражданина Российской Федерации"));
        System.out.println("\n result" + l + "\n");

        //поиск по фильтру в котором есть только гражданство с
        //одним(единственным заполненым полем) типом документа(и то, только одним параметром)

        Country c = new Country();
        c.setCode("052");
        e = new Employee(
                null,
                null,
                null,
                null,
                null,
                null,
                c,
                null
        );

        l = empDao.getByFilter(e);
        assertNotNull(l);
        assertTrue(l.size() > 0);
        assertThat(l.toString(), containsString("Барбадос"));
        System.out.println("\n result" + l + "\n");

    }

    @Test
    public void mapperTest() throws NotFoundException {
        Employee e = new Employee(
                1L,
                "firstName",
                "lastName",
                "middleName",
                "position",
                "7778889996",
                oDao.getById(1L),
                cDao.getById(1L),
                dDao.getById(1L)
        );

        EmployeeView view = mapperFacade.map(e, EmployeeView.class);
        assertNotNull(view);
        System.out.println("\n\nview:\n\t " + view.toString() + "\n");
        Employee employee = mapperFacade.map(view, Employee.class);
        assertNotNull(employee);

        EmployeeListFilterView view1 = mapperFacade.map(e, EmployeeListFilterView.class);
        assertNotNull(view1);
        System.out.println("\n\nview1:\n\t " + view1.toString() + "\n");

        Country c = mapperFacade.map(e, Country.class);
        assertNotNull(c);
        System.out.println("\n\ncountry:\n\t " + c.getName() + "/" + c.getCode() + "\n");

        Document d = mapperFacade.map(e, Document.class);
        assertNotNull(c);
        System.out.println("\n\ndoc:\n\t " + d.getDocType().getName() + "/" + d.getDocNumber() + "\n");
    }

}
