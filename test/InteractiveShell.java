import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;


import cs3500.animator.controller.KeyListener;
import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.IReadableShape;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.view.InteractiveView;

/**
 * Empty interactive shell that appends to an output whenever a method is called. For testing
 * purposes.
 */
public class InteractiveShell extends InteractiveView {

  private StringBuilder testOutput;

  /**
   * Constructs a test interactive view.
   * @param testOutput the output to see what methods are being called.
   */
  public InteractiveShell(StringBuilder testOutput) {
    this.testOutput = testOutput;
  }

  @Override
  public void setKeyListener(KeyListener k) {
    super.setKeyListener(k);
    testOutput.append("Key listener set\n");
  }

  @Override
  public void resetFocus() {
    testOutput.append("Reset focus\n");
  }

  @Override
  public void setButtonListener(ActionListener a) {
    super.setButtonListener(a);
    testOutput.append("Button listener set\n");
  }

  @Override
  public void setPause(boolean paused) {
    testOutput.append("Paused\n");
  }

  @Override
  public void showFPS(int fps) {
    testOutput.append("FPS changed to " + fps + "\n");
  }

  @Override
  public void toggleLooping(boolean looping) {
    testOutput.append("Looping turned to " + looping + "\n");
  }

  @Override
  public String getFileName() {
    testOutput.append("File name retrieved\n");
    return "out.svg";
  }

  @Override
  public void showMessage(String error) {
    testOutput.append("Message shown: " + error + "\n");
  }

  @Override
  public void drawShapes(List<IReadableShape> shapes) {
    testOutput.append("Shapes drawn\n");
  }

  @Override
  public void setLooping(boolean looping, int maxTime) {
    testOutput.append("Looping set\n");
  }

  @Override
  public void outputText(List<IReadableShapeAnimation> shapes, List<IReadableTransformation>
          transformations, int fps) throws IllegalArgumentException {
    testOutput.append("Text outputted\n");
  }

  @Override
  public void setOutput(Appendable out) throws NullPointerException {
    testOutput.append("Output set\n");
  }

  /**
   * Simulates pressing pause.
   */
  public void propagatePauseButton() {
    this.pausePlay.doClick();
  }

  /**
   * Simulates pressing export.
   */
  public void propagateExport() {
    this.exportSVG.doClick();
  }

  /**
   * Simulates pressing space.
   */
  public void propagateSpace() {
    getKeyListeners()[0].keyPressed(new KeyEvent(this,
            0, 0, 0, KeyEvent.VK_SPACE));
  }

  /**
   * Simulates pressing L.
   */
  public void propagateL() {
    getKeyListeners()[0].keyPressed(new KeyEvent(this,
            0, 0, 0, KeyEvent.VK_L));
  }

  /**
   * Simulates pressing R.
   */
  public void propagateR() {
    getKeyListeners()[0].keyPressed(new KeyEvent(this,
            0, 0, 0, KeyEvent.VK_R));
  }

  /**
   * Simulates pressing up arrow.
   */
  public void propagateUp() {
    getKeyListeners()[0].keyPressed(new KeyEvent(this,
            0, 0, 0, KeyEvent.VK_UP));
  }

  /**
   * Simulates pressing down arrow.
   */
  public void propagateDown() {
    getKeyListeners()[0].keyPressed(new KeyEvent(this,
            0, 0, 0, KeyEvent.VK_DOWN));
  }
}

