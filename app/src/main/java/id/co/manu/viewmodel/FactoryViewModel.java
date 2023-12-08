package id.co.manu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import id.co.manu.model.Factory;
import id.co.manu.repository.FactoryRepo;

public class FactoryViewModel extends AndroidViewModel {

    private final FactoryRepo factoryRepo;
    private final MutableLiveData<ArrayList<Factory>> factoryList;
    private final MutableLiveData<ArrayList<Factory>> recommendedFactoryList;
//    private final MutableLiveData<List<String>> daerahList;
//    private final MutableLiveData<List<String>> categoryList;

    public FactoryViewModel(@NonNull Application application) {
        super(application);
        factoryRepo = new FactoryRepo(application);
        factoryList = factoryRepo.getFactoryListMutableLiveData();
        recommendedFactoryList = factoryRepo.getRecommendedFactoryListMutableLiveData();
//        daerahList = factoryRepo.getUniqueDaerah();
//        categoryList = factoryRepo.getUniqueCategory();
    }

    public MutableLiveData<ArrayList<Factory>> getAllFactory() {
        factoryRepo.getAllFactory();
        return factoryList;
    }

    public MutableLiveData<ArrayList<Factory>> getRecommendedFactory() {
        factoryRepo.getRecommendedFactory();
        return recommendedFactoryList;
    }

//    public MutableLiveData<List<String>> getDaerahList(){
//        factoryRepo.getUniqueDaerah();
//        return daerahList;
//    }
//
//    public MutableLiveData<List<String>> getCategoryList(){
//        factoryRepo.getUniqueCategory();
//        return categoryList;
//    }
}
