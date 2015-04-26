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
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.fragment.ImageGalleryFragment;
import org.church.peachingthegospel.app.fragment.ImageListFragment;
import org.church.peachingthegospel.app.fragment.ImagePagerFragment;
import org.church.peachingthegospel.app.fragment.ImageGridFragment;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */

public class SimpleImageActivity extends ActionBarActivity {
	android.support.v7.app.ActionBar actionbar;

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				this.finish();
//				Intent intent = new Intent(this, HomeActivity.class);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//				getSupportFragmentManager().popBackStack();
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int frIndex = getIntent().getIntExtra(Constants.Extra.FRAGMENT_INDEX, 0);
		String title = getIntent().getStringExtra(titleKey);

        String[] imageUrls = getIntent().getStringArrayExtra(imageKey);
		String[] titles = getIntent().getStringArrayExtra(titlesKey);
		String contextType = getIntent().getStringExtra(contextTypeKey);

 		Fragment fr;
		String tag;
		actionbar = getSupportActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		android.support.v4.app.FragmentManager fragmentManager  = getSupportFragmentManager();

		switch (frIndex) {
			default:
			case ImageListFragment.INDEX:
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
				tag = ImageGridFragment.class.getSimpleName();
				fr = fragmentManager.findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImageGridFragment();
                    ((ImageGridFragment)fr).setImageUrls(imageUrls);
				}
				actionbar.show();
				break;
			case ImagePagerFragment.INDEX:
				tag = ImagePagerFragment.class.getSimpleName();
				fr = fragmentManager.findFragmentByTag(tag);
				if (fr == null) {
					fr = new ImagePagerFragment();
					((ImagePagerFragment)fr).setImageUrls(imageUrls);
					fr.setArguments(getIntent().getExtras());
				}
				actionbar.hide();
				break;
			case ImageGalleryFragment.INDEX:
				tag = ImageGalleryFragment.class.getSimpleName();
				fr = fragmentManager.findFragmentByTag(tag);

				if (fr == null) {
					fr = new ImageGalleryFragment();
				}
				actionbar.show();
				break;
		}

		setTitle(title);
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(android.R.id.content, fr, tag).commit();
	}
}