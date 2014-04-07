package me.jetblack.examples.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import me.jetblack.examples.client.place.NameTokens;
import me.jetblack.examples.client.service.CallbackCountryService;
import me.jetblack.examples.client.service.CountryService;
import me.jetblack.examples.client.util.ExtendedList;
import me.jetblack.examples.client.util.FutureUtils;
import me.jetblack.examples.client.util.ListUtils;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.future.Future;

import java.util.List;

public class ApplicationPresenter extends Presenter<ApplicationView, ApplicationPresenter.MyProxy> {


    @ContentSlot
    public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();
    private final ApplicationView view;
    private CountryService countryService;

    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<ApplicationPresenter> {
    }

    @Inject
    public ApplicationPresenter(EventBus eventBus, ApplicationView view, MyProxy proxy, CountryService countryService) {
        super(eventBus, view, proxy, RevealType.Root);
        this.view = view;
        this.countryService = countryService;
    }

    @Override
    protected void onReset() {
        super.onReset();
        Future<ExtendedList<Future<ExtendedList<Region>>>> regionFutures = countryService.getCountries()
                .map(
                        countries ->
                                countries.map(country -> countryService.getRegions(country.getId()))
                );
        FutureUtils
                .flatten(regionFutures.map(FutureUtils::toFutureOfList))
                .map(ListUtils::flatten)
                .forEach();

    }

    public void test() {
        CallbackCountryService countryService = GWT.create(CallbackCountryService.class);
        countryService.getCountries(new MethodCallback<List<Country>>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                view.displayError(exception.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<Country> response) {
                countryService.getRegions(response.get(0).getId(), new MethodCallback<List<Region>>() {
                    @Override
                    public void onFailure(Method method, Throwable exception) {
                        view.displayError(exception.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, List<Region> response) {
                        view.displayRegions(response);
                    }
                });
            }
        });

    }

    public void futureTest() {
        countryService.getCountries().handle(t -> view.displayError(t.getMessage()), view::displayCountries);

    }

}
