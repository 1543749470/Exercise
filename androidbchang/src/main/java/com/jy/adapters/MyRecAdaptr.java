package com.jy.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidbchang.Main2Activity;
import com.example.androidbchang.R;
import com.jy.beans.RecentBean;

import java.util.ArrayList;
import java.util.List;

public class MyRecAdaptr extends RecyclerView.Adapter<MyRecAdaptr.ViewHolder> {
    Context context;
    ArrayList<RecentBean> list =new ArrayList<>();
//    private MyReceiver myReceiver

    public MyRecAdaptr(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.rec_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.rec_tv.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.rec_img);
//        myReceiver = new MyReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("aa");
//        LocalBroadcastManager.getInstance(context).registerReceiver(myReceiver,intentFilter);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent aa = new Intent(context, Main2Activity.class);
//                aa.putExtra("data",list.get(i));
//                context.startActivity(aa);
              Intent aa = new Intent(context, Main2Activity.class);
                aa.putExtra("data",list);
                aa.putExtra("id",i);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<RecentBean> list) {
        if (this.list!=null){
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView rec_img;
        private TextView rec_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_img = itemView.findViewById(R.id.rec_img);
            rec_tv = itemView.findViewById(R.id.rec_tv);
        }
    }
//    class MyReceiver extends BroadcastReceiver{
//        private static final String TAG = "MyReceiver";
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String data = intent.getStringExtra("data");
//            Log.e(TAG, "onReceive: "+data );
//
//        }
//    }
}
