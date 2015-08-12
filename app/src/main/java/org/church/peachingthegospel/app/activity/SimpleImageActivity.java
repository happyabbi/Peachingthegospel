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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.InjectableActivity;
import org.church.peachingthegospel.app.fragment.ImageListFragment;
import org.church.peachingthegospel.app.fragment.ImagePagerFragment;
import org.church.peachingthegospel.app.fragment.ImageGridFragment;

import javax.inject.Inject;



/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */

public class SimpleImageActivity extends InjectableActivity {
	android.support.v7.app.ActionBar actionbar;
	private static final String TAG = SimpleImageActivity.class.getName();

	@Inject
	public Tracker tracker;

	@Inject
	public Picasso picasso;

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				this.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	String imageKey = "images";
	String titlesKey = "titles";
	String titleKey = "title";
	String contextTypeKey = "contextType";

	@Override
	public int getLayoutResource() {
		return android.R.layout.simple_list_item_1;
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt("frIndex", frIndex);
		outState.putString("title",title);
		outState.putStringArray("imageUrls", imageUrls);
		outState.putStringArray("titles",titles);
		outState.putString("contextType",contextType);
	}

	int frIndex;
	String title;
	String[] imageUrls;
	String[] titles;
	String contextType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tracker.setScreenName(TAG);

		if (savedInstanceState != null) {
			frIndex = savedInstanceState.getInt("frIndex");
			title = savedInstanceState.getString("title");
			imageUrls = savedInstanceState.getStringArray("imageUrls");
			titles = savedInstanceState.getStringArray("titles");
			contextType = savedInstanceState.getString("contextType");
		} else{
			frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
			title = getIntent().getStringExtra(titleKey);
			imageUrls = getIntent().getStringArrayExtra(imageKey);
			titles = getIntent().getStringArrayExtra(titlesKey);
			contextType = getIntent().getStringExtra(contextTypeKey);
		}




 		Fragment fr;
		String tag;
		actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		android.support.v4.app.FragmentManager fragmentManager  = getSupportFragmentManager();

		switch (frIndex) {
			default:
			case ImageListFragment.INDEX:

				// All subsequent hits will be send with screen name = "main screen"


				tracker.send(new HitBuilders.EventBuilder()
						.setCategory("UX")
						.setAction("click")
						.setLabel("ImageListFragment.INDEX")
						.build());

				tag = ImageListFragment.class.getSimpleName();
				fr = fragmentManager.findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageListFragment();
					((ImageListFragment)fr).setImageUrls(imageUrls);
					((ImageListFragment)fr).setContextType(contextType);
				}
				((ImageListFragment)fr).setTitles(titles);
				actionbar.show();


				break;
			case ImageGridFragment.INDEX:

				tracker.send(new HitBuilders.EventBuilder()
						.setCategory("UX")
						.setAction("click")
						.setLabel("ImageGridFragment.INDEX")
						.build());

				tag = ImageGridFragment.class.getSimpleName();
				fr = fragmentManager.findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageGridFragment();
                    ((ImageGridFragment)fr).setImageUrls(imageUrls);
				}
				actionbar.show();
				break;
			case ImagePagerFragment.INDEX:

				tracker.send(new HitBuilders.EventBuilder()
						.setCategory("UX")
						.setAction("click")
						.setLabel("ImagePagerFragment.INDEX")
						.build());

				tag = ImagePagerFragment.class.getSimpleName();
				fr = fragmentManager.findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImagePagerFragment();
					((ImagePagerFragment)fr).setImageUrls(imageUrls);

					fr.setArguments(getIntent().getExtras());

				}
				actionbar.hide();
				break;

		}

		setTitle(title);
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(android.R.id.content, fr, tag).commit();
	}
}