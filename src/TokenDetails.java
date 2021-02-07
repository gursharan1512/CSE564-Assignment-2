public class TokenDetails {

    private  int lineNumber;
    private  String tokenType;
    private  String word;

    public TokenDetails(int lineNumber, String tokenType, String word) {
        this.lineNumber = lineNumber;
        this.tokenType = tokenType;
        this.word = word;
    }

    public int getLineNumber() {
        return lineNumber;
    }


    @Override
    public String toString() {
        return "TokenDetails{" +
                "lineNumber=" + lineNumber +
                ", tokenType='" + tokenType + '\'' +
                ", word='" + word + '\'' +
                '}';
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    //default constructor
    public TokenDetails() {
    }
}
