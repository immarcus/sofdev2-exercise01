package at.fhj.swd;

import java.io.*;
import java.util.ArrayList;


public class QuestionReader {

    public ArrayList<Question> readQuestions(String inputPath) {
        BufferedReader reader = null;
        String line = null;
        ArrayList<Question> readQuestions = new ArrayList<>();
        int lineCounter = 0;

        try {
            reader = new BufferedReader(new FileReader(inputPath));

            while ((line = reader.readLine()) != null) {    // reads a line in one loop

                lineCounter++;
                if (lineCounter == 1)                       // makes sure the first line is ignored
                    continue;

                if (isComment(line))                        // checks if the line is a comment
                    continue;
                String[] parts = line.split(";");     // array contains three parts (question, answers, correct answer index
                if (parts.length != 3)                      // skips the line if the array does not have three parts
                    continue;
                if (hasEmptyParts(parts))                   // skips the line if the array has empty parts
                    continue;

                String question = parts[0];
                String allAnswersTogether = parts[1];
                int correctAnswerIndex = Integer.parseInt(parts[2]);

                String[] answers = allAnswersTogether.split("/");                 // the String with all the answers gets split and is put into an array
                if (answers.length < 2)                                                 // skips the line if there are less than two answers
                    continue;
                if (correctAnswerIndex > answers.length - 1)                            // skips the line if the correct answer index is bigger than the size of the array
                    continue;
                if (hasEmptyParts(answers))                                             // skips the line if the array has empty parts
                    continue;

                Question q = new Question(question, answers, correctAnswerIndex);
                readQuestions.add(q);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
            return null;
        } catch (IOException ex) {
            System.out.println("error while reading the file");
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("error while reading the file");
                    return null;
                }
            }
        }
        return readQuestions;
    }

    private static boolean isComment(String input) {
        char[] array = input.toCharArray();
        if (array[0] == '#') {
            return true;
        }
        return false;
    }

    private static boolean hasEmptyParts(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() == 0) {
                return true;
            }
        }
        return false;
    }
}
