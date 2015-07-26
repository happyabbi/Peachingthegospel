package org.church.peachingthegospel.app.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.church.peachingthegospel.app.R;

/**
 * Created by abraham.chen on 2015/3/15.
 */
public class PagerImageAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private String[] imageUrls;
    Picasso picasso;
    public PagerImageAdapter(Context context,String[] imageUrls,Picasso picasso) {
        this.context=context;
        this.imageUrls=imageUrls;
        this.inflater = LayoutInflater.from(context);
        this.picasso = picasso;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
        assert imageLayout != null;

        picasso.load(imageUrls[position]).into((ImageView)
                imageLayout.findViewById(R.id.image));
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
