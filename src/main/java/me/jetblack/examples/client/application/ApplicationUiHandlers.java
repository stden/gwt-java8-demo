package me.jetblack.examples.client.application;

import com.gwtplatform.mvp.client.UiHandlers;
import me.jetblack.examples.shared.Country;

public interface ApplicationUiHandlers extends UiHandlers {

    void changeRegions(Country country);

    void getAllRegions();

}
