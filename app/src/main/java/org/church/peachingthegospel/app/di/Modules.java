package org.church.peachingthegospel.app.di;


import org.church.peachingthegospel.app.App;

public final class Modules {
    public static Object list(final App app) {
        return new AppModule(app);
    }
}
