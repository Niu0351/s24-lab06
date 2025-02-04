package drawing.shapes;

import drawing.writing.JPEGWriter;
import drawing.writing.PNGWriter;

import java.io.IOException;
import java.io.Writer;

/**
 * Refactor Task 3: (Mis-)Shaped
 *
 * @author Zishen Wen (F22), Deyuan Chen (S22)
 */
public interface Shape {

    /**
     * Converts shape into lines.
     *
     * @return lines of this shape.
     */
    Line[] toLines();

    /**
     * Draws lines to file.
     */
    default void draw(Writer writer, Line[] lines) {
        try {
            for (Line line : lines) {
                // TODO: what is the purpose of the code there?
                // Each Writer implementation should know how to write a Line object without the 
                // draw method needing to check its type. Define a common interface method like writeLine(Line line) 
                // in the Writer interface, which each writer class (JPEGWriter, PNGWriter, etc.) 
                // will implement according to how it needs to convert Line objects to the respective format.
                if (writer instanceof JPEGWriter) {
                    writer.write(line.toJPEG());
                } else if (writer instanceof PNGWriter) {
                    writer.write(line.toPNG());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
