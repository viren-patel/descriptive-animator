package cs3500.animator.model.shape.transformation;

import cs3500.animator.view.visitors.IReadableTransformationVisitor;

/**
 * This interface represents a transformation in which you may only read from. You may only read
 * when it starts and disappears and the name of the shape it acts on.
 */
public interface IReadableTransformation {
  /**
   * Gets the time that the transformation starts happening.
   *
   * @return the time
   */
  int getStartTime();

  /**
   * Gets the time that the transformation ends.
   *
   * @return the time
   */
  int getEndTime();

  /**
   * Returns the ID of the shape that the Transformation is on.
   *
   * @return the shapeId
   */
  String getShapeId();


  /**
   * Accepts a IReadableTransformationVisitor and returns a copy of the transformation.
   *
   * @param visitor the visitor being accepted
   * @return returns whatever this visitor decides by passing a copy of itself
   */
  <R> R accept(IReadableTransformationVisitor<R> visitor);

}
