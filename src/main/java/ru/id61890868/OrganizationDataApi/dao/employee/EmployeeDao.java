package ru.id61890868.OrganizationDataApi.dao.employee;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    /**
     * Сохранить данные сотрудника
     *
     * @param o сохраняемые даннык
     * @throws Exception ошибка при сохранении
     */
    void save(Employee o) throws Exception;

    /**
     * Получить данные сотрудника по id
     *
     * @param id идентификатор сотрудника
     * @return объект данных сотрудника
     * @throws NotFoundException офис не найден
     */
    Employee getById(Long id) throws NotFoundException;

    /**
     * Изменить данные сотрудника
     *
     * @param o  изменяемый объект данных сотрудника
     * @param id идентификатор сотрудника
     * @throws Exception при office.id == null
     */
    void update(Employee o, Long id) throws Exception;

    /**
     * Получить список сотрудников по фильтру
     *
     * @return List<Employee> лист объектов данных сотрудников
     * @throws Exception         некорректный фильтр, проблемы с доступом к БД
     * @throws NotFoundException данные по фильтру не найдены
     */
    List<Employee> getByFilter(Employee filter) throws Exception;

    /**
     * Удалить объект данных сотрудника.
     * Так же удаляется связанный с ним объек данных документа
     *
     * @param id идентификатор удаляемого сотрудника
     * @throws Exception ошибка при удалении
     */
    void removeById(Long id) throws NotFoundException;
}
