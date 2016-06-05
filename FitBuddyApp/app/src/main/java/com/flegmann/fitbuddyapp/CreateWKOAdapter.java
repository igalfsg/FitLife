package com.flegmann.fitbuddyapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by songnob on 17/08/2015.
 */
public class CreateWKOAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<CreateWKOItem> items;
    private LayoutInflater inflater;
    private volatile CreateWKOItem currentItem;
    private int currentView = 0;
    private boolean shouldShowFirstRow = true;
    private volatile int visibleindex = 0;
    private ListView listview;

    public CreateWKOAdapter(Context context, ArrayList<CreateWKOItem> items, ListView listview)
    {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.items = items;
        this.listview = listview;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public CreateWKOItem getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        if (convertView == null)
        {
            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.create_wko_layout_item, null);
            holder.name_text_view = (TextView) convertView.findViewById(R.id.text_name);
            holder.header = convertView.findViewById(R.id.relativeLayout1);
            holder.input = convertView.findViewById(R.id.oclick_layout);
            holder.image_view = (ImageView) convertView.findViewById(R.id.foto_createwko);
            holder.sets_edit_text = (CustomEditText) convertView.findViewById(R.id.sets_edit_text);
            holder.reps_edit_text = (CustomEditText) convertView.findViewById(R.id.rep_edit_text);
            holder.checkbox = (ImageView) convertView.findViewById(R.id.image_chk);
            System.out.println("View Get View");
            holder.header.setTag(holder.input);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
            System.out.println("View Get View 2");
        }


        if (visibleindex == position)
        {
            holder.input.setVisibility(View.VISIBLE);
            if(holder.sets_edit_text != null)
            {
                final EditText clone = holder.sets_edit_text;
                holder.sets_edit_text.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        clone.requestFocus();
                        Log.i("su", "Request focus");
                    }
                }, 300);
            }
        }
        else
        {
            holder.input.setVisibility(View.GONE);
            System.out.println("Visibility gone");
        }

        CreateWKOItem item = getItem(position);

        if(item.isShouldShowCheckBox())
        {
            holder.checkbox.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.checkbox.setVisibility(View.GONE);
        }
        String image_drawable_str = item.getName().toLowerCase().replace(" ", "_");
        Drawable drawable = getImageFromDrawableByString(image_drawable_str);
        if (drawable != null)
        {
            holder.image_view.setImageDrawable(drawable);
        }
        else
        {
            holder.image_view.setImageResource(R.drawable.bg_count);
        }

     //   holder.sets_edit_text.removeTextWatcher();
     //   holder.reps_edit_text.removeTextWatcher();

        holder.name_text_view.setText(item.getName());
        holder.sets_edit_text.setHint("" + item.getSets());
        holder.reps_edit_text.setHint("" + item.getReps());

        holder.header.setTag(holder.input);
        holder.header.setTag(R.id.tag_id, position);
        holder.header.setTag(R.id.view_id, holder.reps_edit_text);

        holder.sets_edit_text.setTag(item);
        holder.reps_edit_text.setTag(item);

        holder.sets_edit_text.addTextWatcher();
        holder.reps_edit_text.addTextWatcher();

        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = (EditText) v.getTag(R.id.view_id);

                final int a = (int) v.getTag(R.id.tag_id);
                visibleindex = a;

                View input = (View) v.getTag();
                if (input.getVisibility() == View.VISIBLE) {
                    input.setVisibility(View.GONE);
                    visibleindex = -1;
                } else {
                    //input.setVisibility(View.VISIBLE);

                }
                notifyDataSetChanged();

            }
        });



        holder.sets_edit_text.setiOnTextChanged(new CustomEditText.IOnTextChanged() {
            @Override
            public void onTextChanged(View v, String newText) {
                CreateWKOItem it = (CreateWKOItem) v.getTag();
                int value = 0;
                try {
                    String str = newText.toString();
                    Log.i("su", "Sets text change to: " + str);
                    if (str.length() > 0)
                        value = Integer.parseInt(str);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (it != null) {
                    it.setSets(value);
                    Log.i("su", "Current item set sets to: " + value);
                }
                notifyDataSetChanged();

            }
        });
        holder.reps_edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                currentView = 1;
            }
        });
        holder.reps_edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                currentView = 2;
            }
        });
        holder.reps_edit_text.setiOnTextChanged(new CustomEditText.IOnTextChanged()
        {
            @Override
            public void onTextChanged(View v, String newText)
            {
                CreateWKOItem it = (CreateWKOItem) v.getTag();
                int value = 0;
                try
                {
                    String str = newText.toString();
                    Log.i("su", "Reps text change to: " + str);
                    if (str.length() > 0)
                        value = Integer.parseInt(str);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                if (it != null)
                {
                    it.setReps(value);
                    Log.i("su", "Current item set reps to: " + value);
                }
                //notifyDataSetChanged();
            }
        });
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

    static class ViewHolder
    {
        TextView name_text_view;
        CustomEditText sets_edit_text, reps_edit_text;
        View header, input;
        CreateWKOItem item ;
        ImageView image_view;
        ImageView checkbox;

    }
}
