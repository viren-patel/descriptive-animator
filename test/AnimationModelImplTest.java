import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;
import cs3500.animator.model.shape.transformation.ITransformation;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Test class for testing only the model.
 */
public class AnimationModelImplTest {
  AnimationModel simpleAnimation;

  /**
   * Initializes a simple animation.
   */
  public void initSimpleAnimation() {

    simpleAnimation = AnimationModelImpl.builder().build();
    simpleAnimation.addRectangle("R", new Point(200, 200), 50,
            10, IColor.RED);
    simpleAnimation.addOval("C", new Point(500, 100), 60, 30, IColor.BLUE);
    simpleAnimation.setShapeAppearTimes("R", 1, 100);
    simpleAnimation.setShapeAppearTimes("C", 6, 100);
    simpleAnimation.moveShape("R", new Point(200, 200), new Point(300, 300),
            10, 50);
    simpleAnimation.moveShape("C", new Point(500, 100), new Point(500, 400),
            20, 70);
    simpleAnimation.moveShape("R", new Point(300, 300), new Point(200, 200),
            70, 100);
    simpleAnimation.changeColor("C", IColor.BLUE, IColor.GREEN, 50, 80);
    simpleAnimation.scaleShape("R", 50, 0, 25, 0,
            51, 70);
  }

  @Test
  public void testAddRectangleException() {
    simpleAnimation = AnimationModelImpl.builder().build();
    simpleAnimation.addOval("C", new Point(10, 10), 30, 50, IColor.BLUE);

    try {
      simpleAnimation.addRectangle("C", new Point(40, 40), 30, 30,
              IColor.BLUE);
      fail("Test failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }

  @Test
  public void testAddOvalException() {
    simpleAnimation = AnimationModelImpl.builder().build();
    simpleAnimation.addRectangle("C", new Point(40, 40), 30, 30,
            IColor.BLUE);
    try {
      simpleAnimation.addOval("C", new Point(10, 10), 30, 50, IColor.BLUE);
      fail("Test failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }


  @Test
  public void testMoveException() {
    simpleAnimation = AnimationModelImpl.builder().build();
    try {
      simpleAnimation.moveShape("S", new Point(4, 3), new Point(4, 2),
              2, 5);
      fail("Test failed");
    } catch (NullPointerException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }

  @Test
  public void testChangeWidthException() {
    this.initSimpleAnimation();

    try {
      simpleAnimation.scaleShape("Q", 4, 0, 5, 0,
              2, 5);
      fail("Test failed");
    } catch (NullPointerException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }

  @Test
  public void testChangeHeightException() {
    this.initSimpleAnimation();

    try {
      simpleAnimation.scaleShape("yeet", 3, 0, 4, 0,
              2, 4);
      fail("Test failed");
    } catch (NullPointerException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }

  @Test
  public void testChangeColorException() {
    this.initSimpleAnimation();

    try {
      simpleAnimation.changeColor("Shape", IColor.BLUE, IColor.BLACK, 4, 10);
      fail("Test Failed");
    } catch (NullPointerException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }


  @Test
  public void testTransformationTimeException() {
    this.initSimpleAnimation();
    List<ITransformation> transformationList = new ArrayList<>();
    try {
      transformationList = simpleAnimation.transformationsAtTime(-4);
      fail("Test failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
    try {
      simpleAnimation.transformationsAtTime(-1);
      fail("Test failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }


}
