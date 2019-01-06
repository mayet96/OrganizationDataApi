package ru.id61890868.OrganizationDataApi.service.docType;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.country.CountryView;
import ru.id61890868.OrganizationDataApi.view.docType.DocTypeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;


@Validated
public interface DocTypeService {

    @Transactional
    ResultView add(@Valid DocTypeView docTypeView) throws Exception;

    @Transactional
    DataView loadById(long id) throws Exception;

    @Transactional
    DataView getAll() throws Exception;
}
