package ru.id61890868.OrganizationDataApi.view.response;

/**
 * Представление данных
 */
public class DataView<T> {

    /**
     * Объект данных
     */
    public T data;

    public DataView(T data) {
        this.data = data;
    }

}
