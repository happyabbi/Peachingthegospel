package org.church.peachingthegospel.app.di;

import org.church.peachingthegospel.app.activity.SimpleImageActivity;
import org.church.peachingthegospel.app.fragment.ImageGridFragment;
import org.church.peachingthegospel.app.fragment.ImageListFragment;
import org.church.peachingthegospel.app.home.HomeActivity;

import dagger.Module;

/**
 * Created by abraham on 15/7/25.
 */

@Module(
        injects = {
                HomeActivity.class,
                SimpleImageActivity.class,
                ImageListFragment.class
        },
        complete = false,
        library = true
)
public class UiModule {
}
