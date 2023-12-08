package id.co.manu.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import id.co.manu.MainActivity;
import id.co.manu.R;
import id.co.manu.model.Customer;
import id.co.manu.viewmodel.AuthViewModel;
import id.co.manu.viewmodel.CustomerViewModel;

public class ProfileFragment extends Fragment {

    private AuthViewModel authViewModel;
    private CustomerViewModel customerViewModel;
    private TextView userId, name, email, phoneNumber;
    private Button signOutBtn;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        authViewModel.getLoggedStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userId = view.findViewById(R.id.idUserTxt);
        name = view.findViewById(R.id.namaUserTxt);
        email = view.findViewById(R.id.emailUserTxt);
        phoneNumber = view.findViewById(R.id.teleponUserTxt);

        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        customerViewModel.getCustomerData().observe(getViewLifecycleOwner(), new Observer<Customer>() {
            @Override
            public void onChanged(Customer customer) {
                // Update UI with the new customer data
                userId.setText(customer.getUid());
                name.setText(customer.getName());
                email.setText(customer.getEmail());
                phoneNumber.setText(customer.getPhoneNumber());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signOutBtn = view.findViewById(R.id.signOutBtn);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authViewModel.signOut();
                getActivity().finish();
            }
        });
    }
}