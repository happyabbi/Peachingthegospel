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
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.adapter.PagerImageAdapter;
import org.church.peachingthegospel.app.R;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerFragment extends BaseFragment {
	public static final int INDEX = 2;
	private String[] imageUrls = Constants.HUMANLIFE_TW_IMAGES;

	public String[] getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls (String[] imageUrls) {
		this.imageUrls = imageUrls;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fr_image_pager, container, false);
		ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
		pager.setAdapter(new PagerImageAdapter(getActivity(),this.imageUrls));
		pager.setCurrentItem(getArguments().getInt(Constants.Extra.IMAGE_POSITION, 0));
        String imageKey="images";
        this.imageUrls = getArguments().getStringArray(imageKey);
		return rootView;
	}
}