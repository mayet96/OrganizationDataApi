package ru.id61890868.OrganizationDataApi.dao.organization;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Organization;

import java.util.List;

public interface OrganizationDao {

    /**
     * Получить организацию по id
     *
     * @param id идентификатор организации
     *
     * @return Organization
     * @throws NotFoundException организация не найдена
     */
    Organization loadById(Long id) throws NotFoundException;


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
     * @throws Exception некорректный фильтр, проблемы с доступом к БД
     * @throws NotFoundException данные по фильтру не найдены
     */
    List<Organization> list(Organization filter) throws Exception;

    /**
     * Сохранить организацию
     *
     * @param organization сохраняемый объект организации
     * @throws Exception ошибка при сохранении
     */
    void save(Organization organization) throws Exception;


    /**
     * Изменить организацию
     *
     * @param org изменяемый объект организации
     *
     * @throws Exception при org.id == null
     *
     */
    void update(Organization org) throws Exception;

    /**
     * Удалить организацию по id
     *
     * @param orgId идентификатор удаляемой организации
     * @throws Exception при org.id == null
     */
    void removeById(long orgId) throws Exception;


}
