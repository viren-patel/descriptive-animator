package cs3500.animator.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class MouseHandler implements MouseListener {

  Map<Integer, Runnable> mouseEvents;

  public MouseHandler() {
    mouseEvents = new HashMap<>();
  }

  public void setMouseEvents(Map<Integer, Runnable> mouseEvents) {
    this.mouseEvents = mouseEvents;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (mouseEvents.containsKey(e.getButton())) {
      mouseEvents.get(e.getButton()).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //uses mouse clicked
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //uses mouse clicked
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //does not use this functionality
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //does not use this functionality

  }
}
