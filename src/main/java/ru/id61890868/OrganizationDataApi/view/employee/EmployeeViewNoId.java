package ru.id61890868.OrganizationDataApi.view.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Сотрудник")
public class EmployeeViewNoId {


    /**
     * Имя сотрудника
     */
    @Size(max = 50)
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be null")
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
     * Место работы сотрудника
     */
    @ApiModelProperty(value = "id офиса работы", example = "1")
    public Long office;

    /**
     * Должность сотрудника
     */
    @Size(max = 50)
    @NotNull(message = "position cannot be null")
    @NotEmpty(message = "position cannot be null")
    @ApiModelProperty(value = "Должность сотрудника", example = "Инженер")
    public String position;

    /**
     * Телефон сотрудника
     */
    @ApiModelProperty(value = "Телефон", example = "8325698744")
    public String phone;

    /**
     * Код документа
     */
    public String docCode;


    /**
     * Номер документа
     */
    public String docNumber;

    /**
     * Дата документа
     */
    public Date docDate;

    /**
     * Код страны
     */
    public String citizenshipCode;

    /**
     * Статус подтверждения документов
     */
    public Boolean isIdentified;


    @Override
    public String toString() {
        return String.format(
                "firstName: %s, secondName: %s, middleName: %s, position: %s," +
                        "phone: %s, docNum: %s, docDate: \"%s\", sitzCode: %s, identif: %s",
                firstName, secondName, middleName, position, phone, docNumber, docDate.toString(),
                citizenshipCode, isIdentified.toString()
        );
    }
}
