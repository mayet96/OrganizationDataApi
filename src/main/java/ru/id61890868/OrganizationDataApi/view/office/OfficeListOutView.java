package ru.id61890868.OrganizationDataApi.view.office;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Представление запроса списка офисов
 */
public class OfficeListOutView {


    /**
     * id офиса
     */
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    /**
     * Наименование офиса
     */
    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование офиса", example = "OMEGA office")
    public String name;


    /**
     * Статус офиса
     */
    @ApiModelProperty(value = "Действительность", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id +
                ";name:" + name
                + ";isActive:" + isActive + "}";
    }
}
