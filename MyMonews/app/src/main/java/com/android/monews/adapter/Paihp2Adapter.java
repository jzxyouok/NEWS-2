package com.android.monews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.monews.R;
import com.android.monews.analysis.Paihp2;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21 0021.
 *
 * 排行榜里面公众号ListView的专用适配器
 */
public class Paihp2Adapter extends BaseAdapter{
    List<Paihp2> paihb2s;


    public Paihp2Adapter(List<Paihp2> paihb2s){
        this.paihb2s=paihb2s;
    }

    @Override
    public int getCount() {
        return paihb2s==null?0:paihb2s.size();
    }

    @Override
    public Object getItem(int position) {
        return paihb2s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.paihp2,parent,false);
            holder=new ViewHolder();
            holder.simple= (SimpleDraweeView) convertView.findViewById(R.id.paihp2_simple);
            holder.tv1= (TextView) convertView.findViewById(R.id.paihp2_tv1);
            holder.tv2= (TextView) convertView.findViewById(R.id.paihp2_tv2);
            holder.tv3= (TextView) convertView.findViewById(R.id.paihp2_tv3);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        Paihp2 paihp2=paihb2s.get(position);

        holder.simple.setImageURI(paihp2.getAvatar());

        holder.tv1.setText((position+1) + "");

        if(position==0 || position==1 || position==2){
            holder.tv1.setTextColor(parent.getContext().getResources().getColor(R.color.main_text_yes));
        }

        holder.tv2.setText(paihp2.getNickname());
        holder.tv3.setText(paihp2.getDesc());

        return convertView;
    }



    class ViewHolder{
        public SimpleDraweeView simple;
        public TextView tv1,tv2,tv3;
    }
}
