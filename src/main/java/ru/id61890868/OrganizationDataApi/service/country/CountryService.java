package ru.id61890868.OrganizationDataApi.service.country;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.country.CountryView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;


@Validated
public interface CountryService {

    @Transactional
    ResultView add(@Valid CountryView countryView) throws Exception;

    @Transactional
    DataView loadById(long id) throws Exception;

    @Transactional
    DataView getAll() throws Exception;
}
