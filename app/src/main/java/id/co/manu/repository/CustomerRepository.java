package id.co.manu.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import id.co.manu.model.Customer;

public class CustomerRepository {

    private Application application;
    private MutableLiveData<Customer> customerLiveData;
}
