package ru.id61890868.OrganizationDataApi.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.id61890868.OrganizationDataApi.view.OnlyDigits;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Организация фильтор")
public class OrganizationListFilterView {

    @Size(max = 50)
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование", example = "OMEGA")
    public String name;

    @OnlyDigits(message = "inn value is not valid")
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
