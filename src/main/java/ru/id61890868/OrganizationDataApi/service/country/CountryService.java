package ru.id61890868.OrganizationDataApi.service.country;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.country.CountryView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;


@Validated
public interface CountryService {

    @Transactional
    ResultView add(@Valid CountryView countryView) throws Exception;

    @Transactional
    DataView<CountryView> loadById(long id) throws Exception;

    @Transactional
    DataView<List<CountryView>> getAll() throws Exception;
}
