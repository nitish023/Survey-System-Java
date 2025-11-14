import java.io.PrintStream;

public class Output {
    private PrintStream out;
    public Output() {
        this.out = System.out;
    }

    public void print(String text) {
        out.print(text);
    }

    public void print(int val) {
        out.print(val);
    }

    public void print(char c) {
        out.print(c);
    }

    public void print(Object obj) {
        out.print(obj);
    }

    public void println(String text) {
        out.println(text);
    }

    public void println(int val) {
        out.println(val);
    }

    public void println(char c) {
        out.println(c);
    }

    public void println(Object obj) {
        out.println(obj);
    }

    public void println() {
        out.println();
    }

    public void flush() {
        out.flush();
    }
    public void close() {
        out.close();
    }
}
