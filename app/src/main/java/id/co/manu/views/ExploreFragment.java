package id.co.manu.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

import id.co.manu.R;
import id.co.manu.model.Factory;
import id.co.manu.viewmodel.FactoryViewModel;
import id.co.manu.views.adapter.FactoryAdapter;

public class ExploreFragment extends Fragment {

    private FactoryViewModel factoryViewModel;
    GridView gridView;
    FactoryAdapter factoryAdapter;

    ArrayList<Factory> arrayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        factoryViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(FactoryViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AutoCompleteTextView autoCompleteDaerah = view.findViewById(R.id.autoCompleteDaerahTxt);
        AutoCompleteTextView autoCompleteKategori = view.findViewById(R.id.autoCompleteKategoriTxt);
        gridView = view.findViewById(R.id.pabrikGrid);

        factoryAdapter = new FactoryAdapter(new ArrayList<>(), requireContext());
        gridView.setAdapter(factoryAdapter);

        factoryViewModel.getAllFactory().observe(getActivity(), factoryList -> {
            if(arrayList != null){
                factoryAdapter.setFactoryList(factoryList);
            }
        });

    }
}