
package org.church.peachingthegospel.app.home;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.squareup.picasso.Picasso;
import org.church.peachingthegospel.app.Constants;
import org.church.peachingthegospel.app.InjectableActivity;
import org.church.peachingthegospel.app.R;
import org.church.peachingthegospel.app.activity.SimpleImageActivity;
import org.church.peachingthegospel.app.home.adapter.HomeListViewAdapter;
import org.church.peachingthegospel.app.home.datamodule.HomeListViewItem;
import org.church.peachingthegospel.app.fragment.ImageListFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class HomeActivity extends InjectableActivity {

	private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";
	private HomeListViewAdapter homeListViewAdapter;
	private List<HomeListViewItem> homeListViewItemList;

	@Inject
	protected Picasso picasso;

	@InjectView(R.id.ac_home_listview)
	protected ListView listView;

	@Override
	public int getLayoutResource() {
		return R.layout.ac_home;
	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ButterKnife.inject(this);

		homeListViewItemList=new ArrayList<HomeListViewItem>();
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2858,"人生的奧祕"));
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2903,"軟弱人的需要"));
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2868,"上流人的需要"));
		homeListViewItemList.add(new HomeListViewItem(R.drawable.img_2937,"不道德人的需要"));
		homeListViewAdapter=new HomeListViewAdapter(this,homeListViewItemList,picasso);
		listView.setAdapter(homeListViewAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Constants.ACTIONBAR_STATUS = "Home";
				switch (position) {
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
				"https://lh3.googleusercontent.com/qyoEWCHnWM9otAz8U_xn_l-ndfzKH4LTTMCXzyzQCho=w222-h220-no"
		};
		String contextType="HumanLife";
		String[] titles ={"中文"};
        intent.putExtra("images", imageUrls);
		intent.putExtra("titles",titles);
		intent.putExtra("contextType", contextType);
		startActivity(intent);
	}

	public void ParcheddryClick(View view) {
		Intent intent = new Intent(this, SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImageListFragment.INDEX);
		intent.putExtra("title","不道德人的需要");
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
				"https://lh3.googleusercontent.com/qyoEWCHnWM9otAz8U_xn_l-ndfzKH4LTTMCXzyzQCho=w222-h220-no",
				"https://lh3.googleusercontent.com/BdFtUCzvu2isjXxZnTRtP0ORDbkhZAqlCa3ddwDKQoU=w208-h243-no",
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
				"https://lh3.googleusercontent.com/qyoEWCHnWM9otAz8U_xn_l-ndfzKH4LTTMCXzyzQCho=w222-h220-no",
				"https://lh3.googleusercontent.com/qyoEWCHnWM9otAz8U_xn_l-ndfzKH4LTTMCXzyzQCho=w222-h220-no",
				"https://lh3.googleusercontent.com/BdFtUCzvu2isjXxZnTRtP0ORDbkhZAqlCa3ddwDKQoU=w208-h243-no",
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
		super.onBackPressed();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
}