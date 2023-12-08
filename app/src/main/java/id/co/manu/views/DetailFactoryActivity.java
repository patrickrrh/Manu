package id.co.manu.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import id.co.manu.R;
import id.co.manu.model.Factory;
import id.co.manu.viewmodel.CustomViewModelFactory;
import id.co.manu.viewmodel.CustomerViewModel;
import id.co.manu.viewmodel.TransactionViewModel;

public class DetailFactoryActivity extends AppCompatActivity {

    private TransactionViewModel transactionViewModel;
    private CustomerViewModel customerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_factory);

        transactionViewModel = new ViewModelProvider(this, new CustomViewModelFactory(getApplication(), this)).get(TransactionViewModel.class);
        customerViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())).get(CustomerViewModel.class);

        AtomicReference<String> userId = new AtomicReference<>();
        customerViewModel.getCustomerData().observe(this, customer -> {
            userId.set(customer.getUid());
        });

        ImageView backDetailBtn = findViewById(R.id.backDetailBtn);
        ImageView factoryDetailImg = findViewById(R.id.factoryDetailImg);
        Button kirimPengajuanBtn = findViewById(R.id.kirimPengajuanBtn);
        TextView priceDetailTxt = findViewById(R.id.priceDetailTxt);
        TextView categoryDetailTxt = findViewById(R.id.categoryDetailTxt);
        TextView companyNameDetailTxt = findViewById(R.id.companyNameDetailTxt);
        TextView addressDetailTxt = findViewById(R.id.addressDetailTxt);
        TextView descriptionDetailTxt = findViewById(R.id.descriptionDetailTxt);
        ProgressBar progressBar = findViewById(R.id.loadingPesanProgressBar);


        Factory factory = (Factory) getIntent().getSerializableExtra("factory");

        String priceString = factory.getPrice();
        double price = Double.parseDouble(priceString);
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("id", "ID"));
        DecimalFormatSymbols symbols = decimalFormat.getDecimalFormatSymbols();
        symbols.setCurrencySymbol("Rp");
        decimalFormat.setDecimalFormatSymbols(symbols);
        String formattedPrice = decimalFormat.format(price) + "/ bulan";

        Picasso.get().load(factory.getImageUrl()).into(factoryDetailImg);
        priceDetailTxt.setText(formattedPrice);
        categoryDetailTxt.setText(factory.getCategory());
        companyNameDetailTxt.setText(factory.getName());
        addressDetailTxt.setText(factory.getAddress());
        descriptionDetailTxt.setText(factory.getDescription());

        backDetailBtn.setOnClickListener(view -> {
            finish();
        });

        kirimPengajuanBtn.setOnClickListener(onClick ->{
            transactionViewModel.createTransaction(factory, userId.get()).observe(this, isLoading -> {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                kirimPengajuanBtn.setEnabled(!isLoading);
                kirimPengajuanBtn.setText(isLoading ? "" : "Pesan");
                kirimPengajuanBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, isLoading ? R.color.light_grey: R.color.manu_blue)));
            });
        });

    }
}