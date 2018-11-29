package ru.id61890868.OrganizationDataApi.service.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.organization.OrganizationDao;
import ru.id61890868.OrganizationDataApi.model.Organization;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.OrganizationView;

import javax.validation.Valid;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    private OrganizationDao dao;
    private MapperFacade mapperFacade;

    @Autowired
    OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void add(@Valid OrganizationView org) {
        Organization newOrg = new Organization(org.name, org.fullName, org.inn, org.kpp, org.address);
        dao.save(newOrg);
    }

    @Override
    @Transactional
    public List<OrganizationView> organizations() {
        List<Organization> l = dao.all();
        return mapperFacade.mapAsList(l, OrganizationView.class);
    }

    @Override
    @Transactional
    public OrganizationView loadById(long id) {
        return mapperFacade.map(dao.loadById(id), OrganizationView.class);
    }
}
