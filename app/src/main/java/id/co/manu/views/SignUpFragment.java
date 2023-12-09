package id.co.manu.views;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import id.co.manu.R;
import id.co.manu.viewmodel.AuthViewModel;

public class SignUpFragment extends Fragment {

    private EditText namaRegisterTbx, nomorHandphoneRegisterTbx, emailRegisterTbx, passwordRegisterTbx;
    private TextView signInTxtBtn;
    private Button signUpBtn;
    private AuthViewModel authViewModel;
    private ProgressBar loadingProgressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        authViewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    Intent intent = new Intent(getActivity(), NavigationActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        namaRegisterTbx = view.findViewById(R.id.namaRegisterTbx);
        nomorHandphoneRegisterTbx = view.findViewById(R.id.nomorHandphoneRegisterTbx);
        emailRegisterTbx = view.findViewById(R.id.emailRegisterTbx);
        passwordRegisterTbx = view.findViewById(R.id.passwordRegisterTbx);
        signInTxtBtn = view.findViewById(R.id.signInTxtBtn);
        signUpBtn = view.findViewById(R.id.signUpBtn);
        loadingProgressBar = view.findViewById(R.id.loadingRegisterProgressBar);

        signInTxtBtn.setOnClickListener(v -> {
            Fragment fragment = new SignInFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFrameLayout, fragment);
            fragmentTransaction.commit();
        });

        signUpBtn.setOnClickListener(v -> {
            String nama = namaRegisterTbx.getText().toString();
            String phoneNum = nomorHandphoneRegisterTbx.getText().toString();
            String email = emailRegisterTbx.getText().toString();
            String password = passwordRegisterTbx.getText().toString();

            if(!email.isEmpty() && !password.isEmpty() && !nama.isEmpty() && !phoneNum.isEmpty()){
                authViewModel.register(email, password, nama, phoneNum);
                authViewModel.getLoadingState().observe(getViewLifecycleOwner(), isLoading->{
                    loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    signUpBtn.setEnabled(!isLoading);
                    signUpBtn.setText(isLoading ? "" : "Buat Akun");
                    signUpBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), isLoading ? R.color.light_grey: R.color.manu_blue)));
                });
            }else{
                Toast.makeText(getContext(), "Isi semua field yang dibutuhkan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}