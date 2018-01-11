package com.project.msrit.pretick.data.network.interfaces;

import com.project.msrit.pretick.data.network.model.Role;

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

    @GET("/PreTick/pendingTickets")
    Observable<Role> getPendingTickets();
}
