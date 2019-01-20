package ru.id61890868.OrganizationDataApi.model.mapper;

import java.util.List;

/**
 * Фасад для преобразования между можелями БД и фронта
 */
public interface MapperFacade {

    /**
     * Преобразование sourceObject в экземпляр класса destinationClass
     *
     * @param sourceObject     исходный объект
     * @param destinationClass класс, в который надо преобразовать объект
     * @param <S>              тип исходного объекта
     * @param <D>              тип объекта, в который надо преобразовать исходный объект
     * @return экземпляр класса D с данными из sourceObject
     */
    <S, D> D map(S sourceObject, Class<D> destinationClass);

    /**
     * Запись занных из sourceObject в destinationObject
     *
     * @param sourceObject      исходный объект
     * @param destinationObject класс, в который надо преобразовать объект
     * @param <S>               тип исходного объекта
     * @param <D>               тип объекта, в который надо преобразовать исходный объект
     */
    <S, D> void map(S sourceObject, D destinationObject);

    /**
     * Преобразование коллекции оъектов
     *
     * @param source           исходная коллекция
     * @param destinationClass класс, в который надо преобразовать объект
     * @param <S>              тип исходного объекта
     * @param <D>              тип объекта, в который надо преобразовать исходный объект
     * @return лист объектов класса D с данными из листа source
     */
    <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);


}
