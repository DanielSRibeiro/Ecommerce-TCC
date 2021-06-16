package com.example.agnciadeturismo.data.api;

import com.example.agnciadeturismo.model.CartaoDto;
import com.example.agnciadeturismo.model.CidadeDto;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.model.PacoteDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HorizonFlyApi {


    @GET("usuario_consultar.php")
    Call<ArrayList<ClienteDto>> consultarCliente(
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

    @FormUrlEncoded
    @POST("cartao_cadastrar.php")
    Call<Boolean> cadastrarCartao(
            @Field("cpf") String cpf,
            @Field("nomeCartao") String nomeCartao,
            @Field("nomeImpresso") String nomeImpresso,
            @Field("numeroCartao") String numeroCartao,
            @Field("cvv") String cvv,
            @Field("validadeCartao") String validadeCartao
    );

    @GET("cartao_consultar.php")
    Call<ArrayList<CartaoDto>> consultarCartao(
            @Query("cpf") String cpf
    );

    @FormUrlEncoded
    @POST("cartao_deletar.php")
    Call<Boolean> deletarCartao(
            @Field("cpf") String cpf,
            @Field("cdCartao") int codigo
    );

    @GET("pacote_oferta.php")
    Call<ArrayList<PacoteDto>> consultarOferta();

    @GET("cidade_consultarTudo.php")
    Call<ArrayList<CidadeDto>> getAllCidades();

    @GET("cidade_consultarNome.php")
    Call<ArrayList<CidadeDto>> getNomeCidade(
            @Query("cidade") String nome
    );

}
