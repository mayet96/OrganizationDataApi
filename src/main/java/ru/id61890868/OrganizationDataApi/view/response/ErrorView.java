package ru.id61890868.OrganizationDataApi.view.response;

/**
 * Представление исключения
 */
public class ErrorView {

    /**
     * Сообщение исключения
     */
    public String error;

    /**
     * Передача текста исключения
     *
     * @param errorText - текс исключения
     */
    public ErrorView(String errorText) {
        error = errorText;
    }

    @Override
    public String toString() {
        return "{\"error\":" + error + "}";
    }
}
