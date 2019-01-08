package ru.id61890868.OrganizationDataApi.controller.office;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.service.office.OfficeService;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListFilterView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController", description = "Управление информацией о организациях")
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
    public DataView offices() {
        return officeService.offices();
    }

    @ApiOperation(value = "Получить список всех офисов по фильтру", httpMethod = "POST")
    @PostMapping("/list")
    public DataView getList(@RequestBody OfficeListFilterView filterView) throws Exception {
        return officeService.getList(filterView);
    }

    @ApiOperation(value = "Добавить офис", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveOffice(@RequestBody OfficeView officeView) throws Exception {
        return officeService.add(officeView);
    }

    @ApiOperation(value = "Получить офис по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView getOfficeById(@PathVariable("id") long officeId) throws Exception {
        return officeService.getById(officeId);

    }


    @ApiOperation(value = "Изменить данные офиса", httpMethod = "POST")
    @PostMapping("/update")
    public ResultView updateOffice(@RequestBody OfficeView view) throws Exception {
        return officeService.update(view);

    }


    @Deprecated
    @ApiOperation(value = "Получить офис по id(тест)", httpMethod = "GET")
    @GetMapping("/test/{id:[\\d]+}")
    public ResponseEntity<?> test(@PathVariable("id") long officeId) throws Exception {
        return new ResponseEntity<>((officeService.loadByIdTest(officeId)), HttpStatus.OK);

    }

    @ApiOperation(value = "Удалить офис по id", httpMethod = "DELETE")
    @DeleteMapping("/{id:[\\d]+}")
    public ResultView removeOfficeById(@PathVariable("id") long officeId) throws Exception {
        return officeService.removeById(officeId);

    }

}
