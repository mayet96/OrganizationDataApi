package ru.id61890868.OrganizationDataApi.view.response;

public class ResultView {

    public String result;

    /**
     * стандартный конструктор (статус: ОК)
     */
    public ResultView() {

    }

    /**
     * стандартный конструктор (статус: передать в конструктор)
     * @param msg Сообщение результативного ответа
     */
    public ResultView(String msg) {
        this.result = msg;
    }

    @Override
    public String toString() {
        return "{result:" + result + "}";
    }
}
