package cs3500.animator.model.shape;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;

/**
 * Represents a Shape.
 */
public interface IShape extends IReadableShape {
  /**
   * Sets a shape's color field.
   *
   * @param c the Color
   */
  void setColor(IColor c);

  /**
   * Sets a shape's width field.
   *
   * @param width the width
   */
  void setWidth(float width);

  /**
   * Sets a shape's height field.
   *
   * @param height the height
   */
  void setHeight(float height);

  /**
   * Sets a shape's location.
   *
   * @param location a 2d Point
   */
  void setLocation(Point location);

  /**
   * Sets a  shape's angle from the x axis.
   * @param angle the shape's angle
   */
  void setAngle(float angle);

  /**
   * Returns a copy of this shape.
   * @return a copy of this shape.
   */
  IShape copy();
}
