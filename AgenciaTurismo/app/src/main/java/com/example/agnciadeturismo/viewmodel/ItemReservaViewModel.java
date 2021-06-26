package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.repository.ItemReservaRepositoryTask;
import com.example.agnciadeturismo.data.repository.ReservaRepositoryTask;
import com.example.agnciadeturismo.model.ItensReservaDto;

import java.util.ArrayList;

public class ItemReservaViewModel extends ViewModel {

    private MutableLiveData<Boolean> mCadastrado = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ItensReservaDto>> mAllItens = new MutableLiveData<>();

    public LiveData itensReserva = mAllItens;
    public LiveData cadastrado = mCadastrado;

    public void cadastrarItemReserva(int codigoPagote, String cpf, int codigoReserva, int quantidade, double valorUnitario){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCadastrado.postValue(ItemReservaRepositoryTask.cadastrarItemReserva(codigoPagote, cpf, codigoReserva, quantidade, valorUnitario));
            }
        }).start();
    }

    public void getAllItemReserva(String cpf, int codigoReserva){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAllItens.postValue(ItemReservaRepositoryTask.getAllItemReserva(cpf, codigoReserva));
            }
        }).start();
    }
}
