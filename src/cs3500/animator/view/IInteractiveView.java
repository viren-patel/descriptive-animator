package cs3500.animator.view;

import java.awt.event.ActionListener;

import javax.swing.BoundedRangeModel;

import cs3500.animator.controller.KeyListener;

/**
 * Represents an interactive view that can be interacted with by buttons and key presses to pause,
 * restart, speed up, speed down, and loop.
 */
public interface IInteractiveView extends IGraphicalView, ITextView, ILoopingView {
  /**
   * Sets the key listener of the view.
   * @param k the key listener for the view
   */
  void setKeyListener(KeyListener k);

  /**
   * Rests the focus after clicking a button.
   */
  void resetFocus();

  /**
   * Sets the button listener of the view.
   * @param b the button listener for the view
   */
  void setButtonListener(ActionListener b);

  /**
   * Sets the pause icon according to the given boolean.
   * @param paused whether the animation is paused or not
   */
  void setPause(boolean paused);

  /**
   * Displays what speed the animation is playing at.
   * @param fps how many frames pass in a second
   */
  void showFPS(int fps);

  /**
   * Gives a visual cue as to whether the animation is looping or not.
   * @param looping whether the animation is looping or not
   */
  void toggleLooping(boolean looping);

  /**
   * Returns the name of the file to output to specified by the user.
   * @return the name of the file to output to specified by the user.
   */
  String getFileName();

  /**
   * Shows a message to the user.
   * @param message the message to be shown
   */
  void showMessage(String message);

  /**Sets a View's slider with the given BoundedRangeModel.
   *
   * @param boundedRangeModel used to manage the values on the slider.
   */
  void setSliderModel(BoundedRangeModel boundedRangeModel);
}

