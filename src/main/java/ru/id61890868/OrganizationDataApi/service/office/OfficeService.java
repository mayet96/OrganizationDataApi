package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListInView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeViewNoOrgId;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;

@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     *
     * @param officeView
     */
    ResultView add(@Valid OfficeView officeView) throws Exception;

    /**
     * Получить список офисов
     *
     * @return {@OrganizationView}
     */
    DataView offices();

    /**
     * Получить список офисов
     * по заданному фильтру
     *
     * @return {@OrganizationView}
     */
    DataView getList(@Valid OfficeListInView filter) throws Exception;

    /**
     * Получить офис по id
     *
     * @return {@OrganizationView}
     */
    DataView loadById(long id) throws Exception;

    OfficeView loadByIdTest(long id) throws Exception;

    /**
     * обновить офис
     *
     * @return {@OfficeView}
     */
    ResultView update(@Valid OfficeViewNoOrgId view) throws Exception;

    ResultView removeById(long officeId) throws Exception;
}
