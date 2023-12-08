package id.co.manu.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import id.co.manu.R;
import id.co.manu.model.Transaction;
import id.co.manu.viewmodel.CustomViewModelFactory;
import id.co.manu.viewmodel.CustomerViewModel;
import id.co.manu.viewmodel.TransactionViewModel;
import id.co.manu.views.adapter.HistoryAdapter;

public class ReportFragment extends Fragment {
    private TransactionViewModel transactionViewModel;
    private CustomerViewModel customerViewModel;
    private ListView historyLv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transactionViewModel = new ViewModelProvider(this, new CustomViewModelFactory(getActivity().getApplication(), getContext())).get(TransactionViewModel.class);

        customerViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(CustomerViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyLv = view.findViewById(R.id.historyLv);
        ArrayList<Transaction> transactionList = new ArrayList<>();
        customerViewModel.getCustomerData().observe(getActivity(), customer -> {
            String userId = customer.getUid();

            transactionViewModel.getAlltransactionList(userId).observe(getActivity(), transactions -> {
                transactionList.addAll(transactions);
                HistoryAdapter historyAdapter = new HistoryAdapter(getContext(), R.layout.activity_history_adapter, transactionList);

                historyLv.setAdapter(historyAdapter);
                historyAdapter.notifyDataSetChanged();
                historyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), TransactionDetailActivity.class);
                        intent.putExtra("transaction", transactionList.get(position));
                        startActivity(intent);
                    }
                });
            });
        });

    }
}