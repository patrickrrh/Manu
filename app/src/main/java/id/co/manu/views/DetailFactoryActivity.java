package id.co.manu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import id.co.manu.R;
import id.co.manu.model.Factory;

public class DetailFactoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_factory);

        ImageView backDetailBtn = findViewById(R.id.backDetailBtn);
        ImageView factoryDetailImg = findViewById(R.id.factoryDetailImg);
        Button kirimPengajuanBtn = findViewById(R.id.kirimPengajuanBtn);
        TextView priceDetailTxt = findViewById(R.id.priceDetailTxt);
        TextView categoryDetailTxt = findViewById(R.id.categoryDetailTxt);
        TextView companyNameDetailTxt = findViewById(R.id.companyNameDetailTxt);
        TextView addressDetailTxt = findViewById(R.id.addressDetailTxt);
        TextView descriptionDetailTxt = findViewById(R.id.descriptionDetailTxt);


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
    }
}