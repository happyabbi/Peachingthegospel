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

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.activity.SimpleImageActivity;
import org.church.peachingthegospel.app.adapter.ListImageAdapter;
import org.church.peachingthegospel.app.R;

/**

 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImageListFragment extends AbsListViewBaseFragment {

	public static final int INDEX = 0;
	String[] imageUrls = Constants.HUMANLIFE_TW_IMAGES;

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	public String getContextType() {
		return contextType;
	}

	public void setContextType(String contextType) {
		this.contextType = contextType;
	}

	String contextType="";

	String[] titles=null;
	DisplayImageOptions options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fr_image_list, container, false);
		listView = (ListView) rootView.findViewById(android.R.id.list);
		listView.setAdapter(new ListImageAdapter(getActivity(), imageUrls,titles));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (getContextType().equals("HumanLife")){ //人生的奧祕
					if(getTitles()[position].equals("中文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						intent.putExtra("title", "中文");
						String[] imageUrls = Constants.HUMANLIFE_TW_IMAGES;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("英文")){

					}else if(getTitles()[position].equals("日文")){

					}else if(getTitles()[position].equals("俄文")) {

					}
				}else if (getContextType().equals("Personage")){ //上流人的需要
					if(getTitles()[position].equals("中文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						intent.putExtra("title", "中文");

						String[] imageUrls = Constants.PERSONAGE_TW;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("英文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						intent.putExtra("title", "英文");

						String[] imageUrls = Constants.PERSONAGE_EN;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("日文")){

					}else if(getTitles()[position].equals("俄文")) {

					}
				}else if (getContextType().equals("Weak")) { //軟弱人的需要
					if(getTitles()[position].equals("中文(男生版)")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						intent.putExtra("title", "中文(男生版)");

						String[] imageUrls = Constants.WEAK_TW_FEMALE;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}if(getTitles()[position].equals("中文(女生版)")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra("title", "中文(女生版)");
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						String[] imageUrls = Constants.WEAK_TW_MALE;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("英文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra("title", "英文");
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						String[] imageUrls = Constants.WEAK_EN;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("日文")){

					}else if(getTitles()[position].equals("俄文")) {

					}
				}else if (getContextType().equals("Parcheddry")){ //乾渴的婦人
					if(getTitles()[position].equals("中文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra("title", "中文");
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						String[] imageUrls = Constants.PARCHEDDRY_TW;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("英文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra("title", "英文");
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						String[] imageUrls = Constants.PARCHEDDRY_EN;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("日文")){
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						intent.putExtra("title", "日文");
						String[] imageUrls = Constants.PARCHEDDRY_JP;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}else if(getTitles()[position].equals("俄文")) {
						Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
						intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageGridFragment.INDEX);
						intent.putExtra("title", "俄文");

						String[] imageUrls = Constants.PARCHEDDRY_RUS;
						intent.putExtra("images",imageUrls);
						startActivity(intent);
					}
				}
//				startImagePagerActivity(position);

			}
		});
		return rootView;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ListImageAdapter.AnimateFirstDisplayListener.displayedImages.clear();
	}

	public String[] getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String[] imageUrls) {
		this.imageUrls = imageUrls;
	}
}