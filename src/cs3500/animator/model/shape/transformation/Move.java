package cs3500.animator.model.shape.transformation;

import cs3500.animator.util.Point;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;

/**Transformation that changes a shape's position.
 *
 */
public class Move extends ATransformation<Point> implements ITransformation {

  /**
   * Initializes a MoveS transformation with the given parameters.
   *
   * @param shapeId   the ID of the shape the transformation is on
   * @param fromPoint the starting position
   * @param toPoint   the ending position
   * @param startTime the time the transformation starts
   * @param endTime   the time the transformation ends
   */
  public Move(String shapeId, Point fromPoint, Point toPoint, int startTime, int endTime) {
    super(shapeId, fromPoint, toPoint, startTime, endTime);
  }


  @Override
  public boolean checkOverlap(ITransformation other) {
    if (other instanceof Move) {
      return this.overlap(other.getStartTime(), other.getEndTime());
    } else {
      return false;
    }
  }

  @Override
  public void tween(int time, IShape shape) throws IllegalArgumentException {
    double startTween = this.getStartTween(time);
    double endTween = this.getEndTween(time);
    double newX = this.from.getX() * startTween +
            this.to.getX() * endTween;
    double newY = this.from.getY() * startTween +
            this.to.getY() * endTween;
    shape.setLocation(new Point((int)newX, (int)newY));
  }

  @Override
  public <R> R accept(IReadableTransformationVisitor<R> visitor) {
    return visitor.visitMove(new Move(
            this.shapeId, this.from, this.to, this.startTime, this.endTime));
  }
}
