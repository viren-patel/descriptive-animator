package cs3500.animator.model;

import cs3500.animator.model.shape.IShape;

/**
 * This interface represents a shape animation that you may mutate. You may mutate the shape it
 * represents and the appear and disappear times.
 */
public interface IShapeAnimation extends IReadableShapeAnimation {

  /**
   * Sets the time that this Shape appears.
   *
   * @param appearTime the time
   */
  void setAppearTimes(int appearTime, int disappearTime) throws IllegalArgumentException;

  /**
   * Returns the shape that this Animation represents.
   *
   * @return the shape
   */

  IShape getShape();

  IShapeAnimation copy();

}
