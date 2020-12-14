package cs3500.animator.view;

import java.util.List;

import cs3500.animator.model.shape.IReadableShape;

public interface IGraphicalView extends IView {
  /**
   * Draws the list of shapes as is.
   *
   * @param shapes the list of shapes
   */
  void drawShapes(List<IReadableShape> shapes);
}
