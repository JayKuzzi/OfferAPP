package com.bb.offerapp.fragment.viewpaper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bb.offerapp.R;
import com.bb.offerapp.adapter.MyRecyclerAdapter;

import com.bb.offerapp.bean.ItemInfo;

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
            itemInfo0.setMotto("自定义Lyout复用、RecycleView");
            itemInfo0.setNickName("RecycleView聊天窗");
            itemInfo0.setPortrait(R.mipmap.shengao);
            mItemInfoList.add(itemInfo0);

            ItemInfo itemInfo2 = new ItemInfo();
            itemInfo2.setMotto("GridView加载适配器、数据源的配置");
            itemInfo2.setNickName("手机菜单演示");
            itemInfo2.setPortrait(R.mipmap.caidan);
            mItemInfoList.add(itemInfo2);

            ItemInfo itemInfo3 = new ItemInfo();
            itemInfo3.setMotto("实现网页加载时进度条的监听、返回键的捕捉");
            itemInfo3.setNickName("带进度条的WebView");
            itemInfo3.setPortrait(R.mipmap.webview);
            mItemInfoList.add(itemInfo3);

            ItemInfo itemInfo4 = new ItemInfo();
            itemInfo4.setMotto("使用GestureOverlayView加载预设手势");
            itemInfo4.setNickName("自定义手势");
            itemInfo4.setPortrait(R.mipmap.shoushi);
            mItemInfoList.add(itemInfo4);

            ItemInfo itemInfo5 = new ItemInfo();
            itemInfo5.setMotto("自带及自定义Toast的对比");
            itemInfo5.setNickName("Toast演示");
            itemInfo5.setPortrait(R.mipmap.toast);
            mItemInfoList.add(itemInfo5);


            ItemInfo itemInfo6 = new ItemInfo();
            itemInfo6.setMotto("Lyout布局的设置、优化、计算逻辑的实现");
            itemInfo6.setNickName("计算器逻辑及布局");
            itemInfo6.setPortrait(R.mipmap.jisuanqi);
            mItemInfoList.add(itemInfo6);

            ItemInfo itemInfo9 = new ItemInfo();
            itemInfo9.setMotto("Fragment的事件的操作");
            itemInfo9.setNickName("微信布局");
            itemInfo9.setPortrait(R.mipmap.wechat);
            mItemInfoList.add(itemInfo9);

            ItemInfo itemInfo12 = new ItemInfo();
            itemInfo12.setMotto("调用通知管理系统服务通知管理");
            itemInfo12.setNickName("发送一条通知");
            itemInfo12.setPortrait(R.mipmap.tongzhi);
            mItemInfoList.add(itemInfo12);

            ItemInfo itemInfo13 = new ItemInfo();
            itemInfo13.setMotto("自定义View");
            itemInfo13.setNickName("自定义View");
            itemInfo13.setPortrait(R.mipmap.tongzhi);
            mItemInfoList.add(itemInfo13);

            ItemInfo itemInfo14 = new ItemInfo();
            itemInfo14.setMotto("发送接收广播、清空栈");
            itemInfo14.setNickName("强制下线");
            itemInfo14.setPortrait(R.mipmap.tongzhi);
            mItemInfoList.add(itemInfo14);

            ItemInfo itemInfo15 = new ItemInfo();
            itemInfo15.setMotto("Handler");
            itemInfo15.setNickName("加载网络图片");
            itemInfo15.setPortrait(R.mipmap.tongzhi);
            mItemInfoList.add(itemInfo15);

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

        //禁止嵌套滑动 可以让其由于惯性滑动
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
