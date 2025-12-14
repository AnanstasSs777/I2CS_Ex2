/**
 * This interface represents an integer based coordinate of a 2D raster (aka a 2D matrix).
 * Ex2: Intro2CS - do NOT change this interface
 */
public interface Pixel2D {
    /**
     * @return the X coordinate (integer) of the pixel.
     */
    public int getX();
    /**
     * @return the Y coordinate (integer) of the pixel.
     */
    public int getY();

    /**
     * This method computes the 2D (Euclidean) distance between this pixel and p2 pixel, i.e., (Math.sqrt(dx*dx+dy*dy))
     * @throws RuntimeException if p2==null.
     * @return the 2D Euclidean distance between the pixels.
     */
    public double distance2D(Pixel2D p2);

    /**
     * @return a String representation of this coordinate.
     */
    @Override
    public String toString();

    /**
     * @param p the reference object with which to compare.
     * @return true if and only if this Index is the same index as p/
     */
    @Override
    public boolean equals(Object p);
}
