package ru.id61890868.OrganizationDataApi.view.employee;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Представление фильтра данных сотрудника")
public class EmployeeListFilterView {

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
    public String lastName;

    /**
     * Отчество сотрудника
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Отчество сотрудника", example = "Романов")
    public String middleName;

    /**
     * Место работы сотрудника
     */
    @NotNull(message = "name cannot be null")
    @ApiModelProperty(value = "id офиса работы", example = "1")
    public Long office;

    /**
     * Должность сотрудника
     */
    @Size(max = 50)
    @ApiModelProperty(value = "Должность сотрудника", example = "Инженер")
    public String position;

    /**
     * Код документа
     */
    public String docCode;

    /**
     * Код страны
     */
    public String citizenshipCode;

    @Override
    public String toString() {
        return String.format(
                "firstName: %s, lastName: %s, middleName: %s, position: %s," +
                        " sitzCode: %s",
                firstName, lastName, middleName, position,
                citizenshipCode
        );
    }
}
