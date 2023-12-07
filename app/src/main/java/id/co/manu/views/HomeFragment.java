package id.co.manu.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import id.co.manu.R;
import id.co.manu.viewmodel.CustomerViewModel;
import id.co.manu.viewmodel.FactoryViewModel;

public class HomeFragment extends Fragment {
    private CustomerViewModel customerViewModel;
    private FactoryViewModel factoryViewModel;
    private TextView homeUserTxt;
    private CardView card1, card2, searchBtn;
    private TextView card1Title, card1Category, card1Address;
    private TextView card2Title, card2Category, card2Address;
    private ImageView card1Img, card2Img;

    private OnSearchButtonClickListener searchButtonClickListener;
    public interface OnSearchButtonClickListener {
        void onSearchButtonClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchButtonClickListener) {
            searchButtonClickListener = (OnSearchButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnSearchButtonClickListener");
        }
    }

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

        searchBtn = view.findViewById(R.id.searchBtn);

        homeUserTxt = view.findViewById(R.id.homeUserTxt);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);

        card1Img = view.findViewById(R.id.card1Img);
        card2Img = view.findViewById(R.id.card2Img);

        card1Title = view.findViewById(R.id.card1Title);
        card2Title = view.findViewById(R.id.card2Title);

        card1Category = view.findViewById(R.id.card1Category);
        card2Category = view.findViewById(R.id.card2Category);

        card1Address = view.findViewById(R.id.card1Address);
        card2Address = view.findViewById(R.id.card2Address);
        factoryViewModel.getAllFactory().observe(getActivity(), factoryArrayList -> {
            if (factoryArrayList != null && factoryArrayList.size() >= 2) {
                Picasso.get().load(factoryArrayList.get(0).getImageUrl()).into(card1Img);
                Picasso.get().load(factoryArrayList.get(1).getImageUrl()).into(card2Img);

                card1Title.setText(factoryArrayList.get(0).getName());
                card2Title.setText(factoryArrayList.get(1).getName());

                card1Category.setText(factoryArrayList.get(0).getCategory());
                card2Category.setText(factoryArrayList.get(1).getCategory());

                card1Address.setText(factoryArrayList.get(0).getAddress());
                card2Address.setText(factoryArrayList.get(1).getAddress());

                card1.setOnClickListener(view1 -> {
                    Intent intent = new Intent(getActivity(), DetailFactoryActivity.class);
                    intent.putExtra("factory", factoryArrayList.get(0));
                    startActivity(intent);
                });

                card2.setOnClickListener(view1 -> {
                    Intent intent = new Intent(getActivity(), DetailFactoryActivity.class);
                    intent.putExtra("factory", factoryArrayList.get(1));
                    startActivity(intent);
                });
            }
        });

        searchBtn.setOnClickListener(v -> {
            searchButtonClickListener.onSearchButtonClick();
        });

        customerViewModel.getCustomerData().observe(getActivity(), customer -> homeUserTxt.setText(customer.getName()));
    }
}