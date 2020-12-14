package cs3500.animator.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import cs3500.animator.model.shape.IReadableShape;

public abstract class AbstractVisualView extends JFrame implements IView {
  protected DrawingPanel jPanelDrawing;
  protected final int WIDTH = 800;
  protected final int HEIGHT = 800;

  /**Constructs an abstract visual view with a blank panel and a default size of 800, 800.
   *
   */
  public AbstractVisualView() {
    jPanelDrawing = new DrawingPanel();
    setSize(new Dimension(WIDTH, HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void drawShapes(List<IReadableShape> shapes) {
    jPanelDrawing.setDrawingShapes(shapes);
    jPanelDrawing.repaint();
  }
}
