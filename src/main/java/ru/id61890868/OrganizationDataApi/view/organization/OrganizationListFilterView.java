package ru.id61890868.OrganizationDataApi.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Организация фильтор")
public class OrganizationListFilterView {

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование", example = "OMEGA")
    public String name;

    @ApiModelProperty(value = "ИНН", example = "8912354123")
    public String inn;


    @ApiModelProperty(value = "Действительность", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{name:" + name + ";inn:" + inn
                + ";isActive:" + isActive + "}";
    }
}