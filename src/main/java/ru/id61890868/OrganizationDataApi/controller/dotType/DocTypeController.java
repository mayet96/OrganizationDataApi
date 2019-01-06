package ru.id61890868.OrganizationDataApi.controller.dotType;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.service.docType.DocTypeService;
import ru.id61890868.OrganizationDataApi.view.docType.DocTypeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "DocTypeController", description = "работа со справочником типов документов")
@RestController
@RequestMapping(value = "/api/docs", produces = APPLICATION_JSON_VALUE)
public class DocTypeController {


    private final DocTypeService service;

    @Autowired
    public DocTypeController(DocTypeService service) {
        this.service = service;
    }


    @ApiOperation(value = "Получить список всех типов документов", httpMethod = "GET")
    @GetMapping("/")
    public DataView getAllCountries() throws Exception {
        return service.getAll();
    }

    @ApiOperation(value = "Получить тип документа по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public DataView getById(@PathVariable("id") long id) throws Exception {
        return service.loadById(id);
    }

    @ApiOperation(value = "Добавить тип документа в бд", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveCountry(@RequestBody DocTypeView view) throws Exception {

        return service.add(view);

    }


}
