package ru.id61890868.OrganizationDataApi.dao.docType;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.DocType;

import java.util.List;

public interface DocTypeDao {


    DocType getById(long id) throws NotFoundException;

    DocType getByCode(String filter) throws NotFoundException;

    List<DocType> getAll() throws Exception;

    void save(DocType o) throws Exception;

}
