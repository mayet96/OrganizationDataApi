package ru.id61890868.OrganizationDataApiTest;


import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeControllerTest {


    RestTemplate restTemplate = new RestTemplate();
    String url = "http://127.0.0.1:8887/api";
    Office expected;
    Office actual;

    MapperFacade mapperFacade;


    /**
     * Проверка на сохранение организации
     */

    public void Init(){
        expected =
                new Office(
                        "name","address","8622315652", false
                );

        mapperFacade = new MapperFacadeImpl(new DefaultMapperFactory.Builder().build());
    }



    /**
     * Проверка сохранения офиса
     */
    @Test
    public void test0SaveOffice(){

        Init();

        OfficeView request = mapperFacade.map(expected, OfficeView.class);

        ResponseEntity response = restTemplate.postForEntity(url + "/office/save", request, ResultView.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        //getTest(request);

    }

    /**
     * Проверка списка офисов по фильтру офиса
     */
    @Test
    public void test1GetOffice(){

        ParameterizedTypeReference<DataView<List<OfficeListOutView>>> reference =
                new ParameterizedTypeReference<DataView<List<OfficeListOutView>>>(){};

        //RequestEntity<DataView<OfficeListOutView>> enitty

        ResponseEntity<DataView<List<OfficeListOutView>>> response = restTemplate.exchange(
                url + "/office/list", HttpMethod.POST,null, reference
        );


        assertNotNull(response);

        System.out.println(response.getBody().data.get(0).name);
        DataView<List<OfficeListOutView>> dataView = response.getBody();
        assertThat(dataView.data.get(0).id, is(1));
    }

    /*@Test
    public void test3RemoveOffice(){

        OfficeView request = mapperFacade.map(expected, OfficeView.class);

        ResponseEntity response = restTemplate.postForEntity(url + "/organization/save", request, ResultView.class);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        getTest(request);

    }*/


    /**
     * Получение по идентификатору
     * @param organization - объект для проверки полученых данных
     */
    /*public void getTest(OfficeView organization){

        ParameterizedTypeReference<DataView<OfficeView>> reference =
                new ParameterizedTypeReference<DataView<OfficeView>>(){};

        ResponseEntity<DataView<OfficeView>> response = restTemplate.exchange(url + "/office/2", HttpMethod.GET, null,reference );
        Assert.assertNotNull(response);

        DataView<OfficeView> responseData = response.getBody();
        assertNotNull(responseData);
        assertThat(responseData.data.id, is(2));
        assertThat(responseData.data.name, is(organization.name));
        assertThat(responseData.data.orgId, is(organization.orgId));
        assertThat(responseData.data.address, is(organization.address));
        assertThat(responseData.data.phone, is(organization.phone));
        assertThat(responseData.data.isActive, is(organization.isActive));

    }*/

}
