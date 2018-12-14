package ru.id61890868.OrganizationDataApi.dao.office;

import ru.id61890868.OrganizationDataApi.model.Office;

import java.util.List;

public interface OfficeDao {

    /**
     * Получить офис по id
     *
     * @param id
     *
     * @return
     */
    Office loadById(Long id) throws Exception;


    /**
     * Получить список всех офисов
     *
     * @return
     */
    List<Office> all();

    /**
     * Сохранить офис
     *
     * @param office
     *
     * @return
     */
    void save(Office office) throws Exception;

    /**
     * Изменить офис
     *
     * @param office
     *
     * @throws Exception при office.id == null
     *
     * @return
     */
    void update(Office office) throws Exception;

    /**
     * Перезаписать офис
     *
     * @param office
     *
     * @throws Exception при office.id == null
     *
     * @return
     */
    void override(Office office) throws Exception;
}
