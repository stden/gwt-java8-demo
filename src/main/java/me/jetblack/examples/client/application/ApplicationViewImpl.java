package me.jetblack.examples.client.application;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.tree.Tree;
import me.jetblack.examples.shared.Country;
import me.jetblack.examples.shared.Region;

import javax.inject.Inject;
import java.util.List;

public class ApplicationViewImpl extends ViewWithUiHandlers<ApplicationUiHandlers> implements ApplicationView {

    public interface Binder extends UiBinder<Widget, ApplicationViewImpl> {
    }

    @UiField
    Tree<Country, String> tree;

    @UiField(provided = true)
    TreeStore<Country> store = new TreeStore<Country>(new ModelKeyProvider<Country>() {
        @Override
        public String getKey(Country item) {
            return item.getName();
        }
    });

    @UiField
    Viewport main;
    @UiField
    VerticalLayoutContainer regionsPanel;
    @UiField
    VerticalLayoutContainer allRegionsContainer;
    @UiField
    TextButton allRegionsButton;
    @UiField
    TextButton clearRegionsButton;

    @Inject
    public ApplicationViewImpl(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        initTree();
        initButton();
        initClearButton();
    }

    private void initClearButton() {
        clearRegionsButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                allRegionsContainer.clear();
            }
        });
    }

    private void initButton() {
        allRegionsButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                getUiHandlers().getAllRegions();
            }
        });
    }

    private void initTree() {
        tree.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        tree.getSelectionModel().addSelectionChangedHandler(new SelectionChangedEvent.SelectionChangedHandler<Country>() {
            @Override
            public void onSelectionChanged(SelectionChangedEvent<Country> event) {
                getUiHandlers().changeRegions(event.getSelection().get(0));
            }
        });
    }

    @Override
    public void displayCountries(List<Country> countries) {
        store.clear();
        store.add(countries);
    }

    @Override
    public void displayError(String message) {
        Window.alert(message);
    }

    @Override
    public void displayRegions(List<Region> regions) {
        regionsPanel.clear();
        for (Region region : regions) {
            regionsPanel.add(new Label(region.toString()));
        }
    }

    @Override
    public void displayAllRegions(List<Region> regions) {
        allRegionsContainer.clear();
        for(Region region: regions) {
            allRegionsContainer.add(new Label(region.toString()));
        }
    }

    @UiFactory
    public ValueProvider<Country, String> createValueProvider() {
        return new ValueProvider<Country, String>() {

            @Override
            public String getValue(Country object) {
                return object.getName();
            }

            @Override
            public void setValue(Country object, String value) {
            }

            @Override
            public String getPath() {
                return "name";
            }
        };
    }


}
