import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    private int[][] _ris;



    // ris==risunok==image on matrix
    // edit this class below
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w, h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}

	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}


	@Override
	public void init(int w, int h, int v) {
        this._ris = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this._ris[i][j] = v;
            }
        }
    }

    /**
     * Constructs a new 2D raster map from a given 2D int array (deep copy).
     * @throws RuntimeException if arr == null or if the array is empty or a ragged 2D array.
     * @param arr a 2D int array.
     */
	@Override
	public void init(int[][] arr) {
         //exceptions: (out is trow new - those notes for me only)
        if (arr == null) {
            throw new RuntimeException("Error: The array is null!");
        }
        // if it's empty
        if (arr.length == 0 || arr[0].length == 0) {
            throw new RuntimeException("Error: The array is empty!");
        }

        int w = arr.length;
        int h = arr[0].length;
        // if it ragged ( if w isn't the same in every h) but we check too if i of array is empty
        for (int i = 0; i < w; i++) {
            if (arr[i] == null ||arr[i].length != h) {
                throw new RuntimeException("Error: The array is ragged.");
            }
        }

        this._ris = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this._ris[i][j] = arr[i][j];
            }
        }
	}

    /**
     * Computes a deep copy of the underline 2D matrix.
     * @return a deep copy of the underline matrix.
     */
	@Override
	public int[][] getMap() {
		int[][] ans = null;
        if(_ris == null) {return ans;}
        int w = this._ris.length;
        int h = this._ris[0].length;
        ans = new int[w][h];
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                ans[i][j] = _ris[i][j];
            }
        }
		return ans;
	}

    /**
     * @return the width of this 2D map (first coordinate).
     */
	@Override
	public int getWidth() {
        int ans = -1;
        if(this._ris != null && this._ris.length != 0){
            ans = this._ris.length;
        }
        return ans;
    }
    /**
     * return the height og this 2d map
     **/

	@Override
	public int getHeight() {
        int ans = -1;
        if(this._ris != null  && this._ris[0].length != 0){
            ans = this._ris[0].length;
        }
        return ans;
    }
    /**
     * x – the x coordinate
     * y – the y coordinate
     * Returns: the [x][y] coordinate
     **/
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;
        if(this._ris == null || x<0 || y<0 || x>=this._ris.length || y>=this._ris[0].length){return ans;}
        else{ans = this._ris[x][y];}
        return ans;
    }

    /**
     * @param p the x,y coordinate
     * @return the [p.x][p.y] (int) value of the map.
     */
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;
        if(p != null){
            return getPixel(p.getX(), p.getY());
        }
        return ans;
	}

    /**
     * Set the [x][y] coordinate of the map to v.
     * Specified by:
     * setPixel in interface Map2D
     * Params:
     * x – the x coordinate
     * y – the y coordinate
     * v – the value that the entry at the coordinate [x][y] is set to.
     * **/
	@Override
	public void setPixel(int x, int y, int v) {
        if (_ris == null) {return;}
        if(x<0 || y<0){return;}
        if(x>=this._ris.length || y>=this._ris[0].length){return;}
        _ris[x][y] = v;
    }

    /**
     * Set p the x,y coordinate
     * return the [p.x][p.y] (int) value of the map.
     */
	@Override
	public void setPixel(Pixel2D p, int v) {
        if (p != null) {
            setPixel(p.getX(), p.getY(), v);
        }
    }
    /**
     * check all exceptions if a pixel is inside or not through boolean
     * **/
// p - inside if p.x<ris length & p.y<ris[]length and if they are >0(maybe equals too)
    @Override
    public boolean isInside(Pixel2D p) {
        if (p == null || _ris == null) return false;
        int x = p.getX();
        int y = p.getY();
        return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
    }

    /**
     *  function checks if two matrix have the same size of width and height
     **/
    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;
        if(p == null){return ans;}
        if(this._ris == null){ans = false;}
        int w1 = this.getWidth(); // basic our first matrix
        int h1 = this.getHeight();
        int w2 = p.getWidth(); // matrix for comparing to
        int h2 = p.getHeight();
        if( w1 == w2 && h1 == h2){
            return true;
        }
        return ans;
    }

    /**
     * if matrix have same dimensions so we get a new matrix with the sum of same coordinates of past two matrix
     **/
    //sum of cood of matrix
    @Override
    public void addMap2D(Map2D p) {
        if(p == null){return;}
        if(!this.sameDimensions(p)){return;}
        int w = this.getWidth();
        int h = this.getHeight();
        for( int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                int sum = this.getPixel(i, j)+p.getPixel(i, j);
                this.setPixel(i, j, sum);
            }
        }
    }
