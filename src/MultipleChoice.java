import java.util.ArrayList;
import java.util.List;

public class MultipleChoice extends Question {
    private static final long serialVersionUID = 1L;
    protected ArrayList<String> options;

    public MultipleChoice(String prompt) {
        super(prompt);
        this.options = new ArrayList<>();
    }

    public MultipleChoice(String prompt, int numResponses) {
        super(prompt, numResponses);
        this.options = new ArrayList<>();
    }

    public MultipleChoice(String prompt, ArrayList<String> options) {
        super(prompt);
        this.options = new ArrayList<>(options);
    }

    public MultipleChoice(String prompt, ArrayList<String> options, int numResponses) {
        super(prompt, numResponses);
        this.options = new ArrayList<>(options);
    }

    public ArrayList<String> getOptions() {
        return options;
    }

   public void addOption(String options) {
        this.options.add(options);
   }

   public void setOptions(ArrayList<String> options) {
        this.options = new ArrayList<>(options);
   }

   @Override
   public void display(Output output, int questionNumber) {
       output.print(questionNumber + ") " + prompt);
       if (numResponses > 1) {
           output.println(" Please give " + numResponses + " choices:");
       }else {
           output.println();
       }
       for (int i = 0; i < options.size(); i++) {
           output.print((char)('A' + i) + ") " + options.get(i));
           if (i < options.size() - 1) {
               output.print(" ");
           }
       }
       output.println();
   }

   @Override
   public ArrayList<String> takeAns(Input input, Output output) {
       ArrayList<String> answers = new ArrayList<>();
       for (int i = 0; i < numResponses; i++) {
           String choice;
           while (true) {
               if (numResponses > 1) {
                   choice = input.readLine("Choice "+(i + 1)+": ").toUpperCase();
               } else {
                   choice = input.readLine("Your choice: ").toUpperCase();
               }
               if (choice.length() == 1) {
                   int index = choice.charAt(0) - 'A';
                   if (index >= 0 && index < options.size()) {
                       answers.add(options.get(index));
                       break;
                   }
               }
               output.println("Invalid choice");
           }
       }
       return answers;
   }

   @Override
   public void edit(Input input, Output output) {
        super.edit(input, output);
        String editChoices = input.readLine("Do you wish to modify choices? ");
        if (editChoices.toLowerCase().startsWith("y")) {
            output.println("Current choices:");
            for (int i = 0; i < numResponses; i++) {
                output.println((char) ('A' + i) + ") " + options.get(i));
                if (i < options.size() - 1) {
                    output.print(" ");
                }
            }
        }
        output.println();
        String editLetter = input.readLine("").toUpperCase();
        if (editLetter.length() == 1) {
            int index = editLetter.charAt(0) - 'A';
            if (index >= 0 && index < options.size()) {
                String newVal = input.readLine("New value:\n");
                if (!newVal.isEmpty()) {
                    options.set(index, newVal);
                }
            }
        }
   }
}
