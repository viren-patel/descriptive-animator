package cs3500.animator.model;

import java.util.List;

import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.model.shape.transformation.ITransformation;
import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;

/**
 * This interface represents all the functionality available from a animator model. Those behaviors
 * include adding various shapes, various animations, getting a description/state of the entire
 * animation, setting the start and end times of shapes, and returning the state of the animation
 * at certain times. An implementation of this interface is a model for the animator.
 */
public interface AnimationModel {
  /**
   * adds a Rectangle to the animation with the given size and position parameters.
   *
   * @param shapeId  a unique id for the rectangle
   * @param location the location of the top-left corner of the Rectangle
   * @param width    the width
   * @param height   the height
   * @param color    the color of the Rectangle
   * @throws IllegalArgumentException if the shapeId has already been used on a different shape
   */
  void addRectangle(String shapeId, Point location, float width, float height, IColor color);

  /**
   * Adds a oval to this animation with the given size and position parameters.
   *
   * @param shapeId  a unique id for the Oval
   * @param location the location of the center of the oval
   * @param width    the width
   * @param height   the height
   * @param color    the color of the oval
   * @throws IllegalArgumentException if the shapeId has already been used
   */
  void addOval(String shapeId, Point location, float width, float height, IColor color);


  /**
   * Adds a scaleShape animation to the specified shape.
   *
   * @param shapeId    the name of the shape to add this animation to
   * @param fromWidth  the old width to scaleShape from
   * @param fromHeight the old height to scaleShape from
   * @param toWidth    the new width to scaleShape to
   * @param toHeight   the new height to scaleShape to
   * @param fromTime   the time this animation starts
   * @param toTime     the time this animation ends
   */
  void scaleShape(String shapeId, int fromWidth, int fromHeight, float toWidth, float toHeight,
                  int fromTime, int toTime) throws NullPointerException;

  /**
   * Changes the Color of the shape with the given Id at the given time.
   *
   * @param shapeId   the id of the shape
   * @param fromColor the starting color of the shape
   * @param toColor   the ending color of the shape
   * @param fromTime  the time it starts changing color
   * @param toTime    the time it ends changing color
   * @throws NullPointerException if there is no shape with the given Id
   */
  void changeColor(String shapeId, IColor fromColor, IColor toColor,
                   int fromTime, int toTime) throws NullPointerException;

  /**
   * Moves the shape with the given id using the given location and time parameters.
   *
   * @param shapeId   the id of the shape
   * @param fromPoint the starting point of the shape
   * @param toPoint   the ending point of the shape
   * @param startTime the starting time for the move
   * @param endTime   the ending time for the move
   * @throws NullPointerException if there is not shape with the given id
   */
  void moveShape(String shapeId, Point fromPoint, Point toPoint, int startTime,
                 int endTime) throws NullPointerException;

  /**
   * Rotates the shape with the given id using the given angles and time parameters.
   *
   * @param shapeID   the id of the shape
   * @param fromTheta the starting angle of the shape
   * @param toTheta   the ending angle of the shape
   * @param startTime the starting time for the move
   * @param endTime   the ending time for the move
   * @throws NullPointerException if there is not shape with the given id
   */
  void rotateShape(String shapeID, float fromTheta, float toTheta, int startTime, int endTime)
    throws NullPointerException;

  /**
   * Sets the time that the shape at the given id appears and disappears.
   *
   * @param shapeId       the id of the shape
   * @param appearTime    the time that the shape appears in the animation
   * @param disappearTime the time that the shape disappears from the animation
   * @throws NullPointerException if there is no shape at the given id
   * @throws IllegalArgumentException if the times are invalid
   */
  void setShapeAppearTimes(String shapeId, int appearTime, int disappearTime)
          throws NullPointerException, IllegalArgumentException;


  /**
   * Returns a List of all transformations that occur at the given time.
   *
   * @param time the time
   */
  List<ITransformation> transformationsAtTime(int time) throws IllegalArgumentException;

  /**
   * Returns a List of all shapes that appear during the given time.
   *
   * @param time the time
   * @return the list of shapes
   */
  List<IReadableShape> getShapesAtTime(int time);

  /**
   * Returns the time the animation ends.
   *
   * @return the time the animation ends.
   */
  int maxTime();

  /**
   * Returns a list of all the ShapeAnimations that exist during an animation in order of creation.
   * @return above line
   */
  List<IReadableShapeAnimation> getAllShapes();

  /**
   * Returns a list of all the transformations in an animation in order of start time.
   * @return above line
   */
  List<IReadableTransformation> getAllTransformations();

}
