package id.co.manu.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.manu.MainActivity;
import id.co.manu.R;
import id.co.manu.model.Customer;
import id.co.manu.viewmodel.AuthViewModel;
import id.co.manu.viewmodel.CustomerViewModel;
import id.co.manu.viewmodel.FactoryViewModel;

public class HomeFragment extends Fragment {
    //TODO: Buat untuk fetch data reccommended factory!
    private CustomerViewModel customerViewModel;
    private FactoryViewModel factoryViewModel;
    private TextView homeUserTxt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customerViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(CustomerViewModel.class);
        factoryViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(FactoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeUserTxt = view.findViewById(R.id.homeUserTxt);
        customerViewModel.getCustomerData().observe(getActivity(), new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                homeUserTxt.setText(customer.getName());
            }
        });




    }
}