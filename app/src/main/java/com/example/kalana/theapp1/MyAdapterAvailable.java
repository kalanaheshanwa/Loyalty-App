package com.example.kalana.theapp1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class MyAdapterAvailable extends RecyclerView.Adapter<MyAdapterAvailable.ViewHolder> {

    private List<ListItemAvailable> usermodel;
    private Context context;

    public MyAdapterAvailable(List<ListItemAvailable> usermodel, Context context) {
        this.usermodel = usermodel;
        this.context = context;
    }

    @Override
    public MyAdapterAvailable.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item_available,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapterAvailable.ViewHolder holder, int position) {

        //bind the actual data to recycle view

        final ListItemAvailable usethis=usermodel.get(position);
        holder.tvItemAv.setText(usethis.getTvItemAv());
        holder.tvPresentAv.setText(usethis.getTvPresentAv());
        holder.tvDetailAv.setText(usethis.getTvDetailAv());
        holder.tvStartAv.setText(usethis.getTvStartAv());
        holder.tvEndAv.setText(usethis.getTvEndAv());



    }

    @Override
    public int getItemCount() {
        return usermodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //  View mvView;

        private TextView tvItemAv;
        private TextView tvPresentAv;
        private TextView tvDetailAv;
        private TextView tvStartAv;
        private TextView tvEndAv;
        private LinearLayout linearlayoutAvalible;


        public ViewHolder(View itemView) {
            super(itemView);

            // mvView=itemView;

            tvItemAv = (TextView)itemView.findViewById(R.id.tvItemAv);
            tvPresentAv = (TextView)itemView.findViewById(R.id.tvPresentAv);
            tvDetailAv = (TextView)itemView.findViewById(R.id.tvDetailAv);
            tvStartAv = (TextView)itemView.findViewById(R.id.tvStartAv);
            tvEndAv = (TextView)itemView.findViewById(R.id.tvEndAv);
            linearlayoutAvalible = (LinearLayout)itemView.findViewById(R.id.linearlayoutAvalible);
        }
    }

    }
