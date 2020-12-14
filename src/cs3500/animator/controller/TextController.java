package cs3500.animator.controller;

import java.io.IOException;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.ITextView;
import cs3500.animator.view.IView;

/**
 * This class is a concrete implementation of a text controller. When ran, the controller will pass
 * information from the model to the view so it can create some sort of textual output. The
 * controller keeps track of a text view, an animation model, and the fps.
 */
public class TextController extends AbstractController {

  protected ITextView view;

  /**
   * Constructs a new text controller that should be  given a text view for the IView.
   * The constructor is protected and therefore ideally should be constructed through the
   * IController builder.
   * @param fps how many frames pass in a second
   * @param view the view this controller controls
   * @param model the model this controller controls
   */
  protected TextController(int fps, IView view, AnimationModel model) {
    super(fps, view, model);
    if (view instanceof ITextView) {
      this.view = (ITextView) view;
    }
    else {
      throw new IllegalArgumentException("View must be a text view");
    }
  }

  @Override
  public void run() {
    try {
      this.view.outputText(model.getAllShapes(), model.getAllTransformations(), this.fps);
    } catch (IOException e) {
      System.out.println("Error: i/o exception from view");
    }
  }
}
