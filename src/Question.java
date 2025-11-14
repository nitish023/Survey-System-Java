import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String prompt;
    protected ArrayList<Response> responses;
    protected int numResponses;

    public Question(String prompt) {
        this.prompt = prompt;
        this.responses = new ArrayList<>();
        this.numResponses = 1;
    }

    public Question(String prompt, int numResponses) {
        this.prompt = prompt;
        this.responses = new ArrayList<>();
        this.numResponses = numResponses;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getNumResponses() {
        return numResponses;
    }

    public void setNumResponses(int numResponses) {
        this.numResponses = numResponses;
    }

    public ArrayList<Response> getResponses() {
        return responses;
    }

    public void addResponse(Response response) {
        this.responses.add(response);
    }

    public void clearResponses() {
        this.responses.clear();
    }
    //below are methods that should be implemented by the subclasses
    public abstract void display(Output output, int questionNum);

    public abstract ArrayList<String> takeAns(Input input, Output output);

    public void edit(Input input, Output output) {
        output.println(prompt);
        String modifyPrompt = input.readLine("Do you wish to modify the prompt? ");
        if (modifyPrompt.toLowerCase().startsWith("y")) {
            output.println("Current prompt: " + prompt);
            String newPrompt = input.readLine("Enter a new prompt:\n");
            if (!newPrompt.isEmpty()) {
                prompt = newPrompt;
            }
        }
}
}
