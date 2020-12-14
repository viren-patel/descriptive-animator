package cs3500.animator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cs3500.animator.model.shape.IReadableShape;

/**
 * This view represents a visual view that plays the animation in a JFrame at a given frame rate.
 * It achieves that by drawing on a drawing panel. Throws an exception for outputting text as this
 * view does not support text output.
 */
public class VisualView extends JFrame implements IGraphicalView {

  protected DrawingPanel jPanelDrawing;
  protected final int WIDTH = 800;
  protected final int HEIGHT = 800;

  /**
   * Constructs the visual view by initialized the JFrame and the drawing panel and scroll panel.
   */
  public VisualView() {
    setSize(new Dimension(WIDTH, HEIGHT));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jPanelDrawing = new DrawingPanel();
    JScrollPane jScrollPane = new JScrollPane(jPanelDrawing);
    jScrollPane.createHorizontalScrollBar();
    jScrollPane.createVerticalScrollBar();
    jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    add(jScrollPane);
    setVisible(true);
  }


  @Override
  public void drawShapes(List<IReadableShape> shapes) {
    Objects.requireNonNull(shapes);
    jPanelDrawing.setDrawingShapes(shapes);
    jPanelDrawing.repaint();
  }

  @Override
  public void setOutput(Appendable out) throws NullPointerException {
    throw new UnsupportedOperationException("Output not needed for the visual view");
  }
}
