/*******************************************************************************
 * Copyright 2014 Sergey Tarasevich
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
package org.church.peachingthegospel.app.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.ActionBarActivity;
import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.fragment.ImageGalleryFragment;
import org.church.peachingthegospel.app.fragment.ImageListFragment;
import org.church.peachingthegospel.app.fragment.ImagePagerFragment;
import org.church.peachingthegospel.app.fragment.ImageGridFragment;
import org.church.peachingthegospel.app.R;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */

public class SimpleImageActivity extends ActionBarActivity {
	android.support.v7.app.ActionBar actionbar;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
		String title = getIntent().getStringExtra("title");
        String imageKey="images";
        String[] imageUrls = getIntent().getStringArrayExtra(imageKey);
		String[] titles =getIntent().getStringArrayExtra("titles");
		String contextType = getIntent().getStringExtra("contextType");
 		Fragment fr;
		String tag;
		actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		int titleRes;
		switch (frIndex) {
			default:
			case ImageListFragment.INDEX:
				tag = ImageListFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageListFragment();
					((ImageListFragment)fr).setImageUrls(imageUrls);
					((ImageListFragment)fr).setTitles(titles);
					((ImageListFragment)fr).setContextType(contextType);
				}
				actionbar.show();
				titleRes = R.string.ac_name_image_list;
				break;
			case ImageGridFragment.INDEX:
				tag = ImageGridFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageGridFragment();
                    ((ImageGridFragment)fr).setImageUrls(imageUrls);
				}
				actionbar.show();
				titleRes = R.string.ac_name_image_grid;
				break;
			case ImagePagerFragment.INDEX:
				tag = ImagePagerFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImagePagerFragment();
					((ImagePagerFragment)fr).setImageUrls(imageUrls);
					fr.setArguments(getIntent().getExtras());
				}
				actionbar.hide();
				titleRes = R.string.ac_name_image_pager;
				break;
			case ImageGalleryFragment.INDEX:
				tag = ImageGalleryFragment.class.getSimpleName();
				fr = getSupportFragmentManager().findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageGalleryFragment();
				}
				actionbar.show();
				titleRes = R.string.ac_name_image_gallery;
				break;
		}

		setTitle(title);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();
	}
}