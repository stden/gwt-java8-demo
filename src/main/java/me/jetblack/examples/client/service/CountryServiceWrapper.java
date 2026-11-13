
package me.jetblack.examples.client.service;

import com.google.inject.Inject;
import me.jetblack.examples.client.util.ExtendedArrayList;
import me.jetblack.examples.client.util.ExtendedList;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

public class CountryServiceWrapper {
    private CountryService service;

    @Inject
    public CountryServiceWrapper(CountryService service) {
        this.service = service;
    }

    public void getCountries(final MethodCallback<ExtendedList<Country>> callback) {
        service.getCountries(new MethodCallback<List<Country>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                callback.onFailure(method, throwable);
            }

            @Override
            public void onSuccess(Method method, List<Country> countries) {
                callback.onSuccess(method, new ExtendedArrayList<>(countries));
            }
        });
    }

    public void getRegions(Integer countryId, final MethodCallback<ExtendedList<Region>> callback) {
        service.getRegions(countryId, new MethodCallback<List<Region>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                callback.onFailure(method, throwable);
            }

            @Override
            public void onSuccess(Method method, List<Region> regions) {
                callback.onSuccess(method, new ExtendedArrayList<>(regions));
            }
        });
    }
}
