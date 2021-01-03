public class Bullet {
    double x;
    double y;
    int type;

    private static final double v = 0.05;

    public Bullet(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void move() {
        y += v; //TODO: remove fps correlation
        //TODO:disapear
    }
}
