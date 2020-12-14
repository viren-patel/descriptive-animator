import org.junit.Test;

import cs3500.animator.util.IColor;
import cs3500.animator.util.Point;
import cs3500.animator.model.shape.transformation.ChangeColor;
import cs3500.animator.model.shape.transformation.ITransformation;
import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Scale;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Test class for transformations.
 */
public class ITransformationTest {
  ITransformation.TransformationComparator comparator =
          new ITransformation.TransformationComparator();
  ITransformation move = new Move("S", new Point(3, 2), new Point(2, 4),
          0, 10);
  ITransformation scale = new Scale("D", 3, 3,5,6,
          6, 7);
  ITransformation changeColor = new ChangeColor("X", IColor.GREEN, IColor.ORANGE,
          0, 10);

  @Test
  public void testCompare() {
    assertTrue(comparator.compare(move, scale) < 0);
    assertEquals(0, comparator.compare(move, move));
    assertTrue(comparator.compare(scale, move) > 0);
    assertEquals(0, comparator.compare(changeColor, move));
    assertTrue(comparator.compare(changeColor, scale) < 0);
  }
}
