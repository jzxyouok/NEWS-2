package com.android.monews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.monews.R;
import com.android.monews.analysis.ContentList;
import com.android.monews.analysis.Paihp2;
import com.android.monews.utils.PicassoUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/21 0021.
 * <p>
 * Paihp2Activity里面RecyclerView适配器
 */
public class Paigp2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ContentList> contentLists;
    private Paihp2 paihp2;//头部的布局数据
    private Context context;

    private static final int TOP = 1;
    private static final int AAA = 2;

    public Paigp2Adapter(List<ContentList> contentLists, Paihp2 paihp2,Context context) {
        this.contentLists = contentLists;
        this.paihp2 = paihp2;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP;
        }
        return AAA;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TOP) {
            return new TopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_paihp22, parent, false));
        }

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_paihp2_listview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            TopViewHolder topViewHolder = (TopViewHolder) holder;

            topViewHolder.simple.setImageURI(paihp2.getAvatar());
            topViewHolder.tv1.setText(paihp2.getNickname());
            topViewHolder.tv2.setText(paihp2.getDesc());

            if(mOnItemClickLitener!=null){
                topViewHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //mOnItemClickLitener.onItemClick(contentList.getAid());
                    }
                });
            }

        } else if (holder instanceof ViewHolder) {
            final ContentList contentList = contentLists.get(position-1);
            ViewHolder viewHolder = (ViewHolder) holder;
            PicassoUtil.setImageView(context,viewHolder.imageView,contentList.getImgurl());
            viewHolder.tv.setText(contentList.getTitle());

            if(mOnItemClickLitener!=null){
            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(contentList.getAid());
                }
            });
            }
        }
    }

    @Override
    public int getItemCount() {
        return (contentLists.size() + 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;
        public ImageView imageView;
        public TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.paihp22_listview_layout);
            imageView = (ImageView) itemView.findViewById(R.id.listview_image);
            tv = (TextView) itemView.findViewById(R.id.listview_tv);
        }
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout layout;
        public SimpleDraweeView simple;
        public TextView tv1, tv2;

        public TopViewHolder(View itemView) {
            super(itemView);
            layout= (LinearLayout) itemView.findViewById(R.id.paihp22_layout);
            simple = (SimpleDraweeView) itemView.findViewById(R.id.activity_paihp_simple);
            tv1 = (TextView) itemView.findViewById(R.id.activity_paihp_tv1);
            tv2 = (TextView) itemView.findViewById(R.id.activity_paihp_tv2);
        }
    }

    private OnItemClickLitener mOnItemClickLitener;//接口对象

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public interface OnItemClickLitener {//定义一个回调接口
        void onItemClick(String aid);
    }



}
