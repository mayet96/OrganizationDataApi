package ru.id61890868.OrganizationDataApi.service.organization;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.organization.OrganizationDao;
import ru.id61890868.OrganizationDataApi.model.Organization;

import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListFilterView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationListItemView;
import ru.id61890868.OrganizationDataApi.view.organization.OrganizationView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger log = LoggerFactory.getLogger("OrganizationServiceImpl");

    private OrganizationDao dao;
    private MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultView add(@Valid OrganizationView org) throws Exception {
        Organization newOrg = new Organization(org.name, org.fullName,
                org.inn, org.kpp, org.address, org.phone, org.isActive);
        dao.save(newOrg);
        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public DataView<List<OrganizationView>> organizations() {
        List<Organization> l = dao.all();
        return new DataView<List<OrganizationView>>(
                mapperFacade.mapAsList(l, OrganizationView.class)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataView<List<OrganizationListItemView>> getList(@Valid OrganizationListFilterView filter) throws Exception {
        Organization _filter = mapperFacade.map(filter, Organization.class);
        return new DataView<List<OrganizationListItemView>>(
                mapperFacade.mapAsList(dao.list(_filter), OrganizationListItemView.class)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public DataView<OrganizationView> loadById(long id) throws Exception {
        return new DataView<OrganizationView>(
                mapperFacade.map(dao.loadById(id), OrganizationView.class)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultView update(@Valid OrganizationView view) throws Exception {
        Organization upOrg = new Organization(view.id, view.name, view.fullName,
                view.inn, view.kpp, view.address, view.phone, view.isActive);
        log.info("service: update - new Org(" + view.toString() + ")");
        dao.update(upOrg);
        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultView removeById(long officeId) throws Exception {
        dao.removeById(officeId);
        return new ResultView("success");
    }

}
