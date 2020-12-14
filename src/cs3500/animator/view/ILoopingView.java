package cs3500.animator.view;

/**
 * This interface represents that a view has the ability to loop an animation.
 */
public interface ILoopingView {

  /**
   * Sets a view to either looping or not looping.
   * @param looping whether it is looping or not
   * @param maxTime the max time of the animation
   */
  void setLooping(boolean looping, int maxTime);

}
