package ru.id61890868.OrganizationDataApi.dao.document;

import ru.id61890868.OrganizationDataApi.dao.NotFoundException;
import ru.id61890868.OrganizationDataApi.model.Document;

public interface DocDao {

    /**
     * Получить документ по id
     *
     * @param id идентификатор документа
     * @return Document
     * @throws NotFoundException документ не найден
     */
    Document getById(long id) throws NotFoundException;


    /**
     * Сохранить офис
     *
     * @param o сохраняемый документ
     * @throws Exception ошибка при сохранении
     */
    void save(Document o) throws Exception;


    /**
     * Изменить офис
     *
     * @param o изменный документ с существующим идентификатором
     *          <p>
     *          перед внесением изменений выполняется поиск объекта в бд
     *          по o.id
     * @throws Exception при o.id == null
     */
    void update(Document o) throws Exception;
}
