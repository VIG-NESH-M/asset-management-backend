package com.staunch.tech.enums;

public enum TicketStatus {

    OPEN("OPEN"), IN_PROGRESS("IN_PROGRESS"),RESOLVED("RESOLVED"), CLOSED("CLOSED");

    public String status= "";

    TicketStatus(String status){
        this.status = status;
    }
}
