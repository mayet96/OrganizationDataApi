package ru.id61890868.OrganizationDataApi.service.organization;

import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.OrganizationView;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface OrganizationService {

    /**
     * Добавить новую организацию в БД
     *
     * @param org
     */
    void add(@Valid OrganizationView org);

    /*@Transactional
    void update(@Valid OrganizationView org);*/

    /**
     * Получить список организаций
     *
     * @return {@OrganizationView}
     */
    List<OrganizationView> organizations();

    /**
     * Получить организацию по id
     *
     * @return {@OrganizationView}
     */
    OrganizationView loadById(long id);

    /**
     * обновить организацию
     *
     * @return {@OrganizationView}
     */
    void update(@Valid OrganizationView view) throws Exception;
}