/** multiplay matrix on double scalar (all its coordinates) but we have return it in int **/
    @Override
    public void mul(double scalar) {
        //Exception
        if (_ris == null) {return;}

        int w = this.getWidth();
        int h = this.getHeight();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int mult = (int) (this._ris[i][j] * scalar);
                this.setPixel(i, j, mult);
            }
        }
    }
/** we have to change matrix, but stay with the same "picture" just in different zoom (save coordinates in the
 * second matrix just in another proportion
 **/
    @Override
    public void rescale(double sx, double sy) {
        if (_ris == null) {return;}
        if( sx < 0 || sy < 0 ){
            throw new RuntimeException("Error: Enter natural values bigger than 0.");
        }
        int w = this.getWidth();
        int h = this.getHeight();
        int w2 = (int) (w*sx);
        int h2 = (int) (h*sy);

        if(w2 == 0 || h2 == 0){
            w2 =1;
            h2 =1;
        }
        if(w2 < 0 || h2 < 0){
            throw new RuntimeException("Error: wrong matrix ");
        }
        //
        int[][] nm = new int[w2][h2];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int newX =  (int)((i/(double)w)*w2);// for right res instead of 1 and 0 - double
                int newY =  (int)((j/(double)h)*h2);
                nm[newX][newY] = this._ris[i][j];
            }
        }
        _ris = nm; // question is if I have to save old map or not
    }

    /** Pixel2D(center.getX(), center.getY());
     check all pixels in the matrix
     check distance from the center
     if our circle in matrix so to color area
     **/
    //to use index's methods I wrote before like distance
    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        int centerX = center.getX();
        int centerY = center.getY();

        for(int x = 0; x < this.getWidth(); x++){
            for(int y = 0; y < this.getHeight(); y++){
                Pixel2D np = new Index2D(x,y);
                double dist = np.distance2D(center);
                if(dist <= rad){
                    setPixel(x,y,color);
                }

            }
        }
    }
/** This method draws a line by changing the pixels between p1 to p2 to the newColor.
 * assuming dx = |p2.x-p1.x|, dy = |p2.y-p1.y|, and both p1 and p2 are within this map.
 * Note:
 * 1. if p1 equals p2 - a single pixel will be drawn.
 * 2. assuming dx>=dy & p1.x  all (x,round(f(x))
 * 3. assuming dx>=dy & p1.x>p2.x: the line p2,p1 will be drawn.
 * 4. assuming dx   all (y,round(g(y))
 * 5. assuming dy>dx & p1.y>p2.y: the line p2,p1 will be drawn.**/
    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        //exceptions
        if(p1 == null || p2 == null){return;}
        if(!isInside(p1) || !isInside(p2)){
            throw new RuntimeException("Error: one of the points isn't in matrix");}

        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();

        int dx = Math.abs(p2.getX() - p1.getX());
        int dy = Math.abs(p2.getY() - p1.getY());
        // first situation:
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {setPixel(p1.getX(), p1.getY(), color);}
        //second if dx>=dy and check if x1>x2 so swap them and draw by the function f(x) by the X
        // (y=y1+m*(x-x1)
        if(dx>= dy){
            if(p1.getX()>p2.getX()){
                int tX = x1; x1 = x2; x2 = tX;
                dx = x2 - x1;
                int tY = y1; y1 = y2; y2= tY;
                dy = Math.abs(y2 - y1);
            }
            double m = (double) (y2-y1) /dx;
            for(int x = x1; x <= x2; x++){
                double y = y1+m*(x-x1);
                int roundY = (int) (Math.round(y));
                setPixel(x,roundY,color);
            }
        }
        // if dy>dx + the same swap if x1>x2 + g(y) by the Y
        //(x=x1+m(y-y1)
        else if(dy>=dx){
            if(p1.getY()>p2.getY()){
                int tX = x1; x1 = x2; x2 = tX;
                dx = Math.abs(x2 - x1);
                int tY = y1; y1 = y2; y2= tY;
                dy = y2 - y1;
            }
            double m = (double) (x2-x1) /(y2-y1);
            for(int y = y1; y <= y2; y++){
                double x = x1+m*(y-y1);
                int roundX = (int) (Math.round(x));
                setPixel(roundX, y,color);
            }
        }

    }
