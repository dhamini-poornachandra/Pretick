package com.project.msrit.pretick.data.network.service;

import com.project.msrit.pretick.data.network.interfaces.RestApi;
import com.project.msrit.pretick.data.network.model.Role;
import com.project.msrit.pretick.data.network.model.Ticketstatus;

import java.util.List;

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
}
