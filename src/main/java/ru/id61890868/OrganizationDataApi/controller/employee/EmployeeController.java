package ru.id61890868.OrganizationDataApi.controller.employee;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.service.employee.EmployeeService;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListFilterView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewNoId;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Api(value = "EmployeeController", description = "работа с данными сотрудников")
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @ApiOperation(value = "Получить список всех сотрудников по фильтру", httpMethod = "POST")
    @PostMapping("/list")
    public DataView getList(@RequestBody EmployeeListFilterView filterView) throws Exception {
        return service.getList(filterView);
    }

    @ApiOperation(value = "Добавить сотрудника", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveEmployee(@RequestBody EmployeeViewNoId view) throws Exception {
        return service.save(view);
    }

    @ApiOperation(value = "Получить сотрудника по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView getEmployeeById(@PathVariable("id") long id) throws Exception {
        return service.getById(id);
    }

    @ApiOperation(value = "Изменить данные сотрудника", httpMethod = "POST")
    @PostMapping("/update")
    public ResultView updateEmployee(@RequestBody EmployeeView view) throws Exception {
        return service.update(view);

    }

    @ApiOperation(value = "Удалить офис по id", httpMethod = "DELETE")
    @DeleteMapping("/{id:[\\d]+}")
    public ResultView removeEmployeeById(@PathVariable("id") long officeId) throws Exception {
        return service.removeById(officeId);

    }
}
