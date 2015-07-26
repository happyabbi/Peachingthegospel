package org.church.peachingthegospel.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.church.peachingthegospel.app.R;
import org.church.peachingthegospel.app.model.ListImageModel;
import org.church.peachingthegospel.app.util.ViewHolder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by abraham.chen on 2015/3/15.
 */
public class ListImageAdapter extends BaseAdapter {
    private Context context;
    private List<ListImageModel> listImageModel;
    private Picasso picasso;
    public ListImageAdapter(Context context, List<ListImageModel> listImageModel,Picasso picasso) {
        this.context= context;
        this.listImageModel = listImageModel;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return listImageModel.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_image, parent, false);
        }
        TextView text = ViewHolder.get(convertView, R.id.text);
        text.setText(listImageModel.get(position).getTitle());
        picasso.load(listImageModel.get(position).getImageUrl()).into((ImageView)
                convertView.findViewById(R.id.image));
        return convertView;
    }


}