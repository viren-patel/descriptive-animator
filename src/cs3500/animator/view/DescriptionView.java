package cs3500.animator.view;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

import cs3500.animator.model.IReadableShapeAnimation;
import cs3500.animator.model.shape.transformation.IReadableTransformation;
import cs3500.animator.view.visitors.description.DescriptionShapeVisitor;
import cs3500.animator.view.visitors.description.DescriptionTransformationVisitor;
import cs3500.animator.view.visitors.IReadableShapeVisitor;
import cs3500.animator.view.visitors.IReadableTransformationVisitor;

/**
 * This view outputs the animation by outputting a description of the model in a string format.
 * That description lists all the shapes created and all the transformations.
 */
public class DescriptionView extends AbstractTextView {

  @Override
  public void outputText(List<IReadableShapeAnimation> shapes,
                         List<IReadableTransformation> transformations,
                         int fps) throws IOException, IllegalArgumentException {
    if (fps <= 0) {
      throw new IllegalArgumentException("error: jLabelFPS invalid");
    }
    Objects.requireNonNull(shapes);
    Objects.requireNonNull(transformations);
    this.out.append("Shapes:");
    out.append(this.shapeDescriptions(shapes, fps));
    out.append("\n");
    out.append(this.transformationDescription(transformations, fps));
    if (out instanceof Writer) {
      ((Writer) out).close();
    }
  }

  /**
   * Returns a description of the shapes.
   *
   * @return a description of the shapes
   */
  private String shapeDescriptions(List<IReadableShapeAnimation> shapes, int fps) {
    IReadableShapeVisitor v = new DescriptionShapeVisitor(fps);
    StringBuilder shapeDescription = new StringBuilder();
    for (IReadableShapeAnimation shape : shapes) {
      shapeDescription.append(v.apply(shape)).append("\n");
    }
    return shapeDescription.toString();
  }

  /**
   * Returns a description of the transformations.
   *
   * @return a description of the transformations
   */
  private String transformationDescription(List<IReadableTransformation> transformations, int fps) {
    IReadableTransformationVisitor v = new DescriptionTransformationVisitor(fps);
    StringBuilder stringBuilder = new StringBuilder();
    for (IReadableTransformation transformation : transformations) {
      stringBuilder.append(v.apply(transformation));
    }
    if (stringBuilder.length() > 0) {
      stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
    return stringBuilder.toString();
  }
}
