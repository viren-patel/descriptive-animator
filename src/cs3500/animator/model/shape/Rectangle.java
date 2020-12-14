package cs3500.animator.model.shape;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;
import cs3500.animator.view.visitors.IReadableShapeVisitor;

/**
 * Shape that represents a Rectangle. The location is the location of the bottom left corner, the
 * width the length of the bottom/top side, the height the length of the right/left side.
 */
public class Rectangle extends AShape {

  /**
   * Constructs a rectangle on the given parameters.
   *
   * @param location The location of the bottom left corner.
   * @param width    The length of the top/bottom side
   * @param height   the length of the right/left side
   * @param color    the color of the shape
   */
  public Rectangle(Point location, float width, float height, IColor color) {
    super(location, width, height, color, ShapeType.Rectangle);
  }


  @Override
  public <R> R accept(IReadableShapeVisitor<R> visitor) {
    return visitor.visitRectangle(new Rectangle(location, width,  height, color));
  }

  @Override
  public IShape copy() {
    return new Rectangle(location, width, height, color);
  }
}
