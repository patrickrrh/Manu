package id.co.manu.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import id.co.manu.model.Transaction;
import id.co.manu.repository.TransactionRepo;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionRepo transactionRepo;
    private final MutableLiveData<ArrayList<Transaction>> transactionList;
    public TransactionViewModel(@NonNull Application application, Context context) {
        super(application);
        transactionRepo = new TransactionRepo(application, context);
        transactionList = transactionRepo.getTransactionMutableLiveData();
    }

    public MutableLiveData<ArrayList<Transaction>> getTransactionList() {
        return transactionList;
    }

    public MutableLiveData<ArrayList<Transaction>> getAlltransactionList(String userId){
        transactionRepo.getAllTransactionFromFirestore(userId);
        return transactionList;
    }
}
