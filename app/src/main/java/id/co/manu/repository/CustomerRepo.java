package id.co.manu.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import id.co.manu.model.Customer;

public class CustomerRepo {

    private Application application;
    private MutableLiveData<Customer> customerLiveData;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private ListenerRegistration customerListener;

    public CustomerRepo(Application application) {
        this.application = application;
        customerLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
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
                Customer customer = documentSnapshot.toObject(Customer.class);
                customerLiveData.postValue(customer);
            }
        });
    }

    public MutableLiveData<Customer> getCustomerLiveData() {
        return customerLiveData;
    }

    public void removeCustomerListener() {
        if (customerListener != null) {
            customerListener.remove();
        }
    }



}
