package com.bb.offerapp2.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp2.R;
import com.bb.offerapp2.bean.ItemBean;

import java.util.List;

/**
 * Created by bb on 2017/4/18.
 */

public class MyListViewAdapter extends ArrayAdapter<ItemBean> {
    private int resource;

    public MyListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ItemBean> objects) {
        super(context, resource, objects);
        this.resource = resource;

    }


    @NonNull
    @Override
    //listview中每滑动出来一个item，就会走一个getview方法，且重新加载布局，重新获取控件实例
    // convertView为布局缓存，通过以下设置可以避免重新加载布局，提高效率.
    //通过ViewHolder来进行优化，可以避免每次都加载空间实例。
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
////        Log.i("getview","getview");
//        ItemBean itemBean=getItem(position);
//        View view= LayoutInflater.from(getContext()).inflate(resource,parent,false);
//        ImageView imageView= (ImageView) view.findViewById(R.id.ImageView);
//        TextView textView= (TextView) view.findViewById(R.id.TextView);
//        imageView.setImageResource(itemBean.getImageId());
//        textView.setText(itemBean.getName());
//        return view;
//    }




    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        Log.i("getview","getview");
        ItemBean itemBean=getItem(position);

        View view;

        ViewHolder viewHolder ;

        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder =new ViewHolder();
            viewHolder.imageView=(ImageView) view.findViewById(R.id.ImageView);
            viewHolder.textView= (TextView) view.findViewById(R.id.TextView);
            view.setTag(viewHolder);

        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.imageView.setImageResource(itemBean.getImageId());
        viewHolder.textView.setText(itemBean.getName());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
