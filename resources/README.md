The only changes were made to the views.
Originally, there was only IView which had all the methods
for all the views. We changed this so that there is interface
segregation. Now, we have IGraphicalView, ITextView, ILoopingView,
and IInteractiveView, which extends all three. Each interface 
contains the methods for their respective views, and each concrete
view implements their respective interface. All these views
all extend IView.

Now, the controllers can only hold their respective views.
Because of this, each controller must make sure they have the correct
type of view so that they can use the correct methods.

SVGView changed to support looping. To achieve this, a boolean
looping was added to specify whether it is looping or not
and the appropriate tags where coded in to be added if there is 
looping.