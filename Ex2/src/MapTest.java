import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private int[][] _map_2_5 = {{0,1,0,1,1}, {1,0,1,0,0}};
    private int[][] _map_3_4 = {{0,0,0,1,1}, {0,0,1,1,0},{0,1,1,0,0}};
    private Map2D _m0, _m1, _m4, _m3_3, _m2_5, _m3_4;
    @BeforeEach
    public void setup() {
        _m0 = new Map(3,3,0);
        _m1 = new Map(3,3,0);
        _m4 = new Map(2,5,0);
        _m2_5 = new Map(_map_2_5);
        _m3_3 = new Map(_map_3_3);
        _m3_4 = new Map(_map_3_4);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0,_m1);
    }

    @Test
    void getMap() {
        _m0.init(_map_3_3);
        int[][] m = _m0.getMap();
        assertEquals(1, m[0][1]);
        assertEquals(0, m[1][1]);
        assertNotEquals(2, m[1][1]);
        assertNotNull(_m0.getMap());
    }

    @Test
    void getWidth() {
        int[][] check = new int[4][8];
        int[][] ch2 = new int[2][5];
        _m0.init(check);
        _m1.init(ch2);
        assertNotNull(_m0.getMap());
        assertNotNull(_m1.getMap());
        assertEquals(4, _m0.getWidth());
        assertEquals(2, _m1.getWidth());
        assertNotEquals(7, _m0.getWidth());
        assertNotEquals(54, _m1.getWidth());
    }

    @Test
    void getHeight() {
        int[][] check = new int[4][8];
        int[][] ch2 = new int[2][5];
        _m0.init(check);
        _m1.init(ch2);
        assertNotNull(_m0.getMap());
        assertNotNull(_m1.getMap());
        assertEquals(8, _m0.getHeight());
        assertEquals(5, _m1.getHeight());
        assertNotEquals(7, _m0.getHeight());
        assertNotEquals(54, _m1.getHeight());
    }

    @Test
    void getPixel() {
        _m0.init(_map_3_3);
        int[][] m = _m0.getMap();
        assertEquals(-1, _m0.getPixel(-4,0));
        assertEquals(0, _m0.getPixel(0,0));
        assertEquals(1, _m0.getPixel(0,1));
        assertEquals(0, _m0.getPixel(2,2));
        assertEquals(-1, _m0.getPixel(4,3));
    }


    @Test
    void testGetPixel() {
        _m0.init(_map_3_3);
        Pixel2D p1 = new Index2D(-1,2);
        Pixel2D p2 = new Index2D(4,2);
        Pixel2D p3 = new Index2D(3,4);
        Pixel2D p4 = new Index2D(2,2);
        Pixel2D p5 = new Index2D(3,3);
        Pixel2D emp = null;
        assertEquals(-1, _m0.getPixel(p1));
        assertEquals(-1, _m0.getPixel(p2));
        assertEquals(-1, _m0.getPixel(p3));
        assertEquals(0, _m0.getPixel(p4));
        assertEquals(-1, _m0.getPixel(emp));
        assertEquals(-1, _m0.getPixel(p5));
    }

    @Test
    void setPixel() {
        _m0.init(_map_3_3);
        _m0.setPixel(1,1,5);
        assertEquals(5, _m0.getPixel(1,1));
        assertEquals(0, _m0.getPixel(2,2));
        assertNotEquals(5, _m0.getPixel(2,2));
        assertNotEquals(0, _m0.getPixel(1,1));



    }
//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void testSetPixel() {
        _m0.init(_map_3_3);
        Pixel2D p1 = new Index2D(0,2);
        Pixel2D p2 = new Index2D(2,2);

        _m0.setPixel(p1,4);
        _m0.setPixel(p2,8);
        assertEquals(8, _m0.getPixel(p2));
        assertEquals(4, _m0.getPixel(p1));
        assertEquals(1, _m0.getPixel(0,1));
        assertNotEquals(4, _m0.getPixel(2,0));
    }
//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void isInside() {
        _m0.init(_map_3_3);
        Pixel2D p1 = new Index2D(0,2);
        Pixel2D p2 = new Index2D(2,2);
        Pixel2D p3 = new Index2D(3,4);
        Pixel2D p4 = new Index2D(-2,1);
        assertTrue(_m0.isInside(p1));
        assertTrue(_m0.isInside(p2));
        assertFalse(_m0.isInside(p3));
        assertFalse(_m0.isInside(p4));
    }
//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void sameDimensions() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        _m4.init(_map_2_5);
        assertTrue(_m1.sameDimensions(_m0));
        assertFalse(_m1.sameDimensions(_m4));
        assertTrue(_m1.sameDimensions(_m1));
        assertFalse(_m0.sameDimensions(_m4));
    }

//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void addMap2D() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        int[][] first = _m0.getMap();
        _m0.addMap2D(_m1);
        for(int i=0; i<_m0.getWidth(); i++) {
            for(int j=0; j<_m0.getHeight(); j++) {
                int sum = first[i][j] + _m1.getPixel(i, j);
                assertEquals(sum, _m0.getPixel(i, j));
            }
        }
    }
