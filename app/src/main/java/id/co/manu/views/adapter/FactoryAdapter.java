package id.co.manu.views.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.manu.R;
import id.co.manu.model.Factory;

public class FactoryAdapter extends BaseAdapter {

    private List<Factory> factoryList;
    private List<Factory> filteredFactoryList;
    private Context context;

    public FactoryAdapter(List<Factory> factoryList, Context context) {
        this.factoryList = factoryList;
        this.filteredFactoryList = factoryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filteredFactoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_factory_adapter, null);

        ImageView imageView = view.findViewById(R.id.card_image);
        TextView factoryName = view.findViewById(R.id.card_title);
        TextView factoryCategory = view.findViewById(R.id.card_category);
        TextView factoryLocation = view.findViewById(R.id.card_location);

//        imageView.setImageResource(filteredFactoryList.get(position).getImageUrl()); PAKE PICASSO
        imageView.setImageResource(R.drawable.dummy_image);
        factoryName.setText(filteredFactoryList.get(position).getName());
        factoryCategory.setText(filteredFactoryList.get(position).getCategory());
        factoryLocation.setText(filteredFactoryList.get(position).getAddress());

        return view;
    }
}