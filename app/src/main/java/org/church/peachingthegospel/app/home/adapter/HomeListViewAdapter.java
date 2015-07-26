package org.church.peachingthegospel.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.church.peachingthegospel.app.R;
import org.church.peachingthegospel.app.home.datamodule.HomeListViewItem;
import org.church.peachingthegospel.app.util.ViewHolder;

import java.util.List;

/**
 * Created by abraham.chen on 2015/3/15.
 */
public class HomeListViewAdapter extends BaseAdapter {
    private Context context;
    private Picasso picasso;
    private List<HomeListViewItem> homeListViewItemList;

    public HomeListViewAdapter(Context context, List<HomeListViewItem> homeListViewItemList,Picasso picasso) {
        this.context = context;
        this.homeListViewItemList = homeListViewItemList;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return homeListViewItemList.size();
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
        text.setText(homeListViewItemList.get(position).getTitle());
        picasso.load(homeListViewItemList.get(position).getImageUrl()).into((ImageView)
                convertView.findViewById(R.id.image));

        return convertView;
    }
}