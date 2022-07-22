package io.kgamble.test.gateway;

import java.util.List;
import java.util.Optional;

import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import com.inductiveautomation.ignition.gateway.web.models.ConfigCategory;
import com.inductiveautomation.ignition.gateway.web.models.IConfigTab;
import com.inductiveautomation.perspective.common.api.ComponentRegistry;
import com.inductiveautomation.perspective.gateway.api.PerspectiveContext;

import io.kgamble.test.common.TestModule;
import io.kgamble.test.common.components.input.ExampleButton;

/**
 * Class which is instantiated by the Ignition platform when the module is loaded in the gateway scope.
 */
public class TestGatewayHook extends AbstractGatewayModuleHook {

    private static final LoggerEx log = LoggerEx.newBuilder().build("test.gateway.TestGatewayHook");

    private GatewayContext gatewayContext;
    private PerspectiveContext perspectiveContext;
    private ComponentRegistry componentRegistry;

    /**
     * Called to before startup. This is the chance for the module to add its extension points and update persistent
     * records and schemas. None of the managers will be started up at this point, but the extension point managers will
     * accept extension point types.
     */
    @Override
    public void setup(GatewayContext context) {
        this.gatewayContext = context;
        log.info("Setting up Test module.");
    }

    /**
     * Called to initialize the module. Will only be called once. Persistence interface is available, but only in
     * read-only mode.
     */
    @Override
    public void startup(LicenseState activationState) {
        log.info("Starting up TestGatewayHook!");

        this.perspectiveContext = PerspectiveContext.get(this.gatewayContext);
        this.componentRegistry = this.perspectiveContext.getComponentRegistry();

        if (this.componentRegistry != null) {
            log.info("Registering Test components.");
            this.componentRegistry.registerComponent(ExampleButton.DESCRIPTOR);
        } else {
            log.error("Reference to component registry not found, Test Components will fail to function!");
        }

    }

    /**
     * Called to shutdown this module. Note that this instance will never be started back up - a new one will be created
     * if a restart is desired
     */
    @Override
    public void shutdown() {
        log.info("Shutting down Test module and removing registered components.");
        if (this.componentRegistry != null) {
            this.componentRegistry.removeComponent(ExampleButton.COMPONENT_ID);
        } else {
            log.warn("Component registry was null, could not unregister Test.");
        }
    }

    /**
     * A list (may be null or empty) of panels to display in the config section. Note that any config panels that are
     * part of a category that doesn't exist already or isn't included in {@link #getConfigCategories()} will
     * <i>not be shown</i>.
     */
    @Override
    public List<? extends IConfigTab> getConfigPanels() {
        return null;
    }

    /**
     * A list (may be null or empty) of custom config categories needed by any panels returned by  {@link
     * #getConfigPanels()}
     */
    @Override
    public List<ConfigCategory> getConfigCategories() {
        return null;
    }

    /**
     * @return the path to a folder in one of the module's gateway jar files that should be mounted at
     * /res/module-id/foldername
     */
    @Override
    public Optional<String> getMountedResourceFolder() {
        return Optional.of("mounted");
    }

    /**
     * Used by the mounting underneath /res/module-id/* and /main/data/module-id/* as an alternate mounting path instead
     * of your module id, if present.
     */
    @Override
    public Optional<String> getMountPathAlias() {
        return Optional.of(TestModule.URL_ALIAS);
    }

    /**
     * @return {@code true} if this is a "free" module, i.e. it does not participate in the licensing system. This is
     * equivalent to the now defunct FreeModule attribute that could be specified in module.xml.
     */
    @Override
    public boolean isFreeModule() {
        return true;
    }
}
