package ru.id61890868.OrganizationDataApiTest.ControllerTest;


import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.id61890868.OrganizationDataApi.OrganizationDataApi;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.DocType;
import ru.id61890868.OrganizationDataApi.view.country.CountryView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ErrorView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * При проведении данных тестов предполагается что в БД есть страна
 * с id = 1
 * name: "Российская Федерация",
 * code: "643",
 * <p>
 * а так же отсутствует запись с id = 0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OrganizationDataApi.class})
@DirtiesContext
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryControllerTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://127.0.0.1:8887/api/countries";

    private Country getExpectedEntity() {
        return new Country("000", "CountryName");
    }


    @Test
    public void test0Save() {

        System.out.println("test #0 save:\n");

        //сохраниение офиса без данных
        System.out.println("\tсохраняем пустой экземпляр справочника:");
        DocType request = new DocType();
        ResponseEntity response = restTemplate.postForEntity(url + "/save", request, ErrorView.class);

        System.out.println("\t\tresponse:" + response.getBody());

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        ErrorView error = (ErrorView) response.getBody();

        assertNotNull(error);
        assertThat(error.error, containsString("name can not be null"));
        assertThat(error.error, containsString("code can not be null"));

        //сохранение корректного экземпляра
        System.out.println("\n\tсохраняем корректный экземпляр справочника:");

        response = restTemplate.postForEntity(url + "/save", getExpectedEntity(), ResultView.class);

        assertNotNull(response);
        assertEquals(response.getStatusCodeValue(), 200);
        System.out.println("\t\tresponse:" + response.getBody() + "\n\n");
        ResultView result = (ResultView) response.getBody();

        assertNotNull(result);
        assertThat(result.result, containsString("success"));

    }

    /**
     * Проверка списка
     */
    @Test
    public void test1GetOffice() {

        System.out.println("test #1 get all:\n");


        ParameterizedTypeReference<DataView<List<CountryView>>> reference =
                new ParameterizedTypeReference<DataView<List<CountryView>>>() {
                };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<DataView<List<CountryView>>> result;


        result = restTemplate.exchange(url + "/", HttpMethod.GET, null, reference);

        System.out.println("\t\tresponse: " + result.getBody() + "\n\n");


        assertNotNull(result);
        assertNotNull(result.getBody());
        assertTrue(result.getBody().data.size() >= 1);
        assertEquals(result.getStatusCodeValue(), 200);
        assertEquals(result.getBody().data.get(1).name, "Российская Федерация");
        assertEquals(result.getBody().data.get(1).code, "643");

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

        System.out.println("\tполучаем офис по корректному id");
        ParameterizedTypeReference<DataView<CountryView>> reference =
                new ParameterizedTypeReference<DataView<CountryView>>() {
                };

        ResponseEntity<DataView<CountryView>> result =
                restTemplate.exchange(url + "/" + 1L, HttpMethod.GET, null, reference);
        Assert.assertNotNull(response);
        System.out.println("\t\tresponse: " + result.getBody());

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCodeValue(), 200);

        CountryView view = result.getBody().data;


        assertEquals(view.code, "643");
        assertEquals(view.name, "Российская Федерация");
    }
}
