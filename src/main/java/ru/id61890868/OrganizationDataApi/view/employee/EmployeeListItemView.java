package ru.id61890868.OrganizationDataApi.view.employee;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;

@ApiModel(description = "Представление элемента данных сотрудника в списке")
public class EmployeeListItemView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public Long id;

    /**
     * Имя сотрудника
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Имя сотрудника", example = "Алексей")
    public String firstName;

    /**
     * Фамилия сотрудника
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Фамилия сотрудника", example = "Александрович")
    public String secondName;

    /**
     * Отчество сотрудника
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Отчество сотрудника", example = "Романов")
    public String middleName;

    /**
     * Должность сотрудника
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Должность сотрудника", example = "Инженер")
    public String position;
}
