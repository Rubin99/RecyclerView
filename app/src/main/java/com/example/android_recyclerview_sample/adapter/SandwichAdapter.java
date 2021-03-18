package com.example.android_recyclerview_sample.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_recyclerview_sample.DetailActivity;
import com.example.android_recyclerview_sample.R;
import com.example.android_recyclerview_sample.model.Sandwich;

import java.util.ArrayList;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.ViewHolder> {

    private Context context;
    public SandwichAdapter(Context context){
        this.context = context;
    }

    ArrayList<Sandwich> data = new ArrayList<>();

    public void setData(ArrayList<Sandwich> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_sandwich, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sandwich item = data.get(position);
        holder.textName.setText(item.getMainName());
        holder.textDescription.setText(item.getDescription());

        Glide.with(holder.imageView.getContext())
                .load(item.getImage())
                .apply(new RequestOptions().transform(new CenterCrop(),new RoundedCorners(16))).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private ImageView imageView;
        private TextView textName;
        private TextView textDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Sandwich element = data.get(mPosition);

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("EXTRA", element);
            context.startActivity(intent);
        }
    }
}

