package cs3500.animator.model.shape.transformation;

import cs3500.animator.util.IColor;
import cs3500.animator.util.MyColor;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;

/**
 * Transformation that changes a shape's Color.
 */
public class ChangeColor extends ATransformation<IColor> {

  /**
   * Initializes a ChangeColor transformation with the given parameters.
   *
   * @param shapeId   the ID of the shape the transformation is on
   * @param fromColor the starting color
   * @param toColor   the ending color to change to
   * @param startTime the time the transformation starts
   * @param endTime   the time the transformation ends
   */
  public ChangeColor(String shapeId, IColor fromColor, IColor toColor, int startTime, int endTime) {
    super(shapeId, fromColor, toColor, startTime, endTime);
  }

  @Override
  public boolean checkOverlap(ITransformation other) {
    if (other instanceof ChangeColor) {
      return this.overlap(other.getStartTime(), other.getEndTime());
    } else {
      return false;
    }
  }

  @Override
  public void tween(int time, IShape shape) {
    double startTween = this.getStartTween(time);
    double endTween = this.getEndTween(time);

    double newRed = this.from.getRed() * startTween +
            this.to.getRed() * endTween;

    double newGreen = this.from.getGreen() * startTween +
            this.to.getGreen() * endTween;
    double newBlue = this.from.getBlue() * startTween +
            this.to.getBlue() * endTween;

    shape.setColor(new MyColor(newRed, newGreen, newBlue));
  }

  @Override
  public <R> R accept(IReadableTransformationVisitor<R> visitor) {
    return visitor.visitChangeColor(new ChangeColor(this.shapeId, this.from, this.to,
            this.startTime, this.endTime));
  }

}
