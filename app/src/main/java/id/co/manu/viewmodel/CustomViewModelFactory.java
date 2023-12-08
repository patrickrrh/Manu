package id.co.manu.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CustomViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    private final Application application;
    private final Context context;

    public CustomViewModelFactory(@NonNull Application application, Context context) {
        super(application);
        this.application = application;
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TransactionViewModel.class)) {
            return (T) new TransactionViewModel(application, context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
