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
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.dao.document.DocDao;
import ru.id61890868.OrganizationDataApi.model.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * При проведении данных тестов предполагается что в БД есть документ
 * с id = 1
 * number: "2341354234",
 * date: Date(2000, 11, 1),
 * IsIdentified: false
 * <p>
 * а так же отсутствует запись с id = 0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
//@Transactional
public class DocDaoTest {

    @Autowired
    private DocDao docDao;

    @Autowired
    private DocTypeDao typeDao;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Test
    public void testSave() throws Exception {

        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW
        ));

        Document o = new Document(
                null, "2341354234", new Date(), false, typeDao.getById(1L)
        );
        docDao.save(o);


        transactionManager.rollback(transaction);
    }

    @Test
    public void testGetById() throws Exception {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("2000-11-1");

        Document o = docDao.getById(1L);
        assertNotNull(o);
        assertEquals(o.getDocDate(), date);
        System.out.println(
                String.format(
                        "\ndocDate:\n\t%s\nexpectedDate:\n\t%s",
                        df.format(o.getDocDate()), df.format(date)
                )
        );
        assertEquals(o.getDocNumber(), "2341354234");
        assertEquals(o.getDocType().getCode(), typeDao.getById(1L).getCode());
        assertEquals(o.getIsIdentified(), false);

    }

    @Test
    public void testUpdate() throws Exception {
        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition(
                TransactionDefinition.PROPAGATION_REQUIRES_NEW
        ));


        Document o = new Document(
                1L, "2341354234", new Date(), false, typeDao.getById(1L)
        );

        docDao.update(o);


        transactionManager.rollback(transaction);
    }

}
