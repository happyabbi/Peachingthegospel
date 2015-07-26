package org.church.peachingthegospel.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.church.peachingthegospel.app.R;

/**
 * Created by abraham.chen on 2015/3/15.
 */
public class GridImageAdapter extends BaseAdapter {

    private Context context;
    private String[] imageUrls;

    Picasso picasso;
    public GridImageAdapter(Context context, String[] imageUrls , Picasso picasso) {
        this.context=context;
        this.imageUrls=imageUrls;

        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_image, parent, false);
        }
        picasso.load(imageUrls[position]).into((ImageView)
                convertView.findViewById(R.id.image));

        return convertView;
    }
}
