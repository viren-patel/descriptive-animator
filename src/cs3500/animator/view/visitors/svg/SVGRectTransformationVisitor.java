package cs3500.animator.view.visitors.svg;

import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Rotate;
import cs3500.animator.model.shape.transformation.Scale;

/**
 * This class represents a transformation visitor that formats transformations specific to rects.
 * It should only be used when you know that a transformation is for an rectangle.
 */
public class SVGRectTransformationVisitor extends AbstractSVGTransformationVisitor {


  /**
   * Constructs the visitor at the given FPS (used for formatting time).
   *
   * @param fps how many frames pass in a second
   */

  public SVGRectTransformationVisitor(int fps, boolean looping) {
    super(fps, looping);
  }

  @Override
  public String visitScale(Scale s) {
    return formatScale("width", "height", s);
  }

  @Override
  public String visitMove(Move m) {
    return formatMove("x", "y", m);
  }

  @Override
  public String visitRotate(Rotate r) {
    throw new UnsupportedOperationException("SVG does not support rotation");
  }


}
