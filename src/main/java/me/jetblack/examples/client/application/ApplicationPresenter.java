
package me.jetblack.examples.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import me.jetblack.examples.client.place.NameTokens;
import me.jetblack.examples.client.service.CountryServiceWrapper;
import me.jetblack.examples.client.util.ExtendedList;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class ApplicationPresenter extends Presenter<ApplicationView, ApplicationPresenter.MyProxy> implements ApplicationUiHandlers {
    private final ApplicationView view;
    private CountryServiceWrapper countryService;

    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<ApplicationPresenter> {
    }

    @Inject
    public ApplicationPresenter(EventBus eventBus, ApplicationView view, MyProxy proxy, CountryServiceWrapper countryService) {
        super(eventBus, view, proxy, RevealType.Root);
        this.view = view;
        this.countryService = countryService;
        view.setUiHandlers(this);
    }

    @Override
    protected void onReset() {
        super.onReset();
        initCountries();
        initRegions();
    }

    @Override
    public void changeRegions(Country country) {
        countryService.getRegions(country.getId(), new MethodCallback<ExtendedList<Region>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.displayError(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, ExtendedList<Region> regions) {
                view.displayRegions(regions);
            }
        });
    }

    @Override
    public void getAllRegions() {
        countryService.getCountries(new MethodCallback<ExtendedList<Country>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.displayError(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, ExtendedList<Country> countries) {
                for (Country country : countries) {
                    countryService.getRegions(country.getId(), new MethodCallback<ExtendedList<Region>>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            view.displayError(throwable.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, ExtendedList<Region> regions) {
                            view.displayAllRegions(regions);
                        }
                    });
                }
            }
        });
    }

    private void initRegions() {
        countryService.getCountries(new MethodCallback<ExtendedList<Country>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.displayError(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, ExtendedList<Country> countries) {
                if (!countries.isEmpty()) {
                    countryService.getRegions(countries.get(0).getId(), new MethodCallback<ExtendedList<Region>>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            view.displayError(throwable.getMessage());
                        }

                        @Override
                        public void onSuccess(Method method, ExtendedList<Region> regions) {
                            view.displayRegions(regions);
                        }
                    });
                }
            }
        });
    }

    private void initCountries() {
        countryService.getCountries(new MethodCallback<ExtendedList<Country>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                view.displayError(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, ExtendedList<Country> countries) {
                view.displayCountries(countries);
            }
        });
    }
}
