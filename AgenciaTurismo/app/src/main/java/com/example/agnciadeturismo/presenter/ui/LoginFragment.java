package com.example.agnciadeturismo.presenter.ui;

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
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.services.UsuarioServices;
import com.example.agnciadeturismo.viewmodel.ClienteViewModel;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);

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

        return view;
    }

    private void initView(View view) {
        clienteViewModel = new ViewModelProvider(getActivity()).get(ClienteViewModel.class);
        buttonLogin = view.findViewById(R.id.btn_login);
        textViewCadastrar = view.findViewById(R.id.txt_cadastrarUsuario);
        editTextCPF = view.findViewById(R.id.edt_cpfLogin);
        editTextSenha = view.findViewById(R.id.edt_senhaLogin);

        SimpleMaskFormatter maskCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTextCPF, maskCPF);
        editTextCPF.addTextChangedListener(mtw);

        controleSessao();
    }

    private void controleSessao() {
        SharedPreferences preferences = getActivity().getSharedPreferences("taLogado", Context.MODE_PRIVATE);
        ClienteDto cliente = UsuarioServices.getUsuario();

        if(cliente.getCpf() != null){
            cliente.setNome(preferences.getString("nome", ""));
            cliente.setEmail(preferences.getString("email", ""));
            cliente.setCpf(preferences.getString("cpf", ""));
            cliente.setRg(preferences.getString("rg", ""));
            cliente.setTelefone(preferences.getString("tel", ""));
            cliente.setSenha(preferences.getString("senha", ""));
            cliente.setImg(preferences.getString("img", ""));
            UsuarioServices.setUsuario(cliente);

            mudarTela();
        }
    }

    private void initRetrofit(String cpf, String senha) {
        RetrofitTask.getRetrofit().consultarCliente(cpf, senha).enqueue(new Callback<ArrayList<ClienteDto>>() {
            @Override
            public void onResponse(Call<ArrayList<ClienteDto>> call, Response<ArrayList<ClienteDto>> response) {
                if(response.isSuccessful()){
                    if(response.body().size() != 0){
                        cliente = response.body().get(0);
                        UsuarioServices.setUsuario(cliente);

                        SharedPreferences preferences = getActivity().getSharedPreferences("taLogado", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("nome", cliente.getNome());
                        editor.putString("email", cliente.getEmail());
                        editor.putString("cpf", cliente.getCpf());
                        editor.putString("rg", cliente.getRg());
                        editor.putString("tel", cliente.getTelefone());
                        editor.putString("senha", cliente.getSenha());
                        editor.putString("img", cliente.getImg());
                        editor.commit();

                        mudarTela();
                    }else{
                        Toast.makeText(getActivity(), "E-Mail ou senha está incorreta!!!", Toast.LENGTH_SHORT).show();
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