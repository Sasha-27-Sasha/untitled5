public class Cannon {

    private double y = 0.5;
    private double v = 0.01;

    public Cannon() {

    }

    public void move(int direction, int jump) {
        if (jump != 0) {
            return;
        }
        y += v * direction;
    }

    public double getPos() {
        return y;
    }
}
