package com.bb.offerapp2.fragment.viewpaper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bb.offerapp2.R;
import com.bb.offerapp2.adapter.MyRecyclerAdapter;

import com.bb.offerapp2.bean.ItemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bb on 2016/11/18.
 */
public class AFragment extends Fragment {
    //声明第一页所用到的组件


    //RecyclerView
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;


    //Item列表
    private List<ItemInfo> mItemInfoList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initData();//初始化RecyclerView数据
        View view = inflater.inflate(R.layout.recycle_a_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fragment);
        initView_RecyclerView();//初始化RecyclerView
        return view;
    }


    private void initData() {
        mItemInfoList = new ArrayList<>();
        for(int i=1 ;i<=10;i++) {
            ItemInfo itemInfo0 = new ItemInfo();
            itemInfo0.setMotto("RecycleView、自定义Layout复用");
            itemInfo0.setNickName("聊天窗口");
            itemInfo0.setPortrait(R.mipmap.a1);
            mItemInfoList.add(itemInfo0);

            ItemInfo itemInfo1 = new ItemInfo();
            itemInfo1.setMotto("动态Fragment管理");
            itemInfo1.setNickName("微信布局");
            itemInfo1.setPortrait(R.mipmap.a2);
            mItemInfoList.add(itemInfo1);

            ItemInfo itemInfo2 = new ItemInfo();
            itemInfo2.setMotto("新风格高优先级通知");
            itemInfo2.setNickName("一条通知");
            itemInfo2.setPortrait(R.mipmap.a3);
            mItemInfoList.add(itemInfo2);


            ItemInfo itemInfo3 = new ItemInfo();
            itemInfo3.setMotto("发送接收广播、栈管理、数据处久化、sqlite");
            itemInfo3.setNickName("强制下线");
            itemInfo3.setPortrait(R.mipmap.a4);
            mItemInfoList.add(itemInfo3);

            ItemInfo itemInfo4 = new ItemInfo();
            itemInfo4.setMotto("Handler更新UI、AsyncTask使用");
            itemInfo4.setNickName("网络图片");
            itemInfo4.setPortrait(R.mipmap.a5);
            mItemInfoList.add(itemInfo4);

            ItemInfo itemInfo5 = new ItemInfo();
            itemInfo5.setMotto("横屏布局加载、限定符、动态静态碎片加载、ListView使用、碎片活动交互");
            itemInfo5.setNickName("平板模式");
            itemInfo5.setPortrait(R.mipmap.a6);
            mItemInfoList.add(itemInfo5);

            ItemInfo itemInfo6 = new ItemInfo();
            itemInfo6.setMotto("访问内容提供者、ListView使用");
            itemInfo6.setNickName("联系人访问");
            itemInfo6.setPortrait(R.mipmap.a7);
            mItemInfoList.add(itemInfo6);

            ItemInfo itemInfo7 = new ItemInfo();
            itemInfo7.setMotto("Json数据解析、网络工具类封装、回调接口、handler");
            itemInfo7.setNickName("数据解析");
            itemInfo7.setPortrait(R.mipmap.a8);
            mItemInfoList.add(itemInfo7);

            ItemInfo itemInfo8 = new ItemInfo();
            itemInfo8.setMotto("继承View重写、类封装复用、线程动态View");
            itemInfo8.setNickName("自定义View");
            itemInfo8.setPortrait(R.mipmap.a9);
            mItemInfoList.add(itemInfo8);

            ItemInfo itemInfo9 = new ItemInfo();
            itemInfo9.setMotto("Service、动态权限使用、通知管理、回调接口");
            itemInfo9.setNickName("Service");
            itemInfo9.setPortrait(R.mipmap.aaa);
            mItemInfoList.add(itemInfo9);

            ItemInfo itemInfo10 = new ItemInfo();
            itemInfo10.setMotto("Lyout布局练习、逻辑实现");
            itemInfo10.setNickName("计算器");
            itemInfo10.setPortrait(R.mipmap.a10);
            mItemInfoList.add(itemInfo10);

            ItemInfo itemInfo11 = new ItemInfo();
            itemInfo11.setMotto("GestureOverlayView加载预设手势");
            itemInfo11.setNickName("自定义手势");
            itemInfo11.setPortrait(R.mipmap.a11);
            mItemInfoList.add(itemInfo11);

        }

    }

    private void initView_RecyclerView() {

        //设置线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter = new MyRecyclerAdapter(mItemInfoList,getActivity());
        mRecyclerAdapter.setSmallType(false);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        //嵌套滑动 可以让其由于惯性滑动
        mRecyclerView.setNestedScrollingEnabled(true);
    }




    //方便其他地方调用
    public MyRecyclerAdapter getmRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }


}
