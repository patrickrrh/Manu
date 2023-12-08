package id.co.manu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;

import id.co.manu.repository.AuthenticationRepo;

public class AuthViewModel extends AndroidViewModel {

    private final AuthenticationRepo authRepo;
    private final MutableLiveData<FirebaseUser> userData;
    private final MutableLiveData<Boolean> loggedStatus;
    private final MutableLiveData<Boolean> loadingState;

    public MutableLiveData<Boolean> getLoadingState() {
        return loadingState;
    }

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedStatus() {
        return loggedStatus;
    }

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthenticationRepo(application);
        loadingState = authRepo.getLoadingStateLiveData();
        userData = authRepo.getFirebaseUserMutableLiveData();
        loggedStatus = authRepo.getUserLoggedLiveData();
    }

    public void register(String email, String password, String name, String phoneNum){
        authRepo.register(email, password, name, phoneNum);
    }

    public void login(String email, String password){
        authRepo.login(email, password);
    }

    public void signOut(){
        authRepo.signOut();
    }
}
