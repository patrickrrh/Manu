package id.co.manu.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationRepo {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedLiveData;
    private FirebaseAuth auth;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLoggedLiveData() {
        return userLoggedLiveData;
    }

    public AuthenticationRepo(Application application){
        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }

    public void register(String email, String password, String name, String phoneNum){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user = auth.getCurrentUser();
                if(user != null){
                    String uid = user.getUid();
                    DocumentReference userRef = FirebaseFirestore.getInstance().collection("users").document(uid);
                    Map<String, Object> userData = new HashMap<>();
                    userData.put("name", name);
                    userData.put("phoneNum", phoneNum);

                    userRef.set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                            }else{
                                Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                }else{
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void signOut(){
        auth.signOut();
        userLoggedLiveData.postValue(true);
    }

}
