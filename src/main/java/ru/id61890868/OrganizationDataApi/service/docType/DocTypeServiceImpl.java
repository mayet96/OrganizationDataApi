package ru.id61890868.OrganizationDataApi.service.docType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.docType.DocTypeDao;
import ru.id61890868.OrganizationDataApi.model.DocType;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.docType.DocTypeView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Service
public class DocTypeServiceImpl implements DocTypeService {

    final private DocTypeDao dao;
    final private MapperFacade mapperFacade;


    @Autowired
    public DocTypeServiceImpl(DocTypeDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public ResultView add(@Valid DocTypeView view) throws Exception {

        dao.save(mapperFacade.map(view, DocType.class));

        return new ResultView("success");
    }

    @Override
    @Transactional
    public DataView<DocTypeView> loadById(long id) throws Exception {

        DocType o = dao.getById(id);
        DocTypeView view = mapperFacade.map(o, DocTypeView.class);

        return new DataView<DocTypeView>(view);
    }

    @Override
    @Transactional
    public DataView<List<DocTypeView>> getAll() throws Exception {

        List<DocType> list = dao.getAll();
        List<DocTypeView> listOfViews = mapperFacade.mapAsList(list, DocTypeView.class);

        return new DataView<List<DocTypeView>>(listOfViews);
    }
}
