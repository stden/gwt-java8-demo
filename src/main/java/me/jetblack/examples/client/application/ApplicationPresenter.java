package me.jetblack.examples.client.application;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import me.jetblack.examples.client.place.NameTokens;
import me.jetblack.examples.client.service.ProductRestService;

public class ApplicationPresenter extends Presenter<ApplicationView, ApplicationPresenter.MyProxy> {


    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
    private ProductRestService service;


    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<ApplicationPresenter> {
    }

    @Inject
    public ApplicationPresenter(EventBus eventBus, ApplicationView view, MyProxy proxy, ProductRestService service) {
        super(eventBus, view, proxy, RevealType.Root);
        this.service = service;
    }

    @Override
    protected void onReset() {
        super.onReset();
        service.getProducts()
                .map((products) -> products.get(0).getId())
                .flatMap(service::getDetails)
                .forEach(this::alert);



    }

    private void alert(Object any) {
        Window.alert(any.toString());
    }

}
