package com.example.wgapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EinkauaflistAdapter extends ArrayAdapter<Einkauf> {

    public  EinkauaflistAdapter(Context context, ArrayList<Einkauf> meineEinkaeufe){
        super(context,0,meineEinkaeufe);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Einkauf einkauf= getItem( position );

        if(convertView==null){
            convertView= LayoutInflater.from( getContext()).inflate( R.layout.layout_add_einkauf, parent, false);

        }
        TextView ware=(TextView)convertView.findViewById( R.id.set_ware );
        TextView menge= (TextView)convertView.findViewById( R.id.set_menge );

    ware.setText( einkauf.getArtikel() );
    menge.setText( einkauf.getMenge() );

return convertView;
    }

}
