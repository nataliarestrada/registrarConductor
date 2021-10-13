package com.example.registrarconductor.ui.informes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InformesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InformesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento para los informes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}