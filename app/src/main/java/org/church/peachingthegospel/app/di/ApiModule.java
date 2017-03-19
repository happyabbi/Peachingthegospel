package org.church.peachingthegospel.app.di;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by abraham on 15/7/24.
 */

@Module(

        complete = false,
        library = true
)
public class ApiModule {

        @Provides
        @Singleton
        Endpoint provideEndpoint(){return Endpoints.newFixedEndpoint("https://api.github.com/");}

        @Provides
        @Singleton
        RestAdapter provideRestAdapter(Endpoint endpoint, OkHttpClient client, Gson gson) {
                return new RestAdapter.Builder()
                        .setConverter(new GsonConverter(gson))
                        .setClient(new OkClient(client))
                        .setEndpoint(endpoint)
                        .build();
        }
}
