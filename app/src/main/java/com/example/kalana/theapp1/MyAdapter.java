package com.example.kalana.theapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ListItem> usermodel;
    private Context context;

    public MyAdapter(List<ListItem> usermodel, Context context) {
        this.usermodel = usermodel;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        //bind the actual data to recycle view

        final ListItem usethis=usermodel.get(position);
        holder.tvItemAv.setText(usethis.getTvItemAv());
        holder.tvPresentAv.setText(usethis.getTvPresentAv());
        holder.tvDetailAv.setText(usethis.getTvDetailAv());
        holder.tvStartAv.setText(usethis.getTvStartAv());
        holder.tvEndAv.setText(usethis.getTvEndAv());
        holder.tvItemCode.setText(usethis.getTvItemCode());
        holder.tvPromoId.setText(usethis.getTvPromoId());

        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(),Comment.class);
                intent.putExtra("promoId", usethis.getTvPromoId());
                view.getContext().startActivity(intent);

            }
        });

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
        private TextView tvItemCode;
        private TextView tvPromoId;
        private LinearLayout linearlayout;


        public ViewHolder(View itemView) {
            super(itemView);

            // mvView=itemView;

            tvItemAv = (TextView)itemView.findViewById(R.id.tvItemAv);
            tvPresentAv = (TextView)itemView.findViewById(R.id.tvPresentAv);
            tvDetailAv = (TextView)itemView.findViewById(R.id.tvDetailAv);
            tvStartAv = (TextView)itemView.findViewById(R.id.tvStartAv);
            tvEndAv = (TextView)itemView.findViewById(R.id.tvEndAv);
            tvItemCode = (TextView)itemView.findViewById(R.id.tvItemCode);
            tvPromoId = (TextView)itemView.findViewById(R.id.tvPromoId);
            linearlayout = (LinearLayout)itemView.findViewById(R.id.linearlayout);
        }
    }
}

