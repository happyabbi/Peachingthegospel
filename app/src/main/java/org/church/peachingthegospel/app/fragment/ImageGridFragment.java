
package org.church.peachingthegospel.app.fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import org.church.peachingthegospel.app.activity.SimpleImageActivity;
import org.church.peachingthegospel.app.adapter.GridImageAdapter;
import org.church.peachingthegospel.app.R;

public class ImageGridFragment extends AbsListViewBaseFragment {
	public static final int INDEX = 1;
	private String[] imageUrls;


	public String[] getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls (String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putStringArray("imageUrls", imageUrls);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			imageUrls = savedInstanceState.getStringArray("imageUrls");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fr_image_grid, container, false);
		listView = (GridView) rootView.findViewById(R.id.grid);
		Picasso picasso = ((SimpleImageActivity)this.getActivity()).picasso;
		final Tracker tracker =  ((SimpleImageActivity)this.getActivity()).tracker;

		listView.setAdapter(new GridImageAdapter(getActivity(), imageUrls,picasso));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				tracker.send(new HitBuilders.EventBuilder()
						.setCategory("UX")
						.setAction("click")
						.setLabel("GridImageAdapter.position="+position)
						.build());
				startImagePagerActivity(position,imageUrls);
			}
		});
		return rootView;
	}
}