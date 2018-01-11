package com.project.msrit.pretick.data.network.model;

/**
 * Created by plank-dhamini on 11/01/18.
 */

public class Ticketstatus {

    private String ticketno;
    private String organization;
    private String date;
    private String phoneno;
    private String starttime;
    private String endtime;
    private String slot;
    private String contactperson;
    private String referenceapproval;
    private String adminapproval;

    public String getTicketno() {
        return ticketno;
    }

    public void setTicketno(String ticketno) {
        this.ticketno = ticketno;
    }

    public Ticketstatus withTicketno(String ticketno) {
        this.ticketno = ticketno;
        return this;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Ticketstatus withOrganization(String organization) {
        this.organization = organization;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Ticketstatus withDate(String date) {
        this.date = date;
        return this;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Ticketstatus withPhoneno(String phoneno) {
        this.phoneno = phoneno;
        return this;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Ticketstatus withStarttime(String starttime) {
        this.starttime = starttime;
        return this;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Ticketstatus withEndtime(String endtime) {
        this.endtime = endtime;
        return this;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Ticketstatus withSlot(String slot) {
        this.slot = slot;
        return this;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public Ticketstatus withContactperson(String contactperson) {
        this.contactperson = contactperson;
        return this;
    }

    public String getReferenceapproval() {
        return referenceapproval;
    }

    public void setReferenceapproval(String referenceapproval) {
        this.referenceapproval = referenceapproval;
    }

    public Ticketstatus withReferenceapproval(String referenceapproval) {
        this.referenceapproval = referenceapproval;
        return this;
    }

    public String getAdminapproval() {
        return adminapproval;
    }

    public void setAdminapproval(String adminapproval) {
        this.adminapproval = adminapproval;
    }

    public Ticketstatus withAdminapproval(String adminapproval) {
        this.adminapproval = adminapproval;
        return this;
    }

}
