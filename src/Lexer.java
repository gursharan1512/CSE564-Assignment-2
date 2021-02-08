import java.util.ArrayList;
import java.util.Arrays;

public class Lexer {

    static final String DELIMITER = "DELIMITER";
    static final String KEYWORD = "KEYWORD";
    static final String[] KEYWORD_LIST = {"float", "int"};

    public ArrayList<TokenDetails> identifyToken(String[] tokens, int lineNumber) {
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<TokenDetails>();

        for (String token : tokens) {
            TokenDetails tokenDetails = new TokenDetails();
            tokenDetails.setWord(token);
            tokenDetails.setLineNumber(lineNumber);
            if (isDelimiter(token)) {
                tokenDetails.setTokenType(DELIMITER);
            } else if (isKeyword(token)) {
                tokenDetails.setTokenType(KEYWORD);
            } else {
                tokenDetails.setTokenType("NAME/ID");
            }
            //System.out.println(tokenDetails);
            tokenDetailsList.add(tokenDetails);
        }
        return tokenDetailsList;
    }

    private boolean isKeyword(String token) {
        return Arrays.stream(KEYWORD_LIST).anyMatch(token::equals);
    }

    private Boolean isDelimiter(String token) {
        if (token.equals("(") || token.equals(")") || token.equals("{") || token.equals("}"))
            return true;
        return false;
    }
}