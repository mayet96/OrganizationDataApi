package ru.id61890868.OrganizationDataApi.view.response;

public class ResultView {

    public String result = "success";


    /**
     * стандартный конструктор (статус: ОК)
     *
     */
    public ResultView() {

    }
    /**
     * стандартный конструктор (статус: передать в конструктор)
     *
     */
    public ResultView(String result) {
        this.result = result;
    }



    @Override
    public String toString(){
        return "{result:" + result + "}";
    }
}
