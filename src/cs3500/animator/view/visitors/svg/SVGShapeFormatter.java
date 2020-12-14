package cs3500.animator.view.visitors.svg;

import java.util.List;
import java.util.Objects;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.transformation.IReadableTransformation;

/**
 * This class represents a formatter for making svg out of shape animations. This class should be
 * used by the svg view to do all the formatting for the given shape animations. This class uses
 * visitors to do the formatting. In a way, this class is just a wrapper for the contained visitor
 * for reasons explained below.
 */
public class SVGShapeFormatter implements ISVGShapeFormatter {

  protected boolean looping;

  /**
   * Constructs either a looping or non looking shape formatter.
   * @param looping whether the formatter will account for looping or not.
   */
  public SVGShapeFormatter(boolean looping) {
    this.looping = looping;
  }

  @Override
  public String generateSVG(IReadableShapeAnimation s,
                            List<IReadableTransformation> transformations,
                            int fps) throws IllegalArgumentException {
    Objects.requireNonNull(s);
    Objects.requireNonNull(transformations);
    if (fps <= 0) {
      throw new IllegalArgumentException("Invalid fps");
    }
    return new SVGShapeVisitor(transformations, fps, looping).apply(s);
  }

  @Override
  public void setLooping(boolean looping) {
    this.looping = looping;
  }


}
