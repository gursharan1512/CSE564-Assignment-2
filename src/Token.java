import java.util.ArrayList;

public class Token {
    public ArrayList<TokenDetails> getToken(String inputText) {
        String[] lines = inputText.split("\\n");
        Lexer lexer = new Lexer();
        int lineNumber = 1;
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();
        //tokenDetailsList.add(new TokenDetails(12, "cerc", "cwfce"));
        for (String line : lines) {
//            System.out.println("line - "+(i+1)+",   word - "+word);

//            lexer.identifyToken(line.split(" "), lineNumber).stream()
//                    .forEachOrdered(tokenDetailsList::add);
            tokenDetailsList.addAll(lexer.identifyToken(line.split(" "), lineNumber));
            lineNumber++;
        }

        return tokenDetailsList;

//        for ( TokenDetails abc : tokenDetailsList) {
//            System.out.println(abc);
//        }


        /*lexer.identifyToken(lines);
        for ( int i = 0; i < lines.length; i++ ) {
            for (String word : lines[i].split(" ")) {
                System.out.println("line - "+(i+1)+",   word - "+word);
                Lexer lexer = new Lexer();
                lexer.identifyToken(word);
            }
        }*/
    }
}