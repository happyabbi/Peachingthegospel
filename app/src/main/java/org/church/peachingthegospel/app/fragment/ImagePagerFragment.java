
package org.church.peachingthegospel.app.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.activity.SimpleImageActivity;
import org.church.peachingthegospel.app.adapter.PagerImageAdapter;
import org.church.peachingthegospel.app.R;


public class ImagePagerFragment extends BaseFragment {
	public static final int INDEX = 2;
	private String[] imageUrls = Constants.HUMANLIFE_TW_IMAGES;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putStringArray("imageUrls", imageUrls);
	}

	public void setImageUrls (String[] imageUrls) {
		this.imageUrls = imageUrls;
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
		View rootView = inflater.inflate(R.layout.fr_image_pager, container, false);
		ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
		Picasso picasso = ((SimpleImageActivity)this.getActivity()).picasso;
		pager.setAdapter(new PagerImageAdapter(getActivity(),this.imageUrls,picasso));
		pager.setCurrentItem(getArguments().getInt(Constants.Extra.IMAGE_POSITION, 0));
        String imageKey="images";
        this.imageUrls = getArguments().getStringArray(imageKey);
		return rootView;
	}
}