package cs3500.animator.util;

/**
 * Represents an immutable 2-d Point.
 */
public class Point {
  private final float x;
  private final float y;

  public Point(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns this Point's x coordinate.
   *
   * @return the x coordinate
   */
  public float getX() {
    return this.x;
  }

  /**
   * Returns this Point's y coordinate.
   *
   * @return the y coordinate
   */
  public float getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "(" + Float.toString(this.x) + "," + Float.toString(this.y) + ")";
  }
}
