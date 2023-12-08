package id.co.manu.repository;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import id.co.manu.database.DatabaseHelper;
import id.co.manu.model.Factory;
import id.co.manu.model.Transaction;

public class TransactionRepo {
    private final Application application;
    private final DatabaseHelper db;
    private final FirebaseFirestore firestore;
    private final MutableLiveData<Boolean> loadingStateLiveData;
    private final MutableLiveData<ArrayList<Transaction>> transactionListMutableLiveData;

    public TransactionRepo(Application application, Context context) {
        this.application = application;
        transactionListMutableLiveData = new MutableLiveData<>();
        loadingStateLiveData = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
        db = new DatabaseHelper(context);
    }

    public MutableLiveData<Boolean> getLoadingStateLiveData() {
        return loadingStateLiveData;
    }

    public MutableLiveData<ArrayList<Transaction>> getTransactionMutableLiveData() {
        return transactionListMutableLiveData;
    }

    public void getAllTransactionFromFirestore(String userId){
        firestore.collection("transactions").whereEqualTo("userId", userId).get().addOnCompleteListener(task->{
            if (task.isSuccessful()) {
                ArrayList<Transaction> transactionList = new ArrayList<>();
                QuerySnapshot snapshot = task.getResult();
                for(QueryDocumentSnapshot doc : snapshot){
                    Transaction transaction = new Transaction();
                    transaction.setId(doc.getId());
                    transaction.setUserId(doc.getString("userId"));
                    transaction.setName(doc.getString("name"));
                    transaction.setCategory(doc.getString("category"));
                    transaction.setAddress(doc.getString("address"));
                    transaction.setQuantity(doc.getString("quantity"));
                    transaction.setTotalPrice(doc.getString("totalPrice"));
                    transaction.setTransactionDate(doc.getString("transactionDate"));
                    transaction.setImageUrl(doc.getString("imageUrl"));
                    transactionList.add(transaction);
                }
                Log.d("ZZZZ", "" +transactionList.size());
                transactionListMutableLiveData.postValue(transactionList);

            } else {
                Toast.makeText(application, "Gagal! Memuat data offline...", Toast.LENGTH_LONG).show();
                getAllTransactionFromSQLite(userId);
            }
        });
    }

    public void getAllTransactionFromSQLite(String userId){
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        Cursor cursor = db.getAllTransaction(userId);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Transaction transaction = new Transaction();
                transaction.setId(cursor.getString(0));
                transaction.setUserId(cursor.getString(1));
                transaction.setName(cursor.getString(2));
                transaction.setCategory(cursor.getString(3));
                transaction.setAddress(cursor.getString(4));
                transaction.setQuantity(cursor.getString(5));
                transaction.setTotalPrice(cursor.getString(6));
                transaction.setTransactionDate(cursor.getString(7));
                transactionArrayList.add(transaction);
            }
        }
        transactionListMutableLiveData.postValue(transactionArrayList);
    }

    public void createTransaction(Transaction transaction){
        loadingStateLiveData.postValue(true);
        firestore.collection("transactions").document().set(transaction).addOnCompleteListener(task->{
            loadingStateLiveData.postValue(false);
            if(!task.isSuccessful()){
                Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(application, "Pesanan berhasil dibuat", Toast.LENGTH_LONG).show();
            }
        });
    }

}
