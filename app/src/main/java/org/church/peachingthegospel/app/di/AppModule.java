package org.church.peachingthegospel.app.di;

import android.app.Application;

import org.church.peachingthegospel.app.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abraham on 15/7/24.
 */

@Module(
        includes = {
                DataModule.class,
                UiModule.class
        },
    injects = {
            App.class
    }
)
public class AppModule {
    private App app;
    public AppModule(App app){this.app = app;}
    @Provides
    public Application provideApplication() {
        return app;
    }
}
