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


    /**
     * Изменить организацию
     *
     * @param org
     *
     * @throws Exception при org.id == null
     *
     * @return
     */
    void update(Organization org) throws Exception;

    /**
     * Перезаписать организацию
     *
     * @param org
     *
     * @throws Exception при org.id == null
     *
     * @return
     */
    void override(Organization org) throws Exception;
}
