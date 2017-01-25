package structural.flyweight.entities;

import java.awt.*;

public class Oval implements Shape {

    //intrinsic property
    private boolean fill;

    public Oval(boolean fill) {
        this.fill = fill;
        System.out.println("Create Oval object with fill = " + fill);
        //adding time delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics oval, int x, int y, int width, int height, Color color) {
        oval.setColor(color);
        oval.drawOval(x, y, width, height);
        if (this.fill) {
            oval.fillOval(x, y, width, height);
        }
    }
}
