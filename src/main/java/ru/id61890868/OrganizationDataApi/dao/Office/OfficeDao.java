package ru.id61890868.OrganizationDataApi.dao.Office;

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
    Office loadById(Long id);


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
    void save(Office office);

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

}
