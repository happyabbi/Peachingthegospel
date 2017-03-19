package org.church.peachingthegospel.app;

import android.content.Context;
import org.church.peachingthegospel.app.di.Modules;
import dagger.ObjectGraph;


/**
 * Created by abraham on 15/7/24.
 */
public class App extends android.app.Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraph();
    }

    public void inject(final Object injectable) {
        objectGraph.inject(injectable);
    }

    public static App get(final Context context) {
        return (App)context.getApplicationContext();

    }

    private void buildGraph() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        inject(this);
    }
}
