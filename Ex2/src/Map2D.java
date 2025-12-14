/**
 * This interface represents a 2D map as a raster matrix, image or maze.
 * Ex2: Intro2CS - do NOT change this interface!
 * The data is assumed to be a rectangular 2D matrix (not a ragged array).
 * This interface includes the following functionalities:
 * 1. basic: init, copy, get dimensions.
 * 2. draw: rectangle, line, circle.
 * 3. set: rescale, add (another map), mul (by scalar), crop.
 * 4. shortest path (obstacle avoiding).
 * 5. shortest distances from a source coordinate to each entry in this map (obstacle avoiding).
 * 6. fill a connected component with a new color.
 *
 * @author boaz.ben-moshe
 * Do NOT change this interface!!
 */
public interface Map2D {
    /**
     * Construct a 2D w*h matrix of integers.
     * @param w the width of the underlying 2D array.
     * @param h the height of the underlying 2D array.
     * @param v the init value of all the entries in the 2D array.
     */
    public void init(int w, int h, int v);
    /**
     * Constructs a new 2D raster map from a given 2D int array (deep copy).
     * @throws RuntimeException if arr == null or if the array is empty or a ragged 2D array.
     * @param arr a 2D int array.
     */
    public void init(int[][] arr);

    /**
     * Computes a deep copy of the underline 2D matrix.
     * @return a deep copy of the underline matrix.
     */
    public int[][] getMap();

    /**
     * @return the width of this 2D map (first coordinate).
     */
    public int getWidth();

    /**
     * @return the height of this 2D map (second coordinate).
     */
    public int getHeight();

    /**
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the [x][y] (int) value of the map[x][y].
     */
    public int getPixel(int x, int y);
    /**
     * @param p the x,y coordinate
     * @return the [p.x][p.y] (int) value of the map.
     */
    public int getPixel(Pixel2D p);

    /**
     * Set the [x][y] coordinate of the map to v.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param v the value that the entry at the coordinate [x][y] is set to.
     */
    public void setPixel(int x, int y, int v);
    /**
     * Set the [x][y] coordinate of the map to v.
     * @param p the coordinate in the map.
     * @param v the value that the entry at the coordinate [p.x][p.y] is set to.
     */
    public void setPixel(Pixel2D p, int v);

    /**
     * @param p the 2D coordinate.
     * @return true iff p is with in this map.
     */
    boolean isInside(Pixel2D p);

    /**
     * This method returns true if and only if this Map2D has the same dimensions as p.
     * @param p
     * @return true if and only if this Map2D has the same dimensions as p.
     */
    public boolean sameDimensions(Map2D p);

    /**
     * This method adds the map p to this map - assuming they have the same dimensions (
     * else do nothing).
     * @param p - the map that should be added to this map (just in case they have the same dimensions).
     */
    public void addMap2D(Map2D p);

    /**
     * This method multiplay this map with a scalar (casting to int).
     * @param scalar
     */
    public void mul(double scalar);

    /**
     * This method changes the dimensions of this map, a map of size [100][200]
     * rescaled with (1.2,0.5) will change to [120][100].
     * @param sx
     * @param sy
     */
    public void rescale(double sx, double sy);

    /**
     * This method draws a circle by changing all the pixels in this map
     * which their distance to the center is lower than rad to color.
     * @param center
     * @param rad
     * @param newColor - the (new) color to be used in the drawing.
     */
    public void drawCircle(Pixel2D center, double rad, int newColor);
    /**
     * This method draws a line by changing the pixels between p1 to p2 to the newColor.
     * assuming dx = |p2.x-p1.x|, dy = |p2.y-p1.y|, and both p1 and p2 are within this map.
     * Note:
     * 1. if p1 equals p2 - a single pixel will be drawn.
     * 2. assuming dx>=dy & p1.x<p2.x: dx+1 pixels will be drawn.
     * let f(x) be the linear function going throw p1&p2.
     * let x=p1.x, p1.x+1, p1.x+2...p1.x+dx (=p2.x)
     * all the pixels (x,round(f(x)) will be drawn.
     * 3. assuming dx>=dy & p1.x>p2.x: the line p2,p1 will be drawn.
     * 4. assuming dx<dy & p1.y<p2.y: dy+1 pixels will be drawn.
     * let g(y) be the linear function going throw p1&p2.
     * let y=p1.y, p1.y+1, p1.y+2...p1.y+dy (=p2.y)
     * all the pixels (y,round(g(y)) will be drawn.
     * 5. assuming dy>dx & p1.y>p2.y: the line p2,p1 will be drawn.
     * @param p1
     * @param p2
     * @param newColor - the (new) color to be used in the drawing.
     */
    public void drawLine(Pixel2D p1, Pixel2D p2, int newColor);
    /**
     * This method draws a rectangle by changing all the pixels in this map
     * which are within the [p1,p2] range to color.
     * @param p1
     * @param p2
     * @param newColor - the (new) color to be used in the drawing.
     */
    public void drawRect(Pixel2D p1, Pixel2D p2, int newColor);

    /**
     * @param m the reference object with which to compare.
     * @return true if and only if this Map2D has the same (dimensions) and values.
     */
    @Override
    public boolean equals(Object m);


///////////////// Algorithms //////////////////
/**
 * Fill the connected component of p in the new color (new_v).
 * Note: the connected component of p are all the pixels in the map with the same "color" of map[p] which are connected to p.
 * Note: two pixels (p1,p2) are connected if there is a path between p1 and p2 with the same color (of p1 and p2).
 * @param p the pixel to start from.
 * @param new_v - the new "color" to be filled in p's connected component.
 * @cyclic if true --> the matrix is assumed to be cyclic.
 * @return the number of "filled" pixels.
 */
public int fill(Pixel2D p, int new_v, boolean cyclic);

/**
 * Compute the shortest valid path between p1 and p2.
 * A valid path between p1 and p2 is defined as a path between p1 and p2 does NOT contain the absColor.
 * A path is an ordered set of pixels where each consecutive pixels in the path are neighbors in this map.
 * Two pixels are neighbors in the map, iff they are a single pixel apart (up,down, left, right).
 * In case there is no valid path between p1 and p2 should return null;
 * If this map is cyclic:
 * 1. the pixel to the left of (0,i) is (getWidth()-1,i).
 * 2. the pixel to the right of (getWidth()-1,i) is (0,i).
 * 3. the pixel above (j,getHeight()-1) is (j,0).
 * 4. the pixel below (j,0) is (j,getHeight()-1).
 * Where 0<=i<getWidth(), 0<=j<getWidth().
 *
 * @param p1 first coordinate (start point).
 * @param p2 second coordinate (end point).
 * @param obsColor the color which is addressed as an obstacle.
 * @return the shortest path as an array of consecutive pixels, if none - returns null.
 */
public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic);

/**
 * Compute a new map (with the same dimension as this map) with the
 * shortest path distance (obstacle avoiding) from the start point.
 * None accessible entries should be marked -1.
 * @param start the source (starting) point
 * @param obsColor the color representing obstacles
 * @return a new map with all the shortest path distances from the starting point to each entry in this map.
 */
public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic);
}
