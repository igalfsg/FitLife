package com.flegmann.fitbuddyapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by songnob on 17/08/2015.
 */
public class ExcersesAdapter2 extends ArrayAdapter<ParseObject>
{
    protected Context mContext;
    protected List<com.parse.ParseObject> mExercise;
    protected int image_id;
    protected String nombre;


    public ExcersesAdapter2(Context context, List<com.parse.ParseObject> exercise)
    {
        super(context, R.layout.exercise_adapter, exercise);
        mContext = context;
        mExercise = exercise;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.exercise_adapter, null);
            holder = new ViewHolder();
            holder.exerciseImage = (ImageView)convertView.findViewById(R.id.Exersise_image);
            holder.exerciseName = (TextView) convertView.findViewById(R.id.Exersise_name_menu);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        com.parse.ParseObject exercise_object = mExercise.get(position);
        String title = exercise_object.getString("name");
        Drawable drawable;
        if(title != null) {
             String image_drawable_str = title.toLowerCase().replace(" ", "_");
             drawable = getImageFromDrawableByString(image_drawable_str);
        }
        else{
             drawable = null;
        }
        if (drawable != null)
        {
            holder.exerciseImage.setImageDrawable(drawable);
        }
        else
        {
            holder.exerciseImage.setImageResource(R.drawable.bg_count);
        }
        holder.exerciseName.setText(title);
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
        ImageView exerciseImage;
        TextView exerciseName;

    }
}