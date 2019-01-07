package ru.id61890868.OrganizationDataApi.dao.employee;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void save(Employee o) throws Exception;

    Employee getById(Long id) throws NotFoundException;

    void update(Employee o) throws Exception;

    List<Employee> getByFilter(Employee filter) throws Exception;

    void removeById(Long id) throws NotFoundException;
}
