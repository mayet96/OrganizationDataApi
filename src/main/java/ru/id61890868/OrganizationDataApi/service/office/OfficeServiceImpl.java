package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.office.OfficeDao;
import ru.id61890868.OrganizationDataApi.dao.organization.OrganizationDao;
import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.model.Organization;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListFilterView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeListItemView;
import ru.id61890868.OrganizationDataApi.view.office.OfficeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    final private OfficeDao dao;
    final private MapperFacade mapperFacade;
    final private OrganizationDao organizationDao;

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
        Organization org = organizationDao.loadById(officeView.orgId);
        Office newOffice = mapperFacade.map(officeView, Office.class);
        newOffice.setOrganization(org);
        dao.save(newOffice);
        return new ResultView("success");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public DataView<List<OfficeView>> offices() {

        List<Office> l = dao.all();
        List<OfficeView> v = mapperFacade.mapAsList(l, OfficeView.class);
        return new DataView<List<OfficeView>>(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public DataView<List<OfficeListItemView>> getList(@Valid OfficeListFilterView filter) throws Exception {
        Office _filter = mapperFacade.map(filter, Office.class);
        return new DataView<List<OfficeListItemView>>(
                mapperFacade.mapAsList(dao.list(_filter, filter.orgId), OfficeListItemView.class)
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public DataView<OfficeView> getById(long id) throws Exception {
        Office office = dao.getById(id);
        OfficeView view = mapperFacade.map(office, OfficeView.class);
        return new DataView<OfficeView>(view);
    }

    @Override
    @Transactional
    public OfficeView loadByIdTest(long id) throws Exception {
        Office office = dao.getById(id);
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
