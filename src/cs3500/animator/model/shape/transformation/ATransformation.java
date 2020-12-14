package cs3500.animator.model.shape.transformation;


import java.util.Objects;

/**
 * Abstract class for transformations.
 *
 * @param <T> The Type of the field of Shape the transformation is changing
 */
public abstract class ATransformation<T> implements ITransformation {
  protected T from;
  protected T to;
  protected int startTime;
  protected int endTime;
  protected String shapeId;

  /**
   * Initializes a transformation with the given parameters.
   *
   * @param shapeId   the ID of the shape the transformation is on
   * @param from      the starting value of the field of the shape this transformation is changing
   * @param to        the ending value of the field of the shape this transformation is changing
   * @param startTime the time the transformation starts
   * @param endTime   the time the transformation ends
   */
  ATransformation(String shapeId, T from, T to, int startTime, int endTime) {
    Objects.requireNonNull(from);
    Objects.requireNonNull(to);
    this.from = from;
    this.to = to;
    if (endTime < startTime) {
      throw new IllegalArgumentException("start time must be before end time");
    }
    this.startTime = startTime;
    this.endTime = endTime;
    this.shapeId = shapeId;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public String getShapeId() {
    return this.shapeId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (other instanceof ATransformation) {
      return this.toString().equals(other.toString());
    } else {
      return true;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, startTime, endTime, shapeId);
  }

  /**
   * Checks to see if this animation overlaps with some time interval.
   *
   * @param sTime the start of the time interval to check overlapping with
   * @param eTime the end of the time interval to check overlapping with
   * @return whether there is overlap
   */
  protected boolean overlap(int sTime, int eTime) {
    if (sTime < this.startTime && eTime <= this.startTime) {
      return false;
    } else {
      return sTime < this.endTime || eTime < this.endTime;
    }
  }

  /**
   * Returns a constant value for the first term in the tween formula.
   *
   * @param time the time
   * @return the value
   * @throws IllegalArgumentException if the time is not during the transformation
   */
  protected float getStartTween(int time) throws IllegalArgumentException {
    if (time < startTime) {
      throw new IllegalArgumentException("time is invalid");
    }
    time = Math.min(time, endTime);
    return ((float) (this.endTime - time)) / (this.endTime - this.startTime);
  }

  /**
   * Returns a constant value for the second term in the tween formula.
   *
   * @param time the time
   * @return the value
   * @throws IllegalArgumentException if the time is not during the transformation
   */
  protected float getEndTween(int time) throws IllegalArgumentException {
    if (time < startTime) {
      throw new IllegalArgumentException("time is invalid");
    }
    time = Math.min(time, endTime);

    return ((float) (time - this.startTime)) / (this.endTime - this.startTime);
  }

  @Override
  public T getFrom() {
    return from;
  }

  @Override
  public T getTo() {
    return to;
  }
}
