
package org.church.peachingthegospel.app.fragment;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import org.church.peachingthegospel.app.*;
import org.church.peachingthegospel.app.activity.SimpleImageActivity;


public abstract class AbsListViewBaseFragment extends BaseFragment {

	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";

	protected AbsListView listView;

	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;

	@Override
	public void onResume() {
		super.onResume();
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item_pause_on_scroll:
				pauseOnScroll = !pauseOnScroll;
				item.setChecked(pauseOnScroll);
				return true;
			case R.id.item_pause_on_fling:
				pauseOnFling = !pauseOnFling;
				item.setChecked(pauseOnFling);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
    protected void startImagePagerActivity(int position) {
        Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
        intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
        intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
        startActivity(intent);
    }

	protected void startImagePagerActivity(int position,String[] images) {
		Intent intent = new Intent(getActivity(), SimpleImageActivity.class);
		intent.putExtra(Constants.Extra.FRAGMENT_INDEX, ImagePagerFragment.INDEX);
		intent.putExtra(Constants.Extra.IMAGE_POSITION, position);
        intent.putExtra("images",images);
		startActivity(intent);
	}
}
