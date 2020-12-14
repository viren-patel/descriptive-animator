package cs3500.animator;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.util.AnimationFileReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewFactory;

/**
 * Main class for the animator. Runs the animator from the command line.
 */
public final class EasyAnimator {
  /**
   * Runs the controller from the given arguments.
   * @param args the commands
   */
  public static void main(String[] args) {
    IController controller = parseInput(args);
    controller.run();
  }

  /**
   * Parses the input and creates an animator MVC from it.
   * @param args the commands
   * @return the controller.
   */
  private static IController parseInput(String[] args) {
    IController controller = null;
    AnimationModel model = null;
    IView view = null;
    ViewFactory factory = new ViewFactory();
    String viewType = null;
    IController.Builder builder = new IController.Builder();
    builder.speed(1);
    Appendable output = System.out;
    try {
      String command;
      for (int i = 0; i < args.length; i++) {
        command = args[i];
        switch (command) {
          case "-if":
            String fileName = args[i + 1];
            builder.model(callIf(fileName));
            i++;
            break;

          case "-iv":
            viewType = args[i + 1];
            if (viewType.equals("visual")) {
              builder.makeGraphicalController();
            }
            if (viewType.equals("interactive")) {
              builder.makeInteractiveController();
            }
            i++;
            break;
          case "-o":
            output = createOutput(args[i + 1]);
            i++;
            break;
          case "-speed":
            builder.speed(Integer.valueOf(args[i + 1]));
            i++;
            break;
          default:
            showErrorMessage("Invalid command");
        }
      }
      view = ViewFactory.makeView(viewType);
      view.setOutput(output);
      builder.view(view);
      controller = builder.build();
    } catch (Exception e) {
      showErrorMessage(e.getMessage());
    }
    return controller;
  }

  /**
   * Handles the -if command by setting input.
   * @param fileName the specified file input.
   * @return the model
   */
  private static AnimationModel callIf(String fileName) {
    AnimationModelImpl.Builder builder = AnimationModelImpl.builder();
    AnimationFileReader fileReader = new AnimationFileReader();
    try {
      fileReader.readFile("input/" + fileName, builder);
    } catch (IOException e) {
      showErrorMessage(e.getMessage());
      System.exit(1);
    }
    return builder.build();
  }

  /**
   * Creates the output for the view.
   * @param outputName the output file
   * @return the output for the view
   */
  private static Appendable createOutput(String outputName) {
    Appendable output = null;
    if (outputName.equalsIgnoreCase("out")) {
      output = System.out;
    } else {
      try {
        output = new FileWriter("output/" + outputName);
      } catch (IOException e) {
        showErrorMessage(e.getMessage());
      }
    }
    return output;
  }

  /**
   * Shows a given error message.
   * @param message the error message.
   */
  private static void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }
}