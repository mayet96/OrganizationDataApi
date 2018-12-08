package ru.id61890868.OrganizationDataApi.service.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.Office.OfficeDao;
import ru.id61890868.OrganizationDataApi.model.Office;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.OfficeView;

import javax.validation.Valid;
import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private OfficeDao dao;
    private MapperFacade mapperFacade;


    @Autowired
    public OfficeServiceImpl(OfficeDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public void add(@Valid OfficeView officeView) {
        Office newOffice = new Office(officeView.name, officeView.address,
                officeView.phone, officeView.isActive, officeView.orgId);
        dao.save(newOffice);
    }

    @Override
    @Transactional
    public List<OfficeView> offices() {
        List<Office> l = dao.all();
        return mapperFacade.mapAsList(l, OfficeView.class);
    }

    @Override
    @Transactional
    public OfficeView loadById(long id) {
        return mapperFacade.map(dao.loadById(id), OfficeView.class);
    }

    @Override
    @Transactional
    public void update(@Valid OfficeView view) throws Exception {
        Office upOffice = new Office(view.id, view.name, view.address,
                view.phone, view.isActive, view.orgId);
        dao.update(upOffice);
    }
}
