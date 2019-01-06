package ru.id61890868.OrganizationDataApi.view.office;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Офис")
public class OfficeView {

    //@NotNull(message = "id cannot be null")
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;


    @NotNull(message = "orgId cannot be null")
    @ApiModelProperty(value = "id владеющей организации")
    public Long orgId;

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    @NotNull(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование офиса", example = "OMEGA office")
    public String name;

    @Size(max = 50)
    @NotEmpty(message = "address cannot be null")
    @NotNull(message = "address cannot be null")
    @ApiModelProperty(value = "Адрес офиса", example = "г. Смородинск, проспект Орки, дом 23")
    public String address;

    @ApiModelProperty(value = "Телефон", example = "8325698744")
    public String phone;

    @ApiModelProperty(value = "Действительность", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";orgId:" + orgId + ";name:" + name
                + ";address:" + address + ";phone:" + phone
                + ";isActive:" + isActive + "}";
    }
}
