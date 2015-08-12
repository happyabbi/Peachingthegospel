/*******************************************************************************
 * Copyright 2011-2014 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
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