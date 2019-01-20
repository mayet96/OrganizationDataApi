package ru.id61890868.OrganizationDataApi.dao;

public class NotFoundException extends Exception {


    public NotFoundException() {
        this("object not found");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(Exception e) {
        super("object not found", e);
    }

    public NotFoundException(String msg, Exception e) {
        super(msg, e);
    }
}
