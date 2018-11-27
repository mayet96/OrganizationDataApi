package ru.id61890868.OrganizationDataApi.controller.SimpleController;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Validated
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class SimpleController {

    @ApiOperation("Проверка доступности приложения")
    @RequestMapping(value = "/simple", method = {GET, POST})
    public String simple() {
        return "simple_response";
    }

}