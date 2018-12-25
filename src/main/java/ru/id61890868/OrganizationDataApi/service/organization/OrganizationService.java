package ru.id61890868.OrganizationDataApi.service.organization;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListFilterView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;

@Validated
public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param org
     */
    @Transactional
    ResultView add(@Valid OrganizationView org) throws Exception;

    /*@Transactional
    void update(@Valid OrganizationView org);*/

    /**
     * Получить список организаций
     *
     * @return {@OrganizationView}
     */
    @Transactional
    DataView organizations();

    /**
     * Получить список организаций
     * по заданному фильтру
     *
     * @param filter
     * @return {@DataView<OrganizationListOutView>}
     */
    @Transactional
    DataView getList(@Valid OrganizationListFilterView filter) throws Exception;

    /**
     * Получить организацию по id
     *
     * @return {@OrganizationView}
     */
    @Transactional
    DataView loadById(long id) throws Exception;

    /**
     * обновить организацию
     *
     * @return {@ResultView}
     */
    @Transactional
    ResultView update(@Valid OrganizationView view) throws Exception;

    /**
     * удалить организацию
     *
     * @return {@ResultView}
     */
    @Transactional
    ResultView removeById(long officeId) throws Exception;


}
