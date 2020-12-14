package cs3500.animator.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class AnimationKeyListener implements KeyListener {

  Map<Integer, Runnable> keyPressedMap;

  public void setKeyPressedMap(Map<Integer, Runnable> keyPressedMap) {
    this.keyPressedMap = keyPressedMap;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    //does not use this functionality
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //does not use this functionality
  }
}
