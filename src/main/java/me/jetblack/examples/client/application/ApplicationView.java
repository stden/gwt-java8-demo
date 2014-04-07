package me.jetblack.examples.client.application;

import com.gwtplatform.mvp.client.View;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;

import java.util.List;

public interface ApplicationView extends View {

    void displayCountries(List<Country> countries);

    void displayError(String message);

    void displayRegions(List<Region> regions);

}
