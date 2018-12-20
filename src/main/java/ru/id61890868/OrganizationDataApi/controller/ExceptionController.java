package ru.id61890868.OrganizationDataApi.controller;

import ma.glasnost.orika.impl.ExceptionUtility;
import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.id61890868.OrganizationDataApi.view.response.ErrorView;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class ExceptionController {

    /**
     * Обработка исключений
     * @param e
     * @return ErrorView
     */
    @ExceptionHandler(Exception.class)
    public ErrorView handleAllException(Exception e){
        ErrorView ev = new ErrorView(e.getMessage());

        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));

        Logger.getLogger(this.getClass()).error(errors);
        return ev;
    }
}
