package id.co.manu.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import id.co.manu.R;

public class ExploreRepo {

    public static LiveData<List<Factory>> getFactory(){
        MutableLiveData<List<Factory>> factoryData = new MutableLiveData<>();

        List<Factory> dummyFactories = generateDummyFactories();
        factoryData.setValue(dummyFactories);

        return factoryData;
    }

    private static List<Factory> generateDummyFactories(){

        List<Factory> dummyFactories = new ArrayList<>();

        dummyFactories.add(new Factory(R.drawable.dummy_image, "PT Indo Rame", "Produsen Furnitur Kayu", "Cianjur, Jawa Barat"));
        dummyFactories.add(new Factory(R.drawable.dummy_image, "PT Indo Rame", "Produsen Furnitur Kayu", "Cianjur, Jawa Barat"));
        dummyFactories.add(new Factory(R.drawable.dummy_image, "PT Indo Rame", "Produsen Furnitur Kayu", "Cianjur, Jawa Barat"));

        return dummyFactories;
    }

}
