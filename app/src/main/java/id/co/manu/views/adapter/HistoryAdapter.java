package id.co.manu.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.co.manu.R;
import id.co.manu.model.Transaction;

public class HistoryAdapter extends ArrayAdapter<Transaction> {
    private Context context;
    private int resource;

    public HistoryAdapter(@NonNull Context context, int resource, ArrayList<Transaction> transactionList) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String category = getItem(position).getCategory();
        String address = getItem(position).getAddress();
        String imageUrl = getItem(position).getImageUrl();

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(resource, parent, false);

        TextView nameTxt = (TextView) convertView.findViewById(R.id.historyTitle);
        TextView categoryTxt = (TextView) convertView.findViewById(R.id.historyCategory);
        TextView addressTxt = (TextView) convertView.findViewById(R.id.historyAddress);
        ImageView imageImg = (ImageView) convertView.findViewById(R.id.transactionImg);

        nameTxt.setText(name);
        categoryTxt.setText(category);
        addressTxt.setText(address);
        Picasso.get().load(imageUrl).into(imageImg);

        return convertView;
    }
}
