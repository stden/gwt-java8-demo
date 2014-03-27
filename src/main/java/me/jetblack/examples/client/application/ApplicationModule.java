package me.jetblack.examples.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {


        bindPresenter(ApplicationPresenter.class, ApplicationView.class, ApplicationViewImpl.class,
                ApplicationPresenter.MyProxy.class);
    }
}
