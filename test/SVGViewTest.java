import org.junit.Before;
import org.junit.Test;


import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.AnimationModelImpl;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for the svg view.
 */
public class SVGViewTest {

  AnimationModel model;
  IView view;
  IController controller;
  StringBuilder sb;

  /**
   * Initializes a simple animation.
   */
  @Before
  public void init() {
    AnimationModelImpl.Builder b = AnimationModelImpl.builder();
    b.addRectangle("R", 200, 200, 500, 200,
            0, 0, 1, 5, 100);
    b.addOval("C", 300, 800, 100, 300, 0, .5f, 1,
            0, 100);
    b.addMove("R", 200, 200, 0, 0, 5,
            100);
    b.addMove("C", 300, 800, 500, 200, 50,
            100);
    b.addColorChange("R", 0, 0, 1, 1, 0, 0, 50,
            100);
    b.addColorChange("C", 0, 0, 0, 1, 1, 1, 25,
            75);
    b.addScaleToChange("C", 100, 300, 200, 600, 5,
            100);
    b.addScaleToChange("R", 500, 200, 10, 10, 75,
            100);
    model = b.build();
    sb = new StringBuilder();
    view = new SVGView();
    view.setOutput(sb);
    controller = IController.builder().speed(10).model(model).view(view).build();
  }

  @Test
  public void testSVG1() {
    controller.run();
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"500.0\" height=\"200.0\"" +
            " fill=\"rgb(0,0,255)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"500.0ms\" dur =\"9500.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"9500.0ms\" " +
            "attributeName=\"x\" from=\"200.0\" to=\"0.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"9500.0ms\" " +
            "attributeName=\"y\" from=\"200.0\" to=\"0.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,0,0)\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"7500.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"width\" from=\"500.0\" to=\"10.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"7500.0ms\" dur=\"2500.0ms\" " +
            "attributeName=\"height\" from=\"200.0\" to=\"10.0\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</rect>\n" +
            "<ellipse id=\"C\" cx=\"300.0\" cy=\"800.0\" rx=\"100.0\" ry=\"300.0\" " +
            "fill=\"rgb(0,127,255)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"9500.0ms\" " +
            "attributeName=\"rx\" from=\"100.0\" to=\"200.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"9500.0ms\" " +
            "attributeName=\"ry\" from=\"300.0\" to=\"600.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"5000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(255,255,255)\" " +
            "fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" " +
            "attributeName=\"cx\" from=\"300.0\" to=\"500.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"5000.0ms\" " +
            "attributeName=\"cy\" from=\"800.0\" to=\"200.0\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</ellipse>\n" +
            "</svg>", sb.toString());
  }

  @Test
  public void testEmptyModel() {
    model = AnimationModelImpl.builder().build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();
    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "</svg>");
  }

  @Test
  public void addRect() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();
    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"10.0\" height=\"10.0\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "\n\n" +
            "</rect>\n" +
            "</svg>");
  }

  @Test
  public void addRectMove() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).addMove(
            "R", 0, 0, 5, 5,
            10, 20).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();
    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"10.0\" height=\"10.0\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\"" +
            " attributeName=\"x\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"y\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</rect>\n" +
            "</svg>");
  }

  @Test
  public void addRectScale() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).addScaleToChange(
            "R", 0, 0, 5, 5,
            10, 20).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"10.0\" height=\"10.0\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\"" +
            " attributeName=\"width\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"height\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</rect>\n" +
            "</svg>");
  }

  @Test
  public void addRectColor() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).addColorChange(
            "R", 0, 0, 0, 1, 1, 1,
            10, 20).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"10.0\" height=\"10.0\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" " +
            "dur=\"1000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(255,255,255)\"" +
            " fill=\"freeze\"/>\n" +
            "\n\n" +
            "</rect>\n" +
            "</svg>");
  }


  @Test
  public void addOval() {
    model = AnimationModelImpl.builder().addOval("O", 0, 0,
            10, 10, 0, 0, 0, 0, 100).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"O\" cx=\"0.0\" cy=\"0.0\" rx=\"10.0\" ry=\"10.0\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "\n\n" +
            "</ellipse>\n" +
            "</svg>");
  }

  @Test
  public void addOvalMove() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).addMove(
            "R", 0, 0, 5, 5,
            10, 20).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"R\" cx=\"0.0\" cy=\"0.0\" rx=\"10.0\" ry=\"10.0\" fill=\"rgb(0,0,0)\"" +
            " visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"cx\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"cy\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</ellipse>\n" +
            "</svg>");
  }

  @Test
  public void addOvalScale() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).addScaleToChange(
            "R", 0, 0, 5, 5,
            10, 20).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"R\" cx=\"0.0\" cy=\"0.0\" rx=\"10.0\" ry=\"10.0\" fill=\"rgb(0,0,0)\"" +
            " visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\"" +
            " attributeName=\"rx\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"1000.0ms\" " +
            "attributeName=\"ry\" from=\"0.0\" to=\"5.0\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</ellipse>\n" +
            "</svg>");
  }

  @Test
  public void addOvalColor() {
    model = AnimationModelImpl.builder().addOval("R", 0, 0,
            10, 10, 0, 0, 0, 0, 100).addColorChange(
            "R", 0, 0, 0, 1, 1, 1,
            10, 20).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"R\" cx=\"0.0\" cy=\"0.0\" rx=\"10.0\" ry=\"10.0\"" +
            " fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\" " +
            "begin=\"0.0ms\" dur =\"10000.0ms\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1000.0ms\"" +
            " dur=\"1000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" " +
            "to=\"rgb(255,255,255)\" fill=\"freeze\"/>\n" +
            "\n\n" +
            "</ellipse>\n" +
            "</svg>");
  }


  @Test
  public void hiddenAtStart() {
    model = AnimationModelImpl.builder().addRectangle("R", 0, 0,
            10, 10, 0, 0, 0, 10, 100).build();
    IController controller = IController.builder().speed(10).model(model).view(view).build();

    controller.run();
    assertEquals(sb.toString(), "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<rect id=\"R\" x=\"0.0\" y=\"0.0\" width=\"10.0\" height=\"10.0\"" +
            " fill=\"rgb(0,0,0)\" visibility=\"hidden\" >\n" +
            "<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"1000.0ms\" dur =\"9000.0ms\" />\n" +
            "\n\n" +
            "</rect>\n" +
            "</svg>");
  }

  @Test(expected = NullPointerException.class)
  public void nullOutput() {
    IView v = new SVGView();
    v.setOutput(null);
  }
}
