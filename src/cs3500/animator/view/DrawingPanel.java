package cs3500.animator.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.ShapeType;


/**
 * This class is a drawing JPanel for a visual view. It draws on itself whatever shapes it is given.
 * It achieves this by overriding paintComponent. It keeps track of which shapes need to be drawn.
 */
public class DrawingPanel extends JPanel implements IDrawingPanel {

  private List<IReadableShape> shapes = new ArrayList<>();

  @Override
  public void paintComponent(Graphics g) {
    
    Graphics2D g2d = (Graphics2D)g;
    super.repaint();
    super.paintComponent(g2d);
    for (IReadableShape s : shapes) {
      Color c = new Color((int) (s.getColor().getRed() * 255), (int) (
              s.getColor().getGreen() * 255),
              (int) (s.getColor().getBlue() * 255));
      g2d.setColor(c);
      AffineTransform old = g2d.getTransform();
      g2d.rotate(Math.toRadians(s.getAngle()),
              (int)s.getPosition().getX(), (int)s.getPosition().getY());
      if (s.getType() == ShapeType.Oval) {
        g2d.fillOval((int) s.getPosition().getX(), (int) s.getPosition().getY(),
                (int) s.getWidth(), (int) s.getHeight());
      } else {
        g2d.fillRect((int) s.getPosition().getX(), (int) s.getPosition().getY(),
                (int) s.getWidth(), (int) s.getHeight());
      }
      g2d.setTransform(old);
    }
  }

  @Override
  public void setDrawingShapes(List<IReadableShape> shapes) {
    this.shapes = shapes;
  }
}
