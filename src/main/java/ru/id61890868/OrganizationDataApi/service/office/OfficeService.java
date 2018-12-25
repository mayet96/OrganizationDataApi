package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListFilterView;
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
     *
     * @return {@ResultView}
     */
    @Transactional
    ResultView add(@Valid OfficeView officeView) throws Exception;

    /**
     * Получить список офисов
     *
     * @return {@ResultView}
     */
    @Transactional
    DataView offices();

    /**
     * Получить список офисов
     * по заданному фильтру
     *
     * @param filter
     *
     * @return {@DataView<OfficeListOutView>}
     */
    @Transactional
    DataView getList(@Valid OfficeListFilterView filter) throws Exception;

    /**
     * Получить офис по id
     *
     * @param id
     *
     * @return {@DataView}
     */
    @Transactional
    DataView loadById(long id) throws Exception;

    @Transactional
    OfficeView loadByIdTest(long id) throws Exception;

    /**
     * обновить офис
     *
     * @param view
     *
     * @return {@ResultView}
     */
    @Transactional
    ResultView update(@Valid OfficeViewNoOrgId view) throws Exception;


    /**
     * Удалить офис
     *
     * @param officeId
     * @return {@ResultView}
     */
    @Transactional
    ResultView removeById(long officeId) throws Exception;
}
