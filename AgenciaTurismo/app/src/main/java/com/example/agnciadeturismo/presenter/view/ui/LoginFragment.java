package com.example.agnciadeturismo.presenter.view.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.data.repository.ClienteRepositoryTask;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.presenter.view.services.MascaraServices;
import com.example.agnciadeturismo.presenter.viewmodel.ClienteViewModel;
import com.example.agnciadeturismo.presenter.view.services.UsuarioServices;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    Button buttonLogin;
    TextView textViewCadastrar;
    EditText editTextCPF, editTextSenha;
    ClienteViewModel clienteViewModel;
    private static final String TAG = "LoginFragment";
    ClienteDto cliente;
    static String LOGIN_SHARED = "Login", TA_LOGADO_SHARED = "taLogado",CPF_SHARED = "cpf",
            SENHA_SHARED = "senha", NOME_SHARED = "nome", IMG_SHARED = "img", TEL_SHARED = "tel",
            EMAIL_SHARED = "email", RG_SHARED = "rg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        controleSessao();
        initView(view);
        onClick();

        return view;
    }

    private void initView(View view) {
        clienteViewModel = new ViewModelProvider(getActivity(), new ClienteViewModel.ViewModelFactory(new ClienteRepositoryTask())).get(ClienteViewModel.class);
        buttonLogin = view.findViewById(R.id.btn_login);
        textViewCadastrar = view.findViewById(R.id.txt_cadastrarUsuario);
        editTextCPF = view.findViewById(R.id.edt_cpfLogin);
        editTextSenha = view.findViewById(R.id.edt_senhaLogin);

        MascaraServices.Companion.maskFormatter(editTextCPF, "NNN.NNN.NNN-NN");
    }

    private void controleSessao() {
        SharedPreferences preferences = getActivity().getSharedPreferences(LOGIN_SHARED, Context.MODE_PRIVATE);
        boolean taLogado = preferences.getBoolean(TA_LOGADO_SHARED, false);
        cliente = UsuarioServices.getUsuario();
        if(taLogado){
            cliente.setNome(preferences.getString(NOME_SHARED, ""));
            cliente.setEmail(preferences.getString(EMAIL_SHARED, ""));
            cliente.setCpf(preferences.getString(CPF_SHARED, ""));
            cliente.setRg(preferences.getString(RG_SHARED, ""));
            cliente.setTelefone(preferences.getString(TEL_SHARED, ""));
            cliente.setSenha(preferences.getString(SENHA_SHARED, ""));
            cliente.setImg(preferences.getString(IMG_SHARED, "-1"));

            UsuarioServices.setUsuario(cliente);
            mudarTela();
        }
    }

    private void onClick() {
        textViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarUsuarioActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = editTextCPF.getText().toString();
                String senha = editTextSenha.getText().toString();

                initRetrofit(cpf, senha);
            }
        });
    }

    private void initRetrofit(String cpf, String senha) {
        RetrofitTask.getRetrofit().consultarCliente(cpf, senha).enqueue(new Callback<ArrayList<ClienteDto>>() {
            @Override
            public void onResponse(Call<ArrayList<ClienteDto>> call, Response<ArrayList<ClienteDto>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() != 0){
                        cliente = response.body().get(0);
                        UsuarioServices.setUsuario(cliente);

                        SharedPreferences preferences = getActivity().getSharedPreferences(LOGIN_SHARED, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(TA_LOGADO_SHARED, true);
                        editor.putString(CPF_SHARED, cliente.getCpf());
                        editor.putString(SENHA_SHARED, cliente.getSenha());
                        editor.putString(NOME_SHARED, cliente.getNome());
                        editor.putString(EMAIL_SHARED, cliente.getEmail());
                        editor.putString(RG_SHARED, cliente.getRg());
                        editor.putString(TEL_SHARED, cliente.getTelefone());
                        editor.putString(IMG_SHARED, cliente.getImg());
                        editor.apply();

                        mudarTela();
                    }else{
                        Toast.makeText(getActivity(), "CPF ou senha está incorreta!!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d(TAG, "ERRO: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ClienteDto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void mudarTela() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_home, new PerfilFragment())
                .commit();
    }
}