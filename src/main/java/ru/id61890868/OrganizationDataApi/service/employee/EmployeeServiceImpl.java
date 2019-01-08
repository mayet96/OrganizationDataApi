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
import ru.id61890868.OrganizationDataApi.view.employee.*;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao emplDao;
    private CountryDao cDao;
    private DocTypeDao dtDao;
    private MapperFacade mapper;
    private OfficeDao oDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao emplDao, CountryDao cDao, DocTypeDao dtDao,
                               MapperFacade mapper, OfficeDao oDao) {
        this.emplDao = emplDao;
        this.cDao = cDao;
        this.dtDao = dtDao;
        this.mapper = mapper;
        this.oDao = oDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultView save(@Valid EmployeeViewNoId view) throws Exception {


        Employee employee = mapper.map(view, Employee.class);
        try {
            Country country = cDao.getByCode(view.citizenshipCode);
            employee.setCountry(country);
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: country not found by citizenshipCode");
        } catch (NullPointerException e) {
            employee.setCountry(null);
        }

        DocType docType;
        try {
            docType = dtDao.getByCode(view.docCode);

        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: doc not found by docCode");
        } catch (NullPointerException e) {
            docType = null;
        }
        Document doc = new Document(view.docNumber, view.docDate, view.isIdentified, docType);

        try {
            employee.setOffice(oDao.getById(view.office));
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: office not found by id");
        }

        employee.setDocument(doc);

        emplDao.save(employee);
        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataView getById(long id) throws Exception {
        Employee o = emplDao.getById(id);
        return new DataView<>(mapper.map(o, EmployeeViewWithNames.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataView getList(@Valid EmployeeListFilterView filterView) throws Exception {

        Employee filter = mapper.map(filterView, Employee.class);
        try {
            Country country = cDao.getByCode(filterView.citizenshipCode);
            filter.setCountry(country);
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: country not found by citizenshipCode");
        } catch (NullPointerException e) {
            filter.setCountry(null);
        }

        DocType docType;
        try {
            docType = dtDao.getByCode(filterView.docCode);

        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: doc not found by docCode");
        } catch (NullPointerException e) {
            docType = null;
        }
        Document doc = new Document(null, null, null, docType);
        filter.setDocument(doc);

        try {
            filter.setOffice(oDao.getById(filterView.office));
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: office not found by id");
        }

        List<Employee> l = emplDao.getByFilter(filter);

        return new DataView<>(mapper.mapAsList(l, EmployeeListItemView.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultView update(@Valid EmployeeView view) throws Exception {
        Employee employee = mapper.map(view, Employee.class);
        try {
            Country country = cDao.getByCode(view.citizenshipCode);
            employee.setCountry(country);
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: country not found by citizenshipCode");
        } catch (NullPointerException e) {
            employee.setCountry(null);
        }

        DocType docType;
        try {
            docType = dtDao.getByCode(view.docCode);

        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: doc not found by docCode");
        } catch (NullPointerException e) {
            docType = null;
        }
        Document doc = new Document(view.docNumber, view.docDate, view.isIdentified, docType);
        employee.setDocument(doc);

        try {
            employee.setOffice(oDao.getById(view.office));
        } catch (NotFoundException e) {
            throw new NotFoundException("EmployeeService: office not found by id");
        }

        emplDao.update(employee, view.id);

        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResultView removeById(long id) throws Exception {
        emplDao.removeById(id);
        return new ResultView("success");
    }
}
