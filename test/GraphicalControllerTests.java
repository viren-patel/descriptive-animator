import org.junit.Before;
import org.junit.Test;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;

/**
 * Test class for the graphical controller.
 */
public class GraphicalControllerTests {

  AnimationModel model1;

  @Before
  public void init() {
    model1 = AnimationModelImpl.builder().build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void badFPS() {
    IView view = new VisualView();
    IController controller = IController.builder().speed(0).model(model1).view(view).build();
  }

  @Test(expected = NullPointerException.class)
  public void nullView() {
    IController controller = IController.builder().speed(2).model(model1).view(null).build();
  }

  @Test(expected = NullPointerException.class)
  public void nullModel() {
    IView view = new VisualView();
    IController controller = IController.builder().speed(2).model(null).view(view).build();
  }
}
