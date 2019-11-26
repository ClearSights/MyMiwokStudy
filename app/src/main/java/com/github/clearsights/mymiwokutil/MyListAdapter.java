package com.github.clearsights.mymiwokutil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clearsights.mymiwokstudy.R;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<Word> {
    private Context mContext;
    private int listItemLayoutId;
    private int listBgColor;
    private static final String LOG_TAG = "MyListAdapter";

    private class MyViewHolder {
        TextView engTextViewHolder;
        TextView miwokTextViewHolder;
        ImageView imgViewHolder;
    }

    public MyListAdapter(Context context, int resource, List<Word> objects, int bgColorId) {
        super(context, resource, objects);
        mContext = context;
        listItemLayoutId = resource;
        this.listBgColor = mContext.getResources().getColor(bgColorId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word currentWord = getItem(position);
        MyViewHolder myViewHolder;

        // if the view element has been recycled, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(listItemLayoutId, parent, false);
            myViewHolder = new MyViewHolder();
            myViewHolder.engTextViewHolder = (TextView) convertView.findViewById(R.id.eng_text);
            myViewHolder.miwokTextViewHolder = (TextView) convertView.findViewById(R.id.miwok_text);
            myViewHolder.imgViewHolder = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        // view holder way of getting view object inside list items
        myViewHolder.engTextViewHolder.setText(currentWord.getEnglishName());
        myViewHolder.miwokTextViewHolder.setText(currentWord.getMiwokName());
        if (currentWord.hasImg()) {
            myViewHolder.imgViewHolder.setImageResource(currentWord.getImgResource());
        } else {
            // hide image, if it's a phrase
            myViewHolder.imgViewHolder.setVisibility(View.GONE);
        }
        myViewHolder.engTextViewHolder.setBackgroundColor(listBgColor);
        myViewHolder.miwokTextViewHolder.setBackgroundColor(listBgColor);

        return convertView;
    }
}
