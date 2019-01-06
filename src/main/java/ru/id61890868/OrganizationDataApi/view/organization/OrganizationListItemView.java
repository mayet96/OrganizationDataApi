package ru.id61890868.OrganizationDataApi.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Элемент списка вывода организаций")
public class OrganizationListItemView {

    @NotNull(message = "id cannot be null")
    @NotEmpty(message = "id cannot be null")
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @Size(max = 50)
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование", example = "OMEGA")
    public String name;

    @ApiModelProperty(value = "Действительность", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name +
                ";isActive:" + isActive + "}";
    }
}
