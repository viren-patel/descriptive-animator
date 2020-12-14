import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.util.MyColor;
import cs3500.animator.util.Point;
import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.transformation.IReadableTransformation;

import static org.junit.Assert.assertEquals;

/**
 * A test class for testing the model.
 */
public class AnimationModelTest {

  AnimationModel model1;
  AnimationModel model2;

  /**
   * Initializes a new empty model and a model with two shapes and three animations.
   */
  @Before
  public void init() {
    //This is an unideal way to initialize a model, but these tests are legacy code and were not
    //written when builders were implemented.;
    model1 = AnimationModelImpl.builder().build();
    model2 = AnimationModelImpl.builder().build();
    model2.addRectangle("R", new Point(0, 0), 10, 10,
            new MyColor(1, 0, 0));
    model2.addOval("O", new Point(5, 5), 10, 10,
            new MyColor(0, 1, 1));
    model2.setShapeAppearTimes("R", 1, 10);
    model2.setShapeAppearTimes("O", 5, 13);
    model2.moveShape("R", new Point(5, 5), new Point(7, 7), 3, 5);
    model2.scaleShape("R", 5, 5, 7, 7, 7, 9);
    model2.changeColor("O", new MyColor(0, 0, 0),
            new MyColor(1, 1, 1), 9, 11);
  }


