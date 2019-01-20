package ru.id61890868.OrganizationDataApi.controller.country;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.id61890868.OrganizationDataApi.service.country.CountryService;
import ru.id61890868.OrganizationDataApi.view.country.CountryView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "CountriesController", description = "работа со справочником стран")
@RestController
@RequestMapping(value = "/api/countries", produces = APPLICATION_JSON_VALUE)
public class CountryController {


    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Получить список всех стран", httpMethod = "GET")
    @GetMapping("/")
    public DataView<List<CountryView>> getAllCountries() throws Exception {
        return countryService.getAll();
    }

    @ApiOperation(value = "Получить страну по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView<CountryView> getById(@PathVariable("id") long countryId) throws Exception {
        return countryService.loadById(countryId);
    }

    @ApiOperation(value = "Добавить страну в бд", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveCountry(@RequestBody CountryView countryView) throws Exception {
        return countryService.add(countryView);

    }


}
