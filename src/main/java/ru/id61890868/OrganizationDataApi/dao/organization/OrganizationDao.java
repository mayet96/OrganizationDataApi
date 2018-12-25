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
    Organization loadById(Long id) throws Exception;


    /**
     * Получить список всех организаций
     *
     * @return List<Organization>
     */
    List<Organization> all();


    /**
     * Получить список организаций по фильтру
     *
     * @return List<Organization>
     */
    List<Organization> list(Organization filter) throws Exception;

    /**
     * Сохранить организацию
     *
     * @param organization
     *
     * @return
     */
    void save(Organization organization) throws Exception;


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

    void removeById(long orgId) throws Exception;


}
