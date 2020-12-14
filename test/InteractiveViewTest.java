import org.junit.Before;
import org.junit.Test;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.view.InteractiveView;

import static junit.framework.TestCase.assertEquals;

public class InteractiveViewTest {

  InteractiveShell view;
  AnimationModel model;
  IController controller;
  StringBuilder testOutput;

  /**Initializes the test objects.
   *
   */
  @Before
  public void init() {
    testOutput = new StringBuilder();
    view = new InteractiveShell(testOutput);
    model = AnimationModelImpl.builder().addRectangle("R", 10,10,10, 10,
            0,0,0,0,10).build();
    controller = IController.builder().view(view).model(model).speed(
            20).makeInteractiveController().build();

  }

  @Test
  public void testInit() {
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n");
  }

  @Test
  public void testPauseButton() {
    view.propagatePauseButton();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "Paused\n" +
            "Reset focus\n");
  }

  @Test
  public void testExport() {
    view.propagateExport();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "File name retrieved\n" +
            "Output set\n" +
            "Text outputted\n" +
            "Message shown: Finished exporting!\n" +
            "Reset focus\n");
  }

  @Test
  public void testSpace() {
    view.propagateSpace();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "Paused\n");
  }


  @Test
  public void testLoop() {
    view.propagateL();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "Looping turned to true\n" +
            "Looping set\n");
  }

  @Test
  public void testRestart() {
    view.propagateR();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "Shapes drawn\n");
  }

  @Test
  public void testSpeedUp() {
    view.propagateUp();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "FPS changed to 25\n");
  }

  @Test
  public void testSpeedDown() {
    view.propagateDown();
    assertEquals(testOutput.toString(), "Shapes drawn\n" +
            "FPS changed to 20\n" +
            "Key listener set\n" +
            "Button listener set\n" +
            "FPS changed to 15\n");
  }


  @Test(expected = NullPointerException.class)
  public void testNullDraw() {
    InteractiveView v = new InteractiveView();
    v.drawShapes(null);
  }

  @Test(expected = NullPointerException.class)
  public void testNullBL() {
    InteractiveView v = new InteractiveView();
    v.setButtonListener(null);
  }

  @Test(expected = NullPointerException.class)
  public void testNullKL() {
    InteractiveView v = new InteractiveView();
    v.setKeyListener(null);
  }

  @Test(expected = NullPointerException.class)
  public void testNullMessage() {
    InteractiveView v = new InteractiveView();
    v.showMessage(null);
  }

  @Test(expected = NullPointerException.class)
  public void testNullOut() {
    InteractiveView v = new InteractiveView();
    v.setOutput(null);
  }
}