  //Rectangle tests
  @Test(expected = NullPointerException.class)
  public void testNullName() {
    model1.addRectangle(null, new Point(0, 0), 10, 10,
            new MyColor(1, 1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadWidth() {
    model1.addRectangle("A", new Point(0, 0), -1, 10,
            new MyColor(1, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHeight() {
    model1.addRectangle("A", new Point(0, 0), 10, 0,
            new MyColor(1, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor() {
    model1.addRectangle("A", new Point(0, 0), 10, 10,
            null);
  }

  //Oval tests
  @Test(expected = NullPointerException.class)
  public void testNullName2() {
    model1.addOval(null, new Point(0, 0), 10, 10,
            new MyColor(1, 1, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadWidth2() {
    model1.addOval("A", new Point(0, 0), -1, 10,
            new MyColor(1, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadHeight2() {
    model1.addOval("A", new Point(0, 0), 10, -1,
            new MyColor(1, 0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColor2() {
    model1.addOval("A", new Point(0, 0), 10, 10,
            null);
  }

  //Move
  @Test(expected = NullPointerException.class)
  public void nullShapeName() {
    model1.moveShape(null, new Point(0, 0), new Point(-1, -1),
            3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startBeforeShape() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.moveShape("R", new Point(0, 0), new Point(-1, -1), 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startAfterShape() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.moveShape("R", new Point(0, 0), new Point(-1, -1), 11, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void endAfterShape() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.moveShape("R", new Point(0, 0), new Point(-1, -1), 6, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartEnd() {
    model1.addRectangle("R", new Point(0, 0), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.moveShape("R", new Point(0, 0), new Point(-1, -1), 7, 6);
  }

  //Scale
  @Test(expected = NullPointerException.class)
  public void nullShapeName1() {
    model1.scaleShape(null, 0, 0, -1, -1, 3,
            5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badOldWidth() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 0, 3, 1, 1, 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badOldHeight() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 2, -1, 1, 1, 3,
            4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badNewWidth() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 2, 3, 0, 1, 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badNewHeight() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 2, 3, 1, -1, 3,
            6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startBeforeShape1() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 3, 3, 1, 1, 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startAfterShape1() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 5, 3, 5, 3, 11,
            12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void endAfterShape1() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 3, 3, 1, 1, 6,
            11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartEnd1() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.scaleShape("R", 3, 3, 5, 5, 7, 6);
  }

  //ChangeColor
  @Test(expected = NullPointerException.class)
  public void nullShapeName2() {
    model1.changeColor(null, new MyColor(1, 0, 0),
            new MyColor(1, 0, 1), 3, 5);
  }

  @Test(expected = NullPointerException.class)
  public void nullOldColor() {
    model1.changeColor("A", null, new MyColor(1, 0, 1), 3,
            5);
  }

  @Test(expected = NullPointerException.class)
  public void nullNewColor() {
    model1.changeColor("A", new MyColor(1, 0, 0), null, 3,
            5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startBeforeShape2() {
    model1.addRectangle("R", new Point(200, 20), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.changeColor("R", new MyColor(1, 0, 0),
            new MyColor(1, 0, 1), 3, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void startAfterShape2() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.changeColor("R", new MyColor(1, 0, 0),
            new MyColor(1, 0, 1), 11, 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void endAfterShape2() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.changeColor("R", new MyColor(1, 0, 0),
            new MyColor(1, 0, 1), 6, 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartEnd2() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.setShapeAppearTimes("R", 5, 10);
    model1.changeColor("R", new MyColor(1, 0, 0),
            new MyColor(1, 0, 1), 7, 6);
  }

  //General Animation
  @Test(expected = NullPointerException.class)
  public void noShapeOfName() {
    model1.moveShape("DNE", new Point(0, 0), new Point(2, 2),
            1, 10);
  }

  //General Shapes
  @Test(expected = IllegalArgumentException.class)
  public void addingSameName() {
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
  }

  //Order
  @Test
  public void testAnimationOrder() {
    model1.addRectangle("R", new Point(100, 100), 10, 10,
            new MyColor(0, 0, 1));
    model1.setShapeAppearTimes("R", 2, 12);
    model1.moveShape("R", new Point(10, 10), new Point(20, 20), 5,
            6);
    model1.scaleShape("R", 3, 4, 5, 6, 3, 4);
    model1.changeColor("R", new MyColor(1, 0, 0),
            new MyColor(0, 1, 1), 7, 8);
    List<IReadableTransformation> transformations = model1.getAllTransformations();
    assertEquals(transformations.get(0).getStartTime(), 3);
    assertEquals(transformations.get(1).getStartTime(), 5);
    assertEquals(transformations.get(2).getStartTime(), 7);
  }

  @Test
  public void testShapeOrder() {
    model1.addRectangle("A", new Point(0, 0), 1, 1,
            new MyColor(1, 0, 0));
    model1.addRectangle("B", new Point(2, 3), 4, 1,
            new MyColor(1, 0, 1));
    model1.addRectangle("C", new Point(1, 1), 1, 3,
            new MyColor(1, 1, 0));
    model1.setShapeAppearTimes("A", 1, 2);
    model1.setShapeAppearTimes("B", 3, 4);
    List<IReadableShapeAnimation> list = model1.getAllShapes();
    assertEquals(list.get(0).getShapeId(), "A");
    assertEquals(list.get(1).getShapeId(), "B");
    assertEquals(list.get(2).getShapeId(), "C");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testBadCololorRange() {
    model1.addRectangle("R", new Point(100, 100), 10, 10,
            new MyColor(0, 0, 2));
  }

  //Model 2 test
  @Test
  public void testT0() {
    List<IReadableShape> shapes = model2.getShapesAtTime(0);
    assertEquals(shapes.size(), 0);
  }

  @Test
  public void testT1() {
    List<IReadableShape> shapes = model2.getShapesAtTime(1);
    assertEquals(shapes.size(), 1);

  }

  @Test
  public void testT5() {
    List<IReadableShape> shapes = model2.getShapesAtTime(5);
    assertEquals(shapes.size(), 2);
    assertEquals(shapes.get(0).getPosition().getX(), 7, .01);
    assertEquals(shapes.get(0).getPosition().getY(), 7, .01);
    assertEquals(shapes.get(1).getType(), ShapeType.Oval);
  }

  @Test
  public void testT9() {
    List<IReadableShape> shapes = model2.getShapesAtTime(9);
    assertEquals(shapes.size(), 2);
    assertEquals(shapes.get(0).getWidth(), 7, .01);
    assertEquals(shapes.get(0).getHeight(), 7, .01);

  }

  @Test
  public void testT11() {
    List<IReadableShape> shapes = model2.getShapesAtTime(11);
    assertEquals(shapes.size(), 1);
    assertEquals(shapes.get(0).getColor().getRed(), 1, .01);
    assertEquals(shapes.get(0).getColor().getGreen(), 1, .01);
    assertEquals(shapes.get(0).getColor().getBlue(), 1, .01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testT13() {
    List<IReadableShape> shapes = model2.getShapesAtTime(13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTNegative1() {
    List<IReadableShape> shapes = model2.getShapesAtTime(-1);
  }

  @Test
  public void testMoveAndScale() {
    model2.scaleShape("R", 2, 2, 4, 4, 3, 5);
    assertEquals(model2.getShapesAtTime(5).get(0).getPosition().getX(), 7, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getPosition().getY(), 7, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getWidth(), 4, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getWidth(), 4, .01);
  }

  @Test
  public void testMoveAndChangeColor() {
    model2.changeColor("R", new MyColor(0, 1, 1),
            new MyColor(1, 0, 1), 3, 5);
    assertEquals(model2.getShapesAtTime(5).get(0).getPosition().getX(), 7, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getPosition().getY(), 7, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getColor().getRed(), 1, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getColor().getGreen(), 0, .01);
    assertEquals(model2.getShapesAtTime(5).get(0).getColor().getBlue(), 1, .01);
  }

  @Test
  public void backInTime() {
    model2.getShapesAtTime(10);
    List<IReadableShape> shapes = model2.getShapesAtTime(1);
    assertEquals(shapes.size(), 1);
    assertEquals(model2.getShapesAtTime(1).get(0).getPosition().getX(), 0, .01);
    assertEquals(model2.getShapesAtTime(1).get(0).getPosition().getY(), 0, .01);
    assertEquals(model2.getShapesAtTime(1).get(0).getWidth(), 10, .01);
    assertEquals(model2.getShapesAtTime(1).get(0).getWidth(), 10, .01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveSameTime1() {
    model1.addRectangle("R", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("R", 0, 10);
    model1.moveShape("R", new Point(0, 0), new Point(5, 5), 3, 5);
    model1.moveShape("R", new Point(0, 0), new Point(5, 5), 3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveSameTime2() {
    model1.addRectangle("R", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("R", 0, 10);
    model1.moveShape("R", new Point(0, 0), new Point(5, 5), 3, 5);
    model1.moveShape("R", new Point(0, 0), new Point(5, 5), 2, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveSameTime3() {
    model1.addRectangle("R", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("R", 0, 10);
    model1.moveShape("R", new Point(0, 0), new Point(5, 5), 3, 5);
    model1.moveShape("R", new Point(0, 0), new Point(5, 5), 4, 5);
  }


  @Test(expected = IllegalArgumentException.class)
  public void scaleSameTime() {
    model1.addRectangle("R", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("R", 0, 10);
    model1.scaleShape("R", 3, 5, 5, 6, 3, 5);
    model1.scaleShape("R", 3, 5, 5, 6, 3, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeColorSameTime() {
    model1.addRectangle("R", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("R", 0, 10);
    model1.changeColor("R", MyColor.BLUE, MyColor.RED, 3, 5);
    model1.changeColor("R", MyColor.BLUE, MyColor.RED, 3, 5);
  }

  @Test
  public void testGetAllShapes() {
    List<IReadableShapeAnimation> list = model2.getAllShapes();
    assertEquals(list.size(), 2);
    assertEquals(list.get(0).getShapeId(), "R");
    assertEquals(list.get(1).getShapeId(), "O");
  }

  @Test
  public void testGetAllShapes2() {
    assertEquals(model1.getAllShapes().size(), 0);
  }

  @Test
  public void testGetAllTransformations() {
    model1.addRectangle("r", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("r", 0, 50);
    model1.moveShape("r", new Point(0, 0), new Point(1, 2), 10, 20);
    model1.scaleShape("r", 7, 8, 9, 0, 3, 5);
    model1.changeColor("r", MyColor.BLUE, MyColor.RED, 7, 8);
    List<IReadableTransformation> list = model1.getAllTransformations();
    assertEquals(model1.getAllShapes().size(), 1);
    assertEquals(list.size(), 3);
    assertEquals(list.get(0).getStartTime(), 3);
    assertEquals(list.get(1).getStartTime(), 7);
    assertEquals(list.get(2).getStartTime(), 10);
  }

  @Test
  public void testRotation1(){
    model1.addRectangle("r", new Point(0, 0), 10, 10, MyColor.BLUE);
    model1.setShapeAppearTimes("r", 0, 50);
    model1.rotateShape("r", 0, 30, 0, 10);
    List<IReadableShape> list = model1.getShapesAtTime(10);
    assertEquals(list.get(0).getAngle(), 30, .01);
    list = model1.getShapesAtTime(5);
    assertEquals(list.get(0).getAngle(), 15, .01);
  }
}