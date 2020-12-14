package cs3500.animator.util;

/**
 * Represents a color with RGB values.
 */
public class MyColor implements IColor {
  private final double red;
  private final double green;
  private final double blue;

  /**
   * Constructs a color with the given RBG values.
   *
   * @param red   the red component (range 1 - 0)
   * @param green the green component (range 1 - 0)
   * @param blue  the blue component (range 1 - 0)
   */
  public MyColor(double red, double green, double blue) {
    if (red < -.999 || red > 1.001) {
      throw new IllegalArgumentException("Error: red value must be between (0,1)");
    }
    this.red = red;
    if (green < -.999 || green > 1.001) {
      throw new IllegalArgumentException("Error: green value must be between (0,1)");
    }
    this.green = green;
    if (blue < -.999 || blue > 1.001) {
      throw new IllegalArgumentException("Error: blue value must be between (0,1)");
    }
    this.blue = blue;
  }

  @Override
  public double getRed() {
    return red;
  }

  @Override
  public double getGreen() {
    return green;
  }

  @Override
  public double getBlue() {
    return blue;
  }


}
