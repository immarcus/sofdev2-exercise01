package at.fhj.swd;

public class Question {
    private String question;
    private String[] answers;
    /**
     * contains the index of the array element which contains the correct answer
     */
    private int solutionIndex;

    public Question(String question, String[] answers, int solutionIndex){

        if(question == null || question.isEmpty()){
            throw new IllegalArgumentException("Question must not be null or empty");
        }
        this.question = question;

        if(answers == null ||  answers.length < 2){
            throw new IllegalArgumentException("There must be at least 2 answers");
        }
        this.answers = answers;

        if(solutionIndex < 0 || solutionIndex >= answers.length){
            throw new IllegalArgumentException("Answer index must match the array size");
        }

        this.solutionIndex = solutionIndex;
    }

    public boolean isCorrect(int input){
        return (input-1) == solutionIndex;
        /*
        int modifiedInput = input -1;
        if(modifiedInput == solutionIndex){
            return true;
        }
        else
        {
            return false;
        }
        */
    }

    public String generateQuestionText(){
        String text = question;
        //text = text + "\n";
        text += "\n";

        for(int i = 0; i < answers.length; i++){
            String curAnswer = answers[i];
            int questionNr = i + 1;
            text += questionNr + ". " + curAnswer + " ";
        }

        return text;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
