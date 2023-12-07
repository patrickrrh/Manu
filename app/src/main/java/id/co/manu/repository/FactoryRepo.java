package id.co.manu.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Map;

import id.co.manu.model.Factory;

public class FactoryRepo {
    private final Application application;
    private final MutableLiveData<Factory> factoryMutableLiveData;
    private final MutableLiveData<ArrayList<Factory>> factoryListMutableLiveData;
    private final MutableLiveData<ArrayList<Factory>> recommendedFactoryMutableLiveData;
    private final FirebaseFirestore firestore;
    private final String collectionName = "factories";

    public FactoryRepo(Application application){
        this.application = application;
        firestore = FirebaseFirestore.getInstance();

        factoryMutableLiveData = new MutableLiveData<>();
        factoryListMutableLiveData = new MutableLiveData<>();
        recommendedFactoryMutableLiveData = new MutableLiveData<>();

    }

    public void getAllFactory(){
        firestore.collection(collectionName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Factory> factoryList = new ArrayList<>();
                for (QueryDocumentSnapshot factoryData : task.getResult()) {
                    Factory factory = new Factory(
                            factoryData.getId(),
                            (String) factoryData.get("name"),
                            (String) factoryData.get("price"),
                            (String) factoryData.get("category"),
                            (String) factoryData.get("address"),
                            (String) factoryData.get("description"),
                            (String) factoryData.get("imageUrl")
                    );
                    factoryList.add(factory);
                }
                factoryListMutableLiveData.postValue(factoryList);
            }
        });
    }

    public void getRecommendedFactory(){
        firestore.collection(collectionName).limit(2).get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        ArrayList<Factory> factoryList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            String documentId = document.getId();
                            Map<String, Object> data = document.getData();

                            // Assuming Factory has a constructor that takes documentId and a Map<String, Object>
                            Factory factory = new Factory(
                                    documentId,
                                    (String) data.get("name"),
                                    (String) data.get("price"),
                                    (String) data.get("category"),
                                    (String) data.get("address"),
                                    (String) data.get("description"),
                                    (String) data.get("imageUrl"));
                            factoryList.add(factory);
                        }
                        recommendedFactoryMutableLiveData.postValue(factoryList);
                    } else {
                        Exception exception = task.getException();
                        if (exception != null) {
                            exception.printStackTrace();
                        }
                    }
                });
    }

    public MutableLiveData<ArrayList<Factory>> getRecommendedFactoryListMutableLiveData() {
        return recommendedFactoryMutableLiveData;
    }

    public MutableLiveData<ArrayList<Factory>> getFactoryListMutableLiveData() {
        return factoryListMutableLiveData;
    }
}
