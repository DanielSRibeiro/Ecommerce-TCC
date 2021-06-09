package com.example.agnciadeturismo.data.api;

import com.example.agnciadeturismo.domain.model.ClienteDto;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HorizonFlyApi {

    @FormUrlEncoded
    @GET("usuario_consultar.php")
    Call<ClienteDto> consultarCliente(
            @Query("cpf") String cpf,
            @Query("senha") String senha
    );

    @FormUrlEncoded
    @POST("usuario_cadastrar.php")
    Call<Boolean> cadastrarCliente(
            @Field("nome") String nome,
            @Field("email") String email,
            @Field("cpf") String cpf,
            @Field("rg") String rg,
            @Field("telefone") String telefone,
            @Field("senha") String senha
    );

    @FormUrlEncoded
    @POST("usuario_update.php")
    Call<Boolean> updateCliente(
            @Field("nome") String nome,
            @Field("email") String email,
            @Field("cpf") String cpf,
            @Field("rg") String rg,
            @Field("telefone") String telefone,
            @Field("senha") String senha,
            @Field("img") String img
    );

}
