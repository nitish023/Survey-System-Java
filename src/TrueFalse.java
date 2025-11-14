import java.util.ArrayList;

public class TrueFalse extends MultipleChoice {
    private static final long SerialVersionUID = 1L;

    public TrueFalse(String prompt) {
        super(prompt, 1);
        this.options.add("True");
        this.options.add("False");
    }

    public TrueFalse(String prompt, int numResponses) {
        super(prompt, numResponses);
        this.options.add("True");
        this.options.add("False");
    }

    @Override
    public void display(Output output, int questionNum) {
        output.println(questionNum + ") " + prompt);
        output.println("T/F");
    }

    @Override
    public ArrayList<String> takeAns(Input input, Output output) {
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < numResponses; i++) {
            int choice;
            while (true) {
                if (numResponses > 1) {
                    choice = input.readInt("Response " + (i + 1) + " (1=True, 2=False");
                } else {
                    choice = input.readInt("");
                }
                if (choice == 1) {
                    answers.add("True");
                    break;
                } else if (choice == 2) {
                    answers.add("False");
                    break;
                } else {
                    output.println("Invalid input. Please enter 1 for True or 2 for False");
                }
            }
        }
        return answers;
    }

    @Override
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
