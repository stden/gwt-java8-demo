package me.jetblack.examples.server;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import me.jetblack.examples.server.service.ProductService;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashMap;
import java.util.Map;

public class ExampleServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(ProductService.class).asEagerSingleton();
        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.feature.Trace", "true");
        initParams.put("com.sun.jersey.config.feature.Redirect", "true");
        initParams.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/rest/*").with(GuiceContainer.class, initParams);
    }
}
