package com.example.androidgeolocator;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Establishment> {
    private List<Establishment> establishmentList;


    private Context mCtx;

    //list and context
    public ListViewAdapter(List<Establishment> establishmentList, Context mCtx) {
        super(mCtx, R.layout.list_items, establishmentList);
        this.establishmentList = establishmentList;
        this.mCtx = mCtx;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text and image views
        TextView businessname = (TextView) listViewItem.findViewById(R.id.BusinessName);
        TextView postcode =(TextView) listViewItem.findViewById(R.id.Postcode);
        TextView AddressLine1 = (TextView) listViewItem.findViewById(R.id.AddressLine1);
        TextView AddressLine2 = (TextView) listViewItem.findViewById(R.id.AddressLine2);
        ImageView imageview = listViewItem.findViewById(R.id.logo);
        //Getting the hero for the specified position
        Establishment establishment = establishmentList.get(position);

        //setting values to textviews

        businessname.setText(establishment.BusinessName());
        postcode.setText(establishment.PostCode());
        AddressLine1.setText(establishment.AddressLine1());
        AddressLine2.setText(establishment.AddressLine2());


        //fill the list view with the corresponding jpgrating/stringrating
        if (establishment.RatingValue.equals("1")){
            imageview.setImageResource(R.drawable.rating_1);
        } else if (establishment.RatingValue.equals("2")){
            imageview.setImageResource(R.drawable.rating_2);
        } else if (establishment.RatingValue.equals("3")){
            imageview.setImageResource(R.drawable.rating_3);
        } else if (establishment.RatingValue.equals("4")){
            imageview.setImageResource(R.drawable.rating_4);
        } else if (establishment.RatingValue.equals("5")){
            imageview.setImageResource(R.drawable.rating_5);
        } else if (establishment.RatingValue.equals("0")){
            imageview.setImageResource(R.drawable.rating6);
        } else if (establishment.RatingValue.equals("-1")){
            imageview.setImageResource(R.drawable.rating_e);
        }

        if (establishment.AddressLine1== null || establishment.AddressLine1.equals("")) {

        }

        if (establishment.AddressLine2== null || establishment.AddressLine2.equals("")) {
            // null
        }
        //returning the listitem
        return listViewItem;
    }

}
