package cs3500.animator.view;

import java.io.IOException;
import java.util.List;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.transformation.IReadableTransformation;

public interface ITextView extends IView {

  /**
   * Outputs the information of the animation in some sort of way using the given shapes and
   * transformations of the model.
   *
   * @param shapes          All the shapes of the animation in order of creation.
   * @param transformations all the transformations of the animation in order of start time
   * @param fps             the FPS of the animation
   * @throws IllegalArgumentException if the jLabelFPS is invalid
   * @throws IOException              if there is an I/O exception when the view attempts
   *                                  to output information
   */
  void outputText(List<IReadableShapeAnimation> shapes,
                  List<IReadableTransformation> transformations,
                  int fps) throws IOException, IllegalArgumentException;

}
