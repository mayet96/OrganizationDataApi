package ru.id61890868.OrganizationDataApi.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(description = "Организация")
public class OrganizationView {

    //@NotEmpty(message = "id - пуст!")
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 50)
    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(value = "Наименование", example = "OMEGA")
    public String name;

    @NotNull(message = "age cannot be null")
    @ApiModelProperty(value = "Полное наименование", example = "OMEGA Org.")
    public String fullName;

    @NotNull(message = "age cannot be null")
    @ApiModelProperty(value = "ИНН", example = "8912354123")
    public String inn;

    @NotNull(message = "age cannot be null")
    @ApiModelProperty(value = "КПП", example = "025496512684")
    public String kpp;

    @NotNull(message = "age cannot be null")
    @ApiModelProperty(value = "Адрес", example = "г. Смородинск, проспект Орки, дом 23")
    public String address;

    @ApiModelProperty(value = "Телефон", example = "8325698744")
    public String phone;

    @ApiModelProperty(value = "Действительность", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + "name:" + name  + ";full_name:" + fullName
                + ";inn:" + inn + ";kpp:" + kpp + ";address:" + address
                + ";phone:" + phone  + ";is_active:" + isActive + "}";
    }

}
