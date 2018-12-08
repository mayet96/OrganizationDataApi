package ru.id61890868.OrganizationDataApi.controller.office;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.id61890868.OrganizationDataApi.model.Office;
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
        //return officeService.offices();
    }

    @ApiOperation(value = "Добавить офис", httpMethod = "POST")
    /*@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})*/
    @PostMapping("/save")
    public ResponseEntity<?> saveOffice(@RequestBody OfficeView officeView) {
        try{
            officeService.add(officeView);
            return new ResponseEntity<>("{\"result\":\"success\"}", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("{\"error\":,\"" + e.getMessage()
                    +"\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<?> getOfficeById(@PathVariable("id")long officeId) {
        try{
            OfficeView o = officeService.loadById(officeId);
            if(o == null){
                throw new Exception("not found");
            }
            return new ResponseEntity<>(o, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("{\"error\":,\"" + e.getMessage()
                    +"\"}", HttpStatus.BAD_REQUEST);
        }
    }

}
