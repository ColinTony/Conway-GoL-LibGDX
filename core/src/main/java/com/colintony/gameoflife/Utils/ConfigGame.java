package
 com.colintony.gameoflife.Utils;

         import java.awt.Dimension;
         import java.awt.Toolkit;

public class ConfigGame {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH_PANTALLA = screenSize.width;
    public static int HEIGTH_PANTALLA = screenSize.height;
    public static int GRID_WIDTH = 20;
    public static int GRID_HEIGTH = 20;

}