package id.co.manu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import id.co.manu.model.Customer;
import id.co.manu.repository.CustomerRepo;

public class CustomerViewModel extends AndroidViewModel {

    private CustomerRepo customerRepo;
    private MutableLiveData<Customer> customerData;

    public MutableLiveData<Customer> getCustomerData() {
        return customerData;
    }

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepo = new CustomerRepo(application);
        customerData = customerRepo.getCustomerLiveData();

        if (customerData == null) {
            customerData = new MutableLiveData<>();
        }
    }
}
