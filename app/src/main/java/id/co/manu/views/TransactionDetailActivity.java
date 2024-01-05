package id.co.manu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import id.co.manu.R;
import id.co.manu.model.Transaction;

public class TransactionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        ImageView backTransactionDetailBtn = findViewById(R.id.backTransactionDetailBtn);
        backTransactionDetailBtn.setOnClickListener(onClick->{
            finish();
        });

        TextView orderIdTxt = findViewById(R.id.orderIdTxt);
        TextView transactionDateTxt = findViewById(R.id.transactionDateTxt);
        TextView factoryNameTxt = findViewById(R.id.factoryNameTxt);
        TextView totalPriceTxt = findViewById(R.id.totalPriceTxt);
        TextView grandTotalTxt = findViewById(R.id.grandTotalTxt);

        Transaction transaction = (Transaction) getIntent().getSerializableExtra("transaction");
        orderIdTxt.setText(transaction.getId());
        transactionDateTxt.setText(transaction.getTransactionDate());
        factoryNameTxt.setText(transaction.getName());

        String grandTotalPrice = transaction.getTotalPrice();
        double price = Double.parseDouble(grandTotalPrice);
        double priceNew = Double.parseDouble(grandTotalPrice) + 15000;
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("id", "ID"));
        DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("Rp");
        decimalFormat.setDecimalFormatSymbols(symbols);
        String formattedGrandTotalPrice = decimalFormat.format(priceNew);
        String formattedTotalPrice = decimalFormat.format(price);

        totalPriceTxt.setText(formattedTotalPrice);
        grandTotalTxt.setText(formattedGrandTotalPrice);
    }
}