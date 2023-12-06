package id.co.manu.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.Map;

import id.co.manu.model.Customer;

public class CustomerRepo {

    private Application application;
    private MutableLiveData<Customer> customerLiveData;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private ListenerRegistration customerListener;
    private FirebaseUser user;

    public CustomerRepo(Application application) {this.application = application;
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();  // Move this line after initializing auth

        customerLiveData = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            observeCustomerDetails(currentUser.getUid());
        }
    }

    private void observeCustomerDetails(String uid) {
        DocumentReference customerRef = firestore.collection("users").document(uid);

        customerListener = customerRef.addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                return;
            }

            if (documentSnapshot != null && documentSnapshot.exists()) {
                Map<String, Object> data = documentSnapshot.getData();
                if (data != null) {
                    String name = (String) data.get("name");
                    String phoneNum = (String) data.get("phoneNum");
                    String email = user.getEmail();
                    Customer customer = new Customer(name, email, phoneNum);

                    // Assuming that your Customer class has setName and setPhoneNum methods
                    customerLiveData.postValue(customer);
                }

            }
        });
    }

    public MutableLiveData<Customer> getCustomerLiveData() {
        return customerLiveData;
    }




}
