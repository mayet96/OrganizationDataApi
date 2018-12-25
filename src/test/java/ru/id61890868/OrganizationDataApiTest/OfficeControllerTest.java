package ru.id61890868.OrganizationDataApiTest;

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

import ru.id61890868.OrganizationDataApi.model.mapper.*;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeControllerTest {


    RestTemplate restTemplate = new RestTemplate();
    final String url = "http://127.0.0.1:8887/api";


    private Office getExpectedOffice() {
        Office expectedEnity;
        expectedEnity = new Office(
                "name", "address", "8622315652", false
        );
        return expectedEnity;
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
        ResponseEntity response = restTemplate.postForEntity(url + "/office/save", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = (ErrorView) response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertThat(error.error, containsString("address cannot be null"));
        assertThat(error.error, containsString("name cannot be null"));
        assertThat(error.error, containsString("orgId cannot be null"));

        //корректное сохранение офиса
        System.out.println("\tсохраняем корректный офис");

        response = restTemplate.postForEntity(url + "/office/save", expectedView, ResultView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        System.out.println("\t\tresponse: " + response.getBody());
        ResultView result = (ResultView) response.getBody();

        assertThat(result.result, containsString("success"));

    }

    /**
     * Проверка списка офисов по фильтру офиса
     */
    @Test
    public void test1GetOffice() {

        System.out.println("test #1 get list(by filter):");

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
        ResponseEntity response = restTemplate.postForEntity(url + "/office/list", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = (ErrorView) response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertThat(error.error, containsString("orgId cannot be null"));


        System.out.println("\tполучаем офис по корректному фильтру");
        request = mapperFacade.map(expected, OfficeListFilterView.class);
        request.orgId = 2L;
        ResponseEntity dataView =
                restTemplate.postForEntity(url + "/office/list", request, DataView.class);

        System.out.println("\t\tresponse: " + dataView.getBody());
        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);

        DataView<List<OfficeListItemView>> result = (DataView<List<OfficeListItemView>>) dataView.getBody();

        assertNotNull(result.data);
    }


    @Test
    public void test3GetOfficeById() {

        MapperFacade mapperFacade;
        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());

        System.out.println("test #3 getById:\n");
        System.out.println("\tполучаем офис по не существующему id");
        ResponseEntity<ErrorView> response = restTemplate.getForEntity(url + "/office/" + 2, ErrorView.class);
        System.out.println("\t\tresponse: " + response.getBody());

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        assertThat(response.getBody().error, containsString("not found"));

        ////////////////////////
        Office expected = getExpectedOffice();

        System.out.println("\tполучаем офис по корректному id");
        ParameterizedTypeReference<DataView<OfficeViewNoOrgId>> reference =
                new ParameterizedTypeReference<DataView<OfficeViewNoOrgId>>() {
                };

        ResponseEntity<DataView<OfficeViewNoOrgId>> result =
                restTemplate.exchange(url + "/office/" + 1, HttpMethod.GET, null, reference);
        Assert.assertNotNull(response);
        System.out.println("\t\tresponse: " + result.getBody());

        assertNotNull(result);
        assertEquals(result.getStatusCodeValue(), 200);

        OfficeViewNoOrgId view = result.getBody().data;

        assertThat(view.id, is(1l));
        assertEquals(view.address, expected.getAddress());
        assertEquals(view.name, expected.getName());
        assertEquals(view.phone, expected.getPhone());
    }

}
