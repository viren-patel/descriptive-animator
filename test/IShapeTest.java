import org.junit.Test;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;
import cs3500.animator.model.shape.IShape;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Rectangle;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Test class for shapes.
 */
public class IShapeTest {

  @Test
  public void testShapeWidthHeightException() {
    try {
      IShape rectangle = new Rectangle(new Point(3, 2), -1, 2, IColor.RED);
      fail("Test Failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
    try {
      IShape rectangle = new Rectangle(new Point(3, 2), -1, 0, IColor.RED);
      fail("Test Failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }

  @Test
  public void testShapeNullException() {
    try {
      IShape rectangle = new Oval(null, 1, 2, IColor.RED);
      fail("Test Failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
    try {
      IShape rectangle = new Oval(new Point(3, 2), 1, 2, null);
      fail("Test Failed");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().length() > 0);
    }
  }
}
