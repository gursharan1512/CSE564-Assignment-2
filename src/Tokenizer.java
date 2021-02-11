import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class implements methods to create tokens from the given block of code.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */

public class Tokenizer {

    private ArrayList<String> lineTokens = new ArrayList<>();
    private boolean isString = false;
    private boolean addString = false;
    static final List<Character> OPERATOR_DELIMITER_LIST = Arrays.asList('=','(',')','{','}',';','+','-','*','/','%', '>', '<');
    boolean isDoubleOperator = false;

    /**
     * Takes given block of code to convert them into tokens.
     * @param inputText - Given block of code.
     * @return List of String tokens.
     */
    public ArrayList<TokenDetails> getToken(String inputText) {
        String[] lines = inputText.split("\\n");
        Lexer lexer = new Lexer();
        int lineNumber = 1;
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            if (!isString && lineNumber != 1) {
                lineTokens.clear();
            }
            createTokensFromSingleString(lines[i]);
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

    /**
     *Takes input single string and separates tokens from it.
     * @param inputText - Line of code.
     */
    private void createTokensFromSingleString(String inputText) {
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
                charPosition = identifyToken(inputText, charPosition, i);
                if (isDoubleOperator) {
                    isDoubleOperator =false;
                    i++;
                    charPosition++;
                }
            }
        }
        if (!inputText.substring(charPosition).equals("") && !inputText.substring(charPosition).equals(" ")) {
            lineTokens.add(inputText.substring(charPosition));
        }
    }

    /**
     * Methods handles the strings which are spread into multiple lines.
     * @param inputText Input code String
     * @param charPosition - position from which string token started.
     * @param stringEndPosition - Position at String token ended.
     * @return - new position of counter for rest of the tokens.
     */
    private int stringParser(String inputText, int charPosition, int stringEndPosition) {
        if (!isString && !inputText.substring(charPosition,stringEndPosition).equals("")) {
            lineTokens.add(inputText.substring(charPosition, stringEndPosition));
            charPosition = stringEndPosition;
        }
        isString = !isString;
        return charPosition;
    }

    /**
     * Separates the given code into tokens which dio not have spaces using operators and handles multiple characters operators such as '>='
     * @param inputText - Input code block.
     * @param charPosition - pointer position where given token started.
     * @param i - counter for the input string.
     * @return - new position of the counter where tokens needs to be identified further.
     */
    private int identifyToken(String inputText, int charPosition, int i) {
        char[] inputChar = inputText.toCharArray();
        if (OPERATOR_DELIMITER_LIST.contains(inputChar[i]) && !inputText.substring(charPosition,i).equals(" ")) {
            if (!inputText.substring(charPosition,i).equals("")) {
                lineTokens.add(inputText.substring(charPosition,i));
            }
            if ((inputChar[i] == '>' || inputChar[i] == '<' || inputChar[i] == '=') && (i+1 < inputChar.length)) {
                if (inputChar[i+1] == '=') {
                    isDoubleOperator = true;
                    lineTokens.add(inputText.substring(i,i+2));
                }
            }
            if (inputText.charAt(i) != '\n' && !isDoubleOperator) {
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