package cs3500.animator.view.visitors.description;

import cs3500.animator.model.shape.transformation.ChangeColor;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Rotate;
import cs3500.animator.model.shape.transformation.Scale;
import cs3500.animator.view.AbstractTextView;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;

/**
 * This class represents a transformation visitor that formats transformations into a description.
 * It can return a text description of scale, move, and changeColor transformations. It keeps
 * track of frames per second to format in the right time.
 */
public class DescriptionTransformationVisitor implements IReadableTransformationVisitor<String> {

  private int fps;

  /**
   * Constructs a new visitor that can make descriptions of various transformations.
   *
   * @param fps how many frames pass in a second, which will decide part of the format
   */
  public DescriptionTransformationVisitor(int fps) {
    this.fps = fps;
  }

  @Override
  public String visitScale(Scale s) {
    StringBuilder sb = new StringBuilder();
    sb.append("Shape ").append(s.getShapeId());
    sb.append(" scales from Width: ").append(String.format("%.1f", s.getFrom().getKey()));
    sb.append(", Height: ").append(String.format("%.1f", s.getFrom().getValue()));
    sb.append(" to Width: ").append(s.getTo().getKey());
    sb.append(", Height: ").append(s.getTo().getValue());
    return sb.toString();
  }

  @Override
  public String visitChangeColor(ChangeColor c) {
    StringBuilder sb = new StringBuilder();
    sb.append("Shape ").append(c.getShapeId());
    sb.append(" changes color from (").append(String.format("%.1f", c.getFrom().getRed()));
    sb.append(",").append(String.format("%.1f", c.getFrom().getGreen()));
    sb.append(",").append(String.format("%.1f", c.getFrom().getBlue())).append(")");
    sb.append(" to (").append(String.format("%.1f", c.getTo().getRed()));
    sb.append(",").append(String.format("%.1f", c.getTo().getGreen()));
    sb.append(",").append(String.format("%.1f", c.getTo().getBlue())).append(")");
    return sb.toString();
  }

  @Override
  public String visitMove(Move m) {
    StringBuilder sb = new StringBuilder();
    sb.append("Shape ").append(m.getShapeId());
    sb.append(" moves from (").append(String.format("%.1f", m.getFrom().getX()));
    sb.append(",").append(String.format("%.1f", m.getFrom().getY())).append(")");
    sb.append(" to (").append(String.format("%.1f", m.getTo().getX()));
    sb.append(",").append(String.format("%.1f", m.getTo().getY())).append(")");
    return sb.toString();
  }

  @Override
  public String visitRotate(Rotate r) {
    StringBuilder sb = new StringBuilder();
    sb.append("Shape ").append(r.getShapeId());
    sb.append(" rotates from ").append(String.format("%.1f", r.getFrom()));
    sb.append(" to ").append(String.format("%.1f", r.getTo()));
    return sb.toString();
  }

  @Override
  public String apply(IReadableTransformation t) {
    StringBuilder sb = new StringBuilder();
    sb.append(t.accept(this));
    sb.append(" ").append(
            formatFromTo(t.getStartTime(),
                    t.getEndTime(), fps)).append("\n");
    return sb.toString();
  }

  /**
   * Formats to given times, from and to, into the appropriate format in seconds.
   *
   * @param from the "from" time in frames
   * @param to   the "to" time in frames
   * @param fps  how many frames pass in a second
   * @return a formatted string of from and to times
   */
  private String formatFromTo(float from, float to, int fps) {
    String fromSeconds = AbstractTextView.toSeconds(from, fps);
    String toSeconds = AbstractTextView.toSeconds(to, fps);
    StringBuilder sb = new StringBuilder();
    sb.append("from t=").append(fromSeconds);
    sb.append(" to t=").append(toSeconds);
    return sb.toString();
  }
}
