package at.fhj.swd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class HtmlQuestionWriter {
    public void writeQuestions(String destination, ArrayList<Question> questions) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream(destination));
            printHeading(writer);

            for (int i = 0; i < questions.size(); i++) {
                printQuestion(writer, questions.get(i));
                printAnswers(writer, questions.get(i));
            }

            printFooter(writer);
        } catch (IOException e) {
            System.out.println("error while writing the file");
        }
        finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {}
        }
    }
    private static void printHeading(PrintWriter writer) {
        writer.write("<html>\n" +
                    "<head>\n" +
                    "<meta charset='utf-8'>\n" +
                    "<title>SWD Quiz - Questions</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>SWD Quiz</h1>\n");
    }
    private static void printFooter(PrintWriter writer) {
        writer.write("</body>\n" +
                        "</html>");
    }
    private static void printQuestion(PrintWriter writer, Question q) {
        writer.write("<h3>" + q.getQuestion() + "</h3>\n");
    }
    private static void printAnswers(PrintWriter writer, Question q) {
        String[] array = q.getAnswers();
        writer.write("<ul>\n");
        for (int i = 0; i < array.length; i++) {
            if (q.isCorrect(i + 1)) {
                writer.write("<li><b>" + array[i] + "</b></li>\n");
            } else {
                writer.write("<li>" + array[i] + "</li>\n");
            }
        }
        writer.write("</ul>\n");
    }
}
