// Nitish Chawla, nkc47
import java.util.ArrayList;

public class DateQuestion extends Question {
    private static final long serialVersionUID = 1L;
    public DateQuestion(String prompt) {
        super(prompt);
    }

    public DateQuestion(String prompt, int numResponses) {
        super(prompt, numResponses);
    }

    @Override
    public void display(Output output, int questionNumber) {
        output.println(questionNumber + ") " + prompt);
        output.println("Enter date in format MM/DD/YYYY:");
    }

    @Override
    public ArrayList<String> takeAns(Input input, Output output) {
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < numResponses; i++) {
            while(true) {
                String dateInput;
                if (numResponses > 1) {
                    dateInput = input.readLine("Date " + (i+1) + ": ");
                } else {
                    dateInput = input.readLine("");
                }

                if (isValidDate(dateInput)) {
                    answers.add(dateInput);
                    break;
                } else {
                    output.println("Invalid date. Please follow format.");
                }
            }
        }
        return answers;
    }

    private boolean isValidDate(String dateInput) {
        if (dateInput == null || dateInput.isEmpty()) {
            return false;
        }

        return dateInput.matches("\\d{1,2}/\\d{1,2}/\\d{4}");
    }

    public void edit(Input input, Output output) {
        super.edit(input, output);
    }

}
