package io.kgamble.test.common;

import java.util.Set;

import com.inductiveautomation.perspective.common.api.BrowserResource;
public class TestModule {
    public static final String MODULE_ID = "io.kgamble.test";
    public static final String URL_ALIAS = "test";
    public static final String COMPONENT_CATEGORY = "Test Components";
    public static final Set<BrowserResource> BROWSER_RESOURCES =
        Set.of(
            new BrowserResource(
                "example-components-js",
                String.format("/res/%s/ExampleComponent.js", URL_ALIAS),
                BrowserResource.ResourceType.JS
            ),
            new BrowserResource("example-components-css",
                String.format("/res/%s/ExampleComponent.css", URL_ALIAS),
                BrowserResource.ResourceType.CSS
            )
        );
}
