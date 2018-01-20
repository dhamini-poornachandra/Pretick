package com.project.msrit.pretick.data.network.service;

import com.project.msrit.pretick.data.network.interfaces.RestApi;
import com.project.msrit.pretick.data.network.model.ContactPerson;
import com.project.msrit.pretick.data.network.model.Role;
import com.project.msrit.pretick.data.network.model.Ticketstatus;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by dhamini-poorna-chandra on 26/11/2017.
 */

public class RestService implements RestApi {

    private final String URL = "http://drcandt.com/";
    private RestApi api;

    public RestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        this.api = retrofit.create(RestApi.class);
    }

    @Override
    public Observable<Role> postLogin(String username, String password) {
        return this.api.postLogin(username, password);
    }

    @Override
    public Observable<List<Ticketstatus>> getPendingTickets() {
        return this.api.getPendingTickets();
    }

    @Override
    public Observable<List<Ticketstatus>> getApprovedTickets() {
        return this.api.getApprovedTickets();
    }

    @Override
    public Observable<List<ContactPerson>> getContactPersons() {
        return this.api.getContactPersons();
    }

    @Override
    public Observable<Response<Void>> getSignUp(String fname,
                                                String lname,
                                                String organization,
                                                String email,
                                                String password,
                                                String role,
                                                String pnumber) {
        return this.api.getSignUp(fname, lname, organization, email, password, role, pnumber);
    }

    @Override
    public Observable<Response<Void>> postRaiseTicket(String date,
                                                      String starttime,
                                                      String endtime,
                                                      String contactperson,
                                                      String vehicletype,
                                                      String orgname,
                                                      String pnumber) {
        return this.api.postRaiseTicket(date, starttime, endtime, contactperson, vehicletype, orgname, pnumber);
    }

    @Override
    public Observable<Ticketstatus> getTicketDetails(String ticketId) {
        return this.api.getTicketDetails(ticketId);
    }

    @Override
    public Observable<List<Ticketstatus>> getFacultyPendingRequests(String userid) {
        return this.api.getFacultyPendingRequests(userid);
    }

    @Override
    public Observable<List<Ticketstatus>> getFacultyApprovedRequests(String userid) {
        return this.api.getFacultyApprovedRequests(userid);
    }

    @Override
    public Observable<Response<Void>> postAdminTicketApproval(String ticketid, String status, String date, String starttime, String endtime, String userid) {
        return this.api.postAdminTicketApproval(ticketid, status, date, starttime, endtime, userid);
    }

    @Override
    public Observable<Response<Void>> postFacultyTicketApproval(String ticketid, String status, String date, String starttime, String endtime, String userid) {
        return this.api.postFacultyTicketApproval(ticketid, status, date, starttime, endtime, userid);
    }

    @Override
    public Observable<List<Ticketstatus>> getGuestTicketRequests(String userid) {
        return this.api.getGuestTicketRequests(userid);
    }
}
