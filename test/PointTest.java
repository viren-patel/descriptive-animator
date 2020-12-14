import org.junit.Test;

import cs3500.animator.util.Point;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for points.
 */
public class PointTest {
  Point point1 = new Point(3, 2);
  Point point2 = new Point(-3, 1);

  @Test
  public void testPointToString() {
    String expected1 = "(3.0,2.0)";
    String expected2 = "(-3.0,1.0)";
    assertEquals(expected1, point1.toString());
    assertEquals(expected2, point2.toString());
  }

}
