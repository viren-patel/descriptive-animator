package cs3500.animator.model.shape.transformation;

import cs3500.animator.model.shape.IShape;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;

public class Rotate extends ATransformation<Float> {
  /**
   * Initializes a rotation with the given parameters.
   *
   * @param shapeId   the ID of the shape the transformation is on
   * @param from      the starting angle
   * @param to        the ending angle
   * @param startTime the time the transformation starts
   * @param endTime   the time the transformation ends
   */
  public Rotate(String shapeId, Float from, Float to, int startTime, int endTime) {
    super(shapeId, from, to, startTime, endTime);
  }

  @Override
  public boolean checkOverlap(ITransformation other) {
    if (other instanceof Rotate) {
      return this.overlap(other.getStartTime(), other.getEndTime());
    } else {
      return false;
    }
  }

  @Override
  public void tween(int time, IShape shape) {
    float startTween = this.getStartTween(time);
    float endTween = this.getEndTween(time);
    float newTheta = this.from * startTween +
            this.to * endTween;
    shape.setAngle(newTheta);
  }

  @Override
  public <R> R accept(IReadableTransformationVisitor<R> visitor) {
    return visitor.visitRotate(new Rotate(shapeId, from, to, startTime, endTime));
  }
}
