/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.listview;

import android.content.Context;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import nyp.edu.sg.pamatewear.R;
import nyp.edu.sg.pamatewear.model.ExercisePlan;

/**
 * Created by GBXM on 12/12/2017.
 */

public class WearableAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ExercisePlan> exerciseItems;
    int resource;

    public WearableAdapter(Context context, int resource, ArrayList<ExercisePlan> items) {
        this.context = context;
        this.resource = resource;
        this.exerciseItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ExercisePlan exercisePlanList = this.exerciseItems.get(position);

        WearableAdapterViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);

            viewHolder = new WearableAdapterViewHolder();

            viewHolder.exPlanName = convertView.findViewById(R.id.tvPlanName);
            viewHolder.exPlanDate = convertView.findViewById(R.id.tvPlanDate);
        } else {
            viewHolder = (WearableAdapterViewHolder) convertView.getTag();
        }

        viewHolder.exPlanName.setText(exercisePlanList.getPlanName());
        viewHolder.exPlanDate.setText(exercisePlanList.getPlanStartDate());
        return convertView;
    }

    @Override
    public int getCount() {
        return exerciseItems.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class WearableAdapterViewHolder {
        TextView exPlanName;
        TextView exPlanDate;
    }
}
