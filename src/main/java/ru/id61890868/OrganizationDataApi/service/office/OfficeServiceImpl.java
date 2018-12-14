package ru.id61890868.OrganizationDataApi.service.office;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.office.OfficeDao;
import ru.id61890868.OrganizationDataApi.dao.organization.OrganizationDao;
import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.model.Organization;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.swing.text.View;
import javax.validation.Valid;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private static final Logger log = LoggerFactory.getLogger("OfficeServiceImpl");

    private OfficeDao dao;
    private MapperFacade mapperFacade;
    private OrganizationDao organizationDao;



    /*public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }*/

    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade, OrganizationDao organizationDao) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultView add(@Valid OfficeView officeView) throws Exception {
        //Office newOffice = new Office(officeView.name, officeView.address,
                //officeView.phone, officeView.isActive);
        Organization org = organizationDao.loadById(officeView.orgId);
        Office newOffice = mapperFacade.map(officeView, Office.class);
        newOffice.setOrganization(org);
        dao.save(newOffice);
        return new ResultView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeView> offices() {
        List<Office> l = dao.all();
        return mapperFacade.mapAsList(l, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeView loadById(long id) throws Exception {
        Office office = dao.loadById(id);
        OfficeView view = mapperFacade.map(office, OfficeView.class);
        view.orgId = office.getOrganization().getId();
        return view;
    }

    @Override
    public OfficeView loadByIdTest(long id) throws Exception {
        Office office = dao.loadById(id);
        OfficeView view = mapperFacade.map(office, OfficeView.class);
        view.orgId = office.getOrganization().getId();
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultView update(@Valid OfficeView view) throws Exception {
        Office upOffice = new Office(view.id, view.name, view.address,
                view.phone, view.isActive);
        dao.update(upOffice);
        return new ResultView();
    }
}
