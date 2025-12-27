/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class Ex2_GUI {
    public static void drawMap(Map2D map) {
        if(map == null) return;

        int w = map.getWidth();
        int h = map.getHeight();
        int[][] mat = new int[w][h];
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                mat[y][x] = map.getPixel(x,y);
            }
        }
        drawMat(mat);
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        if (mapFileName == null) return null;

        try {
            Scanner sc = new Scanner(new File(mapFileName));
            int w = sc.nextInt();
            int h = sc.nextInt();

            int[][] mat = new int[w][h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    mat[x][y] = sc.nextInt();
                }
            }
            sc.close();
            return new Map(mat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {
        if (map == null || mapFileName == null) return;

        try {
            PrintWriter pw = new PrintWriter(new File(mapFileName));
            int w = map.getWidth();
            int h = map.getHeight();

            pw.println(w + " " + h);

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    pw.print(map.getPixel(x, y) + " ");
                }
                pw.println();
            }
            pw.close();}
        catch (Exception e) {e.printStackTrace();}

    }
    public static void main(String[] a) {
        // I drew a heart :)
        int[][] mat = {
                {0,1,1,0,0},
                {1,1,1,1,0},
                {0,1,1,1,1},
                {1,1,1,1,0},
                {0,1,1,0,0},
        };

        Map2D map = new Map(mat);


        map.fill(new Index2D(2,2), 2, false);

        //map.drawRect(new Index2D(0,2), new Index2D(1,1), 3);
        //map.drawCircle(new Index2D(2,3), 1, 4);

        drawMap(map);
    }

    /// ///////////// Private functions ///////////////
    public static void drawMat(int[][] mat) {
        StdDraw.clear();
        int rows = mat.length;
        int cols = mat[0].length;
        double cellSize = 1.0 / Math.max(rows, cols);
        double padding = 0.05 * cellSize;

        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                int v = mat[y][x];
                if(v == 0) StdDraw.setPenColor(StdDraw.PINK);
                else if(v == 1) StdDraw.setPenColor(StdDraw.DARK_GRAY);
                else if(v == 2) StdDraw.setPenColor(StdDraw.BOOK_RED);
                else if(v == 3) StdDraw.setPenColor(StdDraw.PINK);
                else if(v == 4) StdDraw.setPenColor(StdDraw.GREEN);
                else StdDraw.setPenColor(StdDraw.BLACK);
                double cx = (x + 0.5) * cellSize;
                double cy = 1.0 - (y + 0.5) * cellSize;
                StdDraw.filledRectangle(cx, cy, (cellSize - padding) / 2, (cellSize - padding) / 2);
            }
        }
        StdDraw.show();
        StdDraw.pause(20);
    }
}
