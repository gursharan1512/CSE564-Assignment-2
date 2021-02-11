import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parser class implement methods which takes list of string tokens and parse them according to the java rules.
 * @author Gursharanjit Singh Ghotra
 * @author Manthan Agrawal
 */
public class Lexer {

    public static final String DELIMITER = "DELIMITER";
    public static final String OPERATOR = "OPERATOR";
    public static final String KEYWORD = "KEYWORD";
    public static final String DECIMAL_NUMBER = "DECIMAL_NUMBER";
    public static final String HEX_DECIMAL_NUMBER = "HEX_DECIMAL_NUMBER";
    public static final String BINARY_NUMBER = "BINARY_NUMBER";
    public static final String STRING = "STRING";
    public static final String ERROR = "ERROR";
    public static final String NAME_ID = "NAME/ID";
    public static final List<String> DELIMITER_LIST = Arrays.asList("{", "}", "(", ")", ";");
    public static final List<String> OPERATOR_LIST = Arrays.asList("+", "-", "*", "/", "=", "%", ".", "<", ">", "?", "~", "<=", ">=", "==", "|", "&", "^", ":");
    public static final List<String> KEYWORD_LIST = Arrays.asList("abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double",
            "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum",
            "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final",
            "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
            "native", "super", "while");

    /**
     * Method takes input in the form of sequence of tokens and produces output in the form of segregated tokens according to their type.
     * @param tokens - List of String
     * @param lineNumber - Line number in code block where string is present.
     * @return - {@link TokenDetails} List of TokenDetails with details for each token.
     */
    public ArrayList<TokenDetails> identifyToken(ArrayList<String> tokens, int lineNumber) {
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();

        for (String token : tokens) {
            TokenDetails tokenDetails = new TokenDetails();
            tokenDetails.setWord(token);
            tokenDetails.setLineNumber(lineNumber);
            if (isDelimiter(token)) {
                tokenDetails.setTokenType(DELIMITER);
            } else if (isOperator(token)) {
                tokenDetails.setTokenType(OPERATOR);
            } else if (isKeyword(token)) {
                tokenDetails.setTokenType(KEYWORD);
            } else if (isHexNumber(token)) {
                tokenDetails.setTokenType(HEX_DECIMAL_NUMBER);
            } else if (isBinaryNumber(token)) {
                tokenDetails.setTokenType(BINARY_NUMBER);
            } else if (isDecimalNumber(token)) {
                tokenDetails.setTokenType(DECIMAL_NUMBER);
            } else if (token.startsWith("\"") && token.endsWith("\"")) {
                tokenDetails.setTokenType(STRING);
            } else if (token.startsWith("\"")) {
                tokenDetails.setTokenType(ERROR);
            } else {
                tokenDetails.setTokenType(NAME_ID);
            }
            tokenDetailsList.add(tokenDetails);
        }
        return tokenDetailsList;
    }

    /**
     * Identify whether given string token is an operator.
     * @param token - String token
     * @return - boolean result, True if token is operator.
     */
    private boolean isOperator(String token) {
        return OPERATOR_LIST.contains(token);
    }

    /**
     * Identify whether given string token is decimal number.
     * @param token - String token
     * @return - boolean result, True if token is decimal number.
     */
    private boolean isDecimalNumber(String token) {
        return verifyTokenWithNumberSystem(token, 10);
    }
    /**
     * Identify whether given string token is Hexadecimal number.
     * @param token - String token
     * @return - boolean result, True if token is Hexadecimal number.
     */
    private boolean isHexNumber(String token) {
        if (token.startsWith("0X") || token.startsWith("0x"))
            return verifyTokenWithNumberSystem(token.substring(2), 16);
        return false;
    }
    /**
     * Identify whether given string token is Binary number.
     * @param token - String token
     * @return - boolean result, True if token is Binary number.
     */
    private boolean isBinaryNumber(String token) {
        if (token.startsWith("0B") || token.startsWith("0b") ) return verifyTokenWithNumberSystem(token.substring(2), 2);
        return false;
    }

    /**
     * Verify whether given token is part of desired number system or not.
     * @param token - String token
     * @param numberOfDigitsInNumberSystem - number of digit in the desired number system.
     *                                     Eg - decimal number system - 10 , Binary System - 2.
     * @return boolean result, True if token belongs to given number system.
     */
    private boolean verifyTokenWithNumberSystem(String token, int numberOfDigitsInNumberSystem) {
        int flag = 0;
        char[] charArray = token.toCharArray();
        for (char c : charArray) {
            flag = Character.digit(c, numberOfDigitsInNumberSystem);
            if (flag == -1)
                break;
        }
        return flag != -1;
    }
    /**
     * Identify whether given string token is Keyword in Java.
     * @param token - String token
     * @return - boolean result, True if token is Keyword in Java.
     */
    private boolean isKeyword(String token) {
        return KEYWORD_LIST.contains(token);
    }
    /**
     * Identify whether given string token is Delimiter in Java.
     * @param token - String token
     * @return - boolean result, True if token is Delimiter in Java.
     */
    private Boolean isDelimiter(String token) {
        return DELIMITER_LIST.contains(token);
    }
}