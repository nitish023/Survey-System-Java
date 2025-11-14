import java.io.Serializable;
import java.util.ArrayList;

public class Survey implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private ArrayList<Question> questions;

    public Survey() {
        this.name = "Untitled";
        this.questions = new ArrayList<>();
    }

    public Survey(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public boolean removeQuestion(int i) {
        if (i < this.questions.size()) {
            this.questions.remove(i);
            return true;
        }
        return false;
    }

    public Question getQuestion(int i) {
        if (i < this.questions.size()) {
            return this.questions.get(i);
        }
        return null;
    }

    public int getSize() {
        return this.questions.size();
    }

    public boolean isEmpty() {
        return this.questions.isEmpty();
    }

    public void display(Output output) {
        output.println("Survey: " +  this.name);
        output.println("------------------------");
        if (this.questions.isEmpty()) {
            output.println("Survey: No questions found");
        } else {
            for (int i = 0; i < this.questions.size(); i++) {
                questions.get(i).display(output, i+1);
                output.println();
            }
        }
        output.println("-----------------------");
    }

    public ArrayList<Response> take(Input input, Output output) {
        ArrayList<Response> responses = new ArrayList<>();
        output.println("Taking Survey: " + name);
        output.println("-----------------------");

        for (int i = 0; i < this.questions.size(); i++) {
            Question question = this.questions.get(i);
            question.display(output, i+1);
            ArrayList<String> answers = question.takeAns(input, output);
            Response response = new Response(answers);
            responses.add(response);
            question.addResponse(response);
            output.println();
        }
        output.println("Survey completed.");
        output.println("-----------------------");
        return responses;
    }

    public void modifyQuestion(int i, Input input, Output output) {
        if (i < this.questions.size()) {
            output.println("\nModifying Question: " + (i+1) + ":");
            questions.get(i).edit(input, output);
            output.println("Question modified.");
        } else {
            output.println("Question number doesnt exist\n");
        }
    }

    public void clearAllResponses() {
        for (Question question : this.questions) {
            question.clearResponses();
        }
    }



}
