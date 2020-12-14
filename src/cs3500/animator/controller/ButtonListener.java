package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Button listener for the interactive view. Must be initialized by a controller with the button
 * mapping.
 */
public class ButtonListener implements ActionListener {

  protected Map<String, Runnable> buttonEvents;

  /**
   * Constructs a new, uninitialized button listener.
   */
  public ButtonListener() {
    this.buttonEvents = new HashMap<>();
  }

  /**
   * Initializes the listener by mapping the button events.
   * @param buttonEvents The different button events that can occur.
   */
  public void setButtonEvents(Map<String, Runnable> buttonEvents) {
    this.buttonEvents = buttonEvents;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonEvents.containsKey(e.getActionCommand())) {
      buttonEvents.get(e.getActionCommand()).run();
    }
  }
}
