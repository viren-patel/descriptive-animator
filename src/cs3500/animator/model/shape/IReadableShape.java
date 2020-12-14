package cs3500.animator.model.shape;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;
import cs3500.animator.view.visitors.IReadableShapeVisitor;

/**
 * This interface represents all the attributes that you can read from a shape. An implementation
 * is essentially an immutable shape that you can only read from. The attributes of the shape
 * are position, color, width, and height.
 */
public interface IReadableShape {

  /**
   * Gets the position of the shape. What the position represents is up to implementation.
   *
   * @return a Point representing the x,y position on a cartesian plane
   */
  Point getPosition();

  /**
   * Gets the color of the shape.
   *
   * @return an IColor which you can read RGB values from
   */
  IColor getColor();

  /**
   * Returns the "width" of the shape. What the width represents is up to implementation.
   *
   * @return the "width"
   */
  float getWidth();

  /**
   * Returns the "height" of the shape. What the height represents is up to implementation.
   *
   * @return the "height"
   */
  float getHeight();


  /**
   * Returns the type of shape this shape is.
   *
   * @return the type of shape
   */
  ShapeType getType();

  /**
   * Accepts a readable shape visitor and returns a copy of the shape.
   *
   * @param visitor the visitor being accepted
   */
  <R> R accept(IReadableShapeVisitor<R> visitor);

  /**
   * Returns the angle of this shape.
   * @return the angle of this shape
   */
  float getAngle();

}
