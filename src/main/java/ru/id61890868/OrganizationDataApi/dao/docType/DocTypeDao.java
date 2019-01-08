package ru.id61890868.OrganizationDataApi.dao.docType;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.DocType;

import java.util.List;

public interface DocTypeDao {


    /**
     * Получить тип документа по id
     *
     * @param id идентификатор типа документа
     * @return объект данных типа документа
     * @throws NotFoundException тип документа не найден
     */
    DocType getById(long id) throws NotFoundException;

    /**
     * Получить тип документа по коду
     *
     * @param code код типа документа
     * @return объект данных типа документа
     * @throws NotFoundException тип документа не найден
     */
    DocType getByCode(String code) throws NotFoundException;

    /**
     * Получить список типов документа
     *
     * @return List<DocType>
     */
    List<DocType> getAll() throws Exception;

    /**
     * Сохранить тип документа
     *
     * @param o сохраняемые данные
     * @throws Exception ошибка при сохранении
     */
    void save(DocType o) throws Exception;

}
