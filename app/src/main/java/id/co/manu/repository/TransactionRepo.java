package id.co.manu.repository;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;

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
    private final MutableLiveData<ArrayList<Transaction>> transactionListMutableLiveData;

    public TransactionRepo(Application application, Context context) {
        this.application = application;
        transactionListMutableLiveData = new MutableLiveData<>();
        firestore = FirebaseFirestore.getInstance();
        db = new DatabaseHelper(context);
    }

    public MutableLiveData<ArrayList<Transaction>> getTransactionMutableLiveData() {
        return transactionListMutableLiveData;
    }

    public void getAllTransactionFromFirestore(String userId){
        firestore.collection("transaction").whereEqualTo("userId", userId).get().addOnCompleteListener(task->{
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
                    transactionList.add(transaction);
                }
                transactionListMutableLiveData.postValue(transactionList);

            } else {
                //IF GETTING FROM FIREBASE NOT SUCCESS
                //THEN AMBIL DATA DARI SQLITE
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
}
