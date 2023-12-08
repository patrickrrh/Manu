package id.co.manu.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationRepo {
    private final Application application;
    private final MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private final MutableLiveData<Boolean> userLoggedLiveData;
    private final FirebaseAuth auth;
    private final MutableLiveData<Boolean> loadingStateLiveData;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLoggedLiveData() {
        return userLoggedLiveData;
    }


    public MutableLiveData<Boolean> getLoadingStateLiveData() {
        return loadingStateLiveData;
    }

    public AuthenticationRepo(Application application){
        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedLiveData = new MutableLiveData<>();
        loadingStateLiveData = new MutableLiveData<>(false);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }

    public void register(String email, String password, String name, String phoneNum){
        loadingStateLiveData.postValue(true);
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            FirebaseUser user = auth.getCurrentUser();
            if(!task.isSuccessful()){
                loadingStateLiveData.postValue(false);
                Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }else if(user != null){
                String uid = user.getUid();
                DocumentReference userRef = FirebaseFirestore.getInstance().collection("users").document(uid);
                Map<String, Object> userData = new HashMap<>();
                userData.put("name", name);
                userData.put("phoneNum", phoneNum);

                userRef.set(userData).addOnCompleteListener(task1 -> {
                    loadingStateLiveData.postValue(false);
                    if(task1.isSuccessful()){
                        firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    }else{
                        Toast.makeText(application, task1.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }

    public void login(String email, String password){
        loadingStateLiveData.postValue(true);
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            loadingStateLiveData.postValue(false);
            if(task.isSuccessful()){
                firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
            }else{
                Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signOut(){
        auth.signOut();
        userLoggedLiveData.postValue(true);
    }

}
