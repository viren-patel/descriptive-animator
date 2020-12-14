package cs3500.animator.view.visitors.description;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.view.AbstractTextView;
import cs3500.animator.view.visitors.IReadableShapeVisitor;

/**
 * This class is a shape visitor. Its job is to take readable shapes and create descriptions of them
 * based on their concrete implementation. Keeps track of frames per second to decide time.
 */
public class DescriptionShapeVisitor implements IReadableShapeVisitor<String> {

  private int fps;

  /**
   * Constructs a description shape visitor that can format descriptions for various shapes.
   *
   * @param fps how many frames pass in a second
   */
  public DescriptionShapeVisitor(int fps) {
    this.fps = fps;
  }

  @Override
  public String visitOval(Oval o) {
    StringBuilder sb = new StringBuilder();
    sb.append("Type: oval").append("\n");
    sb.append("Center: ").append(o.getPosition().toString());
    sb.append(", X radius: ").append(Float.toString(o.getWidth()));
    sb.append(", Y radius: ").append(Float.toString(o.getHeight()));
    sb.append(formatColor(o));
    return sb.toString();
  }

  @Override
  public String visitRectangle(Rectangle r) {
    StringBuilder sb = new StringBuilder();
    sb.append("Type: rectangle").append("\n");
    sb.append("Corner: ").append(r.getPosition().toString());
    sb.append(", Width: ").append(Float.toString(r.getWidth()));
    sb.append(", Height: ").append(Float.toString(r.getHeight()));
    sb.append(formatColor(r));
    return sb.toString();
  }

  @Override
  public String apply(IReadableShapeAnimation s) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("\nName: ").append(s.getShapeId()).append("\n");
    stringBuilder.append(s.getReadableShape().accept(this));
    stringBuilder.append("\n");
    stringBuilder.append(formatAppearTimes(s.getAppearTime(), s.getDisappearTime(), fps));
    return stringBuilder.toString();
  }

  /**
   * Formats the appear and disappear times in seconds.
   *
   * @param appearTime    the shape's appear time
   * @param disappearTime the shape's disappear time
   * @param fps           how many frames in a second
   * @return a formatted string of appear and disappear times
   */
  private String formatAppearTimes(float appearTime, float disappearTime, int fps) {
    String appearTimeInSeconds = AbstractTextView.toSeconds(appearTime, fps);
    String disappearTimeInSeconds = AbstractTextView.toSeconds(disappearTime, fps);
    StringBuilder sb = new StringBuilder();
    sb.append("Appears at t=").append(appearTimeInSeconds).append("\n");
    sb.append("Disappears at t=").append(disappearTimeInSeconds);
    return sb.toString();
  }

  /**
   * Formats the color to (r,g,b).
   *
   * @param s the shape
   * @return the formatted color
   */
  private String formatColor(IReadableShape s) {
    StringBuilder sb = new StringBuilder();
    sb.append(", Color: (");
    sb.append(String.format("%.1f", s.getColor().getRed())).append(",");
    sb.append(String.format("%.1f", s.getColor().getGreen())).append(",");
    sb.append(String.format("%.1f", s.getColor().getBlue())).append(")");
    return sb.toString();
  }
}
