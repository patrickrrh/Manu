package id.co.manu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import id.co.manu.model.Factory;
import id.co.manu.repository.FactoryRepo;

public class FactoryViewModel extends AndroidViewModel {

    FactoryRepo factoryRepo;
    private MutableLiveData<ArrayList<Factory>> factoryListMutableLiveData;
    private MutableLiveData<ArrayList<Factory>> recommendedFactoryMutableLiveData;

    public FactoryViewModel(@NonNull Application application) {
        super(application);
        factoryRepo = new FactoryRepo(application);
        factoryListMutableLiveData = factoryRepo.getFactoryListMutableLiveData();
        recommendedFactoryMutableLiveData = factoryRepo.getRecommendedFactoryListMutableLiveData();
    }

    public MutableLiveData<ArrayList<Factory>> getFactoryListMutableLiveData() {
        return factoryListMutableLiveData;
    }

    public MutableLiveData<ArrayList<Factory>> getRecommendedFactoryMutableLiveData() {
        return recommendedFactoryMutableLiveData;
    }
}
