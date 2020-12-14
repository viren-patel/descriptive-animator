package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.transformation.ChangeColor;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.model.shape.transformation.ITransformation;
import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Rotate;
import cs3500.animator.model.shape.transformation.Scale;
import cs3500.animator.util.IColor;
import cs3500.animator.util.MyColor;
import cs3500.animator.util.Point;
import cs3500.animator.util.TweenModelBuilder;
import javafx.util.Pair;

/**
 * Represents an concrete implementation of an animator model. This class keeps track of all
 * shapes and transformations. Supports rectangles and ovals and supports moves, scales, and
 * color changes.
 */
public class AnimationModelImpl implements AnimationModel {

  /**
   * Constructs a builder for configuring the animation model. The builder originally starts in
   * a state that builds a model with no shapes or transformations.
   *
   * @return the new builder
   */
  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder implements TweenModelBuilder<AnimationModel> {
    private LinkedHashMap<String, IShape> shapeMap = new LinkedHashMap<>();
    private List<ITransformation> transformationList = new ArrayList<>();
    private LinkedHashMap<String, Pair<Integer, Integer>> times = new LinkedHashMap<>();

    /**
     * Private constructor as to force the user to use the above static method.
     */
    private Builder() {

    }

    @Override
    public TweenModelBuilder<AnimationModel> addOval(String name, float cx, float cy,
                                                     float xRadius, float yRadius, float red,
                                                     float green, float blue, int startOfLife,
                                                     int endOfLife) {
      shapeMap.put(name, new Oval(new Point(cx, cy), xRadius, yRadius,
              new MyColor(red, green, blue)));
      times.put(name, new Pair<>(startOfLife, endOfLife));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRectangle(String name, float lx,
                                                          float ly, float width, float height,
                                                          float red, float green, float blue,
                                                          int startOfLife, int endOfLife) {
      shapeMap.put(name, new Rectangle(new Point(lx, ly), width, height,
              new MyColor(red, green, blue)));
      times.put(name, new Pair<>(startOfLife, endOfLife));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addMove(String name, float moveFromX, float moveFromY,
                                                     float moveToX, float moveToY, int startTime,
                                                     int endTime) {
      transformationList.add(new Move(name, new Point(moveFromX, moveFromY),
              new Point(moveToX, moveToY), startTime, endTime));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addColorChange(String name, float oldR, float oldG,
                                                            float oldB, float newR, float newG,
                                                            float newB, int startTime,
                                                            int endTime) {
      transformationList.add(new ChangeColor(name, new MyColor(oldR, oldG, oldB),
              new MyColor(newR, newG, newB), startTime, endTime));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addScaleToChange(String name, float fromSx,
                                                              float fromSy, float toSx, float toSy,
                                                              int startTime, int endTime) {
      transformationList.add(new Scale(name, fromSx, fromSy, toSx, toSy, startTime, endTime));
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRotate(String name, float fromTheta, float toTheta,
                                                       int startTime, int endTime) {
      transformationList.add(new Rotate(name, fromTheta, toTheta, startTime, endTime));
      return this;
    }

    @Override
    public AnimationModel build() {
      AnimationModelImpl model = new AnimationModelImpl();
      for (Map.Entry<String, IShape> entry : shapeMap.entrySet()) {
        model.addAnimation(entry.getKey(), entry.getValue());
      }

      for (Map.Entry<String, Pair<Integer, Integer>> entry : times.entrySet()) {
        model.setShapeAppearTimes(entry.getKey(), entry.getValue().getKey(),
                entry.getValue().getValue());
      }

      for (ITransformation transformation : transformationList) {
        model.addTransformation(transformation.getShapeId(), transformation);
      }

      return model;
    }
  }


  private Map<String, IShapeAnimation> shapes;
  private List<ITransformation> allTransformations;
  private Map<String, List<ITransformation>> shapeTransformations;
  private List<List<IShapeAnimation>> timeline;

  private ITransformation.TransformationComparator comparator;

  /**
   * Initializes the animation model by initializing the lists, maps, and comparator.
   */
  private AnimationModelImpl() {
    this.shapes = new LinkedHashMap<>();
    this.allTransformations = new ArrayList<>();
    this.shapeTransformations = new HashMap<>();
    this.comparator = new ITransformation.TransformationComparator();
    this.timeline = new ArrayList<>();
  }

  @Override
  public void addRectangle(String shapeId, Point location, float width, float height, IColor color)
          throws IllegalArgumentException {
    Rectangle rectangle = new Rectangle(location, width, height, color);
    this.addAnimation(shapeId, rectangle);
  }

  @Override
  public void addOval(String shapeId, Point location, float width, float height, IColor color)
          throws IllegalArgumentException {
    Oval oval = new Oval(location, width, height, color);
    this.addAnimation(shapeId, oval);
  }

  /**
   * Takes a shape and uses it to create an animatable shape.
   *
   * @param shapeId the ID for the shape
   * @param shape   the shape to be used for the animatable
   */
  private void addAnimation(String shapeId, IShape shape)
          throws IllegalArgumentException {
    if (shapes.containsKey(shapeId)) {
      throw new IllegalArgumentException("shape Id already used");
    }
    IShapeAnimation animation = new ShapeAnimation(shapeId, shape);
    shapes.put(shapeId, animation);
    this.shapeTransformations.put(shapeId, new ArrayList<>());
  }

  @Override
  public void scaleShape(String shapeId, int fromWidth, int fromHeight, float toWidth,
                         float toHeight,
                         int fromTime, int toTime) throws NullPointerException {
    this.addTransformation(shapeId, new Scale(
            shapeId, fromWidth, fromHeight, toWidth, toHeight, fromTime, toTime));
  }

  @Override
  public void changeColor(String shapeId, IColor fromColor, IColor toColor,
                          int fromTime, int toTime) throws NullPointerException {
    this.addTransformation(shapeId, new ChangeColor(
            shapeId, fromColor, toColor, fromTime, toTime));
  }

  @Override
  public void moveShape(String shapeId, Point fromPoint, Point toPoint, int startTime,
                        int endTime) throws NullPointerException {
    this.addTransformation(shapeId, new Move(shapeId, fromPoint, toPoint, startTime, endTime));
  }

  @Override
  public void rotateShape(String shapeID, float fromTheta, float toTheta, int startTime, int endTime)
          throws NullPointerException {
    this.addTransformation(shapeID, new Rotate(shapeID, fromTheta, toTheta, startTime, endTime));
  }

  /**
   * Adds the given transformation to the ShapeAnimation with the given Id.
   *
   * @param shapeId        the id of the shape
   * @param transformation the transformation on the shape
   * @throws NullPointerException if there is no shape with the given id
   */
  private void addTransformation(String shapeId, ITransformation transformation)
          throws NullPointerException, IllegalArgumentException {
    IShapeAnimation shape = Objects.requireNonNull(this.shapes.get(shapeId),
            "Given ID does not map to a shape");

    if (transformation.getStartTime() >= shape.getAppearTime()
            && transformation.getEndTime() <= shape.getDisappearTime()) {
      this.checkTransformationOverlap(shapeId, transformation);

      allTransformations.add(transformation);
      allTransformations.sort(this.comparator);
      this.shapeTransformations.get(shapeId).add(transformation);
      this.shapeTransformations.get(shapeId).sort(comparator);
    } else {
      throw new IllegalArgumentException("transformation not while shape is appearing");
    }
  }

  /**
   * Asserts that a known shape does not overlap with the given transformation. By overlap, we mean
   * that the transformation can not be added to the shapes transformations because the shape has
   * transformations of the same nature at the same time and it would not make sense if they were
   * both active at the same time.
   *
   * @param shapeId        the shape to check with
   * @param transformation the transformation to check with
   * @throws IllegalArgumentException if the shape overlaps with the transformation.
   */
  private void checkTransformationOverlap(String shapeId, ITransformation transformation)
          throws IllegalArgumentException {
    for (ITransformation t : this.shapeTransformations.get(shapeId)) {
      if (t.checkOverlap(transformation)) {
        throw new IllegalArgumentException("Overlapping transformations");
      }
    }
  }

  @Override
  public void setShapeAppearTimes(String shapeId, int appearTime, int disappearTime)
          throws NullPointerException, IllegalArgumentException {
    IShapeAnimation shapeAnimation = this.shapes.get(shapeId);
    Objects.requireNonNull(shapeAnimation);
    if (appearTime < 0 || appearTime > disappearTime) {
      throw new IllegalArgumentException("Invalid times");
    }
    shapeAnimation.setAppearTimes(appearTime, disappearTime);
    this.addTimeline(shapeAnimation);
  }


  @Override
  public List<ITransformation> transformationsAtTime(int time) throws IllegalArgumentException {
    if (time < 0) {
      throw new IllegalArgumentException("time must be positive");
    }
    ArrayList<ITransformation> ret = new ArrayList<>();
    for (ITransformation t : allTransformations) {
      if (time >= t.getStartTime() && time < t.getEndTime()) {
        ret.add(t);
      }
    }
    return ret;
  }

  @Override
  public List<IReadableShape> getShapesAtTime(int time) throws IllegalArgumentException {
    if (time < 0 || time >= timeline.size()) {
      throw new IllegalArgumentException("time is not valid");
    }
    List<IReadableShape> ret = new ArrayList<>();
    List<IShapeAnimation> shapesAtTime = new ArrayList<>(this.timeline.get(time).size());
    for (IShapeAnimation s : this.timeline.get(time)) {
      shapesAtTime.add(s.copy());
    }
    for (IShapeAnimation s : shapesAtTime) {
      ret.add(s.getShape());
      for (ITransformation t : this.shapeTransformations.get(s.getShapeId())) {
        if (time >= t.getStartTime()) {
          t.tween(time, s.getShape());
        }
      }
    }
    return ret;
  }

  /**
   * Adds the given shape to the timeline that keeps track of what shapes are visible at what times.
   *
   * @param shapeAnimation the shape being added to the timeline
   */
  private void addTimeline(IShapeAnimation shapeAnimation) {
    while (timeline.size() < shapeAnimation.getDisappearTime()) {
      timeline.add(new ArrayList<>());
    }
    for (int i = shapeAnimation.getAppearTime(); i < shapeAnimation.getDisappearTime(); i++) {
      timeline.get(i).add(shapeAnimation);
    }
  }

  @Override
  public int maxTime() {
    return timeline.size();
  }

  @Override
  public List<IReadableShapeAnimation> getAllShapes() {
    List<IReadableShapeAnimation> ret = new ArrayList<>();
    for (Map.Entry<String, IShapeAnimation> entry : this.shapes.entrySet()) {
      ret.add(entry.getValue());
    }
    return ret;
  }

  @Override
  public List<IReadableTransformation> getAllTransformations() {
    List<IReadableTransformation> ret = new ArrayList<>(this.allTransformations);
    return ret;
  }
}