//{{0,1,0}, {1,0,1}, {0,1,0}}
    // the same logic as before
    @Test
    void mul() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        int[][] first = _m0.getMap();
        _m1.mul(7.4);
        for(int i=0; i<_m0.getWidth(); i++) {
            for(int j=0; j<_m0.getHeight(); j++) {
                int mult = (int) (first[i][j] * 7.4);
                assertEquals(mult, _m1.getPixel(i, j));
            }
        }

    }

//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void rescale() {
        _m0.init(_map_3_3);
        _m0.rescale(3.0,3.0);
        assertEquals(9.0,_m0.getWidth());
        assertEquals(9.0,_m0.getHeight());
        assertEquals(_map_3_3[0][0], _m0.getPixel(0,0));
        int oldX = _map_3_3.length-1;
        int oldY = _map_3_3[0].length-1;
        int newX = (int) (oldX * 3.0);
        int newY = (int) (oldY * 3.0);
        assertEquals(_map_3_3[oldY][oldX], _m0.getPixel(newX, newY));
    }

//{{0,1,0},
// {1,0,1},
// {0,1,0}}
    @Test
    void drawCircle() {
        _m0.init(_map_3_3);
        Pixel2D center = new Index2D(1,1);
        double radius = 1.0;
        _m0.drawCircle(center, radius, 5);
        assertEquals(5, _m0.getPixel(0,1));
        assertEquals(5, _m0.getPixel(1,0));
        assertEquals(5, _m0.getPixel(1,2));
        assertEquals(5, _m0.getPixel(2,1));
        assertNotEquals(5, _m0.getPixel(2,2));
        assertNotEquals(5, _m0.getPixel(-2,2));
    }
//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void drawLine() {
        _m0.init(_map_3_3);
        int color = 9;
        // for the same pixel
        Pixel2D oneP = new Index2D(0,1);
        _m0.drawLine(oneP, oneP, color);
        assertEquals(color, _m0.getPixel(0,1));
        // X
        Pixel2D p1 = new Index2D(0,1);
        Pixel2D p2 = new Index2D(2,1);
        _m0.drawLine(p1, p2, color);
        for(int x=0; x<= 2; x++ ){
            assertEquals(color, _m0.getPixel(x,1));
        }
        // Y
        Pixel2D p3 = new Index2D(1,0);
        Pixel2D p4 = new Index2D(1,2);
        _m0.drawLine(p3, p4, color);
        for(int y=0; y<=2; y++ ){
            assertEquals(color, _m0.getPixel(1,y));}
        // from 0,0 to 2,2 (don't straight)
        Pixel2D p5 = new Index2D(0,0);
        Pixel2D p6 = new Index2D(2,2);
        _m0.drawLine(p5, p6, color);
        for(int i=0; i<= 2; i++ ){
            assertEquals(color, _m0.getPixel(i,i));
        }
        //exception
        Pixel2D out = new Index2D(3, 0);
        assertThrows(RuntimeException.class, () -> _m0.drawLine(oneP,out,color));
    }

//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void drawRect() {
        _m0.init(_map_3_3);
        int color = 9;
        Pixel2D p1 = new Index2D(0,2);
        Pixel2D p2 = new Index2D(1,0);
        _m0.drawRect(p1, p2, color);
        for(int x=0; x<= 1; x++ ){
            for(int y=2; y>=0; y-- ){assertEquals(color, _m0.getPixel(x,y));
            }
        }
        assertNotEquals(color, _m0.getPixel(2,2));
        assertNotEquals(color, _m0.getPixel(2,1));
        assertNotEquals(color, _m0.getPixel(-2,2));
    }
//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void fill() {
    _m0.init(_map_3_3);
    Pixel2D p1 = new Index2D(1,1);
    int count = _m0.fill(p1,5, false);
    assertEquals(5, _m0.getPixel(1,1));
    assertNotEquals(5, _m0.getPixel(2,1));
    assertTrue(count>0);
    }

//{{0,1,0}, {1,0,1}, {0,1,0}}
    @Test
    void shortestPath() {
        _m0.init(_map_3_3);
        Pixel2D p1 = new Index2D(0,0);
        Pixel2D p2 = new Index2D(2,2);
        Pixel2D[] path = _m0.shortestPath(p1, p2, 5, false);
        assertNotNull(path);

        assertTrue(path.length>0);
        assertEquals(p1, path[0]);
        assertEquals(p2, path[path.length-1]);
        //if it shorted (+1 cuz it include points start/end)
        int expL = Math.abs(p2.getX() - p1.getX()) + Math.abs(p2.getY() - p1.getY()) + 1;
        assertEquals(expL, path.length);
        //obsColor
        _m0.setPixel(p2,3);
        Pixel2D[] da = _m0.shortestPath(p1, p2, 3, false);
        assertNull(da);


    }
    //{{0,0,0,1,1}
    // {0,0,1,1,0},
    // {0,1,1,0,0}};
    @Test
    void allDistance() {
        _m0.init(_map_3_4);
        Pixel2D p1 = new Index2D(0,0);
        int obs = 1;
        Map2D dis = _m0.allDistance(p1, obs, false);
        assertNotNull(dis);
        assertEquals(0, dis.getPixel(p1));
        assertTrue(dis.getPixel(1,0) >= 0);
        assertEquals(1, dis.getPixel(0,1));
        assertEquals(-1, dis.getPixel(3,0));
    }
}