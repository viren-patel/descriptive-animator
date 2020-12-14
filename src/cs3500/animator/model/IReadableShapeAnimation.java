package cs3500.animator.model;

import cs3500.animator.model.shape.IReadableShape;

/**
 * Represents a shape animation that can only be read from. You can read when it appears, when it
 * disappears, its name, its description, and receive a readable shape that the shape animation
 * is animating.
 */
public interface IReadableShapeAnimation {

  /**
   * Returns the time that this ShapeAnimation appears at.
   *
   * @return the time
   */
  int getAppearTime();

  /**
   * Returns the time that this ShapeAnimation disappears at.
   *
   * @return the time
   */
  int getDisappearTime();

  /**
   * Returns the ID this Shape is mapped to.
   *
   * @return the String ID
   */
  String getShapeId();

  /**
   * Returns a readable version of the shape that the shape animation is animating.
   *
   * @return read above
   */
  IReadableShape getReadableShape();
}
