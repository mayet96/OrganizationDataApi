package ru.id61890868.OrganizationDataApi.dao.country;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Country;

import java.util.List;

public interface CountryDao {


    Country getById(long id) throws NotFoundException;

    List<Country> getAll() throws Exception;

    void save(Country o) throws Exception;

}
