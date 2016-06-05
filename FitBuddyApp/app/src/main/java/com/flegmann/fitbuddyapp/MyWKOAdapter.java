package com.flegmann.fitbuddyapp;

/**
 * Created by songnob on 17/08/2015.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

public class MyWKOAdapter extends BaseAdapter
{

    private LayoutInflater inflater;
    private List<ParseObject> items;
    private Context mContext;

    public MyWKOAdapter(Context context, List<ParseObject> items)
    {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.mContext = context;
    }

    public int getCount()
    {
        return items.size();
    }

    public ParseObject getItem(int position)
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

            convertView = inflater.inflate(R.layout.wko_class_adapter, null);
            holder.image_view = (ImageView) convertView.findViewById(R.id.image_view);
            holder.class_name_text_view = (TextView) convertView.findViewById(R.id.class_name_text_view);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            //Log.e("LISTTT", "Image......" + items.get(position));
            //holder.image_view.setImageResource(items.get(position).getIconID());
            //holder.class_name_text_view =
        }

        ParseObject po = items.get(position);
        String title = po.getString("name");
        //String img = po.getString("img_name");
        holder.class_name_text_view.setText(title);

        String image_drawable_str = title.toLowerCase().replace(" ", "_");
        Drawable drawable = getImageFromDrawableByString(image_drawable_str);
        if (drawable != null)
        {
            holder.image_view.setImageDrawable(drawable);
        }
        else
        {
            holder.image_view.setImageResource(R.drawable.image1);
        }

        return convertView;
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

    public static class ViewHolder
    {
        ImageView image_view;
        TextView class_name_text_view;
        TextView description_text_view;
        TextView day_text_view;
    }

    public static class MyWKOItem
    {
        private String name;
        private int iconID;
        private String day;

        public MyWKOItem(String name, int iconID, String day)
        {
            super();
            this.name = name;
            this.day = day;
            this.iconID = iconID;
        }

        public String getName()
        {
            return name;
        }

        public int getIconID()
        {
            return iconID;
        }

        public String getDay()
        {
            return day;
        }
    }

}