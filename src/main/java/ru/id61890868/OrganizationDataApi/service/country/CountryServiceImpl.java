package ru.id61890868.OrganizationDataApi.service.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.id61890868.OrganizationDataApi.dao.country.CountryDao;
import ru.id61890868.OrganizationDataApi.model.Country;
import ru.id61890868.OrganizationDataApi.model.mapper.MapperFacade;
import ru.id61890868.OrganizationDataApi.view.country.CountryView;
import ru.id61890868.OrganizationDataApi.view.response.DataView;
import ru.id61890868.OrganizationDataApi.view.response.ResultView;

import javax.validation.Valid;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    final private CountryDao dao;
    final private MapperFacade mapperFacade;


    @Autowired
    public CountryServiceImpl(CountryDao dao, MapperFacade mapperFacade) {
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional
    public ResultView add(@Valid CountryView countryView) throws Exception {

        dao.save(mapperFacade.map(countryView, Country.class));
        return new ResultView("success");
    }

    @Override
    @Transactional
    public DataView<CountryView> loadById(long id) throws Exception {

        Country c = dao.getById(id);
        CountryView view = mapperFacade.map(c, CountryView.class);

        return new DataView<CountryView>(view);
    }

    @Override
    @Transactional
    public DataView<List<CountryView>> getAll() throws Exception {

        List<Country> list = dao.getAll();
        List<CountryView> listOfViews = mapperFacade.mapAsList(list, CountryView.class);

        return new DataView<List<CountryView>>(listOfViews);
    }
}
