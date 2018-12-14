package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.validation.annotation.Validated;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface OfficeService {

    /**
     * Добавить новый офис в БД
     *
     * @param officeView
     */
    ResultView add(@Valid OfficeView officeView) throws Exception;

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
    OfficeView loadById(long id) throws Exception;

    OfficeView loadByIdTest(long id) throws Exception;

    /**
     * обновить офис
     *
     * @return {@OfficeView}
     */
    ResultView update(@Valid OfficeView view) throws Exception;
}
