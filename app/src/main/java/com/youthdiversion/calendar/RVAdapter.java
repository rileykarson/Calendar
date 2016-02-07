package com.youthdiversion.calendar;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by riley on 2016-02-06.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AvailabilityHolder>{

    List<Availability> times;

    RVAdapter(List<Availability> times){
        this.times = times;
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    @Override
    public AvailabilityHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        AvailabilityHolder pvh = new AvailabilityHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AvailabilityHolder personViewHolder, int i) {
        personViewHolder.startTime.setText(times.get(i).getStartTime());
        personViewHolder.endTime.setText(times.get(i).getEndTime());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class AvailabilityHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView startTime;
        TextView endTime;

        AvailabilityHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);

            startTime = (TextView)itemView.findViewById(R.id.textView);
            endTime = (TextView)itemView.findViewById(R.id.textView2);
        }
    }

}