package cs3500.animator.view;

/**
 * This class is a view factory that constructs a view based on the type given.
 */
public class ViewFactory {

  /**
   * Creates the appropriate view.
   * @param viewName the view type
   * @return a new view
   * @throws IllegalArgumentException if given an invalid type
   */
  public static IView makeView(String viewName) throws IllegalArgumentException {
    switch (viewName) {
      case "text":
        IView view = new DescriptionView();
        return view;
      case "visual":
        return new VisualView();
      case "svg":
        IView svgView = new SVGView();
        return svgView;
      case "interactive":
        IView interactiveView = new InteractiveView();
        return interactiveView;
      default:
        throw new IllegalArgumentException("invalid view name");
    }
  }


}