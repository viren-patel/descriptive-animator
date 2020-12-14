package cs3500.animator.view.visitors.svg;

import cs3500.animator.model.shape.transformation.ChangeColor;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.model.shape.transformation.Move;
import cs3500.animator.model.shape.transformation.Rotate;
import cs3500.animator.model.shape.transformation.Scale;
import cs3500.animator.view.AbstractTextView;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;

/**
 * This class is an abstract transformation visitor for svg. Because how a transformation will be
 * formatted depends on the concrete class of the shape its operating on, there must be multiple
 * transformation visitors that can handle all transformations but operate for different shapes.
 * This class is the abstraction that all those visitors will inherit from as it implements shared
 * behaviours.
 */
public abstract class AbstractSVGTransformationVisitor
        implements IReadableTransformationVisitor<String> {

  protected int fps;
  protected boolean looping;


  /**
   * Abstract constructor for a svg transformation visitor that initializes fps.
   *
   * @param fps how many frames pass in a second
   */
  public AbstractSVGTransformationVisitor(int fps, boolean looping) {

    if (fps <= 0) {
      throw new IllegalArgumentException("invalid fps");
    }
    this.fps = fps;
    this.looping = looping;
  }

  @Override
  public String visitChangeColor(ChangeColor c) {
    StringBuilder sb = new StringBuilder();
    sb.append("fill\" ");
    String from = "rgb("
            + (int) (c.getFrom().getRed() * 255) + ","
            + (int) (c.getFrom().getGreen() * 255) + ","
            + (int) (c.getFrom().getBlue() * 255) + ")";
    String to = "rgb("
            + (int) (c.getTo().getRed() * 255) + ","
            + (int) (c.getTo().getGreen() * 255) + ","
            + (int) (c.getTo().getBlue() * 255) + ")";
    sb.append(formatFromTo(from, to));
    sb.append(" fill=\"freeze\"");
    sb.append("/>");

    return sb.toString();
  }


  /**
   * Formats an animate tag in svg for moving given the move animation and the attribute names.
   *
   * @param xName the attribute name for the x of whats being moved
   * @param yName the attribute name for the y of whats being moved
   * @param m     the move transformation
   * @return a formatted svg animate tag
   */
  protected String formatMove(String xName, String yName, Move m) {
    StringBuilder sb = new StringBuilder();
    sb.append(xName).append("\" ");
    sb.append(formatFromTo(Float.toString(m.getFrom().getX()),
            Float.toString(m.getTo().getX())));
    sb.append(" fill=\"freeze\"");
    sb.append("/>");
    sb.append("\n");
    sb.append(animationStub(m));
    sb.append(yName).append("\" ");
    sb.append(formatFromTo(Float.toString(m.getFrom().getY()),
            Float.toString(m.getTo().getY())));
    sb.append(" fill=\"freeze\"");
    sb.append("/>");
    return sb.toString();
  }

  /**
   * Formats an animate tag in svg for scaling given the scale animation and the attribute names.
   *
   * @param wName the attribute name for the width of whats being scaled
   * @param hName the attribute name for the height of whats being scaled
   * @param s     the scale transformation
   * @return a formatted svg animate tag
   */
  protected String formatScale(String wName, String hName, Scale s) {
    StringBuilder sb = new StringBuilder();
    sb.append(wName).append("\" ");
    sb.append(formatFromTo(Float.toString(s.getFrom().getKey()),
            Float.toString(s.getTo().getKey())));
    sb.append(" fill=\"freeze\"");
    sb.append("/>");
    sb.append("\n");
    sb.append(animationStub(s));
    sb.append(hName).append("\" ");
    sb.append(formatFromTo(Float.toString(s.getFrom().getValue()),
            Float.toString(s.getTo().getValue())));
    sb.append(" fill=\"freeze\"");
    sb.append("/>");
    return sb.toString();
  }

  /**
   * Creates the start of an animate tag that all transformations start with.
   *
   * @param t the transformation being formatted
   * @return a stub for animate tags to be added to with cconcrete details
   */
  protected String animationStub(IReadableTransformation t) {
    StringBuilder sb = new StringBuilder();
    sb.append("<animate attributeType=\"xml\" begin=\"");
    if (looping) {
      sb.append("base.begin+");
    }
    sb.append(AbstractTextView.toMilli(t.getStartTime(), fps)).append("\"");
    sb.append(" dur=\"").append(AbstractTextView.toMilli(t.getEndTime() - t.getStartTime(),
            fps)).append("\"");
    sb.append(" attributeName=\"");
    return sb.toString();
  }

  /**
   * Formats a time from and to into a proper format.
   *
   * @param from the "from" time
   * @param to   the "to" time
   * @return two times in the proper svg format
   */
  protected String formatFromTo(String from, String to) {
    return "from=\"" + from + "\" to=\"" + to + "\"";
  }

  @Override
  public String apply(IReadableTransformation t) {
    StringBuilder sb = new StringBuilder();
    sb.append(animationStub(t));
    sb.append(t.accept(this));
    return sb.toString();
  }

  /**
   * Visits the transformation and returns what type of transformation it is.
   */
  private static class TypeVisitor implements IReadableTransformationVisitor<String> {

    @Override
    public String visitScale(Scale s) {
      return "scale";
    }

    @Override
    public String visitChangeColor(ChangeColor c) {
      return "color";
    }

    @Override
    public String visitMove(Move m) {
      return "move";
    }

    @Override
    public String visitRotate(Rotate r) {
      return "rotate";
    }

    @Override
    public String apply(IReadableTransformation t) {
      return t.accept(this);
    }
  }

  /**
   * Returns the type of the given transformation.
   * @param t the transformation
   * @return its type
   */
  public static String getType(IReadableTransformation t)  {
    return new TypeVisitor().apply(t);
  }


}
