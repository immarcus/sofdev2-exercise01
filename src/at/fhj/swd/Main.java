package at.fhj.swd;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String inputPath = args[0];
        String outputPath = args[1];

        if (args.length != 2) {
            throw new IllegalArgumentException("the program expects two arguments");
        }

        QuestionReader q = new QuestionReader();

        ArrayList<Question> questionsFromFile = q.readQuestions(inputPath);

        HtmlQuestionWriter w = new HtmlQuestionWriter();
        w.writeQuestions(outputPath, questionsFromFile);
    }
}
