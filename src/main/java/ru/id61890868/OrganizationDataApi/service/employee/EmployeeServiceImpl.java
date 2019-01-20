package ru.id61890868.OrganizationDataApi.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.dao.country.CountryDao;
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.dao.employee.EmployeeDao;
import ru.id61890868.OrganizationDataApi.dao.office.OfficeDao;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.DocType;
import ru.id61890868.OrganizationDataApi.model.Document;
import ru.id61890868.OrganizationDataApi.model.Employee;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListFilterView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListItemView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewNoId;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewWithNames;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private CountryDao countryDao;
    private DocTypeDao docTypeDao;
    private MapperFacade mapper;
    private OfficeDao officeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao emplDao, CountryDao cDao, DocTypeDao dtDao,
                               MapperFacade mapper, OfficeDao oDao) {
        this.employeeDao = emplDao;
        this.countryDao = cDao;
        this.docTypeDao = dtDao;
        this.mapper = mapper;
        this.officeDao = oDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultView save(@Valid EmployeeViewNoId view) throws Exception {


        Employee employee = mapper.map(view, Employee.class);
        try {
            Country country = countryDao.getByCode(view.citizenshipCode);
            employee.setCountry(country);
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: country not found by citizenshipCode", e);
        } catch (NullPointerException e) {
            employee.setCountry(null);
        }

        DocType docType;
        try {
            docType = docTypeDao.getByCode(view.docCode);

        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: doc not found by docCode", e);
        } catch (NullPointerException e) {
            docType = null;
        }
        Document doc = new Document(view.docNumber, view.docDate, view.isIdentified, docType);

        try {
            employee.setOffice(officeDao.getById(view.office));
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: office not found by id", e);
        }

        employee.setDocument(doc);

        employeeDao.save(employee);
        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataView<EmployeeViewWithNames> getById(long id) throws Exception {
        Employee o = employeeDao.getById(id);
        return new DataView<EmployeeViewWithNames>(mapper.map(o, EmployeeViewWithNames.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataView<List<EmployeeListItemView>> getList(@Valid EmployeeListFilterView filterView) throws Exception {

        Employee filter = mapper.map(filterView, Employee.class);
        try {
            Country country = countryDao.getByCode(filterView.citizenshipCode);
            filter.setCountry(country);
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: country not found by citizenshipCode", e);
        } catch (NullPointerException e) {
            filter.setCountry(null);
        }

        DocType docType;
        try {
            docType = docTypeDao.getByCode(filterView.docCode);

        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: doc not found by docCode", e);
        } catch (NullPointerException e) {
            docType = null;
        }
        Document doc = new Document(null, null, null, docType);
        filter.setDocument(doc);

        try {
            filter.setOffice(officeDao.getById(filterView.office));
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: office not found by id", e);
        }

        List<Employee> l = employeeDao.getByFilter(filter);

        return new DataView<List<EmployeeListItemView>>(mapper.mapAsList(l, EmployeeListItemView.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultView update(@Valid EmployeeView view) throws Exception {
        Employee employee = mapper.map(view, Employee.class);
        try {
            Country country = countryDao.getByCode(view.citizenshipCode);
            employee.setCountry(country);
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: country not found by citizenshipCode", e);
        } catch (NullPointerException e) {
            employee.setCountry(null);
        }

        DocType docType;
        try {
            docType = docTypeDao.getByCode(view.docCode);

        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: doc not found by docCode", e);
        } catch (NullPointerException e) {
            docType = null;
        }
        Document doc = new Document(view.docNumber, view.docDate, view.isIdentified, docType);
        employee.setDocument(doc);

        try {
            employee.setOffice(officeDao.getById(view.office));
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: office not found by id", e);
        }

        employeeDao.update(employee, view.id);

        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultView removeById(long id) throws Exception {
        employeeDao.removeById(id);
        return new ResultView("success");
    }
}
