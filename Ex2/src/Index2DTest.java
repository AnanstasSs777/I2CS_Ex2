import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Index2DTest {

    @Test
    void getX() {
        Index2D p = new Index2D(5, 6);
        Index2D q = new Index2D(2, 6);
        Index2D r = new Index2D(7, 6);
        assert p.getX() == 5;
        assert q.getX() == 2;
        assert r.getX() == 7;
        assertNotEquals(67, p.getX());
        assertNotEquals("lol", q.getX());
        assertNotEquals(45, r.getX());
    }

    @Test
    void getY() {
    Index2D p = new Index2D(5, 0);
    Index2D q = new Index2D(2, 9);
    Index2D r = new Index2D(7, 6);
    assert p.getY() == 0;
    assert q.getY() == 9;
    assert r.getY() == 6;
    assertNotEquals(67, p.getY());
    assertNotEquals("lol", q.getY());
    assertNotEquals(45, r.getY());
    }

    @Test
    void distance2D() {
        Index2D p = new Index2D(5, 6);
        Index2D q = new Index2D(2, 6);
        Index2D r = new Index2D(5, 6);
        Index2D d = new Index2D(7, 6);
        assertEquals( p.distance2D(q),q.distance2D(r));
        assertNotEquals(p.distance2D(r),q.distance2D(r));
        assertNotEquals(p.distance2D(r),d.distance2D(r));
        assertNotEquals("net",d.distance2D(p));

    }

    @Test
    void testToString() {
        Index2D p = new Index2D(5, 6);
        Index2D q = new Index2D(2, 9);
        assert p.toString().equals( "(5,6)");
        System.out.println("Test passed");
        assert q.toString().equals("(2,9)");
        System.out.println("Test passed");

    }

    @Test
    void testEquals() {
        Pixel2D a = new Index2D(1, 2);
        Pixel2D b = new Index2D(1, 2);
        Pixel2D c = new Index2D(3, 5);
        Pixel2D d = new Index2D(3, 5);
        Assertions.assertEquals(a, b);
        Assertions.assertNotEquals(a, c);
        Assertions.assertNotEquals(a, d);
        Assertions.assertEquals(c, d);
        Assertions.assertNotEquals("da", a);
    }
}