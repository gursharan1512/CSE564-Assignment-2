import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Token {

    private ArrayList<String> lineTokens = new ArrayList<>();
    private boolean isString = false;
    private boolean addString = false;
    static final List<Character> OPERATOR_DELIMITER_LIST = Arrays.asList('=','(',')','{','}',';','+','-','*','/','%');

    public ArrayList<TokenDetails> getToken(String inputText) {
        String[] lines = inputText.split("\\n");
        Lexer lexer = new Lexer();
        int lineNumber = 1;
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!isString && lineNumber != 1) {
                lineTokens.removeAll(lineTokens);
            }
            setToken(lines[i]);
            if (!isString || (lines.length == (i+1))) {
                tokenDetailsList.addAll(lexer.identifyToken(lineTokens, lineNumber));
                lineNumber++;
            }
            else {
                addString = true;
            }
        }
        return tokenDetailsList;
    }

    private void setToken(String inputText) {
        int charPosition = 0;
        char[] inputChar = inputText.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[i] == '"') {
                charPosition = stringParser(inputText, charPosition, i);
            }
            if ((inputChar[i] == '"' || inputChar.length == (i+1)) && addString) {
                lineTokens.set(lineTokens.size() - 1, lineTokens.get(lineTokens.size() - 1)+" "+inputText.substring(charPosition,i+1));
                addString = false;
                charPosition = i+1;
            }
            if (!isString) {
                charPosition = identifyToken(inputText, inputChar, charPosition, i);
            }
        }
        if (!inputText.substring(charPosition).equals("") && !inputText.substring(charPosition).equals(" ")) {
            lineTokens.add(inputText.substring(charPosition));
        }
    }

    private int stringParser(String inputText, int charPosition, int i) {
        if (!isString && !inputText.substring(charPosition,i).equals("")) {
            lineTokens.add(inputText.substring(charPosition, i));
            charPosition = i;
        }
        isString = !isString;
        return charPosition;
    }

    private int identifyToken(String inputText, char[] inputChar, int charPosition, int i) {
        if (OPERATOR_DELIMITER_LIST.contains(inputChar[i]) && !inputText.substring(charPosition,i).equals(" ")) {
            if (!inputText.substring(charPosition,i).equals("")) {
                lineTokens.add(inputText.substring(charPosition,i));
            }
            if (inputText.charAt(i) != '\n') {
                lineTokens.add(inputText.substring(i,i+1));
            }
            charPosition = i+1;
        }
        else if (inputChar[i] == ' ') {
            if (!inputText.substring(charPosition,i).equals("") && !inputText.substring(charPosition,i).equals("\t")) {
                lineTokens.add(inputText.substring(charPosition,i));
            }
            charPosition = i+1;
        }
        return charPosition;
    }
}