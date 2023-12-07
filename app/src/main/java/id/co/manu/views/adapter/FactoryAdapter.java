package id.co.manu.views.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import id.co.manu.R;
import id.co.manu.model.Factory;

public class FactoryAdapter extends ArrayAdapter<Factory> {

    private Context context;
    private int resource;


    public FactoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Factory> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {




    }
}