package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IInteractiveView;

public abstract class AbstractInteractiveController extends AbstractController {
  public AbstractInteractiveController(int fps, IInteractiveView view, AnimationModel model) {
    super(fps, view, model);
  }
}
