package cs3500.animator.model;

import java.util.Objects;

import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.IShape;

/**
 * Represents a shape that may be animated. The shape animation has a appear and dissapear time.
 */
public class ShapeAnimation implements IShapeAnimation {
  private final String shapeId;
  private IShape shape;
  private int appearTime;
  private int disappearTime;

  /**
   * Constructs a shape animation that allows the given shape to be animatable.
   *
   * @param shapeId the id of the shape
   * @param shape   the shape being animated
   */
  public ShapeAnimation(String shapeId, IShape shape) {
    Objects.requireNonNull(shapeId);
    Objects.requireNonNull(shape);
    this.shapeId = shapeId;
    this.shape = shape;
    this.appearTime = 0;
    this.disappearTime = 0;
  }

  @Override
  public void setAppearTimes(int appearTime, int disappearTime) throws IllegalArgumentException {
    if (disappearTime < appearTime) {
      throw new IllegalArgumentException("appear time must be before disappear time");
    } else {
      this.appearTime = appearTime;
      this.disappearTime = disappearTime;
    }
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public IShape getShape() {
    return this.shape;
  }

  @Override
  public IShapeAnimation copy() {
    IShapeAnimation sa = new ShapeAnimation(shapeId, shape.copy());
    sa.setAppearTimes(appearTime, disappearTime);
    return sa;
  }

  @Override
  public String getShapeId() {
    return this.shapeId;
  }

  @Override
  public IReadableShape getReadableShape() {
    return this.shape;
  }
}