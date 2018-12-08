package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.OfficeView;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     *
     * @param officeView
     */
    void add(@Valid OfficeView officeView);

    /**
     * Получить список офисов
     *
     * @return {@OrganizationView}
     */
    List<OfficeView> offices();

    /**
     * Получить офис по id
     *
     * @return {@OrganizationView}
     */
    OfficeView loadById(long id);

    /**
     * обновить офис
     *
     * @return {@OfficeView}
     */
    void update(@Valid OfficeView view) throws Exception;
}
