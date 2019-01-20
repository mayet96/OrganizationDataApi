package ru.id61890868.OrganizationDataApi.service.employee;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListFilterView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListItemView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewNoId;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewWithNames;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface EmployeeService {

    /**
     * Добавить нового сотрудника в БД
     *
     * @param view представление данных нового сотрудника
     * @return @ResultView
     * @throws Exception ошибка при добавлении
     */
    @Transactional
    ResultView save(@Valid EmployeeViewNoId view) throws Exception;

    /**
     * Получить сотрудника по id
     *
     * @param id идентификатор сотрудника
     * @return @DataView
     * @throws Exception ошибка при получении по id
     */
    @Transactional
    DataView<EmployeeViewWithNames> getById(long id) throws Exception;

    /**
     * Получить список сотрудников
     * по заданному фильтру
     *
     * @param filter представление данных фильтра
     * @return @DataView<EmployeeListItemView>
     * @throws Exception ошибка при получении по фильтру
     */
    @Transactional
    DataView<List<EmployeeListItemView>> getList(@Valid EmployeeListFilterView filter) throws Exception;

    /**
     * обновить данные сотрудника
     *
     * @param view представление новых данных обновляемого сотрудника
     * @return @ResultView
     * @throws Exception ошибка при обновлении
     */
    @Transactional
    ResultView update(@Valid EmployeeView view) throws Exception;

    /**
     * Удалить сотрудника
     *
     * @param id идентификатор удаляемого сотрудника
     * @return @ResultView
     * @throws Exception ошибка при удалении
     */
    @Transactional
    ResultView removeById(long id) throws Exception;

}
