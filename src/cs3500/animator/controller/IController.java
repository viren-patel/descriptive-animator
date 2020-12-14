package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IView;

/**
 * This interface represents a controller for the animator. It allows the view to run using the
 * model.
 */
public interface IController {

  /**
   * This class is a builder class for a controller.
   */
  final class Builder {
    private IView view;
    private AnimationModel model;
    private int speed;
    private String type = "text";

    /**
     * Sets the speed of the controller.
     *
     * @param speed the speed
     * @return the updated builder
     */
    public Builder speed(int speed) {
      this.speed = speed;
      return this;
    }

    /**
     * Sets the model of the controller.
     *
     * @param model the model
     * @return the updated builder
     */
    public Builder model(AnimationModel model) {
      this.model = model;
      return this;
    }

    /**
     * Sets the controller to graphical mode.
     *
     * @return the updated builder
     */
    public Builder makeGraphicalController() {
      this.type = "graphical";
      return this;
    }

    //todo
    public Builder makeInteractiveController() {
      this.type = "interactive";
      return this;
    }

    /**
     * Sets the view of the controller.
     *
     * @param view the view
     * @return the updated builder
     */
    public Builder view(IView view) {
      this.view = view;
      return this;
    }

    /**
     * Builds the controller to the given specifications.
     *
     * @return a complete controller
     */
    public IController build() {
      if (this.type.equals("graphical")) {
        return new GraphicalController(speed, view, model);
      }
      else if (this.type.equals("interactive")) {
        return new InteractiveController(speed, view, model);
      }
      else {
        return new TextController(speed, view, model);
      }
    }
  }

  /**
   * Returns a controller builder to be used.
   *
   * @return a controller builder.
   */
  static Builder builder() {
    return new Builder();
  }

  /**
   * Runs the animation. The interpretation of "running" depends on the concrete implementation, it
   * may entail displaying the animation visually going frame by frame, outputting all the
   * information of the animation in some format, or some other interpretation.
   */
  void run();
}
