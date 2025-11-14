import java.util.ArrayList;

public class ShortAnswer extends Essay{
    private static final long serialVersionUID = 1L;
    private int charLimit;

    public ShortAnswer(String prompt) {
        super(prompt);
        this.charLimit = 500;
    }

    public ShortAnswer(String prompt, int charLimit) {
        super(prompt);
        this.charLimit = charLimit;
    }

    public ShortAnswer(String prompt, int charLimit, int numResponses) {
        super(prompt, numResponses);
        this.charLimit = charLimit;
    }

    public int getCharLimit() {
        return charLimit;
    }

    public void setCharLimit(int charLimit) {
        this.charLimit = charLimit;
    }

    @Override
    public void display(Output output, int questionNumber) {
        output.println(questionNumber + ") " + prompt);
    }

    @Override
    public ArrayList<String> takeAns(Input input, Output output) {
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < numResponses; i++) {
            if (numResponses > 1) {
                output.println((char)('A' + i) + ")");
            }

            String answer;
            while (true){
                answer = input.readLine("");
                if (answer.length() <= charLimit) {
                    answers.add(answer);
                    break;
                } else {
                    output.println("Max character limit reached:(");
                }
            }
        }
        return answers;
    }

    @Override
    public void edit(Input input, Output output) {
        super.edit(input, output);
    }

}
