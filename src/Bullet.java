public class Bullet {
    double x;
    double y;//TODO: getters
    int type;

    private static final double speed = 0.025;

    public Bullet(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean move() {
        x += speed; //TODO: remove fps correlation
        return x < 1.1;
    }
}
