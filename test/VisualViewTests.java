import org.junit.Test;

import cs3500.animator.view.VisualView;

/**
 * Test class for the visual view.
 */
public class VisualViewTests {
  @Test (expected = UnsupportedOperationException.class)
  public void testSetOutput() {
    VisualView v = new VisualView();
    v.setOutput(System.out);
  }
}
