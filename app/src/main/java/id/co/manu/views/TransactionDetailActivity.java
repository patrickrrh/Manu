package id.co.manu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.co.manu.R;
import id.co.manu.model.Transaction;

public class TransactionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        Transaction transaction = (Transaction) getIntent().getSerializableExtra("transaction");
    }
}