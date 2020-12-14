package cs3500.animator.view;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.view.visitors.svg.ISVGShapeFormatter;
import cs3500.animator.view.visitors.svg.SVGShapeFormatter;

/**
 * This view represents a text view that outputs the animation in svg format.
 */
public class SVGView extends AbstractTextView implements ILoopingView {

  protected final int WIDTH = 1000;
  protected final int HEIGHT = 1000;
  protected boolean looping;
  protected int maxTime;

  /**
   * Constructs an SVG view that may or may not be looping.
   * @param looping whether this view is looping or not
   * @param maxTime the max time of the animation
   */
  public SVGView(boolean looping, int maxTime) {
    this.looping = looping;
    this.maxTime = maxTime;
  }

  /**
   * Constructs a non looping SVG view.
   */
  public SVGView() {
    this.looping = false;
  }

  @Override
  public void outputText(List<IReadableShapeAnimation> shapes,
                         List<IReadableTransformation> transformations, int fps)
          throws IOException, IllegalArgumentException {
    Objects.requireNonNull(shapes);
    Objects.requireNonNull(transformations);
    if (fps <= 0) {
      throw new IllegalArgumentException("Error: invalid jLabelFPS");
    }
    Map<String, List<IReadableTransformation>> transformationMap =
            this.getTransformationMap(transformations);
    out.append("<svg width=\"").append(Integer.toString(WIDTH)).append("\" ");
    out.append("height=\"").append(Integer.toString(HEIGHT)).append("\" ");
    out.append("version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">");
    out.append("\n");

    if (looping) {
      out.append("<rect>\n");
      out.append("   <animate id=\"base\" begin=\"0;base.end\" dur=\"");
      out.append(AbstractTextView.toMilli(maxTime, fps));
      out.append("\" ");
      out.append("attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n</rect>");
      out.append("\n");
    }

    ISVGShapeFormatter formatter = new SVGShapeFormatter(looping);
    for (IReadableShapeAnimation shapeAnimation : shapes) {
      out.append(formatter.generateSVG(shapeAnimation, transformationMap.getOrDefault(
              shapeAnimation.getShapeId(), new ArrayList<>()), fps));
      out.append("\n");
    }
    out.append("</svg>");

    if (out instanceof Writer) {
      ((Writer) out).close();
    }
  }

  /**
   * Creates a map of transformations for each shape.
   *
   * @param transformations the list of all the transformations
   * @return a map where the key is the shape ID and the value is that shapes list of transformsx
   */
  private Map<String, List<IReadableTransformation>> getTransformationMap(
          List<IReadableTransformation> transformations) {
    Map<String, List<IReadableTransformation>> ret = new HashMap<>();
    for (IReadableTransformation t : transformations) {
      if (ret.containsKey(t.getShapeId())) {
        ret.get(t.getShapeId()).add(t);
      } else {
        List<IReadableTransformation> lst = new ArrayList<>();
        lst.add(t);
        ret.put(t.getShapeId(), lst);
      }
    }
    return ret;
  }

  @Override
  public void setLooping(boolean looping, int maxTime) {
    this.looping = looping;
    this.maxTime = maxTime;
  }

}
