import org.junit.Test;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.model.shape.ShapeType;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the builder implementation in the model.
 */
public class BuilderTests {

  AnimationModel model;

  @Test
  public void emptyBuild() {
    model = AnimationModelImpl.builder().build();
    assertEquals(model.getAllShapes().size(), 0);
    assertEquals(model.getAllTransformations().size(), 0);
  }

  @Test
  public void testRec() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0, 10, 5,
            0, 0, 0, 0, 10).build();
    assertEquals(model.getAllShapes().size(), 1);
    assertEquals(model.getAllShapes().get(0).getShapeId(), "R");
    assertEquals(model.getAllShapes().get(0).getReadableShape().getPosition().getX(), 0.0,
            .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getPosition().getY(), 0.0,
            .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getWidth(), 10.0, .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getWidth(), 10.0, .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getColor().getRed(), 0.0);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getColor().getRed(), 0.0);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getColor().getRed(), 0.0);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getType(), ShapeType.Rectangle);
    assertEquals(model.getAllShapes().get(0).getAppearTime(), 0);
    assertEquals(model.getAllShapes().get(0).getDisappearTime(), 10);

  }

  @Test
  public void testOval() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 10, 5,
            0, 0, 0, 0, 10).build();
    assertEquals(model.getAllShapes().size(), 1);
    assertEquals(model.getAllShapes().get(0).getShapeId(), "R");
    assertEquals(model.getAllShapes().get(0).getReadableShape().getPosition().getX(), 0.0,
            .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getPosition().getY(), 0.0,
            .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getWidth(), 10.0, .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getWidth(), 10.0, .01);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getColor().getRed(), 0.0);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getColor().getRed(), 0.0);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getColor().getRed(), 0.0);
    assertEquals(model.getAllShapes().get(0).getReadableShape().getType(), ShapeType.Oval);
    assertEquals(model.getAllShapes().get(0).getAppearTime(), 0);
    assertEquals(model.getAllShapes().get(0).getDisappearTime(), 10);
  }

  @Test
  public void testMove() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 10, 5,
            0, 0, 0, 0, 10).addMove(
            "R", 0, 0, 1, 1, 3, 4).build();
    assertEquals(model.getAllTransformations().size(), 1);
    assertEquals(model.getAllTransformations().get(0).getShapeId(), "R");
    assertEquals(model.getAllTransformations().get(0).getStartTime(), 3);
  }

  @Test
  public void testScale() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 10, 5,
            0, 0, 0, 0, 10).addScaleToChange(
            "R", 0, 0, 1, 1, 3, 4).build();
    assertEquals(model.getAllTransformations().size(), 1);
    assertEquals(model.getAllTransformations().get(0).getShapeId(), "R");
    assertEquals(model.getAllTransformations().get(0).getStartTime(), 3);
  }

  @Test
  public void testChangeColor() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 10, 5,
            0, 0, 0, 0, 10).addColorChange(
            "R", 0, 0, 1, 1, 0, 0, 3, 4).build();
    assertEquals(model.getAllTransformations().size(), 1);
    assertEquals(model.getAllTransformations().get(0).getShapeId(), "R");
    assertEquals(model.getAllTransformations().get(0).getStartTime(), 3);
  }

  @Test
  public void testChain() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0, 10, 5,
            0, 0, 0, 0, 10).addOval("C", 0, 0, 10,
            5,
            0, 0, 0, 0, 10).addColorChange(
            "R", 0, 0, 1, 1, 0, 0, 3, 4).addMove(
            "C", 0, 0, 1, 1, 3, 4).build();
    assertEquals(model.getAllTransformations().size(), 2);
    assertEquals(model.getAllShapes().size(), 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRectangle() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0, 0, 5,
            0, 0, 0, 0, 10).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTimes1() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0, 5, 5,
            0, 0, 0, -1, 10).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTimes2() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0, 5, 5,
            0, 0, 0, 5, 3).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOval() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 5, 5,
            0, 0, 4, 0, 10).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOval2() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 5, -1,
            0, 0, 4, 0, 10).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void noName() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 5, 5,
            0, 0, 4, 0, 10).addMove(
            "DNE", 0, 0, 1, 1, 3, 4).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void badMove() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 5, 5,
            0, 0, 4, 0, 10).addMove(
            "R", 0, 0, 1, 1, 4, 3).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void badColorChange() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 5, 5,
            0, 0, 4, 0, 10).addColorChange(
            "R", 0, -10, 1, 1, 0, 0,
            3, 4).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void badScale() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0, 5, 5,
            0, 0, 4, 0, 10).addScaleToChange("R",
            -1, 0, 3, 4, 5, 6).build();
  }


}
