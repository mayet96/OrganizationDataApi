package ru.id61890868.OrganizationDataApiTest.ControllerTest;

import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.id61890868.OrganizationDataApi.OrganizationDataApi;
import ru.id61890868.OrganizationDataApi.model.Organization;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacadeImpl;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListFilterView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListItemView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ErrorView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


/**
 * При проведении данных тестов предполагается что в БД есть организация
 * с id = 30
 * name: "name",
 * fullName: "full_name"
 * address: "address",
 * phone: "1111111111",
 * inn: "21268744562",
 * kpp: ""6544126554
 * isActive: false
 * <p>
 * а так же отсутствует запись с id = 0
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizationControllerTest {


    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://127.0.0.1:8887/api/organization";

    private static Long id;


    private Organization getExpectedOrg() {
        return new Organization(
                "name", "full_name", "21268744562", "6544126554",
                "address", "1111111111", false
        );
    }


    /**
     * Проверка сохранения организации
     */
    @Test
    public void test0Save() {

        System.out.println("test #0 save:\n");

        //инициализация входных данных

        OrganizationView expectedView;
        MapperFacade mapperFacade;
        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());

        expectedView = mapperFacade.map(getExpectedOrg(), OrganizationView.class);
        ///////////////////////

        //сохраниение организации без данных
        System.out.println("\tсохраняем пустой офис");
        OrganizationView request = new OrganizationView();
        ResponseEntity<ErrorView> response = restTemplate.postForEntity(url + "/save", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(error);
        assertThat(error.error, containsString("address cannot be null"));
        assertThat(error.error, containsString("name cannot be null"));
        assertThat(error.error, containsString("full name cannot be null"));
        assertThat(error.error, containsString("inn cannot be null"));
        assertThat(error.error, containsString("kpp cannot be null"));
        assertThat(error.error, containsString("address cannot be null"));

        //корректное сохранение организации
        System.out.println("\tсохраняем корректный офис");


        ResponseEntity<ResultView> result = restTemplate.postForEntity(url + "/save", expectedView, ResultView.class);

        assertNotNull(result);
        assertEquals(result.getStatusCodeValue(), 200);
        System.out.println("\t\tresponse: " + result.getBody());
        ResultView resultView = result.getBody();

        assertNotNull(resultView);
        assertThat(resultView.result, containsString("success"));

    }

    /**
     * Проверка списка организаций по фильтру
     */
    @Test
    public void test1GetOrganization() {

        System.out.println("test #1 get list(by filter):\n");

        //инициализация входных данных

        Organization expected = getExpectedOrg();
        OrganizationListFilterView filter;
        MapperFacade mapperFacade;
        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());

        filter = mapperFacade.map(expected, OrganizationListFilterView.class);
        ///////////////////////

        System.out.println("\tполучаем организацию по пустому фильтру");
        OrganizationListFilterView request = new OrganizationListFilterView();
        ResponseEntity<ErrorView> response = restTemplate.postForEntity(url + "/list", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(error);
        assertThat(error.error, containsString("name cannot be null"));

        //////////////////////
        System.out.println("\tполучаем организацию по корректному фильтру");

        //******
        ParameterizedTypeReference<DataView<List<OrganizationListItemView>>> reference =
                new ParameterizedTypeReference<DataView<List<OrganizationListItemView>>>() {
                };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrganizationListFilterView> httpEntity = new HttpEntity<>(filter, headers);

        ResponseEntity<DataView<List<OrganizationListItemView>>> result;


        result = restTemplate.exchange(url + "/list", HttpMethod.POST, httpEntity, reference);

        System.out.println("\t\tfilter: " + filter.toString());
        System.out.println("\t\tresponse: " + result.getBody());


        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCodeValue(), 200);


        id = result.getBody().data.get(1).id;

    }


    @Test
    public void test2GetOrganizationById() {

        System.out.println("test #2 getById:\n");
        System.out.println("\tполучаем организацию по не существующему id");
        ResponseEntity<ErrorView> response = restTemplate.getForEntity(url + "/" + 0, ErrorView.class);
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody().error, containsString("not found"));

        ////////////////////////
        Organization expected = getExpectedOrg();

        System.out.println("\tполучаем организацию по корректному id");
        ParameterizedTypeReference<DataView<OrganizationView>> reference =
                new ParameterizedTypeReference<DataView<OrganizationView>>() {
                };

        ResponseEntity<DataView<OrganizationView>> result =
                restTemplate.exchange(url + "/" + 30, HttpMethod.GET, null, reference);
        Assert.assertNotNull(response);
        System.out.println("\t\tresponse: " + result.getBody());

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCodeValue(), 200);

        OrganizationView view = result.getBody().data;

        assertThat(view.id, is(30L));
        assertEquals(view.fullName, expected.getFullName());
        assertEquals(view.address, expected.getAddress());
        assertEquals(view.name, expected.getName());
        assertEquals(view.phone, expected.getPhone());
        assertEquals(view.inn, expected.getInn());
        assertEquals(view.kpp, expected.getKpp());
    }

    @Test
    public void test3DeleteOrganizationById() {

        System.out.println("test #3 getById:\n");
        System.out.println("\tУдаляем организацию по не существующему id");
        ResponseEntity<ErrorView> response =
                restTemplate.exchange(url + "/" + 0, HttpMethod.DELETE, null, ErrorView.class);
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody().error, containsString("not found"));

        ////////////////////////
        System.out.println("\tУдаляем организацию по корректному id");

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