package id.co.manu.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.co.manu.repository.ExploreRepo;
import id.co.manu.repository.Factory;

public class ExploreViewModel extends AndroidViewModel {

    private ExploreRepo exploreRepo;
    private MutableLiveData<List<Factory>> factoryList = new MutableLiveData<>();
    private List<Factory> allFactories;

    public ExploreViewModel(Application application){
        super(application);
        exploreRepo = new ExploreRepo();
        fetchData();
    }

    public LiveData<List<Factory>> getFactoryList() {
        return factoryList;
    }

    private void fetchData() {
        // Use the repository to retrieve data
        LiveData<List<Factory>> data = ExploreRepo.getFactory();

        // Observe changes in the LiveData and update itemList accordingly
        data.observeForever(factories -> factoryList.setValue(factories));
    }

}
