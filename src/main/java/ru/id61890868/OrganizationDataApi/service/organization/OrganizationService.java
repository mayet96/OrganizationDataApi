package ru.id61890868.OrganizationDataApi.service.organization;

import javax.validation.Valid;
import java.util.List;

public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param organization
     */
    void add(@Valid PersonView person);

    /**
     * Получить список людей
     *
     * @return {@Person}
     */
    List<PersonView> persons();
}
