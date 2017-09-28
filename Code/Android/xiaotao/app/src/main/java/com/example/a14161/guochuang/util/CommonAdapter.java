package com.example.a14161.guochuang.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by 14161 on 2017/9/4.
 */

public abstract class CommonAdapter<T> extends BaseAdapter{
    protected Context mContext;
    protected List<T>mData;
    protected int mLayoutId;

    public CommonAdapter(Context context,List<T> data,int id){
        this.mContext=context;
        this.mData=data;
        this.mLayoutId=id;
    }
    @Override
    public int getCount(){return mData.size();}

    @Override
    public T getItem(int i){
        return mData.get(i);
    }
    @Override
    public long getItemId(int i){
        return i;
    }
    @Override
    public View getView(int position,View contentView,ViewGroup parent){
        ViewHolder holder=ViewHolder.getHolder(mContext,contentView,mLayoutId,parent,position);
        convert(holder,position);
        return holder.getConvertView();
    }
    public abstract void convert(ViewHolder holder,int position);
}
