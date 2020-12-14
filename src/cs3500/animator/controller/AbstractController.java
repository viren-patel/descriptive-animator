package cs3500.animator.controller;

import java.util.Objects;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

/**
 * Abstract class for all controllers that sets the fps and model. The view is to be handled by
 * the concrete implementation.
 */
public abstract class AbstractController implements IController {

  protected int fps;
  protected AnimationModel model;

  /**
   * Initializes the fps and model of the controller.
   *
   * @param fps   how many frames pass in a second
   * @param view  what view this controller is controlling
   * @param model what model this controller is controlling
   */
  public AbstractController(int fps, IView view, AnimationModel model) throws
          IllegalArgumentException, NullPointerException {
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);
    if (fps <= 0) {
      throw new IllegalArgumentException("Error: invalid fps");
    }
    this.fps = fps;
    this.model = model;
  }
}
