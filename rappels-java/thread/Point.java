package thread;

public class Point {
    private int x;
    private int y;

    // public synchronized void translate(int x, int y) {
    //     this.x += x;
    //     this.y += y;
    // }
    public void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return "[x = " + this.x + ", y = " + this.y + "]";
    }
}
