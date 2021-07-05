package com.example.agnciadeturismo.presenter.view.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agnciadeturismo.data.repository.ClienteRepositoryTask;
import com.example.agnciadeturismo.presenter.viewmodel.ClienteViewModel;
import com.example.agnciadeturismo.presenter.view.services.MascaraServices;
import com.example.agnciadeturismo.presenter.view.services.PermissoesServices;
import com.example.agnciadeturismo.presenter.view.services.UsuarioServices;
import com.example.agnciadeturismo.presenter.view.services.FirebaseServices;
import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.presenter.view.services.ValidarCPFServices;
import com.example.agnciadeturismo.model.ClienteDto;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class CadastrarUsuarioActivity extends AppCompatActivity {

    ClienteDto cliente = new ClienteDto(null, null, null, null, null, null, null, null);
    Toolbar toolbar;
    Button buttonCadastrar;
    ImageView imageViewCliente;
    EditText editTextNome, editTextSenha, editTextTelefone, editTextCPF, editTextRG, editTextEmail;
    TextInputLayout inputNome, inputSenha, inputTelefone, inputCPF, inputRG, inputEmail;
    String nome, email, cpf, rg, telefone, senha, img = "-1";
    boolean alterar = false, valido;
    String[] permissoes = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    int CAMERA = 1000, GALERIA = 2000;
    AlertDialog.Builder builder;
    Bitmap bitmap = null;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    ClienteViewModel clienteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        initView();
        initObeserve();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = editTextNome.getText().toString();
                cpf = editTextCPF.getText().toString();
                email = editTextEmail.getText().toString();
                rg = editTextRG.getText().toString();
                telefone = editTextTelefone.getText().toString();
                senha = editTextSenha.getText().toString();

                validarFormulario();
                if(valido){
                    if(ValidarCPFServices.validarCPF(cpf)){
                        cadastrarFirebase();
                    }else{
                        Toast.makeText(CadastrarUsuarioActivity.this, "CPF inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        imageViewCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissaoTask();
            }
        });
    }

    private void cadastrarFirebase() {
        if(!alterar){
            firebaseAuth = FirebaseServices.getFirebaseAuth();
            firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        if(bitmap != null){
                            cadastrarImagemFirebase();
                        }else{
                            clienteViewModel.modificarCliente(alterar, nome, email, cpf, rg, telefone, senha, img);
                        }
                    }else{
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            Toast.makeText(CadastrarUsuarioActivity.this, "Senha fraca!!!", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthEmailException e) {
                            Toast.makeText(CadastrarUsuarioActivity.this, "Formato de E-Mail errado!!!", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthUserCollisionException e) {
                            Toast.makeText(CadastrarUsuarioActivity.this, "E-Mail já cadastrado!!!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else{
            if(bitmap != null){
                cadastrarImagemFirebase();
            }else{
                clienteViewModel.modificarCliente(alterar, nome, email, cpf, rg, telefone, senha, img);
            }
        }
    }

    private void initView()  {
        clienteViewModel = new ViewModelProvider(this, new ClienteViewModel.ViewModelFactory(new ClienteRepositoryTask())).get(ClienteViewModel.class);
        toolbar = findViewById(R.id.toolbar_cadastrar);
        buttonCadastrar = findViewById(R.id.btn_cadastrarUsuario);
        editTextNome = findViewById(R.id.edt_nome);
        editTextEmail = findViewById(R.id.edt_email);
        editTextCPF = findViewById(R.id.edt_cpf);
        editTextRG = findViewById(R.id.edt_rg);
        editTextTelefone = findViewById(R.id.edt_telefone);
        editTextSenha = findViewById(R.id.edt_senha);
        imageViewCliente = findViewById(R.id.imageViewCliente);
        inputNome = findViewById(R.id.textInputLayoutNome);
        inputEmail = findViewById(R.id.textInputLayoutEmail);
        inputCPF = findViewById(R.id.textInputLayoutCPF);
        inputRG = findViewById(R.id.textInputLayoutRG);
        inputTelefone = findViewById(R.id.textInputLayoutTelefone);
        inputSenha = findViewById(R.id.textInputLayoutSenha);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Você precisa conceder pelo menos uma permissão");
        builder.setNeutralButton("Cancelar", null);

        MascaraServices.Companion.maskFormatter(editTextCPF, "NNN.NNN.NNN-NN");
        MascaraServices.Companion.maskFormatter(editTextTelefone, "(NN)NNNNN-NNNN");

        cliente = UsuarioServices.getUsuario();
        if(cliente.getCpf() != null){
            alterar = true;
            getSupportActionBar().setTitle("Alterar sua conta");
            img = cliente.getImg();
            Picasso.get().load(img).into(imageViewCliente);
            buttonCadastrar.setText("Alterar conta");
            editTextCPF.setEnabled(false);
            editTextEmail.setEnabled(false);
            editTextNome.setText(cliente.getNome());
            editTextCPF.setText(cliente.getCpf());
            editTextEmail.setText(cliente.getEmail());
            editTextRG.setText(cliente.getRg());
            editTextTelefone.setText(cliente.getTelefone());
            editTextSenha.setText(cliente.getSenha());
        }
    }

    private void initObeserve() {
        clienteViewModel.getCadastrado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean cadastrado) {
                if(cadastrado == true){
                    Toast.makeText(CadastrarUsuarioActivity.this, clienteViewModel.getCadastradoSucesso(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CadastrarUsuarioActivity.this, "Esse CPF já foi cadastrado!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void permissaoTask() {
        PermissoesServices.Companion.validarPermissoes(CadastrarUsuarioActivity.this, permissoes, 1);

        if(ActivityCompat.checkSelfPermission(CadastrarUsuarioActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED){
            buttonPositive();
        }
        if(ActivityCompat.checkSelfPermission(CadastrarUsuarioActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
            buttonNegative();
        }
        if(ActivityCompat.checkSelfPermission(CadastrarUsuarioActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CadastrarUsuarioActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
            builder.show();
        }
    }

    private void buttonNegative() {
        builder.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA);
            }
        });
    }

    private void buttonPositive() {
        builder.setPositiveButton("CAMERA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA);
            }
        });
    }

    private void validarFormulario() {
        valido = true;
        if(nome.isEmpty()){
            validacaoFormulario(inputNome, false);
        }else{
            validacaoFormulario(inputNome, true);
        }
        if(cpf.length() < 14){
            validacaoFormulario(inputCPF, false);
        }else{
            validacaoFormulario(inputCPF, true);
        }
        if(email.isEmpty()){
            validacaoFormulario(inputEmail, false);
        }else{
            validacaoFormulario(inputEmail, true);
        }
        if(rg.length() < 9){
            validacaoFormulario(inputRG, false);
        }else{
            validacaoFormulario(inputRG, true);
        }
        if(telefone.length() < 11){
            validacaoFormulario(inputTelefone, false);
        }else{
            validacaoFormulario(inputTelefone, true);
        }
    }

    private void validacaoFormulario(TextInputLayout input, boolean campo){
        if(campo){
            input.setError("");
        }else{
            input.setError("Campo obrigatório");
            valido = false;
        }
    }

    private void cadastrarImagemFirebase() {
        storageReference = FirebaseServices.getFirebaseStorage().child("imagens").child("clientes").child(cpf+".jpg");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] byteArray = stream.toByteArray();

        UploadTask uploadTask = storageReference.putBytes(byteArray);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    task.getException().printStackTrace();
                }
                return storageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri downloadUrl = task.getResult();
                    img = downloadUrl.toString();
                    clienteViewModel.modificarCliente(alterar, nome, email, cpf, rg, telefone, senha, img);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALERIA && resultCode == RESULT_OK){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(requestCode == CAMERA && resultCode == RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
        }
        imageViewCliente.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            if(permissions[i].equals("android.permission.CAMERA") && grantResults[i] == 0){
                buttonPositive();
            }else if(permissions[i].equals("android.permission.READ_EXTERNAL_STORAGE") && grantResults[i] == 0){
                buttonNegative();
            }
        }
        builder.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}