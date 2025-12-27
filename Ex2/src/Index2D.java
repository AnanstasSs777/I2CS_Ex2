public class Index2D implements Pixel2D {
    private int _w;
    private int _h;
    public Index2D(int w, int h) {
         _w = w;
         _h = h;
    }
    public Index2D(Pixel2D other) {
        _w = other.getX();
        _h = other.getY();
    }
    @Override
    public int getX() {

        return _w;
    }

    @Override
    public int getY() {

        return _h;
    }

    @Override
    public double distance2D(Pixel2D p2) {
    int dx = this.getX() - p2.getX();
    int dy = this.getY() - p2.getY();
    return (Math.sqrt(dx*dx+dy*dy));

    }

    @Override
    public String toString() {
        String ans = null;
        ans = "(" +this.getX()+ "," +this.getY()+ ")";
        return ans;
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;
        if(this == p) {return true;}
        if (!(p instanceof Pixel2D)) { return false;}
        Pixel2D other = (Pixel2D)p;
        if(this.getX() == other.getX() && this.getY() == other.getY()) {return ans;}
        else {return false;}

    }
}
