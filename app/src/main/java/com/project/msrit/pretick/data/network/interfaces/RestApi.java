package com.project.msrit.pretick.data.network.interfaces;

import com.project.msrit.pretick.data.network.model.SampleResponseModel;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by dhamini-poorna-chandra on 26/11/2017.
 */

public interface RestApi {

    @GET("/posts/1")
    Observable<SampleResponseModel> getCharacters();

}
