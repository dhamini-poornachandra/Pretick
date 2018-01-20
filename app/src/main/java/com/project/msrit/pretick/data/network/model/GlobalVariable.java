package com.project.msrit.pretick.data.network.model;

import java.util.List;

/**
 * Created by plank-dhamini on 11/01/18.
 */

public class GlobalVariable {
    private static GlobalVariable instance;

    private List<Ticketstatus> pendingTicketStatus;
    private List<Ticketstatus> approvedTicketStatus;

    private List<Ticketstatus> facultyPendingTicketStatus;
    private List<Ticketstatus> facultyApprovedTicketStatus;

    private List<ContactPerson> contactPersons;

    private GlobalVariable() {
    }

    public static synchronized GlobalVariable getInstance() {
        if (instance == null) {
            instance = new GlobalVariable();
        }
        return instance;
    }

    public void setPendingTicketStatus(List<Ticketstatus> pendingTicketStatus) {
        this.pendingTicketStatus = pendingTicketStatus;
    }

    public List<Ticketstatus> getPendingTicketStatus() {
        return pendingTicketStatus;
    }

    public List<Ticketstatus> getApprovedTicketStatus() {
        return approvedTicketStatus;
    }

    public void setApprovedTicketStatus(List<Ticketstatus> approvedTicketStatus) {
        this.approvedTicketStatus = approvedTicketStatus;
    }

    public List<ContactPerson> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(List<ContactPerson> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public List<Ticketstatus> getFacultyPendingTicketStatus() {
        return facultyPendingTicketStatus;
    }

    public void setFacultyPendingTicketStatus(List<Ticketstatus> facultyPendingTicketStatus) {
        this.facultyPendingTicketStatus = facultyPendingTicketStatus;
    }

    public List<Ticketstatus> getFacultyApprovedTicketStatus() {
        return facultyApprovedTicketStatus;
    }

    public void setFacultyApprovedTicketStatus(List<Ticketstatus> facultyApprovedTicketStatus) {
        this.facultyApprovedTicketStatus = facultyApprovedTicketStatus;
    }
}
