// Nitish Chawla, nkc47

import java.io.*;
import java.util.ArrayList;

// Main driver class for the console-based Survey System.
public class Main {
    private static Input input = new Input();
    private static Output output = new Output();
    // Currently loaded survey
    private static Survey currentSurvey = null;
    // Relative directories for serialized surveys and responses
    private static final String SURVEY_DIR = "surveys";
    private static final String RESPONSES_DIR = "responses";

    public static void main(String[] args) {
        createDirectories();
        output.println("Survey System");
        output.println("-----------------------------\n");
        boolean working = true;
        while (working) {
            displayMainMenu();
            int choice = input.readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    createNewSurvey();
                    break;
                case 2:
                    displaySurvey();
                    break;
                case 3:
                    loadSurvey();
                    break;
                case 4:
                    saveSurvey();
                    break;
                case 5:
                    takeSurvey();
                    break;
                case 6:
                    modifySurvey();
                    break;
                case 7:
                    output.println("Exiting Survey System...");
                    working = false;
                    break;
            }
        }
        input.close();
    }

    // Displays main menu options.
    private static void displayMainMenu() {
        output.println("\nMain Menu");
        output.println("1) Create a new Survey");
        output.println("2) Display an existing Survey");
        output.println("3) Load an existing Survey");
        output.println("4) Save the current Survey");
        output.println("5) Take the current Survey");
        output.println("6) Modify the current Survey");
        output.println("7) Quit");
        output.println("---------------------------\n");
    }

    // Creates a new survey and allows user to add questions to it.
    private static void createNewSurvey() {
        output.println("Create a new Survey");
        String surveyName = input.readLine("Enter survey name: ");
        currentSurvey = new Survey(surveyName);
        output.println("Survey Created");

        boolean adding = true;
        while (adding) {
            displayCreateMenu();
            int choice = input.readInt("Enter choice: ");
            if (choice < 1 || choice > 7) {
                output.println("Invalid choice. Please try again.");
                continue;
            }
            switch (choice) {
                case 1:
                    addTrueFalseQuestion();
                    break;
                case 2:
                    addMultipleChoiceQuestion();
                    break;
                case 3:
                    addShortAnswerQuestion();
                    break;
                case 4:
                    addEssayQuestion();
                    break;
                case 5:
                    addDateQuestion();
                    break;
                case 6:
                    addMatchingQuestion();
                    break;
                case 7:
                    adding = false;
                    output.println("Returning to main menu..\n");
                    break;
            }
        }
    }

    // Displays the submenu for adding different types of questions.
    private static void displayCreateMenu() {
        output.println("\n Add Question Menu");
        output.println("1) Add a new T/F question");
        output.println("2) Add a new multiple-choice question");
        output.println("3) Add a new short answer question");
        output.println("4) Add a new essay question");
        output.println("5) Add a new date question");
        output.println("6) Add a new matching question");
        output.println("7) Return to previous menu");
        output.println("---------------------------\n");
    }

    // Adds a True/False question to the current survey.
    private static void addTrueFalseQuestion() {
        output.println("Add true or false");
        String prompt = input.readLine("Enter prompt:\n");
        TrueFalse question = new TrueFalse(prompt);
        currentSurvey.addQuestion(question);
        output.println("Question added.");

    }

    // Adds a Multiple Choice question to the current survey.
    private static void addMultipleChoiceQuestion() {
        output.println("Add multiple choice question");
        String prompt = input.readLine("Enter prompt:\n");
        int numChoices = input.readInt("Number of choices: ");

        ArrayList<String> choices = new ArrayList<String>();
        for (int i = 0; i < numChoices; i++) {
            String choice = input.readLine("Choice " + (i + 1) + ": ");
            choices.add(choice);
        }
        String multipleStr = input.readLine("Multiple responses? (yes/no): ");
        MultipleChoice question;
        if (multipleStr.toLowerCase().startsWith("y")) {
            int numResponses = input.readInt("Number of responses: ");
            question = new MultipleChoice(prompt, choices, numResponses);
        } else {
            question = new MultipleChoice(prompt, choices);
        }
        currentSurvey.addQuestion(question);
        output.println("Question added.");
    }

    // Adds a Short Answer question to the current survey.
    private static void addShortAnswerQuestion() {
        output.println("Add short answer question");
        String prompt = input.readLine("Enter prompt:\n");
        int charLimit = input.readInt("Enter char limit: ");
        String multipleStr = input.readLine("Multiple responses? (yes/no): ");
        ShortAnswer question;
        if (multipleStr.toLowerCase().startsWith("y")) {
            int numResponses = input.readInt("Number of responses: ");
            question = new ShortAnswer(prompt, charLimit, numResponses);
        } else {
            question = new ShortAnswer(prompt, charLimit);
        }
        currentSurvey.addQuestion(question);
        output.println("Question added.");
    }

    // Adds an Essay question to the current survey.
    private static void addEssayQuestion() {
        output.println("Add essay question");
        String prompt = input.readLine("Enter prompt:\n");
        String multipleStr = input.readLine("Multiple responses? (yes/no): ");
        Essay question;
        if (multipleStr.toLowerCase().startsWith("y")) {
            int numResponses = input.readInt("Number of responses: ");
            question = new Essay(prompt, numResponses);
        } else {
            question = new Essay(prompt);
        }

        currentSurvey.addQuestion(question);
        output.println("Question added.");
    }

    // Adds a Date question to the current survey.
    private static void addDateQuestion() {
        output.println("Add date question");
        String prompt = input.readLine("Enter prompt:\n");
        String multipleStr = input.readLine("Multiple responses? (yes/no): ");
        DateQuestion question;
        if (multipleStr.toLowerCase().startsWith("y")) {
            int numResponses = input.readInt("Number of responses: ");
            question = new DateQuestion(prompt, numResponses);
        } else {
            question = new DateQuestion(prompt);
        }
        currentSurvey.addQuestion(question);
        output.println("Question added.");
    }

    // Adds a Matching question to the current survey.
    private static void addMatchingQuestion() {
        output.println("Add matching question");
        String prompt = input.readLine("Enter prompt:\n");
        int numMatches = input.readInt("How many matches? ");
        ArrayList<String> leftCol = new ArrayList<>();
        ArrayList<String> rightCol = new ArrayList<>();
        output.println("\nEnter items for the left column: ");
        for (int i = 0; i < numMatches; i++) {
            String item = input.readLine((char)('A' + i) + ") ");
            leftCol.add(item);
        }
        output.println("Enter items for the right column: ");
        for (int i = 0; i < numMatches; i++) {
            String item = input.readLine((i+1) + ") ");
            rightCol.add(item);
        }

        Matching question = new Matching(prompt, leftCol, rightCol);
        currentSurvey.addQuestion(question);
        output.println("Question added.");

    }

    // Displays the currently loaded survey.
    private static void displaySurvey() {
        if (currentSurvey == null) {
            output.println("You must have a survey loaded in order to display it.");
            return;
        }
        currentSurvey.display(output);
    }

    // Loads a survey from the surveys directory using serialization.
    private static void loadSurvey() {
        output.println("Loading survey..");
        File surveyFolder = new File(SURVEY_DIR);
        File[] files = surveyFolder.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files == null || files.length == 0) {
            output.println("No survey found.");
            return;
        }
        output.println("Select a survey to load:");
        for (int i = 0; i < files.length; i++) {
            output.println((i+1) + ") " + files[i].getName().replace(".ser", ""));
        }
        int choice = input.readInt("Choice: ");
        if (choice < 1 || choice > files.length) {
            output.println("Invalid choice.");
            return;
        }
        File selected = files[choice-1];

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(selected))) {
            currentSurvey = (Survey) in.readObject();
            output.println("Survey loaded.");
        } catch (IOException | ClassNotFoundException e) {
            output.println("Survey could not be loaded.");
        }

    }

    // Saves the loaded survey to a file using serialization.
    private static void saveSurvey() {
        if (currentSurvey == null) {
            output.println("You must have a survey loaded in order to save it.");
            return;
        }
        output.println("Saving survey..");
        String filename = input.readLine("Enter file name: ");
        File file = new File(SURVEY_DIR, filename + ".ser");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(currentSurvey);
            output.println("Survey saved.");
        } catch (IOException e) {
            output.println("Survey could not be saved.");
        }
    }

    // Takes the loaded survey and saves the user's responses.
    private static void takeSurvey() {
        if (currentSurvey == null) {
            output.println("You must have a survey loaded in order to take it.");
            return;
        }

        ArrayList<Response> responses = currentSurvey.take(input, output);
        saveResponses(responses);
    }

    // Serializes and savesresponses to the responses directory.
    private static void saveResponses(ArrayList<Response> responses) {
        String filename = currentSurvey.getName().replaceAll("\\s", "_") + "_response_" + System.currentTimeMillis() + ".ser";
        File file = new File(RESPONSES_DIR, filename);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(responses);
            output.println("Responses saved.");
        } catch (IOException e) {
            output.println("Responses could not be saved.");
        }
    }

    // Allows user to select and modify a question in the current survey.
    private static void modifySurvey() {
        if (currentSurvey == null) {
            output.println("You must have a survey loaded in order to modify it.");
            return;
        }
        if (currentSurvey.isEmpty()) {
            output.println("\nSurvey is empty.");
            return;
        }
        currentSurvey.display(output);

        output.println("Modifying survey..");
        int questionNum = input.readInt("What question do you wish to modify (1-" + currentSurvey.getSize() + "): ");

        if (questionNum < 1 || questionNum > currentSurvey.getSize()) {
            output.println("Invalid question number.");
            return;
        }
        currentSurvey.modifyQuestion(questionNum - 1, input, output);
    }

    // Creates the directories if they do not already exist.
    private static void createDirectories() {
        File surveyDir = new File(SURVEY_DIR);
        File responseDir = new File(RESPONSES_DIR);

        if (!surveyDir.exists()) {
            surveyDir.mkdir();
        }

        if (!responseDir.exists()) {
            responseDir.mkdir();
        }
    }



}
