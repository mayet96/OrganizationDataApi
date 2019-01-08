package ru.id61890868.OrganizationDataApi.dao.country;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Country;

import java.util.List;

public interface CountryDao {

    /**
     * Получить страну по id
     *
     * @param id идентификатор страны
     * @return объект данных типа документа
     * @throws NotFoundException страна не найдена
     */
    Country getById(long id) throws NotFoundException;

    /**
     * Получить страну по коду
     *
     * @param code код страны
     * @return объект данных страны
     * @throws NotFoundException страна не найдена
     */
    Country getByCode(String code) throws NotFoundException;

    /**
     * Получить список стран
     *
     * @return List<Country>
     */
    List<Country> getAll() throws Exception;

    /**
     * Сохранить страну
     *
     * @param o сохраняемые данные
     * @throws Exception ошибка при сохранении
     */
    void save(Country o) throws Exception;

}
