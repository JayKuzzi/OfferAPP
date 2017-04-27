package com.bb.offerapp.adapter;


import android.content.Context;
import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp.R;
import com.bb.offerapp.activity.Contacts;
import com.bb.offerapp.activity.Gesture_1;
import com.bb.offerapp.activity.JsonTest;
import com.bb.offerapp.activity.LoginActivity;
import com.bb.offerapp.activity.MyView;
import com.bb.offerapp.activity.NotificationTest;

import com.bb.offerapp.activity.PadMode;
import com.bb.offerapp.activity.Pic;
import com.bb.offerapp.activity.ServiceBest;
import com.bb.offerapp.activity.cal;
import com.bb.offerapp.activity.wechat;
import com.bb.offerapp.bean.ItemInfo;

import java.util.List;

/**
 * MyRecyclerAdapter
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<ItemInfo> mItemInfoList;
    private Context context;

    //设置Item形状
    private boolean mIsSmall = false;

    //构造函数
    public MyRecyclerAdapter(List<ItemInfo> itemInfoList, Context context) {
        this.mItemInfoList = itemInfoList;
        this.context = context;
    }

    public void setSmallType(boolean isSmall) {
        this.mIsSmall = isSmall;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (mIsSmall) {
            itemView = inflater.inflate(R.layout.author_small_card_layout, parent, false);
        } else {
            itemView = inflater.inflate(R.layout.author_card_layout, parent, false);
        }
        final MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                switch (position % 12) {
                    case 0:
                        Intent intent0 = new Intent("BBB");
                        context.startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context, wechat.class);
                        context.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(context, NotificationTest.class);
                        context.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(context, LoginActivity.class);
                        context.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(context, Pic.class);
                        context.startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(context, PadMode.class);
                        context.startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6 = new Intent(context, Contacts.class);
                        context.startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7 = new Intent(context, JsonTest.class);
                        context.startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8 = new Intent(context, MyView.class);
                        context.startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9 = new Intent(context, ServiceBest.class);
                        context.startActivity(intent9);
                        break;
                    case 10:
                        Intent intent10 = new Intent(context, cal.class);
                        context.startActivity(intent10);
                        break;
                    case 11:
                        Intent intent11 = new Intent(context, Gesture_1.class);
                        context.startActivity(intent11);
                        break;

                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        ItemInfo itemInfo = mItemInfoList.get(position);
        viewHolder.mPortraitView.setImageResource(itemInfo.getPortrait());
        viewHolder.mNickNameView.setText(itemInfo.getNickName());
        viewHolder.mMottoView.setText(itemInfo.getMotto());
    }

    @Override
    public int getItemCount() {
        if (mItemInfoList == null) {
            return 0;
        }
        return mItemInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View item;//最外层子view

        ImageView mPortraitView;
        TextView mNickNameView;
        TextView mMottoView;

        public MyViewHolder(View itemView) {
            super(itemView);
            item = itemView;//将itemView赋给item拿去做监听
            mPortraitView = (ImageView) itemView.findViewById(R.id.iv_portrait);
            mNickNameView = (TextView) itemView.findViewById(R.id.tv_nickname);
            mMottoView = (TextView) itemView.findViewById(R.id.tv_motto);

        }
    }

}
