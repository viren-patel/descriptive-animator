package cs3500.animator.view.visitors;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Rectangle;

/**
 * This interface is a visitor for readable shapes. Its purpose is to be used by the views
 * to accomplish tasks that rely on concrete objects. This supports ovals and rectangles.
 * To support more, it can be extended.
 *
 * @param <R> The return type of whatever task this visitor is trying to accomplish.
 */
public interface IReadableShapeVisitor<R> {
  /**
   * Does an operation specific to a oval.
   *
   * @param o the oval being operated on.
   * @return whatever the visitor is attempting to accomplish.
   */
  R visitOval(Oval o);

  /**
   * Does an operation specific to a rectangle.
   *
   * @param r the rectangle being operated on.
   * @return whatever the visitor is attempting to accomplish.
   */
  R visitRectangle(Rectangle r);

  /**
   * Gives the visitor to the shape to accept. When the shape accepts it, it will
   * return a copy of itself, resolving the type.
   *
   * @param s the shape being resolved and operated on
   * @return whatever the visitor is attempting to accomplish
   */
  R apply(IReadableShapeAnimation s);
}
