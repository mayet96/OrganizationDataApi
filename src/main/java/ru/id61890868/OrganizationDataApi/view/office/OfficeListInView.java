package ru.id61890868.OrganizationDataApi.view.office;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Представление выходных данных списка офисов
 */
@ApiModel(description = "фильтр поиска офисов")
public class OfficeListInView {


    /**
     * id организации
     */
    @NotNull(message = "orgId cannot be null")
    @ApiModelProperty(value = "id владеющей организации")
    public Long orgId;


    /**
     * Наименование офиса
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Наименование офиса", example = "OMEGA office")
    public String name;

    /**
     * Телефон офиса
     */
    @ApiModelProperty(value = "Телефон", example = "8325698744")
    public String phone;

    /**
     * Статус офиса
     */
    @ApiModelProperty(value = "Действительность", example = "true")
    public String isActive;


    @Override
    public String toString() {
        return "{orgId:" + orgId + ";name:" + name
                 + ";phone:" + phone
                + ";isActive:" + isActive + "}";
    }
}
