package cs3500.animator.model.shape.transformation;

import cs3500.animator.model.shape.IShape;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;
import javafx.util.Pair;

/**
 * This transformation scales both the width and height at the same time. The width/height pair is
 * kept in a Pair where the key is the width and the value is the height.
 */
public class Scale extends ATransformation<Pair<Float, Float>> {

  /**
   * Creates a scale transformation that scales both width and height.
   *
   * @param name      the ID of the shape to change
   * @param oldWidth  the width to change from
   * @param oldHeight the height to change from
   * @param newWidth  the width to change to
   * @param newHeight the height to change to
   * @param startTime the time this transformation starts
   * @param endTime   the time this transformation ends
   */
  public Scale(String name, float oldWidth, float oldHeight, float newWidth, float newHeight,
               int startTime, int endTime) {
    super(name, new Pair<Float, Float>(oldWidth, oldHeight),
            new Pair<Float, Float>(newWidth, newHeight), startTime, endTime);
  }

  @Override
  public boolean checkOverlap(ITransformation other) {
    if (other instanceof Scale) {
      return this.overlap(other.getStartTime(), other.getEndTime());
    } else {
      return false;
    }
  }

  @Override
  public void tween(int time, IShape shape) {
    float startTween = this.getStartTween(time);
    float endTween = this.getEndTween(time);
    float newWidth = this.from.getKey() * startTween +
            this.to.getKey() * endTween;
    float newHeight = this.from.getValue() * startTween +
            this.to.getValue() * endTween;
    shape.setWidth(newWidth);
    shape.setHeight(newHeight);
  }

  @Override
  public <R> R accept(IReadableTransformationVisitor<R> visitor) {
    return visitor.visitScale(new Scale(shapeId, from.getKey(), from.getValue(),
            to.getKey(), to.getValue(), startTime, endTime));
  }

}