/**
 * This method draws a rectangle by changing all the pixels in
 * this map which are within the [p1,p2] range to color.
 * color – - the (new) color to be used in the drawing.
 **/
    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        // the logic of the code from this part of class:
        //this._min = new Point2D(Math.min(p1.x(),p2.x()), Math.min(p1.y(),p2.y()));
        //this._max = new Point2D(Math.max(p1.x(),p2.x()), Math.max(p1.y(),p2.y()));
        if(p1 == null || p2 == null){return;}
        if(!isInside(p1) || !isInside(p2)){
            throw new RuntimeException("Error: one of the points isn't in matrix");}
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
        int minX = Math.min(x1, x2); int minY = Math.min(y1,y2);
        int maxX = Math.max(x1, x2); int maxY = Math.max(y1,y2);
        for (int x = minX; x <= maxX; x++){
            for (int y = minY; y <= maxY; y++){
                setPixel(x,y,color);
            }
        }
    }
    /**
     * Fill the connected component of p in the new color (new_v).
     * Note: the connected component of p are all the pixels in the map with the same "color" of map[p] which are connected to p.
     * Note: two pixels (p1,p2) are connected if there is a path between p1 and p2 with the same color (of p1 and p2).
     *  p the pixel to start from.
     *  new_v - the new "color" to be filled in p's connected component.
     *  if true --> the matrix is assumed to be cyclic.
     * return the number of "filled" pixels.
     */
    @Override
    public boolean equals(Object ob) {
        if (ob == null || !(ob instanceof Map)) {return false;}
        // Exceptions: if it doesn't have the same value of the matrix
        Map2D pr = (Map2D) ob;
        if(!this.sameDimensions((pr))){return false;}

        int w = this.getWidth();
        int h = this.getHeight();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (this.getPixel(i, j) != pr.getPixel(i, j)) {return false;}
            }
        }
        return true;
    }

	@Override
	/**
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
     *
     * Fill the connected component of p in the new color (new_v).
     * Note: the connected component of p are all the pixels in the map with the same "color" of map[p]which are connected to p.
     * Note: two pixels (p1,p2) are connected if there is a path between p1 and p2 with the same color (of p1 and p2)
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
        int ans = 0;
        if (xy == null || !isInside(xy)) return ans;
        int w = this.getWidth();
        int h = this.getHeight();
        int old = getPixel(xy);
        if (old == new_v) {return ans;}


        int count = 0;
        boolean[][] visited = new boolean[w][h];
        ArrayList<Pixel2D> q = new ArrayList<Pixel2D>();
        q.add(xy);
        visited[xy.getX()][xy.getY()] = true;
        // for returning ans
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!q.isEmpty()) {
            Pixel2D cur = q.remove(q.size() - 1);
            int x = cur.getX();
            int y = cur.getY();

            if (getPixel(x,y) != old) continue;

            setPixel(x, y, new_v);
            count++;

            for (int[] d : dirs) {
            int tx = x + d[0];
            int ty = y + d[1];

            if (cyclic) {
                tx = (tx + w) % w;
                ty = (ty + h) % h;
            }

            if (tx >= 0 && tx < w && ty >= 0 && ty < h && !visited[tx][ty]) {
                visited[tx][ty] = true;
                q.add(new Index2D(tx, ty));
            }
        }

        }
        ans = count;
        return ans;
    }


	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
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
     * param p1 first coordinate (start point).
     * param p2 second coordinate (end point).
     * param obsColor the color which is addressed as an obstacle.
     * return the shortest path as an array of consecutive pixels, if none - returns null.
     * return a new map with all the shortest path distances from the starting point to each entry in this map.
     **/
    public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
        Pixel2D[] ans = null;
        // the result.
        int w = this.getWidth();
        int h = this.getHeight();
        boolean[][] visited = new boolean[w][h];
        Pixel2D[][] primary = new Pixel2D[w][h];
        //arrays of visited and in future added pixels to primary
        ArrayList<Pixel2D> queue = new ArrayList<Pixel2D>();
        queue.add(p1);
        visited[p1.getX()][p1.getY()] = true;
        primary[p1.getX()][p1.getY()] = null;
        int ax, ay;
        while (!queue.isEmpty()) {
            Pixel2D cur = queue.remove(0);
            int x = cur.getX();
            int y = cur.getY();
            //path
            if (cur.equals(p2)) {
                ArrayList<Pixel2D> path = new ArrayList<Pixel2D>();
                Pixel2D step = cur;
                while (step != null) {
                    path.add(0, step);
                    step = primary[step.getX()][step.getY()];
                }
                ans = path.toArray(new Pixel2D[0]);
                return ans;
            }

            //down
            ax = x;
            ay = y + 1;
            if (cyclic) {
                ax = (ax + w) % w;
                ay = (ay + h) % h;
            }
            if (ax >= 0 && ax < w && ay >= 0 && ay < h && !visited[ax][ay] && getPixel(ax, ay) != obsColor) {
                visited[ax][ay] = true;
                primary[ax][ay] = cur;
                queue.add(new Index2D(ax, ay));
            }
            //up
            ax = x;
            ay = y - 1;
            if (cyclic) {
                ax = (ax + w) % w;
                ay = (ay + h) % h;
            }
            if (ax >= 0 && ax < w && ay >= 0 && ay < h && !visited[ax][ay] && getPixel(ax, ay) != obsColor) {
                visited[ax][ay] = true;
                primary[ax][ay] = cur;
                queue.add(new Index2D(ax, ay));
            }
            //right
            ax = x + 1;
            ay = y;
            if (cyclic) {
                ax = (ax + w) % w;
                ay = (ay + h) % h;
            }
            if (ax >= 0 && ax < w && ay >= 0 && ay < h && !visited[ax][ay] && getPixel(ax, ay) != obsColor) {
                visited[ax][ay] = true;
                primary[ax][ay] = cur;
                queue.add(new Index2D(ax, ay));
            }

            //left
            ax = x - 1;
            ay = y;
            if (cyclic) {
                ax = (ax + w) % w;
                ay = (ay + h) % h;
            }
            if (ax >= 0 && ax < w && ay >= 0 && ay < h && !visited[ax][ay] && getPixel(ax, ay) != obsColor) {
                visited[ax][ay] = true;
                primary[ax][ay] = cur;
                queue.add(new Index2D(ax, ay));
            }
        }
        return ans;
    }
