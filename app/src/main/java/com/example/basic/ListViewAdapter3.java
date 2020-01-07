package com.example.basic;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter3 extends BaseAdapter{
    private ArrayList<ListViewItem3> listViewItemList = new ArrayList<ListViewItem3>();

    public ListViewAdapter3() {
    }

    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_else2_listitem, parent, false);
        }

        ImageView iconImageView = (ImageView) convertView.findViewById(R.id.iconItem);
        TextView rankTextView = (TextView) convertView.findViewById(R.id.dataItem01);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.dataItem02);
        TextView tierTextView = (TextView) convertView.findViewById(R.id.dataItem03);
        TextView nicknameTextView = (TextView) convertView.findViewById(R.id.dataItem04);

        ListViewItem3 listViewItem = listViewItemList.get(position);

        iconImageView.setImageDrawable(listViewItem.getIcon());
        rankTextView.setText(listViewItem.getrank());
        nameTextView.setText(listViewItem.getname());
        tierTextView.setText(listViewItem.gettier());
        nicknameTextView.setText(listViewItem.getnickname());

        return convertView;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position){
        return listViewItemList.get(position);
    }

    public void addItem(Drawable icon, String rank, String name, String tier, String nickname){
        ListViewItem3 item = new ListViewItem3();

        item.setIcon(icon);
        item.setrank(rank);
        item.setname(name);
        item.settier(tier);
        item.setnickname(nickname);

        listViewItemList.add(item);
    }
}
