package cs3500.animator.controller;

import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * The key listener class for the interactive view.
 */
public class KeyListener implements java.awt.event.KeyListener {

  protected Map<Integer, Runnable> keyPressedMap;

  /**
   * Initializes the key listener by mapping what keys do what.
   * @param keyPressedMap the map of what keys do what
   */
  public void setKeyPressedMap(Map<Integer, Runnable> keyPressedMap) {
    this.keyPressedMap = keyPressedMap;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //Uses key pressed, not key types
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //Uses key pressed, not key released
  }
}
