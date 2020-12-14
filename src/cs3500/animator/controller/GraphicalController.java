package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IGraphicalView;
import cs3500.animator.view.IView;

/**
 * An implementation of IGraphicalController that represents a controller for a graphical view and
 * animation model. The controller keeps track of a visual view, an animation model, and a frame
 * rate. You can run this controller to start a graphical view of the model.
 */
public class GraphicalController extends AbstractController {

  protected int frameCount;
  protected Timer timer;
  protected IGraphicalView view;

  /**
   * Constructs a new graphical controller that should be  given a visual view for the IView.
   * The constructor is protected and therefore ideally should be constructed through the
   * IController builder.
   * @param fps how many frames pass in a second
   * @param view the view this controller controls
   * @param model the model this controller controls
   */
  protected GraphicalController(int fps, IView view, AnimationModel model) {
    super(fps, view, model);
    if (view instanceof IGraphicalView) {
      this.view = (IGraphicalView) view;
    }
    else {
      throw new IllegalArgumentException("Graphical controller must take a graphical view");
    }
  }

  @Override
  public void run() {
    frameCount = 0;
    timer = new Timer(1000 / fps, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.drawShapes(model.getShapesAtTime(frameCount++));
        if (frameCount >= model.maxTime()) {
          timer.stop();
        }
      }
    });
    timer.start();
  }
}
