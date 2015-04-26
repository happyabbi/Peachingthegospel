package org.church.peachingthegospel.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import org.church.peachingthegospel.app.R;
import org.church.peachingthegospel.app.model.ListImageModel1;
import org.church.peachingthegospel.app.util.ViewHolder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by abraham.chen on 2015/3/15.
 */
public class ListImageAdapter extends BaseAdapter {
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private Context context;
    private List<ListImageModel1> listImageModel1;
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public ListImageAdapter(Context context, List<ListImageModel1> listImageModel1) {
        this.context= context;
        this.listImageModel1 = listImageModel1;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_stub)
                .showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
    }

    @Override
    public int getCount() {
        return listImageModel1.size();
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
        ImageView image = ViewHolder.get(convertView,R.id.image);
        text.setText(listImageModel1.get(position).getTitle());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(options)
                .build();
        imageLoader.init(config);
        imageLoader.displayImage(listImageModel1.get(position).getImageUrl(), image, options, animateFirstListener);
        return convertView;
    }

    public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        public static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}