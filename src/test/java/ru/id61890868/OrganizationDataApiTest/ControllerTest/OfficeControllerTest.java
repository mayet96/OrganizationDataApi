package ru.id61890868.OrganizationDataApiTest.ControllerTest;

import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.id61890868.OrganizationDataApi.OrganizationDataApi;
import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacadeImpl;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListFilterView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListItemView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeViewNoOrgId;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ErrorView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * При проведении данных тестов предполагается что в БД есть офис
 * с id = 1
 * name: "name",
 * address: "address",
 * phone: "8622315652",
 * isActive: false
 * <p>
 * а так же отсутствует запись с id = 0
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://127.0.0.1:8887/api/office";

    private static long id;

    private Office getExpectedOffice() {
        Office expectedEntity;
        expectedEntity = new Office(
                "name", "address", "8622315652", false
        );
        return expectedEntity;
    }


    /**
     * Проверка сохранения офиса
     */
    @Test
    public void test0Save() {

        System.out.println("test #0 save:\n");

        //инициализация входных данных

        OfficeView expectedView;
        MapperFacade mapperFacade;
        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());

        expectedView = mapperFacade.map(getExpectedOffice(), OfficeView.class);
        expectedView.orgId = 2L;
        ///////////////////////

        //сохраниение офиса без данных
        System.out.println("\tсохраняем пустой офис");
        OfficeView request = new OfficeView();
        ResponseEntity response = restTemplate.postForEntity(url + "/save", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = (ErrorView) response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(error);
        assertThat(error.error, containsString("address cannot be null"));
        assertThat(error.error, containsString("name cannot be null"));
        assertThat(error.error, containsString("orgId cannot be null"));

        //корректное сохранение офиса
        System.out.println("\tсохраняем корректный офис");

        response = restTemplate.postForEntity(url + "/save", expectedView, ResultView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        System.out.println("\t\tresponse: " + response.getBody());
        ResultView result = (ResultView) response.getBody();

        assertNotNull(result);
        assertThat(result.result, containsString("success"));

    }

    /**
     * Проверка списка офисов по фильтру офиса
     */
    @Test
    public void test1GetOffice() {

        System.out.println("test #1 get list(by filter):\n");

        //инициализация входных данных

        Office expected = getExpectedOffice();
        OfficeListFilterView filter;
        MapperFacade mapperFacade;
        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());

        filter = mapperFacade.map(expected, OfficeListFilterView.class);
        filter.orgId = 2L;
        ///////////////////////

        System.out.println("\tполучаем офис по пустому фильтру");
        OfficeListFilterView request = new OfficeListFilterView();
        ResponseEntity<ErrorView> response = restTemplate.postForEntity(url + "/list", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(error);
        assertThat(error.error, containsString("orgId cannot be null"));

        //////////////////////
        System.out.println("\tполучаем офис по корректному фильтру");
        request = mapperFacade.map(expected, OfficeListFilterView.class);
        request.orgId = 2L;


        //******
        ParameterizedTypeReference<DataView<List<OfficeListItemView>>> reference =
                new ParameterizedTypeReference<DataView<List<OfficeListItemView>>>() {
                };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OfficeListFilterView> httpEntity = new HttpEntity<>(filter, headers);

        ResponseEntity<DataView<List<OfficeListItemView>>> result;


        result = restTemplate.exchange(url + "/list", HttpMethod.POST, httpEntity, reference);

        System.out.println("\t\tfilter: " + filter.toString());
        System.out.println("\t\tresponse: " + result.getBody());


        assertNotNull(result);
        assertNotNull(result.getBody());
        assertTrue(result.getBody().data.size() >= 2);
        assertEquals(result.getStatusCodeValue(), 200);

        id = result.getBody().data.get(1).id;

    }

    /**
     * Проверка получения офиса по id
     */
    @Test
    public void test2GetOfficeById() {

        System.out.println("test #2 getById:\n");
        System.out.println("\tполучаем офис по не существующему id");
        ResponseEntity<ErrorView> response = restTemplate.getForEntity(url + "/" + 0, ErrorView.class);
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody().error, containsString("not found"));

        ////////////////////////
        Office expected = getExpectedOffice();

        System.out.println("\tполучаем офис по корректному id");
        ParameterizedTypeReference<DataView<OfficeViewNoOrgId>> reference =
                new ParameterizedTypeReference<DataView<OfficeViewNoOrgId>>() {
                };

        ResponseEntity<DataView<OfficeViewNoOrgId>> result =
                restTemplate.exchange(url + "/" + 1, HttpMethod.GET, null, reference);
        Assert.assertNotNull(response);
        System.out.println("\t\tresponse: " + result.getBody());

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCodeValue(), 200);

        OfficeViewNoOrgId view = result.getBody().data;

        assertThat(view.id, is(1L));
        assertEquals(view.address, expected.getAddress());
        assertEquals(view.name, expected.getName());
        assertEquals(view.phone, expected.getPhone());
    }

    /**
     * Проверка удаления офиса по id
     */
    @Test
    public void test3DeleteOfficeById() {

        System.out.println("test #3 getById:\n");
        System.out.println("\tУдаляем офис по не существующему id");
        ResponseEntity<ErrorView> response =
                restTemplate.exchange(url + "/" + 0, HttpMethod.DELETE, null, ErrorView.class);
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody().error, containsString("not found"));

        ////////////////////////
        System.out.println("\tУдаляем офис по корректному id");

        ResponseEntity<ResultView> result =
                restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null, ResultView.class);
        Assert.assertNotNull(response);
        System.out.println("\t\tresponse: " + result.getBody());

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCodeValue(), 200);


        assertEquals(result.getBody().result, "success");
    }

}
