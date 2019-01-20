package ru.id61890868.OrganizationDataApi.model.mapper;

import ma.glasnost.orika.MapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.Document;
import ru.id61890868.OrganizationDataApi.model.Employee;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListFilterView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeListItemView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeView;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewNoId;
import ru.id61890868.OrganizationDataApi.view.employee.EmployeeViewWithNames;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MapperFacadeImpl implements MapperFacade {
    final private MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        init();
    }

    private void init() {
        //Employee to EmployeeView
        mapperFactory.classMap(Employee.class, EmployeeView.class)
                .field("id", "id")
                .field("lastName", "secondName")
                .field("document.docNumber", "docNumber")
                .field("document.docType.code", "docCode")
                .field("document.docDate", "docDate")
                .field("document.isIdentified", "isIdentified")
                .field("country.code", "citizenshipCode")
                .field("office.id", "office")
                .byDefault()
                .register();
        mapperFactory.classMap(Employee.class, EmployeeViewNoId.class)
                .field("lastName", "secondName")
                .field("document.docNumber", "docNumber")
                .field("document.docType.code", "docCode")
                .field("document.docDate", "docDate")
                .field("document.isIdentified", "isIdentified")
                .field("country.code", "citizenshipCode")
                .field("office.id", "office")
                .byDefault()
                .register();
        mapperFactory.classMap(Employee.class, EmployeeViewWithNames.class)
                .field("lastName", "secondName")
                .field("document.docNumber", "docNumber")
                .field("document.docType.name", "docName")
                .field("document.docType.code", "docCode")
                .field("document.docDate", "docDate")
                .field("document.isIdentified", "isIdentified")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .field("office.id", "office")
                .byDefault()
                .register();
        mapperFactory.classMap(Employee.class, EmployeeListFilterView.class)
                .field("document.docType.code", "docCode")
                .field("country.code", "citizenshipCode")
                .field("office.id", "office")
                .byDefault()
                .register();
        mapperFactory.classMap(Employee.class, EmployeeListItemView.class)
                .field("lastName", "secondName")
                .byDefault()
                .register();


        //EmployeeView to Employee
        mapperFactory.classMap(EmployeeView.class, Employee.class)
                .field("secondName", "lastName")
                .field("docNumber", "document.docNumber")
                .field("docCode", "document.docType.code")
                .field("docDate", "document.docDate")
                .field("isIdentified", "document.isIdentified")
                .field("citizenshipCode", "country.code")
                .field("office", "office.id")
                .byDefault()
                .register();
        mapperFactory.classMap(EmployeeViewNoId.class, Employee.class)
                .field("secondName", "lastName")
                .field("docNumber", "document.docNumber")
                .field("docCode", "document.docType.code")
                .field("docDate", "document.docDate")
                .field("isIdentified", "document.isIdentified")
                .field("citizenshipCode", "country.code")
                .field("office", "office.id")
                .byDefault()
                .register();
        mapperFactory.classMap(EmployeeViewWithNames.class, Employee.class)
                .field("secondName", "lastName")
                .field("docNumber", "document.docNumber")
                .field("docName", "document.docType.name")
                .field("docCode", "document.docType.code")
                .field("docDate", "document.docDate")
                .field("isIdentified", "document.isIdentified")
                .field("citizenshipName", "country.name")
                .field("citizenshipCode", "country.code")
                .field("office", "office.id")
                .byDefault()
                .register();
        mapperFactory.classMap(EmployeeListItemView.class, Employee.class)
                .field("secondName", "lastName")
                .byDefault()
                .register();
        //Employee to Document
        mapperFactory.classMap(Document.class, Employee.class)
                .field("docNumber", "document.docNumber")
                .field("docType.name", "document.docType.name")
                .field("docType.code", "document.docType.code")
                .field("isIdentified", "document.isIdentified")
                .field("docDate", "document.docDate")
                .byDefault()
                .register();
        //Employee to Country
        mapperFactory.classMap(Country.class, Employee.class)
                .field("id", "country.id")
                .field("code", "country.code")
                .field("name", "country.name")
                .byDefault()
                .register();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }

}
