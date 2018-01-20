package com.project.msrit.pretick.data.network.model;

/**
 * Created by plank-dhamini on 20/01/18.
 */


public class ContactPerson {

    private String name;
    private String userid;
    private static ContactPerson contactPerson;

    public static ContactPerson getInstance() {

        if (contactPerson == null)
            contactPerson = new ContactPerson();
        return contactPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactPerson withName(String name) {
        this.name = name;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ContactPerson withUserid(String userid) {
        this.userid = userid;
        return this;
    }

}
