package org.church.peachingthegospel.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.church.peachingthegospel.app.R;
import org.church.peachingthegospel.app.util.ViewHolder;


/**
 * Created by abraham.chen on 2015/3/15.
 */
public class GalleryImageAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private String[] imageUrls;
    private DisplayImageOptions options;

    public GalleryImageAdapter(Context context,String[] imageUrls) {

        inflater = LayoutInflater.from(context);
        this.context=context;
        this.imageUrls=imageUrls;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount() {
        return imageUrls.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gallery_image, parent, false);
        }
        ImageView imageView = ViewHolder.get(convertView, R.id.image);
        ImageLoader.getInstance().displayImage(imageUrls[position], imageView, options);
        return imageView;
    }

}
