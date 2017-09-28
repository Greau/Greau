package com.example.a14161.guochuang.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 14161 on 2017/9/4.
 */

public class ViewHolder {
    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;
    private int mPosition;

    /**
     *init holder
     */
    public ViewHolder(Context context, int layoutId, ViewGroup parent,int position){
        mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mViews=new SparseArray<>();
        mPosition=position;
        mConvertView.setTag(this);
    }
    public static ViewHolder getHolder(Context context,View convertView,int layoutId,ViewGroup parent,int position ){
        if(convertView==null){
            return new ViewHolder(context,layoutId,parent,position);
        }
        else{
            ViewHolder holder=(ViewHolder)convertView.getTag();
            holder.mPosition=position;
            return holder;
        }
    }
    public View getConvertView(){
        return mConvertView;
    }
    public <T extends View> T getView(int viewId){
        View view= mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }
    public ViewHolder setText(int viewId,String text){
        TextView tv=getView(viewId);
        tv.setText(text);
        return this;
    }
    public ViewHolder setResource(int viewId,int id){
        ImageView iv=getView(viewId);
        iv.setImageResource(id);
        return this;
    }
    public ViewHolder setBitmap(int viewId, Bitmap bitmap){
        ImageView iv=getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;

    }

}
