package io.kgamble.test.common.components.input;

import com.inductiveautomation.ignition.common.jsonschema.JsonSchema;
import com.inductiveautomation.ignition.common.util.LoggerEx;
import com.inductiveautomation.perspective.common.api.ComponentDescriptor;
import com.inductiveautomation.perspective.common.api.ComponentDescriptorImpl;

import io.kgamble.test.common.TestModule;

public interface ExampleButton {
    // unique ID of the component which perfectly matches that provided in the javascript's ComponentMeta implementation
    public static String COMPONENT_ID = "test.input.button";

    public static final LoggerEx logger = LoggerEx.newBuilder().build("ExampleButton");

    /**
     * The schema provided with the component descriptor. Use a schema instead of a plain JsonObject because it gives
     * us a little more type information, allowing the designer to highlight mismatches where it can detect them.
     */
    public static JsonSchema SCHEMA = JsonSchema.parse(TestModule.class.getResourceAsStream("/exampleButton.props.json"));
       

    /**
     * Components register with the Java side ComponentRegistry but providing a ComponentDescriptor.  Here we
     * build the descriptor for this one component. Icons on the component palette are optional.
     */
    public static ComponentDescriptor DESCRIPTOR = ComponentDescriptorImpl.ComponentBuilder.newBuilder()
        .setPaletteCategory(TestModule.COMPONENT_CATEGORY)
        .setId(COMPONENT_ID)
        .setModuleId(TestModule.MODULE_ID)
        .setSchema(SCHEMA) //  this could alternatively be created purely in Java if desired
        .setName("Example Button")
        .addPaletteEntry("", "Example Button", "A simple button example.", null, null)
        .setDefaultMetaName("exampleButton")
        .setResources(TestModule.BROWSER_RESOURCES)
        .build();
}
