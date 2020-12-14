import org.junit.Before;
import org.junit.Test;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.MyColor;
import cs3500.animator.util.Point;

import static org.junit.Assert.assertEquals;

public class InteractiveControllerTest {
  AnimationModel model;
  StringBuilder sb = new StringBuilder();
  InteractiveShell view = new InteractiveShell(sb);
  IController.Builder controllerBuilder;
  InteractiveController controller;

  /**Initializes the test objects.
   *
   */
  @Before
  public void init() {
    controllerBuilder = new IController.Builder();
    model = AnimationModelImpl.builder().build();
    model.addRectangle("R", new Point(0, 0), 10, 10,
            new MyColor(1, 0, 0));
    model.addOval("O", new Point(5, 5), 10, 10,
            new MyColor(0, 1, 1));
    model.setShapeAppearTimes("R", 1, 10);
    model.setShapeAppearTimes("O", 5, 13);
    model.moveShape("R", new Point(5, 5), new Point(7, 7), 3, 5);
    model.scaleShape("R", 5, 5, 7, 7, 7, 9);
    model.changeColor("O", new MyColor(0, 0, 0),
            new MyColor(1, 1, 1), 9, 11);
    controller = (InteractiveController)controllerBuilder.model(model).speed(20).view(view).
            makeInteractiveController().build();
  }

  @Test
  public void testSendToView() {
    controller.run();
    assertEquals("Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n", sb.toString());
  }

  @Test
  public void testSendToController() {
    controller.run();
    view.propagateDown();
    view.propagateSpace();
    view.propagateL();
    view.propagateUp();
    assertEquals("Shapes drawn\n" + "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "FPS changed to 15\n" +
            "Paused\n" +
            "Looping turned to true\n" +
            "Looping set\n" +
            "FPS changed to 20\n", sb.toString());
    view.propagatePauseButton();
    view.propagateExport();
    assertEquals("Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "FPS changed to 15\n" +
            "Paused\n" +
            "Looping turned to true\n" +
            "Looping set\n" +
            "FPS changed to 20\n" +
            "Shapes drawn\n" +
            "Paused\n" +
            "Reset focus\n" +
            "Shapes drawn\n" +
            "File name retrieved\n" +
            "Output set\n" +
            "Text outputted\n" +
            "Message shown: Finished exporting!\n" +
            "Reset focus\n", sb.toString());
  }
}
