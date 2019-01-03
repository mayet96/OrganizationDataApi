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
     * @param officeView представление данных нового офиса
     *
     * @return @ResultView
     *
     * @throws Exception ошибка при добавлении
     */
    @Transactional
    ResultView add(@Valid OfficeView officeView) throws Exception;

    /**
     * Получить список офисов
     *
     * @return @ResultView
     */
    @Transactional
    DataView offices();

    /**
     * Получить список офисов
     * по заданному фильтру
     *
     * @param filter представление данных фильтра
     *
     * @return @DataView<OfficeListOutView>
     * @throws Exception ошибка при получении по фильтру
     */
    @Transactional
    DataView getList(@Valid OfficeListFilterView filter) throws Exception;

    /**
     * Получить офис по id
     *
     * @param id идентификатор офиса
     *
     * @return @DataView
     * @throws Exception ошибка при получении по id
     */
    @Transactional
    DataView loadById(long id) throws Exception;

    @Transactional
    OfficeView loadByIdTest(long id) throws Exception;

    /**
     * обновить офис
     *
     * @param view представление данных обновляемого офиса
     *
     * @return @ResultView
     * @throws Exception ошибка при обновлении
     */
    @Transactional
    ResultView update(@Valid OfficeViewNoOrgId view) throws Exception;


    /**
     * Удалить офис
     *
     * @param officeId идентификатор удаляемого офиса
     * @return @ResultView
     * @throws Exception ошибка при удалении
     */
    @Transactional
    ResultView removeById(long officeId) throws Exception;
}
