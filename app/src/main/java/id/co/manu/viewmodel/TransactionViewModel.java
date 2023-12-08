package id.co.manu.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import id.co.manu.model.Factory;
import id.co.manu.model.Transaction;
import id.co.manu.repository.TransactionRepo;

public class TransactionViewModel extends AndroidViewModel {
    private final TransactionRepo transactionRepo;
    private final MutableLiveData<ArrayList<Transaction>> transactionList;
    private final MutableLiveData<Boolean> loadingStateLiveData;
    public TransactionViewModel(@NonNull Application application, Context context) {
        super(application);
        transactionRepo = new TransactionRepo(application, context);
        transactionList = transactionRepo.getTransactionMutableLiveData();
        loadingStateLiveData = transactionRepo.getLoadingStateLiveData();
    }

    public MutableLiveData<ArrayList<Transaction>> getAlltransactionList(String userId){
        transactionRepo.getAllTransactionFromFirestore(userId);
        return transactionList;
    }

    public MutableLiveData<Boolean> createTransaction(Factory factory, String userId){

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Transaction transaction = new Transaction();
        transaction.setImageUrl(factory.getImageUrl());
        transaction.setTotalPrice(factory.getPrice());
        transaction.setQuantity("100");
        transaction.setAddress(factory.getAddress());
        transaction.setUserId(userId);
        transaction.setName(factory.getName());
        transaction.setCategory(factory.getCategory());
        transaction.setTransactionDate(dateFormat.format(currentDate));
        transactionRepo.createTransaction(transaction);
        return loadingStateLiveData;
    }
}
