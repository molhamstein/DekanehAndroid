package brain_socket.com.dekaneh.dagger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import brain_socket.com.dekaneh.application.AppSchedulerProvider;
import brain_socket.com.dekaneh.application.SchedulerProvider;
import brain_socket.com.dekaneh.fragment.login.LoginFragmentPresenter;
import brain_socket.com.dekaneh.fragment.login.LoginFragmentVP;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext(){
        return activity;
    }

    @Provides
    AppCompatActivity providesActivity(){
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    LoginFragmentVP.Presenter<LoginFragmentVP.View> provideLoginPresenter(LoginFragmentPresenter<LoginFragmentVP.View> presenter) {

        return presenter;

    }

}
