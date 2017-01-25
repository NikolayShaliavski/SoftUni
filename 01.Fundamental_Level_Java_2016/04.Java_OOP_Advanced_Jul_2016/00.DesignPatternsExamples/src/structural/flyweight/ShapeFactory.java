package structural.flyweight;

import structural.flyweight.entities.Line;
import structural.flyweight.entities.Oval;
import structural.flyweight.entities.Shape;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {

    private static final Map<ShapeType, Shape> shapes = new HashMap<>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            switch (type) {
                case OVAL_FILL:
                    shapeImpl = new Oval(true);
                    break;
                case OVAL_NOFILL:
                    shapeImpl = new Oval(false);
                    break;
                case LINE:
                    shapeImpl = new Line();
                    break;
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }
}
