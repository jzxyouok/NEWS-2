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
import com.android.monews.utils.PicassoUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19 0019.
 *
 * 首页RecyclerView的适配器，实现多布局
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ContentList> contentLists;//数据集合
    private Context context;
    private LayoutInflater inflater;

    private static final int A=1;
    private static final int B=2;
    private static final int C=3;

    public HomeRecyclerAdapter(Context context,List<ContentList> contentLists){
        this.contentLists=contentLists;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override//返回的int值传到了onCreateViewHolder方法
    public int getItemViewType(int position) {
        ContentList contentList=contentLists.get(position);
        List<String>list=contentList.getImgList();

        if(contentList.getShowType().equals("cover")){//一张小图片
           return A;
        }else if(contentList.getShowType().equals("small")){//多张小图片
            return B;
        }else if(contentList.getShowType().equals("big")){//一张大图片
            return C;
        }

        return A;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==A){
           return new Recycler1ViewHolder(inflater.inflate(R.layout.home_recycler1,parent,false));
       }else if(viewType==B){
           return new Recycler2ViewHolder(inflater.inflate(R.layout.home_recycler2,parent,false));
       }else if(viewType==C){
            return new Recycler3ViewHolder(inflater.inflate(R.layout.home_recycler3,parent,false));
        }

        return null;
    }

    Recycler1ViewHolder holder1;
    Recycler2ViewHolder holder2;
    Recycler3ViewHolder holder3;
    RecyclerView.ViewHolder holder;
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewholder, int position) {
        final ContentList contentList=contentLists.get(position);

        if(viewholder instanceof Recycler1ViewHolder){
             holder1= (Recycler1ViewHolder) viewholder;

            PicassoUtil.setImageView(context,holder1.imageView,contentList.getImgurl());
            holder1.tv1.setText(contentList.getTitle());
            holder1.tv2.setText(contentList.getNickname());
            holder1.tv3.setText("阅读 "+contentList.getReadNum());


            if(mOnItemClickLitener!=null){//接口对象不为空
                //给布局中最外面的线性布局添加添加事件
                holder1.recycler1_Layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把当前的对象的aid属性值作为参数传会自己的方法
                        mOnItemClickLitener.onItemClick(contentList.getAid());
                    }
                });

            }


        }else if(viewholder instanceof Recycler2ViewHolder){
             holder2= (Recycler2ViewHolder) viewholder;

          /*  LinearLayout.LayoutParams params//动态设置权重
                    =new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1);
            String url;*/
          /*  for (int i = 0; i <list.size() ; i++) {
                url=list.get(i);
                ImageView imageView=new ImageView(context);
                PicassoUtil.setImageView(context,imageView,url);
                imageView.setLayoutParams(params);
                holder.layout.addView(imageView);//添加到线性布局里面
            }*/

            List<String>list=contentList.getImgList();

            PicassoUtil.setImageView(context, holder2.image1, list.get(0));
            PicassoUtil.setImageView(context,holder2.image2,list.get(1));
            PicassoUtil.setImageView(context,holder2.image3,list.get(2));
            holder2.tv1.setText(contentList.getTitle());
            holder2.tv2.setText(contentList.getNickname());
            holder2.tv3.setText(contentList.getReadNum());


            if(mOnItemClickLitener!=null){//接口对象不为空
                //给布局中最外面的线性布局添加添加事件
                holder2.recycler2_Layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把当前的对象的aid属性值作为参数传会自己的方法
                        mOnItemClickLitener.onItemClick(contentList.getAid());
                    }
                });

            }


        }else if(viewholder instanceof Recycler3ViewHolder){
            holder3= (Recycler3ViewHolder) viewholder;
            PicassoUtil.setImageView(context,holder3.image,contentList.getImgurl());
            holder3.tv1.setText(contentList.getTitle());
            holder3.tv2.setText(contentList.getNickname());
            holder3.tv3.setText(contentList.getReadNum());


            if(mOnItemClickLitener!=null){//接口对象不为空
                //给布局中最外面的线性布局添加添加事件
                holder3.recycler3_Layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //把当前的对象的aid属性值作为参数传会自己的方法
                        mOnItemClickLitener.onItemClick(contentList.getAid());
                    }
                });

            }


        }

    }

    @Override
    public int getItemCount() {
        return contentLists.size();
    }

    //继承RecyclerView.ViewHolder类，打造自己的控件打包类()
    public  class Recycler1ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout recycler1_Layout;
        public ImageView imageView;
        public TextView tv1,tv2,tv3;//控件

        public Recycler1ViewHolder(View itemView) {
            super(itemView);//找出控件
            recycler1_Layout = (LinearLayout) itemView.findViewById(R.id.recycler1_Layout);
            imageView = (ImageView) itemView.findViewById(R.id.recycler1_image);
            tv1 = (TextView) itemView.findViewById(R.id.recycler1_tv1);
            tv2 = (TextView) itemView.findViewById(R.id.recycler1_tv2);
            tv3 = (TextView) itemView.findViewById(R.id.recycler1_tv3);
        }
    }

    public  class Recycler2ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout recycler2_Layout;
        public TextView tv1,tv2,tv3;
        public LinearLayout layout;//线性布局(需要动态去添加图片控件)暂时没有成功
        public ImageView image1,image2,image3;
        public Recycler2ViewHolder(View itemView) {
            super(itemView);
            recycler2_Layout = (LinearLayout) itemView.findViewById(R.id.recycler2_Layout);
            tv1 = (TextView) itemView.findViewById(R.id.recycler2_tv1);
            tv2 = (TextView) itemView.findViewById(R.id.recycler2_tv2);
            tv3 = (TextView) itemView.findViewById(R.id.recycler2_tv3);
            layout = (LinearLayout) itemView.findViewById(R.id.recycler2_layout);
            image1= (ImageView) itemView.findViewById(R.id.recycler2_image1);
            image2= (ImageView) itemView.findViewById(R.id.recycler2_image2);
            image3= (ImageView) itemView.findViewById(R.id.recycler2_image3);

        }
    }



    public  class Recycler3ViewHolder extends RecyclerView.ViewHolder{
       public LinearLayout recycler3_Layout;
       public TextView tv1,tv2,tv3;
        public ImageView image;
        public Recycler3ViewHolder(View itemView) {
            super(itemView);
            recycler3_Layout = (LinearLayout) itemView.findViewById(R.id.recycler3_Layout);
            tv1 = (TextView) itemView.findViewById(R.id.recycler3_tv1);
            tv2 = (TextView) itemView.findViewById(R.id.recycler3_tv2);
            tv3 = (TextView) itemView.findViewById(R.id.recycler3_tv3);
            image= (ImageView) itemView.findViewById(R.id.recycler3_image);
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
