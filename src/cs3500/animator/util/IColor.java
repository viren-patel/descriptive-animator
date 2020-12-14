package cs3500.animator.util;

/**
 * This interface represents a color with RGB values. An implementation of this interface is a
 * color.
 */
public interface IColor {
  IColor RED = new MyColor(1, 0, 0);
  IColor BLUE = new MyColor(0, 0, 1);
  IColor GREEN = new MyColor(0, 1, 0);
  IColor ORANGE = new MyColor(1, .6, 0);
  IColor WHITE = new MyColor(1, 1, 1);
  IColor BLACK = new MyColor(0, 0, 0);
  IColor YELLOW = new MyColor(1, 1, 0);

  /**
   * Returns the red component of the color.
   *
   * @return the red value of the color (range 0 - 1)
   */
  double getRed();

  /**
   * Returns the green component of the color.
   *
   * @return the green value of the color (range 0 - 1)
   */
  double getGreen();

  /**
   * Returns the blue component of the color.
   *
   * @return the blue value of the color (range 0 - 1)
   */
  double getBlue();

}
