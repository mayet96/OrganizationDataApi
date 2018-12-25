package ru.id61890868.OrganizationDataApi.view.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Элемент списка вывода организаций")
public class OrganizationListItemView {

    //@NotEmpty(message = "id - пуст!")
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    @Size(max = 50)
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
