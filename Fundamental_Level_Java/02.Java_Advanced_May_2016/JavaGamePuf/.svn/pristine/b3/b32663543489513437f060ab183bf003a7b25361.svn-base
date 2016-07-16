import java.awt.*;


public class Landscape {
    static void drawSun(Graphics2D g, Color clr, int xCoord, int yCoord, int radius) {
        int rayLength = 3 * radius;
        int rayLengthDiag = (int) (rayLength / Math.sqrt(2));
        g.setColor(clr);
        g.fillOval(xCoord, yCoord, 2 * radius, 2 * radius);

        xCoord += radius;
        yCoord += radius;
//<<<<<<< .mine
        g.setStroke(new BasicStroke(2));
        g.drawLine(xCoord, yCoord - rayLength, xCoord, yCoord + rayLength);
        g.drawLine(xCoord - rayLength, yCoord, xCoord + rayLength, yCoord);
        g.drawLine(xCoord - rayLengthDiag, yCoord - rayLengthDiag, xCoord + rayLengthDiag, yCoord + rayLengthDiag);
        g.drawLine(xCoord + rayLengthDiag, yCoord - rayLengthDiag, xCoord - rayLengthDiag, yCoord + rayLengthDiag);
//||||||| .r39
        g.drawLine(xCoord, yCoord - rayLength, xCoord, yCoord + rayLength);
        g.drawLine(xCoord - rayLength, yCoord, xCoord + rayLength, yCoord);
        g.drawLine(xCoord - rayLengthDiag, yCoord - rayLengthDiag, xCoord + rayLengthDiag, yCoord + rayLengthDiag);
        g.drawLine(xCoord + rayLengthDiag, yCoord - rayLengthDiag, xCoord - rayLengthDiag, yCoord + rayLengthDiag);
//=======
//        g.drawLine(xCoord, yCoord - rayLength, xCoord, yCoord + rayLength);
//        g.drawLine(xCoord - rayLength, yCoord, xCoord + rayLength, yCoord);
//        g.drawLine(xCoord - rayLengthDiag, yCoord - rayLengthDiag, xCoord + rayLengthDiag, yCoord + rayLengthDiag);
//        g.drawLine(xCoord + rayLengthDiag, yCoord - rayLengthDiag, xCoord - rayLengthDiag, yCoord + rayLengthDiag);
//>>>>>>> .r48
    }
}
