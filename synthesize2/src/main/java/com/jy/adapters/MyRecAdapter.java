package com.jy.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.synthesize2.R;
import com.jy.beans.ResultsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<ResultsBean> bannerlist = new ArrayList<>();
    ArrayList<ResultsBean> list = new ArrayList<>();

    public MyRecAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==0){
            View view = View.inflate(context, R.layout.banner_layout, null);
            return new ViewHolder1(view);
        }else{
            View view = View.inflate(context, R.layout.rec_layout, null);
            return new ViewHolder2(view);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType==0){
            ViewHolder1 holder1 = (ViewHolder1) viewHolder;
            holder1.myBanner.setImages(bannerlist).setImageLoader(new MyLoader()).start();
        }else{
            ViewHolder2 holder2 = (ViewHolder2) viewHolder;
            if (bannerlist.size()>0){
                i=i-1;
            }
            holder2.rec_tv.setText(list.get(i).getDesc());
            if (i%2==0){
                RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop());
                Glide.with(context)
                        .load(list.get(i).getUrl())
                        .apply(requestOptions)
                        .into(holder2.rec_img);
            }else{
                RoundedCorners roundedCorners = new RoundedCorners(20);
                RequestOptions override = RequestOptions.bitmapTransform(roundedCorners).override(80, 80);
                Glide.with(context)
                        .load(list.get(i).getUrl())
                        .apply(override)
                        .into(holder2.rec_img);

            }
            final int finalI1 = i;
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onLongClickListener(finalI1);
                    return false;
                }
            });

        }
        final int finalI = i;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClickListener(finalI);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (bannerlist.size()>0){
            return list.size()+1;
        }else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (bannerlist.size()>0&&position==0){
            return 0;
        }else{
            return 1;
        }
    }

    public void addData(List<ResultsBean> results) {
        if (list!=null){
            list.clear();
            list.addAll(results);
            notifyDataSetChanged();
        }
    }

    public void addDatas(List<ResultsBean> results) {
        if (bannerlist!=null){
            bannerlist.clear();
            bannerlist.addAll(results);
            notifyDataSetChanged();
        }
    }

    public void delete(int a) {
        if (list!=null){
            list.remove(a);
            notifyDataSetChanged();
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{

        private Banner myBanner;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            myBanner = itemView.findViewById(R.id.myBanner);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{

        private ImageView rec_img;
        private TextView rec_tv;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            rec_img = itemView.findViewById(R.id.rec_img);
            rec_tv = itemView.findViewById(R.id.rec_tv);
        }
    }

    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ResultsBean resultsBean= (ResultsBean) path;
            Glide.with(context).load(resultsBean.getUrl()).into(imageView);
        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClickListener(int i);
        void onLongClickListener(int i);
    }
}
