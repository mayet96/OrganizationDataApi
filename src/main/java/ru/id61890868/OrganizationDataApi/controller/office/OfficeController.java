package ru.id61890868.OrganizationDataApi.controller.office;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.id61890868.OrganizationDataApi.service.office.OfficeService;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListFilterView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListItemView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OfficeController", description = "Управление информацией о организациях")
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }


    @ApiOperation(value = "Получить список всех офисов", httpMethod = "GET")
    @GetMapping("/")
    public DataView<List<OfficeView>> offices() {
        return officeService.offices();
    }

    @ApiOperation(value = "Получить список всех офисов по фильтру", httpMethod = "POST")
    @PostMapping("/list")
    public DataView<List<OfficeListItemView>> getList(@RequestBody OfficeListFilterView filterView) throws Exception {
        return officeService.getList(filterView);
    }

    @ApiOperation(value = "Добавить офис", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveOffice(@RequestBody OfficeView officeView) throws Exception {
        return officeService.add(officeView);
    }

    @ApiOperation(value = "Получить офис по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView<OfficeView> getOfficeById(@PathVariable("id") long officeId) throws Exception {
        return officeService.getById(officeId);

    }


    @ApiOperation(value = "Изменить данные офиса", httpMethod = "POST")
    @PostMapping("/update")
    public ResultView updateOffice(@RequestBody OfficeView view) throws Exception {
        return officeService.update(view);

    }


    @ApiOperation(value = "Удалить офис по id", httpMethod = "DELETE")
    @DeleteMapping("/{id:[\\d]+}")
    public ResultView removeOfficeById(@PathVariable("id") long officeId) throws Exception {
        return officeService.removeById(officeId);

    }

}
