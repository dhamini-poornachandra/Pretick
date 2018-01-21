package com.project.msrit.pretick.data.network.interfaces;

import com.project.msrit.pretick.data.network.model.ContactPerson;
import com.project.msrit.pretick.data.network.model.Role;
import com.project.msrit.pretick.data.network.model.Ticketstatus;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dhamini-poorna-chandra on 26/11/2017.
 */

public interface RestApi {

    @POST("/PreTick/login.php")
    Observable<Role> postLogin(@Query("username") String username, @Query("password") String password);

    @GET("/PreTick/adminviewpnd.php")
    Observable<List<Ticketstatus>> getPendingTickets();

    @GET("/PreTick/adminviewaprd.php")
    Observable<List<Ticketstatus>> getApprovedTickets();

    @GET("/PreTick/contactpersons.php")
    Observable<List<ContactPerson>> getContactPersons();

    @GET("/PreTick/signup.php")
    Observable<Response<Void>> getSignUp(@Query("fname") String fname,
                                         @Query("lname") String lname,
                                         @Query("organization") String organization,
                                         @Query("email") String email,
                                         @Query("password") String password,
                                         @Query("role") String role,
                                         @Query("pnumber") String pnumber);

    @GET("/PreTick/raiseticket.php")
    Observable<Response<Void>> postRaiseTicket(@Query("date") String date,
                                               @Query("starttime") String starttime,
                                               @Query("endtime") String endtime,
                                               @Query("contactperson") String contactperson,
                                               @Query("vehicletype") String vehicletype,
                                               @Query("orgname") String orgname,
                                               @Query("pnumber") String pnumber);

    @GET("/PreTick/ticketdetails.php")
    Observable<Ticketstatus> getTicketDetails(@Query("ticketid") String ticketId);

    @GET("/PreTick/facultyviewpnd.php")
    Observable<List<Ticketstatus>> getFacultyPendingRequests(@Query("userid") String userid);

    @GET("/PreTick/facultyviewaprd.php")
    Observable<List<Ticketstatus>> getFacultyApprovedRequests(@Query("userid") String userid);

    @GET("/PreTick/adminticket.php")
    Observable<Response<Void>> postAdminTicketApproval(@Query("ticketid") String ticketid,
                                                       @Query("status") String status,
                                                       @Query("date") String date,
                                                       @Query("starttime") String starttime,
                                                       @Query("endtime") String endtime,
                                                       @Query("userid") String userid);

    @GET("/PreTick/facultyticket.php")
    Observable<Response<Void>> postFacultyTicketApproval(@Query("ticketid") String ticketid,
                                                         @Query("status") String status,
                                                         @Query("date") String date,
                                                         @Query("starttime") String starttime,
                                                         @Query("endtime") String endtime,
                                                         @Query("userid") String userid);

    @GET("/PreTick/tickets.php")
    Observable<List<Ticketstatus>> getGuestTicketRequests(@Query("userid") String userid);
}
