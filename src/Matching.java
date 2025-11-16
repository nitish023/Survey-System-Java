// Nitish Chawla, nkc47
import java.util.ArrayList;

public class Matching extends Question {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> leftCol;
    private ArrayList<String> rightCol;

    public Matching(String prompt) {
        super(prompt);
        this.leftCol = new ArrayList<>();
        this.rightCol = new ArrayList<>();
    }

    public Matching(String prompt, ArrayList<String> leftCol, ArrayList<String> rightCol) {
        super(prompt);
        this.leftCol = new ArrayList<>(leftCol);
        this.rightCol = new ArrayList<>(rightCol);
    }

    public Matching(String prompt, ArrayList<String> leftCol, ArrayList<String> rightCol, int numResponses) {
        super(prompt, numResponses);
        this.leftCol = new ArrayList<>(leftCol);
        this.rightCol = new ArrayList<>(rightCol);
    }

    public ArrayList<String> getLeftCol() {
        return leftCol;
    }

    public ArrayList<String> getRightCol() {
        return rightCol;
    }

    public void setLeftCol(ArrayList<String> leftCol) {
        this.leftCol = new ArrayList<>(leftCol);
    }

    public void setRightCol(ArrayList<String> rightCol) {
        this.rightCol = new ArrayList<>(rightCol);
    }

    public void addLeftCol(String leftCol) {
        this.leftCol.add(leftCol);
    }

    public void addRightCol(String rightCol) {
        this.rightCol.add(rightCol);
    }

    @Override
    public void display(Output output, int questionNumber) {
        output.println(questionNumber + ") " + prompt);
        int maxLeft = 0;
        for (int i = 0; i < leftCol.size(); i++) {
            int width = leftCol.get(i).length() + 3;
            if (width > maxLeft) {
                maxLeft = width;
            }
        }

        int maxSize = Math.max(leftCol.size(), rightCol.size());
        for (int i = 0; i < maxSize; i++) {
            if (i < leftCol.size()) {
                String leftVal = (char)('A' + i) + ") " + leftCol.get(i);
                output.print(leftVal);
                int padding = maxLeft - leftVal.length() + 6;
                for (int j = 0; j < padding; j++) {
                    output.print(" ");
                }
            } else {
                for (int j = 0; j < maxLeft + 5; j++) {
                    output.print(" ");
                }
            }
            if (i < rightCol.size()) {
                output.print((i+1) + ") " + rightCol.get(i));
            }
            output.println();
        }
    }

    @Override
    public ArrayList<String> takeAns(Input input, Output output) {
        ArrayList<String> answers = new ArrayList<>();
        output.println("Enter your matches as a letter and number: A2");
        for (int i = 0; i < this.leftCol.size(); i++) {
            while (true) {
                String match = input.readLine("");
                if (match.length() >= 2 && Character.isLetter(match.charAt(0)) && Character.isDigit(match.charAt(1))) {
                    answers.add(match.toUpperCase());
                    break;
                } else {
                    output.println("Invalid input.");
                }
            }
        }
        return answers;
    }

    @Override
    public void edit(Input input, Output output) {
        super.edit(input, output);
        String editLeftCol = input.readLine("Do you wish to modify the left column items? ");
        if (editLeftCol.toLowerCase().startsWith("y")) {
            output.println("Current left column items:");
            for (int i = 0; i < this.leftCol.size(); i++) {
                output.println((char)('A' + i) + ") " + leftCol.get(i));
            }
            String letter = input.readLine("Enter letter to modify: ").toUpperCase();
            if (letter.length() == 1) {
                int index = letter.charAt(0) - 'A';
                if (index>= 0 && index < this.leftCol.size()) {
                    String newVal = input.readLine("New value:\n");
                    if (!newVal.isEmpty()) this.leftCol.set(index, newVal);
                }
            }
        }

        String editRightCol = input.readLine("Do you wish to modify the right column items? ");
        if (editRightCol.toLowerCase().startsWith("y")) {
            output.println("Current right column items:");
            for (int i = 0; i < this.rightCol.size(); i++) {
                output.println((i+1) + ") " + rightCol.get(i));
            }
            String number = input.readLine("Enter number to modify: ");
            if (!number.isEmpty()) {
                try {
                    int index = Integer.parseInt(number) - 1;
                    if (index >= 0 && index < this.rightCol.size()) {
                        String newVal = input.readLine("New value:\n");
                        if (!newVal.isEmpty()) this.rightCol.set(index, newVal);
                    }
                } catch (NumberFormatException e) {
                    output.println("Invalid number");
                }
            }
        }
    }
}
