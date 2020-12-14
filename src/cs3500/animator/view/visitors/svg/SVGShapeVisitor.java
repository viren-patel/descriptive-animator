package cs3500.animator.view.visitors.svg;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Rectangle;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.util.IColor;
import cs3500.animator.view.AbstractTextView;
import cs3500.animator.view.visitors.IReadableShapeVisitor;


/**
 * This class is a shape visitor for svg formatting. The reason it is a protected class of the
 * formatter is that this visitor is implemented only to work on one shape only. Because of that,
 * we do not want it to be exposed as it could be misused. Additionally, it specific only to svg,
 * so there is no reason for it to be public.
 */
class SVGShapeVisitor implements IReadableShapeVisitor<String> {
  protected String id;
  protected List<IReadableTransformation> transformations;
  protected int fps;
  protected int appearTime;
  protected int disappearTime;
  protected boolean looping;
  protected SVGRectTransformationVisitor svgRectTransformationVisitor;
  protected SVGOvalTransformationVisitor svgOvalTransformationVisitor;

  /**
   * Constructs a new shape visitor that will format shapes.
   *
   * @param transformations the transformations of the shape that this visitor will be applied on.
   * @param fps             how many frames pass in a second.
   * @param looping whether the animation is looping or not
   */
  protected SVGShapeVisitor(List<IReadableTransformation> transformations,
                            int fps, boolean looping) {
    this.transformations = transformations;
    this.fps = fps;
    this.looping = looping;
    this.svgOvalTransformationVisitor = new SVGOvalTransformationVisitor(fps, looping);
    this.svgRectTransformationVisitor = new SVGRectTransformationVisitor(fps, looping);
  }

  @Override
  public String visitOval(Oval o) {
    StringBuilder sb = new StringBuilder();
    sb.append("<ellipse id=").append("\"").append(id).append("\"");
    sb.append(" cx=\"").append(o.getPosition().getX()).append("\"");
    sb.append(" cy=\"").append(o.getPosition().getY()).append("\"");
    sb.append(" rx=\"").append(o.getWidth()).append("\"");
    sb.append(" ry=\"").append(o.getHeight()).append("\"");
    sb.append(formatFillAndVisibility(appearTime, disappearTime,
            o.getColor()));
    sb.append("\n");
    List<String> modifiedAttributes = new ArrayList<>();

    for (IReadableTransformation t : transformations) {
      sb.append(svgOvalTransformationVisitor.apply(t));
      addModifiedAttribute(t, modifiedAttributes);
      sb.append("\n");
    }
    sb.append("\n");
    if (looping) {
      if (modifiedAttributes.contains("move")) {
        sb.append(loopResetStub());
        sb.append("cx\" to=\"").append(o.getPosition().getX()).append("\" fill=\"freeze\" />\n");
        sb.append(loopResetStub());
        sb.append("cy\" to=\"").append(o.getPosition().getY()).append("\" fill=\"freeze\" />\n");
      }
      if (modifiedAttributes.contains("scale")) {
        sb.append(loopResetStub());
        sb.append("rx\" to=\"").append(o.getWidth()).append("\" fill=\"freeze\" />\n");
        sb.append(loopResetStub());
        sb.append("ry\" to=\"").append(o.getHeight()).append("\" fill=\"freeze\" />\n");
      }
      if (modifiedAttributes.contains("color")) {
        sb.append(loopResetStub());
        sb.append("fill\" to=\"").append(formatRGB(o.getColor())).append("\" fill=\"freeze\" />\n");
      }
    }
    return sb.toString();
  }


