package cs3500.animator.model.shape;

import java.util.Objects;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;

/**
 * Abstract class for Shapes. Contains the basic attributes of shapes such as location, width,
 * height, color, and type. Implements basic getters and setters.
 */
public abstract class AShape implements IShape {
  protected Point location;
  protected float width;
  protected float height;
  protected IColor color;
  protected ShapeType type;
  protected float angle;

  /**
   * Abstract constructor for a shape. Initializes the basic attributes shared by all shapes. The
   * exact interpretation of those attributes is up to concrete implementation.
   *
   * @param location the "location" of the shape
   * @param width    the "width" of the shape
   * @param height   the "height" of the shape
   * @param color    the color of the shape
   * @param type     the type of shape
   */
  public AShape(Point location, float width, float height, IColor color, ShapeType type) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("width and height must be positive");
    } else if (location == null || color == null) {
      throw new IllegalArgumentException("position and color must be non-null");
    }
    Objects.requireNonNull(type);
    this.location = location;
    this.width = width;
    this.height = height;
    this.color = color;
    this.type = type;
  }

  @Override
  public Point getPosition() {
    return this.location;
  }

  @Override
  public IColor getColor() {
    return this.color;
  }

  @Override
  public float getWidth() {
    return this.width;
  }

  @Override
  public float getHeight() {
    return this.height;
  }

  @Override
  public float getAngle() {
    return this.angle;
  }

  @Override
  public void setColor(IColor color) {
    this.color = color;
  }

  @Override
  public void setWidth(float width) {
    this.width = width;
  }

  @Override
  public void setHeight(float height) {
    this.height = height;
  }

  @Override
  public void setLocation(Point location) {
    this.location = location;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public void setAngle(float a) {
    this.angle = a % 360;
  }

}
