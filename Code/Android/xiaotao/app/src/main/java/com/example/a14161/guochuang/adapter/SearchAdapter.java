package com.example.a14161.guochuang.adapter;

import android.content.Context;

import com.example.a14161.guochuang.R;
import com.example.a14161.guochuang.model.Bean;
import com.example.a14161.guochuang.util.CommonAdapter;
import com.example.a14161.guochuang.util.ViewHolder;

import java.util.List;

/**
 * Created by 14161 on 2017/9/4.
 */

public class SearchAdapter extends CommonAdapter<Bean> {

    public SearchAdapter(Context context, List<Bean> data,int layoutId){
        super(context,data,layoutId);
    }
   @Override
    public void convert(ViewHolder holder,int position){
       holder.setResource(R.id.item_search_iv_icon,mData.get(position).getIconId())
               .setText(R.id.item_search_tv_title,mData.get(position).getTitle())
               .setText(R.id.item_search_iv_content,mData.get(position).getContent())
               .setText(R.id.item_search_iv_comments,mData.get(position).getComments());

   }


}
