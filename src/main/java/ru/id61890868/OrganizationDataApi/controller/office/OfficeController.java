package ru.id61890868.OrganizationDataApi.controller.office;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.service.office.OfficeService;
import ru.id61890868.OrganizationDataApi.view.OfficeView;
import ru.id61890868.OrganizationDataApi.view.OrganizationView;

import java.util.List;

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
    public List<OfficeView> offices(){
        return officeService.offices();
    }

    @ApiOperation(value = "Добавить офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void saveOffice(@RequestBody OfficeView officeView) {
        officeService.add(officeView);
    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public OfficeView getOfficeById(@PathVariable("id")long officeId) {
        return officeService.loadById(officeId);
    }


}
