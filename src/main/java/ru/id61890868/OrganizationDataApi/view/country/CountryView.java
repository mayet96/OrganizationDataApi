package ru.id61890868.OrganizationDataApi.view.country;

import io.swagger.annotations.ApiModelProperty;
import ru.id61890868.OrganizationDataApi.view.OnlyDigits;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CountryView {


    @NotNull(message = "name can not be null")
    @NotEmpty(message = "name can not be null")
    @ApiModelProperty(value = "Наименование документа")
    public String name;

    @NotNull(message = "code can not be null")
    @NotEmpty(message = "code can not be null")
    @OnlyDigits(message = "code value is not valid")
    @ApiModelProperty(value = "Код документа")
    public String code;

    @Override
    public String toString() {
        return String.format("{name:\"%s\",code:\"%s\"}", name, code);
    }
}
