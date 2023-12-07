package id.co.manu.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import id.co.manu.R;

public class DetailFactoryActivity extends AppCompatActivity {

    private ImageView backDetailBtn;
    private Button kirimPengajuanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_factory);

        backDetailBtn = findViewById(R.id.backDetailBtn);
        backDetailBtn.setOnClickListener(view -> {
            finish();
        });
    }
}