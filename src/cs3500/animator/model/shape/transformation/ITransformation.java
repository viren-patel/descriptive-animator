package cs3500.animator.model.shape.transformation;

import java.util.Comparator;

import cs3500.animator.model.shape.IShape;

/**
 * Represents a transformation on a shape during an animation.
 */
public interface ITransformation<T> extends IReadableTransformation {

  /**
   * Comparator for transformations based on when they start.
   */
  class TransformationComparator implements Comparator<ITransformation> {
    @Override
    public int compare(ITransformation transformation1, ITransformation transformation2) {
      return transformation1.getStartTime() - transformation2.getStartTime();
    }
  }

  /**
   * Checks if a Transformation overlaps with the given Transformation.
   *
   * @param other the other transformation
   * @return whether they overlap
   */
  boolean checkOverlap(ITransformation other);

  /**
   * Applies the transformation on the given shape at the given time.
   *
   * @param time  the time to tween to
   * @param shape the shape to be modified
   */
  void tween(int time, IShape shape);

  /**
   * Returns what this transformation changes from.
   *
   * @return what this transformation changes from.
   */

  T getFrom();

  /**
   * Returns what this transformation changes to.
   *
   * @return what this transformation changes to.
   */
  T getTo();

}
