/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
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
package org.church.peachingthegospel.app.home;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.L;
import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.R;
import org.church.peachingthegospel.app.activity.SimpleImageActivity;
import org.church.peachingthegospel.app.home.adapter.HomeListViewAdapter;
import org.church.peachingthegospel.app.home.datamodule.HomeListViewItem;
import org.church.peachingthegospel.app.fragment.ImageListFragment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class HomeActivity extends ActionBarActivity {

	private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";
	private HomeListViewAdapter homeListViewAdapter;
	private List<HomeListViewItem> homeListViewItemList;
	private ListView listView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_home);
		File testImageOnSdCard = new File("/mnt/sdcard", TEST_FILE_NAME);
		if (!testImageOnSdCard.exists()) {
			copyTestImageToSdCard(testImageOnSdCard);
		}
		homeListViewItemList=new ArrayList<HomeListViewItem>();
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2858,"人生的奧祕"));
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2903,"軟弱人的需要"));
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2868,"上流人的需要"));
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2937,"乾渴婦人的需要"));
		homeListViewAdapter=new HomeListViewAdapter(this,homeListViewItemList);
		listView= (ListView)findViewById(R.id.ac_home_listview);
		listView.setAdapter(homeListViewAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Constants.ACTIONBAR_STATUS = "Home";
				switch (position){
					case 0:
						onImageHumanLifeListClick(view);
						break;
					case 1:
						WeakClick(view);
						break;
					case 2:
						PersonageClick(view);
						break;
					case 3:
						ParcheddryClick(view);
						break;
				}
			}
		});
	}

	public void onImageHumanLifeListClick(View view) {
		Intent intent = new Intent(this, SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageListFragment.INDEX);
		intent.putExtra("title", "人生的奧祕");
        String[] imageUrls  = new String[]{
				"drawable://" + R.drawable.tw
		};
		String contextType="HumanLife";
		String[] titles ={"中文"};
        intent.putExtra("images",imageUrls);
		intent.putExtra("titles",titles);
		intent.putExtra("contextType", contextType);
		startActivity(intent);
	}

	public void ParcheddryClick(View view) {
		Intent intent = new Intent(this, SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageListFragment.INDEX);
		intent.putExtra("title","乾渴婦人的需要");
		String[] imageUrls = Constants.TEST;
		String contextType="Parcheddry";
		String[] titles ={"中文","English","日本語","Russkiy Yazyk"};
		intent.putExtra("images",imageUrls);
		intent.putExtra("titles",titles);
		intent.putExtra("contextType",contextType);
		startActivity(intent);
	}

	public void PersonageClick(View view) {
		Intent intent = new Intent(this, SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageListFragment.INDEX);
		String[] imageUrls = new String[]{
				"drawable://" + R.drawable.tw,
				"drawable://" + R.drawable.en,
		};
		intent.putExtra("title","上流人的需要");
		String contextType="Personage";
		String[] titles ={"中文","English"};
		intent.putExtra("images",imageUrls);
		intent.putExtra("titles",titles);
		intent.putExtra("contextType",contextType);
		startActivity(intent);
	}

	public void WeakClick(View view) {
		Intent intent = new Intent(this, SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageListFragment.INDEX);
		String[] imageUrls = new String[]{
				"drawable://" + R.drawable.tw,
				"drawable://" + R.drawable.tw,
				"drawable://" + R.drawable.en,
		};
		intent.putExtra("title","軟弱人的需要");
		String contextType="Weak";
		String[] titles ={"中文(男生版)","中文(女生版)","English"};
		intent.putExtra("images",imageUrls);
		intent.putExtra("titles",titles);
		intent.putExtra("contextType",contextType);
		startActivity(intent);
	}


	@Override
	public void onBackPressed() {
		ImageLoader.getInstance().stop();
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item_clear_memory_cache:
				ImageLoader.getInstance().clearMemoryCache();
				return true;
			case R.id.item_clear_disc_cache:
				ImageLoader.getInstance().clearDiskCache();
				return true;
			default:
				return false;
		}
	}

	private void copyTestImageToSdCard(final File testImageOnSdCard) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					InputStream is = getAssets().open(TEST_FILE_NAME);
					FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
					byte[] buffer = new byte[8192];
					int read;
					try {
						while ((read = is.read(buffer)) != -1) {
							fos.write(buffer, 0, read);
						}
					} finally {
						fos.flush();
						fos.close();
						is.close();
					}
				} catch (IOException e) {
					L.w("Can't copy test image onto SD card");
				}
			}
		}).start();
	}
}