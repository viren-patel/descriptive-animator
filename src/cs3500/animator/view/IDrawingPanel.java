package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.shape.IReadableShape;

/**
 * This interface represents a drawing panel for a visual view. An implementation of this view
 * is able to draw a given list of shapes.
 */
public interface IDrawingPanel {
  /**
   * Sets the shapes that are to be drawn.
   *
   * @param shapes the shapes to be drawn
   */
  void setDrawingShapes(List<IReadableShape> shapes);
}
