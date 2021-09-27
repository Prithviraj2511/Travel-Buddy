package com.example.touristguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.Viewholder> {

    private Context context;
    private ArrayList<LocationModel> locationModelArrayList;

    // Constructor
    public LocationAdapter(Context context, ArrayList<LocationModel> locationModelArrayList) {
        this.context = context;
        this.locationModelArrayList = locationModelArrayList;
    }

    @NonNull
    @Override
    public LocationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        LocationModel model = locationModelArrayList.get(position);
        holder.locationNameTV.setText(model.getLocation_name());
        holder.locationRatingTV.setText("" + model.getLocation_rating());
        holder.locationDescTV.setText(model.getLocation_desc());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return locationModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView locationNameTV, locationRatingTV,locationDescTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            locationDescTV = itemView.findViewById(R.id.idTVLocationDesc);
            locationNameTV = itemView.findViewById(R.id.idTVLocationName);
            locationRatingTV = itemView.findViewById(R.id.idTVLocationRating);
        }
    }
}
