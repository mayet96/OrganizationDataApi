package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListFilterView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListItemView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     *
     * @param officeView представление данных нового офиса
     * @return @ResultView
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
    DataView<List<OfficeView>> offices();

    /**
     * Получить список офисов
     * по заданному фильтру
     *
     * @param filter представление данных фильтра
     * @return @DataView<OfficeListOutView>
     * @throws Exception ошибка при получении по фильтру
     */
    @Transactional
    DataView<List<OfficeListItemView>> getList(@Valid OfficeListFilterView filter) throws Exception;

    /**
     * Получить офис по id
     *
     * @param id идентификатор офиса
     * @return @DataView
     * @throws Exception ошибка при получении по id
     */
    @Transactional
    DataView<OfficeView> getById(long id) throws Exception;

    @Transactional
    OfficeView loadByIdTest(long id) throws Exception;

    /**
     * обновить офис
     *
     * @param view представление данных обновляемого офиса
     * @return @ResultView
     * @throws Exception ошибка при обновлении
     */
    @Transactional
    ResultView update(@Valid OfficeView view) throws Exception;


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
