package id.co.manu.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import id.co.manu.model.Factory;

public class FactoryRepo {
    private Application application;
    private MutableLiveData<Factory> factoryMutableLiveData;
    private MutableLiveData<ArrayList<Factory>> factoryListMutableLiveData;
    private MutableLiveData<ArrayList<Factory>> recommendedFactoryMutableLiveData;
    private FirebaseFirestore firestore;

    public FactoryRepo(Application application){
        this.application = application;
        firestore = FirebaseFirestore.getInstance();

        factoryMutableLiveData = new MutableLiveData<>();
        factoryListMutableLiveData = new MutableLiveData<>();
    }

    public void getAllFactory(){
        firestore.collection("factories").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Factory> factoryList = new ArrayList<>();
                for (QueryDocumentSnapshot factoryData : task.getResult()) {
                    Factory factory = new Factory(
                            factoryData.getId(),
                            (String) factoryData.get("name"),
                            (String) factoryData.get("price"),
                            (String) factoryData.get("category"),
                            (String) factoryData.get("address"),
                            (String) factoryData.get("description")
                    );
                    factoryList.add(factory);
                }
                factoryListMutableLiveData.postValue(factoryList);
            }
        });
    }

    public void getRecommendedFactory(){
        DocumentReference factory1 = firestore.collection("factories").document("r2rAJ2dQv3QpYP0vjiAO");
        DocumentReference factory2 = firestore.collection("factories").document("Hq2NvOdG9vWU30MN7gNy");
        ArrayList<Factory> factoryList = new ArrayList<>();
        factory1.get().addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               DocumentSnapshot doc = task.getResult();
               Factory factory = new Factory(
                       doc.getId(),
                       (String) doc.get("name"),
                       (String) doc.get("price"),
                       (String) doc.get("category"),
                       (String) doc.get("address"),
                       (String) doc.get("description")
               );
               factoryList.add(factory);
           }
        });

        factory2.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot doc = task.getResult();
                Factory factory = new Factory(
                        doc.getId(),
                        (String) doc.get("name"),
                        (String) doc.get("price"),
                        (String) doc.get("category"),
                        (String) doc.get("address"),
                        (String) doc.get("description")
                );
                factoryList.add(factory);
            }
        });
        recommendedFactoryMutableLiveData.postValue(factoryList);
    }

    public MutableLiveData<ArrayList<Factory>> getRecommendedFactoryListMutableLiveData() {
        return recommendedFactoryMutableLiveData;
    }

    public MutableLiveData<ArrayList<Factory>> getFactoryListMutableLiveData() {
        return factoryListMutableLiveData;
    }
}
