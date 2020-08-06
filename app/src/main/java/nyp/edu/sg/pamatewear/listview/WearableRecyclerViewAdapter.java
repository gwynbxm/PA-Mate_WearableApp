/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.listview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nyp.edu.sg.pamatewear.R;
import nyp.edu.sg.pamatewear.model.ExercisePlan;

/**
 * Created by GBXM on 8/2/2018.
 */

public class WearableRecyclerViewAdapter extends RecyclerView.Adapter<WearableRecyclerViewAdapter.ViewHolder> {

    private RecyclerViewItemClickListener recyclerViewItemClickListener;
    private ArrayList<ExercisePlan> exerciseItems;
    private Context context;

    public WearableRecyclerViewAdapter(ArrayList<ExercisePlan> exerciseItems, Context context) {
        this.exerciseItems = exerciseItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView exPlanName;
        private TextView exPlanDate;
        private ImageView imgIcon;

        ViewHolder(View view) {
            super(view);
            exPlanName = (TextView) view.findViewById(R.id.tvExercisePlanName);
            exPlanDate = (TextView) view.findViewById(R.id.tvPlanDate);
            imgIcon = (ImageView) view.findViewById(R.id.exercise_icon);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (exerciseItems != null && !exerciseItems.isEmpty()) {
            if (exerciseItems.get(position).getPlanName().contains("Cycle")) {
                holder.imgIcon.setImageResource(R.drawable.ic_cycling);
            } else if (exerciseItems.get(position).getPlanName().contains("Run")) {
                holder.imgIcon.setImageResource(R.drawable.ic_running);
            } else if (exerciseItems.get(position).getPlanName().contains("Swim")) {
                holder.imgIcon.setImageResource(R.drawable.ic_swimming);
            } else if (exerciseItems.get(position).getPlanName().contains("Walk")) {
                holder.imgIcon.setImageResource(R.drawable.ic_walking);
            }
            holder.exPlanName.setText(exerciseItems.get(position).getPlanName());
            holder.exPlanDate.setText(exerciseItems.get(position).getPlanStartDate());
        }
    }

    @Override
    public int getItemCount() {
        if (exerciseItems != null && !exerciseItems.isEmpty()) {
            return exerciseItems.size();
        }
        return 0;
    }

    public interface RecyclerViewItemClickListener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickListener(final RecyclerViewItemClickListener listener) {
        this.recyclerViewItemClickListener = listener;
    }
}
