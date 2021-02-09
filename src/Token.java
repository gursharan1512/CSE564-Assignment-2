//import java.util.ArrayList;
//
//public class Token {
//    public ArrayList<TokenDetails> getToken(String inputText) {
//        String[] lines = inputText.split("\\n");
//        Lexer lexer = new Lexer();
//        int lineNumber = 1;
//        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();
//        //tokenDetailsList.add(new TokenDetails(12, "cerc", "cwfce"));
//        for (String line : lines) {
////            System.out.println("line - "+(i+1)+",   word - "+word);
//
////            lexer.identifyToken(line.split(" "), lineNumber).stream()
////                    .forEachOrdered(tokenDetailsList::add);
//            tokenDetailsList.addAll(lexer.identifyToken(line.split(" "), lineNumber));
//            lineNumber++;
//        }
//
//        return tokenDetailsList;
//
////        for ( TokenDetails abc : tokenDetailsList) {
////            System.out.println(abc);
////        }
//
//
//        /*lexer.identifyToken(lines);
//        for ( int i = 0; i < lines.length; i++ ) {
//            for (String word : lines[i].split(" ")) {
//                System.out.println("line - "+(i+1)+",   word - "+word);
//                Lexer lexer = new Lexer();
//                lexer.identifyToken(word);
//            }
//        }*/
//    }
//}

import java.util.ArrayList;

public class Token {
    public ArrayList<TokenDetails> getToken(String inputText) {
        String[] lines = inputText.split("\\n");
        //ArrayList<String> tokenList = setToken(inputText);
        Lexer lexer = new Lexer();
        int lineNumber = 1;
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();
        for (String line : lines) {
            tokenDetailsList.addAll(lexer.identifyToken(setToken(line), lineNumber));
            lineNumber++;
        }
        return tokenDetailsList;
    }

    public ArrayList<String> setToken(String inputText) {
        ArrayList<String> tokenList = new ArrayList<>();
        int charPosition = 0;
        boolean isString = false;
        char[] inputChar = inputText.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[i] == '"') {
                isString = !isString;
            }
            if (!isString) {
                if (inputChar[i] == '=') {
                    //System.out.println("sh-"+inputChar[i]+"-sh");
                    System.out.println("shartan");
                    System.out.println("sh-"+inputText.substring(charPosition,i)+"-sh");
                    System.out.println("sh-"+inputText.substring(i,i+1)+"-sh");
                    //tokenList.add(inputText.substring(charPosition,i));
                    tokenList.add(inputText.substring(i,i+1));
                    charPosition = i+1;
                }
                else if (inputChar[i] == ' ') {
                    //System.out.println("sh-"+inputText.substring(charPosition,i)+"-sh");
                    if (!inputText.substring(charPosition,i).equals("") && !inputText.substring(charPosition,i).equals("\t")) {
                        //System.out.println("sh-"+inputChar[i]+"-sh");
                        tokenList.add(inputText.substring(charPosition,i));
                    }
                    charPosition = i+1;
                }
            }
        }
        tokenList.add(inputText.substring(charPosition));
        return tokenList;
    }
}