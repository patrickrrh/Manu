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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.co.manu.R;
import id.co.manu.model.Factory;

public class FactoryAdapter extends BaseAdapter implements Filterable {

    private List<Factory> factoryList;
    private List<Factory> filteredFactoryList;
    private Context context;
    private String selectedCategory = "";
    private String selectedLocation = "";

    public FactoryAdapter(List<Factory> factoryList, Context context) {
        this.factoryList = factoryList;
        this.filteredFactoryList = factoryList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filteredFactoryList.size();
    }

    public void setFactoryList(List<Factory> factoryList) {
        this.factoryList = factoryList;
        this.filteredFactoryList = factoryList;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return filteredFactoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.activity_factory_adapter, null);

        ImageView imageView = convertView.findViewById(R.id.card_image);
        TextView factoryName = convertView.findViewById(R.id.card_title);
        TextView factoryCategory = convertView.findViewById(R.id.card_category);
        TextView factoryLocation = convertView.findViewById(R.id.card_location);

        Picasso.get().load(filteredFactoryList.get(position).getImageUrl()).into(imageView);
        factoryName.setText(filteredFactoryList.get(position).getName());
        factoryCategory.setText(filteredFactoryList.get(position).getCategory());
        factoryLocation.setText(filteredFactoryList.get(position).getAddress());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.count = factoryList.size();
                    filterResults.values = factoryList;
                } else {
                    String searchString = charSequence.toString().toLowerCase();
                    List<Factory> resultData = new ArrayList<>();

                    for (Factory factory : factoryList) {
                        if (factory.getName().toLowerCase().startsWith(searchString)) {
                            resultData.add(factory);
                        }
                    }

                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredFactoryList = (List<Factory>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

}