public class Bullet {
    double x;
    double y;
    int type;

    private static final double v = 0.01;

    public Bullet(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean move() {
        x += v; //TODO: remove fps correlation
        //TODO: disapear
        return true;
    }
}
