package cs3500.animator.view;

import java.util.Objects;

/**
 * Abstract class for text views. Contains how the view will output information and initializes it
 * in the constructor. Throws an exception for drawing shapes as this view does not support it.
 */
public abstract class AbstractTextView implements ITextView {

  protected Appendable out;

  /**
   * Abstract constructor for text views that sets the output to System.out by default.
   */
  public AbstractTextView() {
    this.out = System.out;
  }

  /**
   * Converts a time into seconds given a jLabelFPS (frames per second).
   *
   * @param time the time in frames
   * @param fps  how many frames in a second
   * @return the time in seconds
   */
  public static String toSeconds(float time, int fps) {
    return String.format("%.1f", time / fps) + "s";
  }

  /**
   * Converts a time into milliseconds given a jLabelFPS (frames per second).
   *
   * @param time the time in frames
   * @param fps  how many frames in a second
   * @return the time in milliseconds
   */
  public static String toMilli(int time, int fps) {
    return String.format("%.1f", ((float) time / fps) * 1000) + "ms";
  }

  @Override
  public void setOutput(Appendable out) {
    Objects.requireNonNull(out);
    this.out = out;
  }

}