  @Override
  public String visitRectangle(Rectangle r) {
    StringBuilder sb = new StringBuilder();
    sb.append("<rect id=").append("\"").append(id).append("\"");
    sb.append(" x=\"").append(r.getPosition().getX()).append("\"");
    sb.append(" y=\"").append(r.getPosition().getY()).append("\"");
    sb.append(" width=\"").append(r.getWidth()).append("\"");
    sb.append(" height=\"").append(r.getHeight()).append("\"");
    sb.append(formatFillAndVisibility(appearTime, disappearTime,
            r.getColor()));
    sb.append("\n");
    List<String> modifiedAttributes = new ArrayList<>();
    for (IReadableTransformation t : transformations) {
      sb.append(svgRectTransformationVisitor.apply(t));
      addModifiedAttribute(t, modifiedAttributes);
      sb.append("\n");
    }
    if (looping) {
      if (modifiedAttributes.contains("move")) {
        sb.append(loopResetStub());
        sb.append("x\" to=\"").append(r.getPosition().getX()).append("\" fill=\"freeze\" />\n");
        sb.append(loopResetStub());
        sb.append("y\" to=\"").append(r.getPosition().getY()).append("\" fill=\"freeze\" />\n");

      }
      if (modifiedAttributes.contains("scale")) {
        sb.append(loopResetStub());
        sb.append("width\" to=\"").append(r.getWidth()).append("\" fill=\"freeze\" />\n");
        sb.append(loopResetStub());
        sb.append("height\" to=\"").append(r.getHeight()).append("\" fill=\"freeze\" />\n");
      }
      if (modifiedAttributes.contains("color")) {
        sb.append(loopResetStub());
        sb.append("fill\" to=\"").append(formatRGB(r.getColor())).append("\" fill=\"freeze\" />\n");
      }
    }
    sb.append("\n");
    return sb.toString();
  }

  /**
   * Formats the fill and visibility attributes of the shape.
   *
   * @param sTime the time the shape appears
   * @param eTime the time the shape disappears
   * @param color the color of the shape
   */
  protected String formatFillAndVisibility(int sTime, int eTime, IColor color) {
    StringBuilder sb = new StringBuilder();
    sb.append(" fill=\"");
    sb.append(formatRGB(color)).append("\"");
    sb.append(" visibility=\"");
    if (sTime == 0) {
      sb.append("visible\" >");
    } else {
      sb.append("hidden\" >");
    }
    sb.append("\n");

    sb.append(setVisibilityDuration(sTime, eTime));
    return sb.toString();
  }

  @Override
  public String apply(IReadableShapeAnimation s) {
    this.id = s.getShapeId();
    this.appearTime = s.getAppearTime();
    this.disappearTime = s.getDisappearTime();
    StringBuilder sb = new StringBuilder();
    sb.append(s.getReadableShape().accept(this));
    sb.append("\n");
    sb.append(new TagVisitor().apply(s));
    return sb.toString();
  }

  /**
   * Adds the type of transformation to the given list.
   * @param t the given transformation
   * @param modifiedAttributes the list of types of transformations
   */
  private void addModifiedAttribute(IReadableTransformation t, List<String> modifiedAttributes) {
    String type =  AbstractSVGTransformationVisitor.getType(t);
    if (!modifiedAttributes.contains(type)) {
      modifiedAttributes.add(type);
    }
  }

  /**
   * Creates a visibility tag according to the given times.
   * @param sTime when the shape becomes visible
   * @param eTime when the shape becomes invisible.
   * @return a SVG tag that sets visibility
   */
  private String setVisibilityDuration(int sTime, int eTime) {
    StringBuilder sb = new StringBuilder();
    sb.append("<set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\"" +
            " begin=\"");
    if (looping) {
      sb.append("base.begin+");
    }
    sb.append(AbstractTextView.toMilli(sTime, fps));
    sb.append("\" dur =\"");
    sb.append(AbstractTextView.toMilli(eTime - sTime, fps));
    sb.append("\" />");
    return sb.toString();
  }

  /**
   * Formats the given color into the format rgb(r,g,b).
   * @param color the color being formatted
   * @return the formatted String of the color
   */
  private String formatRGB(IColor color) {
    StringBuilder sb = new StringBuilder();
    sb.append("rgb(");
    sb.append((int) (color.getRed() * 255)).append(",");
    sb.append((int) (color.getGreen() * 255)).append(",");
    sb.append((int) (color.getBlue() * 255)).append(")");
    return sb.toString();
  }

  /**
   * This class is a shape visitor who's purpose is only to return the closing tag for the
   * appropriate shape.
   */
  protected class TagVisitor implements IReadableShapeVisitor<String> {

    @Override
    public String visitOval(Oval o) {
      return "</ellipse>";
    }

    @Override
    public String visitRectangle(Rectangle r) {
      return "</rect>";
    }

    @Override
    public String apply(IReadableShapeAnimation s) {
      return s.getReadableShape().accept(this);
    }
  }

  /**
   * A stub for resetting the shape after its been modified.
   * @return a SVG tag that can be added to to reset the shape
   */
  private String loopResetStub() {
    return "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\"";
  }

}