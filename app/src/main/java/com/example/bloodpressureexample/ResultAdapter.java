package com.example.bloodpressureexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.text.Format;
import java.text.SimpleDateFormat;

public class ResultAdapter extends FirestoreRecyclerAdapter<BloodPressureResult, ResultAdapter.ViewHolder> {

    public ResultAdapter(@NonNull FirestoreRecyclerOptions<BloodPressureResult> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull BloodPressureResult bloodPressureResult) {
        Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");


        viewHolder.sys.setText(Integer.toString(bloodPressureResult.getSys()));
        viewHolder.dys.setText(Integer.toString(bloodPressureResult.getDys()));
        viewHolder.pulse.setText(Integer.toString(bloodPressureResult.getPulse()));
        viewHolder.dateText.setText(formatter.format(bloodPressureResult.getTimestamp()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView sys;
        TextView dys;
        TextView pulse;
        TextView dateText;

        public ViewHolder(View v) {
            super(v);
            sys = v.findViewById(R.id.list_item_sys);
            dys = v.findViewById(R.id.list_item_dys);
            pulse = v.findViewById(R.id.list_item_pulse);
            dateText = v.findViewById(R.id.list_item_date);
        }
    }
}
