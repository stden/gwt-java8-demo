package me.jetblack.examples.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.Viewport;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;

import javax.inject.Inject;
import java.util.List;

public class ApplicationViewImpl extends ViewImpl implements ApplicationView {

    public interface Binder extends UiBinder<Widget, ApplicationViewImpl> {
    }

    @UiField
    Viewport main;

    @Inject
    public ApplicationViewImpl(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.TYPE_SetMainContent) {
            main.setWidget(content);
        } else {
            super.setInSlot(slot, content);
        }
    }

    @Override
    public void displayCountries(List<Country> countries) {

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void displayRegions(List<Region> regions) {

    }


}
