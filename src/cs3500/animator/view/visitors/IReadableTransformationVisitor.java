package cs3500.animator.view.visitors;

import cs3500.animator.model.shape.transformation.ChangeColor;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Rotate;
import cs3500.animator.model.shape.transformation.Scale;

/**
 * This interface is a visitor for readable transformations. Its purpose is to be used by the views
 * to accomplish tasks that rely on concrete objects. This supports scale, changecolor, and move
 * transformations. To support more, it can be extended.
 *
 * @param <R> The return type of whatever task this visitor is trying to accomplish.
 */
public interface IReadableTransformationVisitor<R> {
  /**
   * Does an operation specific to a scale transformation.
   *
   * @param s the scale transformation being operated on.
   * @return whatever the visitor is attempting to accomplish.
   */
  R visitScale(Scale s);

  /**
   * Does an operation specific to a color transformation.
   *
   * @param c the color transformation being operated on.
   * @return whatever the visitor is attempting to accomplish.
   */
  R visitChangeColor(ChangeColor c);

  /**
   * Does an operation specific to a move transformation.
   *
   * @param m the move transformation being operated on.
   * @return whatever the visitor is attempting to accomplish.
   */
  R visitMove(Move m);

  /**
   * Does an operation specific to a rotate transformation.
   *
   * @param r the rotate transformation being operated on.
   * @return whatever the visitor is attempting to accomplish.
   */
  R visitRotate(Rotate r);

  /**
   * Gives the visitor to the transformation to accept. When the transformation accepts it, it will
   * return a copy of itself, resolving the type.
   *
   * @param t the transformation being resolved and operated on
   * @return whatever the visitor is attempting to accomplish
   */
  R apply(IReadableTransformation t);
}
