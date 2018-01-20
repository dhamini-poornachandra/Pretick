package com.project.msrit.pretick.data.network.model;

import java.util.List;

/**
 * Created by plank-dhamini on 11/01/18.
 */

public class GlobalVariable {
    private static GlobalVariable instance;

    private List<Ticketstatus> adminPendingTicketStatus;
    private List<Ticketstatus> adminApprovedTicketStatus;

    private List<Ticketstatus> facultyPendingTicketStatus;
    private List<Ticketstatus> facultyApprovedTicketStatus;

    private List<Ticketstatus> guestTicketStatus;

    private List<ContactPerson> contactPersons;

    private GlobalVariable() {
    }

    public static synchronized GlobalVariable getInstance() {
        if (instance == null) {
            instance = new GlobalVariable();
        }
        return instance;
    }

    public void setAdminPendingTicketStatus(List<Ticketstatus> adminPendingTicketStatus) {
        this.adminPendingTicketStatus = adminPendingTicketStatus;
    }

    public List<Ticketstatus> getAdminPendingTicketStatus() {
        return adminPendingTicketStatus;
    }

    public List<Ticketstatus> getAdminApprovedTicketStatus() {
        return adminApprovedTicketStatus;
    }

    public void setAdminApprovedTicketStatus(List<Ticketstatus> adminApprovedTicketStatus) {
        this.adminApprovedTicketStatus = adminApprovedTicketStatus;
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

    public List<Ticketstatus> getGuestTicketStatus() {
        return guestTicketStatus;
    }

    public void setGuestTicketStatus(List<Ticketstatus> guestTicketStatus) {
        this.guestTicketStatus = guestTicketStatus;
    }
}
