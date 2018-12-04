package ru.id61890868.OrganizationDataApi.dao.Office;

import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.model.Organization;

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


}
