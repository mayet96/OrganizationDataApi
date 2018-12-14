package ru.id61890868.OrganizationDataApi.controller.office;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.service.office.OfficeService;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

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
    public ResponseEntity<?> offices(){
        try{
            List<OfficeView> l = officeService.offices();
            if(l == null){
                throw new Exception("not found");
            }
            return new ResponseEntity<>(l, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("{\"error\":,\"" + e.getMessage()
                    +"\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Добавить офис", httpMethod = "POST")
    @PostMapping("/save")
    public ResultView saveOffice(@RequestBody OfficeView officeView) throws Exception {
        return officeService.add(officeView);

    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public OfficeView getOfficeById(@PathVariable("id")long officeId) throws Exception {
        return officeService.loadById(officeId);

    }


    @ApiOperation(value = "Изменить данные офиса", httpMethod = "POST")
    @PostMapping("/update")
    public ResultView updateOffice(@RequestBody OfficeView officeView) throws Exception {
        return officeService.update(officeView);

    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/test/{id:[\\d]+}")
    public ResponseEntity<?> test(@PathVariable("id")long officeId) throws Exception {
        return new ResponseEntity<>((officeService.loadByIdTest(officeId)), HttpStatus.OK);

    }

}
