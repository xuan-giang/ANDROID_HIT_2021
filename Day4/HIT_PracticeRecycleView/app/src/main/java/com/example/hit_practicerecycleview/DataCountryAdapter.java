package com.example.hit_practicerecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class DataCountryAdapter extends RecyclerView.Adapter<DataCountryAdapter.ViewHolder> implements Filterable {
    List<DataCountry> list;
    List<DataCountry> listFull ;
    Context context;
    Boolean isDark = false;

    public DataCountryAdapter(List<DataCountry> list, Context context, Boolean isDark) {
        this.list = list;
        this.listFull = new ArrayList<>(list);
        this.context = context;
        this.isDark = isDark;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.covidnews, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataCountryAdapter.ViewHolder holder, int position) {
        holder.Acontainer.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.imgFlag.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        holder.tvNameCountry.setText(list.get(position).getNameCountry());
        holder.imgFlag.setImageResource(list.get(position).getImage());
        holder.tvRecovered.setText(list.get(position).getNumberOfRecovered());
        holder.tvDeaths.setText(list.get(position).getNumberOfDeath());
        holder.tvConfirmed.setText(list.get(position).getNumberOfConfirmed());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataCountry> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(listFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(DataCountry item : listFull){
                    if (item.getNameCountry().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFlag;
        TextView tvNameCountry, tvDeaths, tvRecovered, tvConfirmed;
        RelativeLayout Acontainer;
        public ViewHolder(View itemView) {
            super(itemView);
            Acontainer=itemView.findViewById(R.id.container);
            imgFlag = itemView.findViewById(R.id.nationalFlag);
            tvConfirmed = itemView.findViewById(R.id.numberConfirmed);
            tvDeaths = itemView.findViewById(R.id.numberDeaths);
            tvRecovered = itemView.findViewById(R.id.numberRecovered);
            tvNameCountry = itemView.findViewById(R.id.nameOfCountry);
            if(isDark){
                setDarkTheme();
            }
        }
        private void setDarkTheme(){
            Acontainer.setBackgroundResource(R.drawable.card_bg_dark);
        }
    }

}
