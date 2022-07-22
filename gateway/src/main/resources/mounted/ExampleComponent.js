/**
 * Example of a component which displays an image, given a URL.
 */
 import * as React from 'react';
 import {
     Component,
     ComponentMeta
 } from '@inductiveautomation/perspective-client';
 
 
 // The 'key' or 'id' for this component type.  Component must be registered with this EXACT key in the Java side as well
 // as on the client side.  In the client, this is done in the index file where we import and register through the
 // ComponentRegistry provided by the perspective-client API.
 export const COMPONENT_TYPE = "test.input.button";
 
 export class ButtonExample extends Component {
     render() {
         // The props we're interested in.
         const { props: { text }, emit } = this.props;
         // Read the 'url' property provided by the perspective gateway via the component 'props'.
 
         // Note that the topmost piece of dom requires the application of an element reference, events, style and
         // className as shown below otherwise the layout won't work, or any events configured will fail. See render
         // of MessengerComponent in Messenger.tsx for more details.
         return (
                <div className="button-example">
                    <button onClick={() => emit('click')}>{text}</button>
                </div>
         );
     }
 }
 
 
 // This is the actual thing that gets registered with the component registry.
 export class ButtonExampleMeta extends ComponentMeta {
 
     getComponentType() {
         return COMPONENT_TYPE;
     }
 
     // the class or React Type that this component provides
     getViewComponent() {
         return ButtonExample;
     }
 
     getDefaultSize() {
         return ({
             width: 100,
             height: 50
         });
     }
 
     // Invoked when an update to the PropertyTree has occurred,
     // effectively mapping the state of the tree to component props.
     getPropsReducer(tree) {
         return {
            text: tree.readString("text", "")
         };
     }
 }
 