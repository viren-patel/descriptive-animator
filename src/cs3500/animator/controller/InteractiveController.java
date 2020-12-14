package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JSlider;
import javax.swing.Timer;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.view.IInteractiveView;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;

/**
 * Represents a controller that can support a view with button and keyboard commands.
 */
public class InteractiveController extends AbstractController {

  protected boolean paused;
  protected boolean looping;
  protected boolean rewinding;
  protected int frameCount;
  protected Timer timer;
  protected IInteractiveView view;
  protected BoundedRangeModel rangeModel;

  /**
   * Constructs a new interactive controller that should be  given a interactive view for the IView.
   * The constructor is protected and therefore ideally should be constructed through the
   * IController builder.
   *
   * @param fps   how many frames pass in a second
   * @param view  the view this controller controls
   * @param model the model this controller controls
   */
  protected InteractiveController(int fps, IView view, AnimationModel model) {
    super(fps, view, model);
    if (view instanceof IInteractiveView) {
      this.view = (InteractiveView) view;
    } else {
      throw new IllegalArgumentException("This controller can only control interactive views");
    }
    paused = true;
    rewinding = false;
    frameCount = 1;
    if (model.maxTime() > 0) {
      this.view.drawShapes(model.getShapesAtTime(0));
    }
    this.view.showFPS(fps);
    initTimer();
    configureKeyListener();
    configureButtonListener();
    this.rangeModel = new DefaultBoundedRangeModel(0, 0, 0, this.model.maxTime() - 1);
    this.view.setSliderModel(rangeModel);
    this.view.resetFocus();
  }

  /**
   * Initializes the timer using the current fps.
   */
  private void initTimer() {
    timer = new Timer(1000 / fps, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frameCount = rangeModel.getValue();
        if (rewinding && frameCount >= 2) {
          frameCount -= 2;
        }
        if (paused) {
          rewinding = false;
          timer.stop();
        }

        view.drawShapes(model.getShapesAtTime(frameCount++));
        if (frameCount >= model.maxTime()) {
          frameCount = 0;
          if (!looping) {
            pause();
          }
        }
        rangeModel.setValue(frameCount);
      }
    });
  }

  /**
   * Initializes the key listener to be passed to the view.
   */
  private void configureKeyListener() {

    Map<Integer, Runnable> keyPressedMap = new HashMap<>();
    keyPressedMap.put(KeyEvent.VK_SPACE, new Runnable() {
      @Override
      public void run() {
        pause();
      }
    });
    keyPressedMap.put(KeyEvent.VK_R, new Runnable() {
      @Override
      public void run() {
        restart();
      }
    });
    keyPressedMap.put(KeyEvent.VK_L, new Runnable() {
      @Override
      public void run() {
        looping = !looping;
        view.toggleLooping(looping);
        view.setLooping(looping, model.maxTime());
      }
    });
    keyPressedMap.put(KeyEvent.VK_UP, new Runnable() {
      @Override
      public void run() {
        changeFPS(5);
      }
    });
    keyPressedMap.put(KeyEvent.VK_DOWN, new Runnable() {
      @Override
      public void run() {
        changeFPS(-5);
      }
    });
    keyPressedMap.put(KeyEvent.VK_LEFT, new Runnable() {
      @Override
      public void run() {
        rewinding = !rewinding;
        paused = false;
      }
    });

    KeyListener keyListener = new KeyListener();
    keyListener.setKeyPressedMap(keyPressedMap);
    view.setKeyListener(keyListener);
  }

  /**
   * Configures the button listener for the view and what should happen upon being clicked.
   */
  private void configureButtonListener() {
    Map<String, Runnable> buttonEvents = new HashMap<>();
    buttonEvents.put("pause", new Runnable() {
      @Override
      public void run() {
        pause();
        view.resetFocus();
      }
    });
    buttonEvents.put("rewind", new Runnable() {
      @Override
      public void run() {
        view.resetFocus();
        //todo
      }
    });
    buttonEvents.put("export", new Runnable() {
      @Override
      public void run() {
        createSVG(view.getFileName());
        view.resetFocus();
      }
    });
    ButtonListener buttonListener = new ButtonListener();
    buttonListener.setButtonEvents(buttonEvents);
    view.setButtonListener(buttonListener);
  }

  /**
   * Pauses the animation by stopping the timer. Unpauses if it is already paused.
   */
  private void pause() {
    if (paused) {
      paused = false;
      run();
    } else {
      paused = true;
    }
    view.setPause(paused);

  }

  /**
   * Restarts the animation from the beginning.
   */
  private void restart() {
    frameCount = 1;
    view.drawShapes(model.getShapesAtTime(0));
  }

  /**
   * Changes the fps by the given amount, with the fps having a maximum value of 200 and
   * a minimum of 1.
   *
   * @param del the change in fps
   */
  private void changeFPS(int del) {
    this.fps = Math.min(fps + del, 200);
    this.fps = Math.max(fps, 1);
    timer.setDelay(1000 / fps);
    this.view.showFPS(fps);
  }

  /**
   * Creates an SVG file with the given name.
   *
   * @param name the file name
   */
  private void createSVG(String name) {
    try {
      FileWriter output;
      output = new FileWriter("output/" + name);
      view.setOutput(output);
      this.view.outputText(model.getAllShapes(), model.getAllTransformations(), this.fps);
      view.showMessage("Finished exporting!");
    } catch (IOException e) {
      view.showMessage(e.getMessage());
    }
  }

  @Override
  public void run() {
    timer.start();
  }
}
