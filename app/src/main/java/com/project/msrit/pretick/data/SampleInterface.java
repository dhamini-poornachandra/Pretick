package com.project.msrit.pretick.data;

import com.project.msrit.pretick.data.network.model.SampleResponseModel;

/**
 * Created by dhamini-poorna-chandra on 26/11/2017.
 */

public interface SampleInterface {

    interface View {

        void onFetchDataStarted();

        void onFetchDataCompleted();

        void onFetchDataSuccess(SampleResponseModel sampleResponseModel);

        void onFetchDataError(Throwable e);
    }

    interface Presenter {

        void loadData();

    }
}
