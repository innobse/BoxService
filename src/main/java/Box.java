import java.io.*;

/**
 * Description
 *
 * @author bse71
 * Created on 01.12.2021
 */
public class Box implements Externalizable {

    private static final long serialVersionUID = 1541651;

    private String title;
    private int priority;
    private double content;
    private transient String unimportantString;

    public Box(String title, int priority, double content) {
        this.title = title;
        this.priority = priority;
        this.content = content;
    }

    public Box(String title, int priority, double content, String unimportantString) {
        this.title = title;
        this.priority = priority;
        this.content = content;
        this.unimportantString = unimportantString;
    }

    public Box() {    }

    public void doNothing() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getContent() {
        return content;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public String getUnimportantString() {
        return unimportantString;
    }

    public void setUnimportantString(String unimportantString) {
        this.unimportantString = unimportantString;
    }

    @Override
    public String toString() {
        return "Box{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", content=" + content +
                ", unimportantString=" + unimportantString +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(priority);
        out.writeDouble(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.title = (String) in.readObject();
        this.priority = in.readInt();
        this.content = in.readDouble();
    }
}
