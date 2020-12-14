import org.junit.Before;
import org.junit.Test;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.MyColor;
import cs3500.animator.util.Point;
import cs3500.animator.view.DescriptionView;
import cs3500.animator.view.IView;

import static junit.framework.TestCase.assertEquals;

/**
 * Tests the description view and text controller.
 */
public class DescriptionViewTest {

  AnimationModel model1;

  /**
   * Initializes a basic animation.
   */
  @Before
  public void init() {
    model1 = AnimationModelImpl.builder().build();
    model1.addRectangle("R", new Point(200, 200), 50, 100,
            new MyColor(1, 0, 0));
    model1.addOval("C", new Point(500, 100), 60, 30,
            new MyColor(0, 0, 1));
    model1.setShapeAppearTimes("R", 1, 100);
    model1.setShapeAppearTimes("C", 6, 100);
    model1.moveShape("R", new Point(200, 200), new Point(300, 300), 10,
            50);
    model1.moveShape("C", new Point(500, 100), new Point(500, 400), 20,
            70);
    model1.changeColor("C", new MyColor(0, 0, 1), new MyColor(0,
            1, 0), 50, 80);
    model1.moveShape("R", new Point(300, 300), new Point(200, 200), 70,
            100);
    model1.scaleShape("R", 50, 100, 25, 100, 51,
            70);
  }

  @Test
  public void testDescription() {
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(2).model(model1).view(view).build();
    textController.run();
    assertEquals("Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=0.5s\n" +
                    "Disappears at t=50.0s\n" +
                    "\n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
                    +
                    "Appears at t=3.0s\n" +
                    "Disappears at t=50.0s\n" +
                    "\n" +
                    "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=5.0s to t=25.0s\n" +
                    "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=10.0s to t=35.0s\n" +
                    "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=25.0s to" +
                    " t=40.0s\n" +
                    "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0" +
                    " from t=25.5s to t=35.0s\n" +
                    "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=35.0s to t=50.0s",
            sb.toString());
  }

  @Test
  public void testDescription1FPS() {
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(1).model(model1).view(view).build();
    textController.run();
    assertEquals("Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=1.0s\n" +
                    "Disappears at t=100.0s\n" +
                    "\n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
                    +
                    "Appears at t=6.0s\n" +
                    "Disappears at t=100.0s\n" +
                    "\n" +
                    "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0s to t=50.0s\n" +
                    "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0s to t=70.0s\n" +
                    "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0s to " +
                    "t=80.0s\n" +
                    "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
                    +
                    "from t=51.0s to t=70.0s\n" +
                    "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0s to t=100.0s",
            sb.toString());
  }

  @Test
  public void testEmpty() {
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    model1 = AnimationModelImpl.builder().build();
    IController textController = IController.builder().speed(2).model(model1).view(view).build();
    textController.run();
    assertEquals(sb.toString(), "Shapes:\n");
  }


  @Test(expected = IllegalArgumentException.class)
  public void badFPS() {
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(0).model(model1).view(view).build();
  }

  @Test(expected = NullPointerException.class)
  public void nullView() {
    IController textController = IController.builder().speed(2).model(model1).view(null).build();
  }

  @Test(expected = NullPointerException.class)
  public void nullModel() {
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(2).model(null).view(view).build();
  }

  @Test
  public void addRect() {
    model1 = AnimationModelImpl.builder().addRectangle("R", 0, 0, 10, 10,
            0, 0, 0, 0, 100).build();
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(10).model(model1).view(view).build();
    textController.run();
    assertEquals(sb.toString(), "Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n" +
            "Appears at t=0.0s\n" +
            "Disappears at t=10.0s\n\n");
  }

  @Test
  public void move() {
    model1 = AnimationModelImpl.builder().addRectangle("R", 0, 0, 10, 10,
            0, 0, 0, 0, 100).addMove(
            "R", 0, 0, 2, 1,
            10, 20).build();
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(10).model(model1).view(view).build();
    textController.run();
    assertEquals(sb.toString(), "Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n" +
            "Appears at t=0.0s\n" +
            "Disappears at t=10.0s\n" +
            "\n" +
            "Shape R moves from (0.0,0.0) to (2.0,1.0) from t=1.0s to t=2.0s");
  }

  @Test
  public void scale() {
    model1 = AnimationModelImpl.builder().addRectangle("R", 0, 0, 10, 10,
            0, 0, 0, 0, 100).addScaleToChange(
            "R", 0, 0, 1, 1,
            10, 20).build();
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(10).model(model1).view(view).build();
    textController.run();
    assertEquals(sb.toString(), "Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n" +
            "Appears at t=0.0s\n" +
            "Disappears at t=10.0s\n" +
            "\n" +
            "Shape R scales from Width: 0.0, Height: 0.0 to Width: 1.0, Height:" +
            " 1.0 from t=1.0s to t=2.0s");
  }

  @Test
  public void changeColor() {
    model1 = AnimationModelImpl.builder().addRectangle("R", 0, 0, 10, 10,
            0, 0, 0, 0, 100).addColorChange(
            "R", 0, 0, 1, 1, 0, 0,
            10, 20).build();
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(10).model(model1).view(view).build();
    textController.run();
    assertEquals(sb.toString(), "Shapes:\n" +
            "Name: R\n" +
            "Type: rectangle\n" +
            "Corner: (0.0,0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n" +
            "Appears at t=0.0s\n" +
            "Disappears at t=10.0s\n" +
            "\n" +
            "Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=1.0s to t=2.0s");
  }

  @Test
  public void addOval() {
    model1 = AnimationModelImpl.builder().addOval("O", 0, 0, 10, 10,
            0, 0, 0, 10, 100).build();
    StringBuilder sb = new StringBuilder();
    IView view = new DescriptionView();
    view.setOutput(sb);
    IController textController = IController.builder().speed(10).model(model1).view(view).build();
    textController.run();
    assertEquals(sb.toString(), "Shapes:\n" +
            "Name: O\n" +
            "Type: oval\n" +
            "Center: (0.0,0.0), X radius: 10.0, Y radius: 10.0, Color: (0.0,0.0,0.0)\n" +
            "Appears at t=1.0s\n" +
            "Disappears at t=10.0s\n\n");
  }

  @Test(expected = NullPointerException.class)
  public void nullOutput() {
    IView v = new DescriptionView();
    v.setOutput(null);
  }


}
