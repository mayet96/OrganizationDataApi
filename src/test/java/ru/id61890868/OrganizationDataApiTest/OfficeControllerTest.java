package ru.id61890868.OrganizationDataApiTest;


import ma.glasnost.orika.impl.DefaultMapperFactory;
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
import ru.id61890868.OrganizationDataApi.view.office.OfficeListInView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListOutView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeViewNoOrgId;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ErrorView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
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
        OfficeListInView filter;
        MapperFacade mapperFacade;
        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());

        filter = mapperFacade.map(expected, OfficeListInView.class);
        filter.orgId = 2L;
        ///////////////////////

        System.out.println("\tполучаем офис по пустому фильтру");
        OfficeListInView request = new OfficeListInView();
        ResponseEntity response = restTemplate.postForEntity(url + "/office/list", request, ErrorView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = (ErrorView) response.getBody();
        System.out.println("\t\tresponse: " + response.getBody());

        assertThat(error.error, containsString("orgId cannot be null"));


        System.out.println("\tполучаем офис по корректному фильтру");
        request = mapperFacade.map(expected, OfficeListInView.class);
        request.orgId = 2L;
        ResponseEntity dataView =
                restTemplate.postForEntity(url + "/office/list", request, DataView.class);

        System.out.println("\t\tresponse: " + dataView.getBody());
        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);

        DataView<List<OfficeListOutView>> result = (DataView<List<OfficeListOutView>>) dataView.getBody();

        assertNotNull(result.data);
    }




}
