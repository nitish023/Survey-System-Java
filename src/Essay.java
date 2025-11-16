// Nitish Chawla, nkc47
import java.util.ArrayList;

public class Essay extends Question {
    private static final long SerialVersionUID = 1L;


    public Essay(String prompt) {
        super(prompt, 1);
    }

    public Essay(String prompt, int numResponses) {
        super(prompt, numResponses);
    }

    public void display(Output output, int questionNumber) {
        output.println(questionNumber + ") " + prompt);
    }

    @Override
    public ArrayList<String> takeAns(Input input, Output output) {
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < numResponses; i++) {
            if (numResponses > 1) {
                output.println((char) ('A' + i) + ")");
            }
            String essay;
            while (true) {
                output.println("Enter your essay:");
                essay = "";
                while (true) {
                    String line = input.readLine();
                    if (line.isEmpty()) break;
                    if (!essay.isEmpty()) essay += "\n";
                    essay += line;
                }
                if (!essay.trim().isEmpty()) {
                    break;
                }

            }
            answers.add(essay);
        }
        return answers;
    }

    @Override
    public void edit(Input input, Output output) {
        super.edit(input, output);
    }
}
