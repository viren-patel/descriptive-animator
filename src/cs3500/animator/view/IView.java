package cs3500.animator.view;

/**
 * This interface represents a view for the animator. An implementation of this view will output or
 * display the animation in some way. If the view does not need to set output, the function can
 * can simply throw an exception or be ignored.
 */
public interface IView {

  /**
   * Where the view will output information if the view is outputting information.
   *
   * @param out the output
   * @throws NullPointerException if the output is null
   */
  void setOutput(Appendable out) throws NullPointerException;

}
