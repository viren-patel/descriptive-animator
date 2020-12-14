package cs3500.animator.view.visitors.svg;

import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Rotate;
import cs3500.animator.model.shape.transformation.Scale;

/**
 * This class represents a transformation visitor that formats transformations specific to ovals.
 * It should only be used when you know that a transformation is for an oval.
 */
public class SVGOvalTransformationVisitor extends AbstractSVGTransformationVisitor {

  /**
   * Constructs the visitor at the given FPS (used for formatting time).
   *
   * @param fps how many frames pass in a second
   */
  public SVGOvalTransformationVisitor(int fps, boolean looping) {
    super(fps, looping);
  }

  @Override
  public String visitScale(Scale s) {
    return formatScale("rx", "ry", s);
  }

  @Override
  public String visitMove(Move m) {
    return formatMove("cx", "cy", m);
  }

  @Override
  public String visitRotate(Rotate r) {
    throw new UnsupportedOperationException("SVG does not support rotation");
  }

}

