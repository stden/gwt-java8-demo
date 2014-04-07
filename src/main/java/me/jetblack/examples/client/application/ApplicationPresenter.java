package me.jetblack.examples.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import me.jetblack.examples.client.place.NameTokens;
import me.jetblack.examples.client.service.CountryService;
import me.jetblack.examples.client.service.CountryServiceWrapper;
import me.jetblack.examples.client.util.ExtendedList;
import me.jetblack.examples.client.util.FutureUtils;
import me.jetblack.examples.client.util.ListUtils;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.future.Future;

import java.util.List;

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
        countryService.getRegions(country.getId()).forEach(res -> view.displayRegions(res));
    }

    @Override
    public void getAllRegions() {
        Future<ExtendedList<Future<ExtendedList<Region>>>> regionFutures = countryService.getCountries()
                .map(
                        countries ->
                                countries.map(country -> countryService.getRegions(country.getId()))
                );

        Future<Future<ExtendedList<ExtendedList<Region>>>> regions = regionFutures.map(FutureUtils::toFutureOfList);

        FutureUtils
                .flatten(regions)
                .map(ListUtils::flatten)
                .handle(err -> view.displayError(err.getMessage()), view::displayAllRegions);

    }

    private void initRegions() {
        countryService.getCountries()
                .map(countries -> countries.get(0).getId())
                .flatMap(countryService::getRegions)
                .handle(err -> view.displayError(err.getMessage()), view::displayRegions);
    }

    private void initCountries() {
        countryService.getCountries().handle(err -> view.displayError(err.getMessage()), view::displayCountries);
    }


}
