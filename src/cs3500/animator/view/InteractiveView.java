package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.swing.BoundedRangeModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JToolBar;
import javax.swing.JOptionPane;

import cs3500.animator.controller.KeyListener;
import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.transformation.IReadableTransformation;

/**
 * Represents a view that can be interacted with with either button clicks or key presses. This view
 * displays a visual view of the animation that can be speed up/down, restarted, looped, and paused,
 * and can also output to SVG.
 */
public class InteractiveView extends VisualView implements IInteractiveView {

  protected SVGView svgView;
  protected JPanel jPanelControl;
  protected ImageIcon rewindIcon;
  protected ImageIcon pauseIcon;
  protected ImageIcon playIcon;
  protected JButton rewind;
  protected JButton pausePlay;
  protected JButton exportSVG;
  protected JTextField fileName;
  protected JSlider scrubber;
  protected JLabel jLabelFPS;
  protected JLabel loop;

  /**
   * Constructs a new SVG view with initialized GUI.
   */
  public InteractiveView() {
    super();
    setSize(new Dimension(1000,1000));
    svgView = new SVGView(false, 0);
    this.scrubber = new JSlider();
    this.scrubber.setFocusable(false);
    this.scrubber.setPaintLabels(true);
    initControl();
    add(jPanelControl, BorderLayout.SOUTH);
    AnimationToolBar toolBar = new AnimationToolBar();
    this.add(toolBar, BorderLayout.NORTH);
  }

  /**
   * Initialized the control panel that specifies the controls.
   */
  private void initControl() {
    jPanelControl = new JPanel();
    jPanelControl.setLayout(new BoxLayout(jPanelControl, BoxLayout.Y_AXIS));
    jPanelControl.add(Box.createVerticalGlue());
    JLabel restart = new JLabel("R: restart");
    JLabel pause = new JLabel("Space: Play/Pause");
    loop = new JLabel("L: enable looping");
    JLabel inc = new JLabel("Up Arrow: increase speed");
    JLabel dec = new JLabel("Down Arrow: decrease speed");
    Font c = new Font("Courier", Font.PLAIN, 18);
    jPanelControl.add(Box.createRigidArea(new Dimension(0, 10)));
    jPanelControl.add(scrubber);
    jPanelControl.add(Box.createRigidArea(new Dimension(0, 10)));
    JPanel infoPane = new JPanel();
    infoPane.setLayout(new BoxLayout(infoPane, BoxLayout.LINE_AXIS));
    infoPane.add(Box.createHorizontalGlue());
    restart.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
    restart.setFont(c);
    pause.setFont(c);
    pause.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
    loop.setFont(c);
    loop.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
    inc.setFont(c);
    inc.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
    dec.setFont(c);
    dec.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

    jLabelFPS = new JLabel();
    jLabelFPS.setFont(new Font("Courier", Font.BOLD, 18));
    jLabelFPS.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

    infoPane.add(restart);
    infoPane.add(pause);
    infoPane.add(loop);
    infoPane.add(inc);
    infoPane.add(dec);
    infoPane.add(jLabelFPS);

    jPanelControl.add(infoPane);
  }


  @Override
  public void setKeyListener(KeyListener k) {
    Objects.requireNonNull(k);
    addKeyListener(k);
  }

  @Override
  public void outputText(List<IReadableShapeAnimation> shapes,
                         List<IReadableTransformation> transformations, int fps)
          throws IOException, IllegalArgumentException {
    svgView.outputText(shapes, transformations, fps);
  }


  /**Represents a toolbar that has buttons with various commands.
   *
   */
  protected class AnimationToolBar extends JToolBar {
    /**
     * Constructs a new toolbar with initialized buttons.
     */
    protected AnimationToolBar() {
      rewindIcon = new ImageIcon("images/rewind.png");
      pauseIcon = new ImageIcon("images/pause.png");
      playIcon = new ImageIcon("images/play.png");
      rewindIcon = new ImageIcon(rewindIcon.getImage().
              getScaledInstance(75, 75, Image.SCALE_SMOOTH));
      pauseIcon = new ImageIcon(pauseIcon.getImage().getScaledInstance(75, 75,
              Image.SCALE_SMOOTH));
      playIcon = new ImageIcon(playIcon.getImage().getScaledInstance(75, 75,
              Image.SCALE_SMOOTH));
      rewind = new JButton(rewindIcon);
      pausePlay = new JButton(playIcon);
      rewind.setActionCommand("rewind");
      pausePlay.setActionCommand("pause");
      exportSVG = new JButton("EXPORT TO SVG");
      exportSVG.setFont(new Font("Courier", Font.PLAIN, 24));
      fileName = new JTextField("out.svg");
      fileName.setFont(new Font("Courier", Font.PLAIN, 24));
      exportSVG.setActionCommand("export");
      this.add(rewind);
      this.addSeparator();
      this.add(pausePlay);
      this.addSeparator();
      this.add(fileName);
      this.add(exportSVG);
      this.setVisible(true);
    }
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setButtonListener(ActionListener a) {
    Objects.requireNonNull(a);
    this.rewind.addActionListener(a);
    this.pausePlay.addActionListener(a);
    this.exportSVG.addActionListener(a);
  }

  @Override
  public void setPause(boolean paused) {
    if (paused) {
      pausePlay.setIcon(playIcon);
    }
    else {
      pausePlay.setIcon(pauseIcon);
    }
  }

  @Override
  public void showFPS(int fps) {
    jLabelFPS.setText("FPS " + Integer.toString(fps));
  }

  @Override
  public void setSliderModel(BoundedRangeModel boundedRangeModel) {
    this.scrubber.setModel(boundedRangeModel);
  }

  @Override
  public void toggleLooping(boolean looping) {
    if (looping) {
      loop.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
    }
    else {
      loop.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
    }
  }

  @Override
  public String getFileName() {
    return fileName.getText();
  }

  @Override
  public void showMessage(String error) {
    Objects.requireNonNull(error);
    JOptionPane.showMessageDialog(null, error);
  }

  @Override
  public void setLooping(boolean looping, int maxTime) {
    svgView.setLooping(looping, maxTime);
  }

  @Override
  public void setOutput(Appendable output) {
    Objects.requireNonNull(output);
    svgView.setOutput(output);
  }

}
