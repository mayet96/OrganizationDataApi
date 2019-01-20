package ru.id61890868.OrganizationDataApi.controller.employee;


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
import ru.id61890868.OrganizationDataApi.service.employee.EmployeeService;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListFilterView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListItemView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewNoId;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewWithNames;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import java.util.List;

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
    public DataView<List<EmployeeListItemView>> getList(@RequestBody EmployeeListFilterView filterView) throws Exception {
        return service.getList(filterView);
    }

    @ApiOperation(value = "Добавить сотрудника", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveEmployee(@RequestBody EmployeeViewNoId view) throws Exception {
        return service.save(view);
    }

    @ApiOperation(value = "Получить сотрудника по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView<EmployeeViewWithNames> getEmployeeById(@PathVariable("id") long id) throws Exception {
        return service.getById(id);
    }

    @ApiOperation(value = "Изменить данные сотрудника", httpMethod = "POST")
    @PostMapping("/update")
    public ResultView updateEmployee(@RequestBody EmployeeView view) throws Exception {
        return service.update(view);

    }

    @ApiOperation(value = "Удалить сотрудника по id", httpMethod = "DELETE")
    @DeleteMapping("/{id:[\\d]+}")
    public ResultView removeEmployeeById(@PathVariable("id") long officeId) throws Exception {
        return service.removeById(officeId);

    }
}
