package ru.id61890868.OrganizationDataApi.controller.organization;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.model.Organization;
import ru.id61890868.OrganizationDataApi.service.organization.OrganizationService;
import ru.id61890868.OrganizationDataApi.view.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController", description = "Управление информацией о организациях")
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService orgService;

    @Autowired
    public OrganizationController(OrganizationService orgService) {
        this.orgService = orgService;
    }


    @ApiOperation(value = "Получить список всех организаций", httpMethod = "GET")
    @GetMapping("/")
    public List<OrganizationView> organizations(){
        return orgService.organizations();
    }

    @ApiOperation(value = "Добавить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void saveOrganization(@RequestBody OrganizationView org) {
        orgService.add(org);
    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public OrganizationView getOrganiationById(@PathVariable("id")long orgId) {
        return orgService.loadById(orgId);
    }

}
