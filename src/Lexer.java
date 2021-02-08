import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {

    static final String DELIMITER = "DELIMITER";
    static final List<String> DELIMITER_LIST = Arrays.asList("{", "}", "(", ")", ";", ".");
    static final String KEYWORD = "KEYWORD";
    static final List<String> KEYWORD_LIST = Arrays.asList("abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double",
            "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum",
            "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final",
            "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
            "native", "super", "while");
    public static final String DECIMAL_NUMBER = "DECIMAL_NUMBER";
    public static final String HEX_DECIMAL_NUMBER = "HEX_DECIMAL_NUMBER";
    public static final String BINARY_NUMBER = "BINARY_NUMBER";

    public ArrayList<TokenDetails> identifyToken(String[] tokens, int lineNumber) {
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();

        for (String token : tokens) {
            TokenDetails tokenDetails = new TokenDetails();
            tokenDetails.setWord(token);
            tokenDetails.setLineNumber(lineNumber);
            if (isDelimiter(token)) {
                tokenDetails.setTokenType(DELIMITER);
            } else if (isKeyword(token)) {
                tokenDetails.setTokenType(KEYWORD);
            } else if (isHexNumber(token)) {
                tokenDetails.setTokenType(HEX_DECIMAL_NUMBER);
            } else if (isBinaryNumber(token)) {
                tokenDetails.setTokenType(BINARY_NUMBER);
            } else if (isDecimalNumber(token)) {
                tokenDetails.setTokenType(DECIMAL_NUMBER);
            }
            else {
                tokenDetails.setTokenType("NAME/ID");
            }
            //System.out.println(tokenDetails);
            tokenDetailsList.add(tokenDetails);
        }
        return tokenDetailsList;
    }

    private boolean isDecimalNumber(String token) {
        return verifyNumberSystem(token, 10);
    }

    private boolean isHexNumber(String token) {
        if (token.startsWith("0x") || token.startsWith("0X")) {
            token = token.substring(2);
            return verifyNumberSystem(token, 16);
        }
        return false;
    }

    private boolean isBinaryNumber(String token) {
        if (token.startsWith("0b") || token.startsWith("0B")) {
            token = token.substring(2);
            return verifyNumberSystem(token, 2);
        }
        return false;
    }

    private boolean verifyNumberSystem(String token, int i) { //suggest some better name.
        int flag = 0;
        for (char c : token.toCharArray()) {
            flag = Character.digit(c, i);
        }
        return flag != -1;
    }

    private boolean isKeyword(String token) {
        return KEYWORD_LIST.contains(token);
    }

    private Boolean isDelimiter(String token) {
        return DELIMITER_LIST.contains(token);
    }
}