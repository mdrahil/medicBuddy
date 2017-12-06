package com.cricbuzz.medicbuddy.di;

import android.app.Application;

import com.cricbuzz.medicbuddy.base.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by rahil on 16/8/17.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ActivityBuilderModule.class,
        ServiceModules.class,
        AndroidInjectionModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
