package ru.id61890868.OrganizationDataApi.dao.organization;

import ru.id61890868.OrganizationDataApi.model.Organization;

import java.util.List;

public interface OrganizationDao {



    /**
     * Получить организацию по id
     *
     * @param id
     *
     * @return
     */
    Organization loadById(Long id);


    /**
     * Получить список всех организаций
     *
     * @return
     */
    List<Organization> all();

    /**
     * Сохранить организацию
     *
     * @param organization
     *
     * @return
     */
    void save(Organization organization);

    //void update(int id, Organization organization);
}
