import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> answers;
    private Date timestamp;

    public Response(ArrayList<String> answers) {
        this.answers = new ArrayList<>(answers);
        this.timestamp = new Date();
    }

    public Response(String answer) {
        this.answers = new ArrayList<>();
        this.answers.add(answer);
        this.timestamp = new Date();
    }

    public Response() {
        this.answers = new ArrayList<>();
        this.timestamp = new Date();
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
