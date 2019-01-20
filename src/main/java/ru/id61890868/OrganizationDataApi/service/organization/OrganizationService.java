package ru.id61890868.OrganizationDataApi.service.organization;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListFilterView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListItemView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param org представление данных сохраняемой организации
     *
     * @return @ResultView
     *
     * @throws Exception ошибка при добавлении
     */
    @Transactional
    ResultView add(@Valid OrganizationView org) throws Exception;

    /**
     * Получить список организаций
     *
     * @return @OrganizationView
     */
    @Transactional
    DataView<List<OrganizationView>> organizations();

    /**
     * Получить список организаций
     * по заданному фильтру
     *
     * @param filter представление данных фильтра
     * @return @DataView<OrganizationListOutView>
     * @throws Exception ошибка при получении списка по фильтру
     */
    @Transactional
    DataView<List<OrganizationListItemView>> getList(@Valid OrganizationListFilterView filter) throws Exception;

    /**
     * Получить организацию по id
     *
     * @return @OrganizationView
     * @throws Exception ошибка при обновлении данных
     */
    @Transactional
    DataView<OrganizationView> loadById(long id) throws Exception;

    /**
     * обновить организацию
     *
     * @return @ResultView
     * @throws Exception ошибка при обновлении данных
     */
    @Transactional
    ResultView update(@Valid OrganizationView view) throws Exception;

    /**
     * удалить организацию
     *
     * @return @ResultView
     * @throws Exception ошибка при удалении
     */
    @Transactional
    ResultView removeById(long officeId) throws Exception;


}
