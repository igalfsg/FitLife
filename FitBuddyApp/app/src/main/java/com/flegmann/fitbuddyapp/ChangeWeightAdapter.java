package com.flegmann.fitbuddyapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChangeWeightAdapter extends BaseAdapter
{
    protected Context mContext;
    private LayoutInflater inflater;
    private ArrayList<ChangeWeightItem> items;

    private class ViewHolder
    {

        TextView name_text_view;
        ImageView icon_img;
        EditText editText;

    }

    public ChangeWeightAdapter(Context context, ArrayList<ChangeWeightItem> items)
    {
        // TODO Auto-generated constructor stub

        inflater = LayoutInflater.from(context);
        this.items = items;

        mContext = context;

    }

    public int getCount()
    {

        return items.size();

    }

    public ChangeWeightItem getItem(int position)
    {

        return items.get(position);

    }

    public long getItemId(int position)
    {

        return position;

    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub

        ViewHolder holder = null;

        if (convertView == null)
        {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.change_weight_adapter, null);
            holder.name_text_view = (TextView) convertView.findViewById(R.id.name_text_view);
            holder.editText = (EditText) convertView.findViewById(R.id.weight_edit_text);
            holder.icon_img = (ImageView) convertView.findViewById(R.id.foto_createwko);
            convertView.setTag(holder);
        }

        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final ChangeWeightItem item = items.get(position);

        holder.name_text_view.setText(item.name);
        holder.editText.setHint("" + item.weight);

        String image_drawable_str = item.name.toLowerCase().replace(" ", "_");
        Drawable drawable = getImageFromDrawableByString(image_drawable_str);
        if (drawable != null)
        {
            holder.icon_img.setImageDrawable(drawable);
        }
        else
        {
            holder.icon_img.setImageResource(R.drawable.bg_count);
        }

        holder.editText.setTag(item);
        holder.editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.toString().length() > 0)
                {
                    item.weight = Integer.parseInt(s.toString().trim());
                }
            }
        });



        return convertView;
    }


    public static class ChangeWeightItem
    {
        public String name;
        public int weight;

        public ChangeWeightItem(String name, int weight)
        {
            this.name = name;
            this.weight = weight;
        }
    }
    public Drawable getImageFromDrawableByString(String name)
    {
        int resource_id = mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
        Drawable drawable = null;

        if (resource_id > 0)
        {
            try
            {
                drawable = mContext.getResources().getDrawable(resource_id);
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        return drawable;
    }
}

