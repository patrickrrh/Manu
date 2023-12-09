package id.co.manu.views;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import id.co.manu.R;
import id.co.manu.viewmodel.AuthViewModel;

public class SignInFragment extends Fragment {

    private EditText emailLoginTbx, passwordLoginTbx;
    private TextView signUpTxtBtn;
    private Button signInBtn;
    private AuthViewModel authViewModel;
    private ProgressBar loadingProgressBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);

        authViewModel.getUserData().observe(this, firebaseUser -> {
            if(firebaseUser != null){
                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailLoginTbx = view.findViewById(R.id.emailLoginTbx);
        passwordLoginTbx = view.findViewById(R.id.passwordLoginTbx);
        signUpTxtBtn = view.findViewById(R.id.signUpTxtBtn);
        signInBtn = view.findViewById(R.id.signInBtn);
        loadingProgressBar = view.findViewById(R.id.loadingLoginProgressBar);

        signUpTxtBtn.setOnClickListener(v -> {
            Fragment fragment = new SignUpFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFrameLayout, fragment);
            fragmentTransaction.commit();
        });

        signInBtn.setOnClickListener(v -> {
            String email = emailLoginTbx.getText().toString();
            String password = passwordLoginTbx.getText().toString();

            if(!email.isEmpty() && !password.isEmpty() ){
                authViewModel.login(email, password);
                authViewModel.getLoadingState().observe(getViewLifecycleOwner(), isLoading -> {
                    loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    signInBtn.setEnabled(!isLoading);
                    signInBtn.setText(isLoading ? "" : "Masuk");
                    signInBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), isLoading ? R.color.light_grey: R.color.manu_blue)));
                });
            }else{
                Toast.makeText(getContext(), "Masukkan Email dan Password!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}