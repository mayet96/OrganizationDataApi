package ru.id61890868.OrganizationDataApi.dao.office;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Office;

import java.util.List;

public interface OfficeDao {

    /**
     * Получить офис по id
     *
     * @param id идентификатор офиса
     * @return Office
     * @throws NotFoundException офис не найден
     */
    Office getById(Long id) throws NotFoundException;


    /**
     * Получить список всех офисов
     *
     * @return List<Office>
     */
    List<Office> all();


    /**
     * Получить список всех офисов
     * по фильтру
     *
     * @return List<Office>
     * @throws Exception некорректный фильтр, проблемы с доступом к БД
     * @throws NotFoundException данные по фильтру не найдены
     */
    List<Office> list(Office filter, Long id) throws Exception;

    /**
     * Сохранить офис
     *
     * @param office сохраняемый офис
     * @throws Exception ошибка при сохранении
     */
    void save(Office office) throws Exception;

    /**
     * Изменить офис
     *
     * @param office изменяемый офис с идентификатором
     * @throws Exception при office.id == null
     */
    void update(Office office) throws Exception;

    /**
     * Удалить офис
     *
     * @param officeId идентификатор удаляемого офиса
     * @throws Exception ошибка при удалении
     */
    void removeById(long officeId) throws Exception;
}
