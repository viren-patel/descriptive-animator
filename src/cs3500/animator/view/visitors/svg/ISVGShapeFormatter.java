package cs3500.animator.view.visitors.svg;

import java.util.List;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.transformation.IReadableTransformation;

/**
 * This interface represents a formatter for a shape animation in the svg view. An implementation
 * of this interface should be able to generate a svg block given a shape animation, the list
 * of animations FOR THE SHAPE (<- very important), and a given FPS.
 */
public interface ISVGShapeFormatter {

  /**
   * Generates a formatted svg block for the shape.
   *
   * @param s               the shape animation being formatted
   * @param transformations the shape's transformations
   * @param fps             how many frames pass in a second (for timing)
   * @return a formatted string in svg format
   * @throws IllegalArgumentException if the FPS is invalid
   */
  String generateSVG(IReadableShapeAnimation s, List<IReadableTransformation> transformations,
                     int fps) throws IllegalArgumentException;

  /**
   * Enables or disables looping.
   * @param looping whether the animation is looping or not
   */
  void setLooping(boolean looping);
}
