package com.example.agnciadeturismo.data.api;

import com.example.agnciadeturismo.model.CartaoDto;
import com.example.agnciadeturismo.model.CidadeDto;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.model.ItensReservaDto;
import com.example.agnciadeturismo.model.PacoteDto;
import com.example.agnciadeturismo.model.ReservaDto;

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

    @GET("pacote_consultar.php")
    Call<ArrayList<PacoteDto>> buscarPacotes(
            @Query("origem") int origem,
            @Query("destino") int destino,
            @Query("tipoTransporte") int tipo
    );

    @GET("cidade_consultarTudo.php")
    Call<ArrayList<CidadeDto>> getAllCidades();

    @GET("cidade_consultarNome.php")
    Call<ArrayList<CidadeDto>> getNomeCidade(
            @Query("cidade") String nome
    );

    @FormUrlEncoded
    @POST("reserva_cadastrar.php")
    Call<Boolean> cadastrarReserva(
            @Field("cdCartao") int codigoCartao,
            @Field("cpf") String cpf,
            @Field("vlTotal") double valorTotal,
            @Field("statusReserva") int status,
            @Field("dthrReserva") String data
    );

    @FormUrlEncoded
    @POST("itensReserva_cadastrar.php")
    Call<Boolean> cadastrarItemReserva(
            @Field("cdPacote") int codigoPacote,
            @Field("cpf") String cpf,
            @Field("cdReserva") int codigoReserval,
            @Field("qtItens") int quantidade,
            @Field("vlUnitario") double unitario
    );

    @GET("reserva_pegarCodigo.php")
    Call<ArrayList<ReservaDto>> getCodigoReserva(
            @Query("cdCartao") int codigo,
            @Query("cpf") String cpf,
            @Query("valorTotal") double valorTotal,
            @Query("statusReserva") int status,
            @Query("data") String data
    );

    @GET("reserva_consultar.php")
    Call<ArrayList<ReservaDto>> getAllReserva(
            @Query("cpf") String cpf
    );

    @GET("ItensReserva_consultar.php")
    Call<ArrayList<ItensReservaDto>> getAllItemReserva(
            @Query("cpf") String cpf,
            @Query("cdReserva") int codigoReserva
    );
}