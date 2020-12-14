package cs3500.animator.model.shape;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;
import cs3500.animator.view.visitors.IReadableShapeVisitor;

/**
 * Shape that represents an Oval where the location is the center, the width is the radius
 * on the X axis, and the height is the radius on the Y axis.
 */
public class Oval extends AShape {
  /**
   * Constructs an oval based on the following parameters.
   *
   * @param location The center of the oval
   * @param width    the radius in the x axis
   * @param height   the radius in the y axis
   * @param color    the color of the shape
   */
  public Oval(Point location, float width, float height, IColor color) {
    super(location, width, height, color, ShapeType.Oval);
  }

  @Override
  public <R> R accept(IReadableShapeVisitor<R> visitor) {
    return visitor.visitOval(new Oval(location, width,  height, color));
  }


  @Override
  public IShape copy() {
    return new Oval(location, width, height, color);
  }
}
