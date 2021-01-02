import static java.lang.Math.*;

public class Cannon {

    private double y = 0.5;
    private double v = 0.01;

    public void move(int direction, int jump) {
        if (jump != 0) {
            return;
        }

        y += v * direction;
        y = max(0.0, min(y, 1.0));
    }

    public double getPos() {
        return y;
    }
}
