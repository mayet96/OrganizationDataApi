package ru.id61890868.OrganizationDataApi.controller.organization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.id61890868.OrganizationDataApi.service.organization.OrganizationService;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListFilterView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListItemView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

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
    public DataView<List<OrganizationView>> organizations() {
        return orgService.organizations();
    }

    @ApiOperation(value = "Получить список всех организаций по фильтру", httpMethod = "POST")
    @PostMapping("/list")
    public DataView<List<OrganizationListItemView>> getList(
            @RequestBody OrganizationListFilterView filter
    ) throws Exception {
        return orgService.getList(filter);
    }

    @ApiOperation(value = "Добавить организацию", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveOrganization(@RequestBody OrganizationView org) throws Exception {
        return orgService.add(org);
    }


    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView<OrganizationView> getOrganziationById(@PathVariable("id") long orgId) throws Exception {
        return orgService.loadById(orgId);
    }

    @ApiOperation(value = "Изменить данные организации", httpMethod = "POST")
    @PostMapping("/update")
    public ResultView updateOrg(@RequestBody OrganizationView orgView) throws Exception {
        return orgService.update(orgView);
    }

    @ApiOperation(value = "Удалить организацию по id", httpMethod = "DELETE")
    @DeleteMapping("/{id:[\\d]+}")
    public ResultView removeOrganizationById(@PathVariable("id") long orgId) throws Exception {
        return orgService.removeById(orgId);
    }
}