/**
 * Map2D
 * Compute a new map (with the same dimension as this map) with the shortest path
 * distance (obstacle avoiding) from the start point. None accessible entries should be marked -1.
 * Specified by:
 * allDistance in interface Map2D
 * Params:
 * start – the source (starting) point
 * obsColor – the color representing obstacles
 * Returns:
 * a new map with all the shortest path distances from the starting point to each entry in this map
 **/
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map ans = new Map(getWidth(), getHeight(), -1);
        int w = this.getWidth();
        int h = this.getHeight();

        if (start == null || !isInside(start) || getPixel(start) == obsColor) {
            return ans;
        }
        boolean[][] visited = new boolean[w][h];
        ArrayList<Pixel2D> queue = new ArrayList<Pixel2D>();
        ArrayList<Integer> distque = new ArrayList<>(); // of distance
        queue.add(start);
        distque.add(0);
        visited[start.getX()][start.getY()] = true;
        ans.setPixel(start, 0); // we start from this point if it correctly

        while (!queue.isEmpty()) {
            Pixel2D cur = queue.remove(0);
            int curDist = distque.remove(0);
            int x = cur.getX();
            int y = cur.getY();

            int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : dirs) {
                int ax = x + dir[0];
                int ay = y + dir[1];

                if (cyclic) {
                    ax = (ax + 1) % w;
                    ay = (ay + 1) % h;
                }
                if (ax < 0 || ax >= w || ay < 0 || ay >= h ||
                        visited[ax][ay] || getPixel(ax, ay) == obsColor){
                    continue;}
                visited[ax][ay] = true;
                ans.setPixel(ax, ay, curDist + 1);
                queue.add(new Index2D(ax, ay));
                distque.add(curDist + 1);
            }
        }
        return ans;
    }
        ////////////////////// Private Methods ///////////////////////

}

