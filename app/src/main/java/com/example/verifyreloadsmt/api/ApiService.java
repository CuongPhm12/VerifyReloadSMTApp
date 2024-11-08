package com.example.verifyreloadsmt.api;

import com.example.verifyreloadsmt.model.DeleteItemReplaceVerifyResponse;
import com.example.verifyreloadsmt.model.Get_TotalByMachine_Response;
import com.example.verifyreloadsmt.model.tblReplaceVerify;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://172.28.10.17:5005/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    //    http://172.28.10.17:5005/Service/PDA_GA_Service.asmx/GetReload?wo=2000975453
    @GET("Service/PDA_GA_Service.asmx/GetReload")
    Call<List<tblReplaceVerify>> getReload(@Query("wo") String wo);

    //    http://172.28.10.17:5005/Service/PDA_GA_Service.asmx/Get_TotalByMachine?wo=2000975453&machine_id=YS12F-3
    @GET("Service/PDA_GA_Service.asmx/Get_TotalByMachine")
    Call<List<Get_TotalByMachine_Response>> Get_TotalByMachine(@Query("wo") String wo,
                                                         @Query("machine_id") String machine_id);

    //    http://172.28.10.17:5005/Service/PDA_GA_Service.asmx/DeleteItemReplaceVerify?wo=2000975453&machine=YS12F-3&slot=5&upn=NQ22252
    @GET("Service/PDA_GA_Service.asmx/DeleteItemReplaceVerify")
    Call<DeleteItemReplaceVerifyResponse> DeleteItemReplaceVerify(@Query("wo") String wo,
                                                                  @Query("machine") String machine_id,
                                                                  @Query("slot") String slot,
                                                                  @Query("upn") String upn);
}
