package com.example.registrarconductor.ui.estadisticas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EstadisticasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EstadisticasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento para las Estadisticas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}