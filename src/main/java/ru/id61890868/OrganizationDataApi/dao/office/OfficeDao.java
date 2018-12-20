package ru.id61890868.OrganizationDataApi.dao.office;

import ru.id61890868.OrganizationDataApi.model.Office;

import java.util.List;

public interface OfficeDao {

    /**
     * Получить офис по id
     *
     * @param id
     * @return Office
     */
    Office loadById(Long id) throws Exception;


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
     */
    List<Office> list(Office filter, Integer id) throws Exception;

    /**
     * Сохранить офис
     *
     * @param office
     */
    void save(Office office) throws Exception;

    /**
     * Изменить офис
     *
     * @param office
     * @throws Exception при office.id == null
     */
    void update(Office office) throws Exception;

    void removeById(long officeId) throws Exception;
}
