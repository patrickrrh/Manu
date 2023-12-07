package id.co.manu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import id.co.manu.model.Factory;
import id.co.manu.repository.FactoryRepo;

public class FactoryViewModel extends AndroidViewModel {

    private final FactoryRepo factoryRepo;
    private final MutableLiveData<ArrayList<Factory>> factoryList;
    private final MutableLiveData<ArrayList<Factory>> recommendedFactoryList;

    public FactoryViewModel(@NonNull Application application) {
        super(application);
        factoryRepo = new FactoryRepo(application);
        factoryList = factoryRepo.getFactoryListMutableLiveData();
        recommendedFactoryList = factoryRepo.getRecommendedFactoryListMutableLiveData();
    }

    public MutableLiveData<ArrayList<Factory>> getAllFactory() {
        factoryRepo.getAllFactory();
        return factoryList;
    }

    public MutableLiveData<ArrayList<Factory>> getRecommendedFactory() {
        factoryRepo.getRecommendedFactory();
        return recommendedFactoryList;
    }
}
