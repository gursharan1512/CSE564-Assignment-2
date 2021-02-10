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

    boolean isString = false;
    boolean addPrev = false;
    ArrayList<String> test = new ArrayList<>();

    public ArrayList<TokenDetails> getToken(String inputText) {
        String[] lines = inputText.split("\\n");
        //ArrayList<String> tokenList = setToken(inputText);
        Lexer lexer = new Lexer();
        int lineNumber = 1;
        ArrayList<TokenDetails> tokenDetailsList = new ArrayList<>();
        //for (String line : lines) {
        for (int i = 0; i < lines.length; i++) {
            if (!isString && lineNumber != 1) {
                test.removeAll(test);
            }
            setToken(lines[i]);

            if (!isString || (lines.length == (i+1))) {
                tokenDetailsList.addAll(lexer.identifyToken(test, lineNumber));
                lineNumber++;
            }
            else {
                addPrev = true;
            }
        }
        return tokenDetailsList;
    }

    public ArrayList<String> setToken(String inputText) {
        ArrayList<String> tokenList = new ArrayList<>();
        int charPosition = 0;
//        boolean append = false;
//        if (isString) {
//            append = true;
//        }
        char[] inputChar = inputText.toCharArray();
        for (int i = 0; i < inputChar.length; i++) {
            if (inputChar[i] == '"') {
                charPosition = stringParser(inputText, inputChar,charPosition,i);
            }
            if (!isString) {
                if (inputChar[i] == '=' || inputChar[i] == '(' || inputChar[i] == ')' || inputChar[i] == '{' || inputChar[i] == '}' || inputChar[i] == ';' ||
                        inputChar[i] == '+' || inputChar[i] == '-' || inputChar[i] == '/' || inputChar[i] == '*' || inputChar[i] == '%') {
                    //System.out.println("sh-"+inputChar[i]+"-sh");
                    System.out.println("charPosition - "+charPosition+" i - "+i+" inputText length - "+inputText.length());
                    System.out.println("sh-"+inputText.substring(charPosition,i)+"-sh");
                    //System.out.println("sh-"+inputText.substring(i,i+1)+"-sh");
                    if (!inputText.substring(charPosition,i).equals("") && !inputText.substring(charPosition,i).equals(" ")) {
                        test.add(inputText.substring(charPosition,i));
                    }
                    System.out.println("charPosition - "+charPosition+" i - "+i+" inputText length - "+inputText.length());
                    if (!inputText.substring(i,i+1).equals("\n") && !inputText.substring(i,i+1).equals(" ")) {
                        test.add(inputText.substring(i,i+1));
                        //System.out.println("shartan");
                        //System.out.println("sh-"+inputText.substring(i,i+1)+"-sh");
                    }
                    //tokenList.add(inputText.substring(i,i+1));
                    charPosition = i+1;
                }
                else if (inputChar[i] == ' ') {
                    //System.out.println("sh-"+inputText.substring(charPosition,i)+"-sh");
                    if (!inputText.substring(charPosition,i).equals("") && !inputText.substring(charPosition,i).equals("\t")) {
                        //System.out.println("sh-"+inputChar[i]+"-sh");
                        test.add(inputText.substring(charPosition,i));
                    }
                    charPosition = i+1;
                }
            }
        }
        if (!inputText.substring(charPosition).equals("") && !inputText.substring(charPosition).equals(" ")) {
            test.add(inputText.substring(charPosition));
        }
        return test;
    }

    private int stringParser(String inputText, char[] inputChar, int charPosition, int i) {
        if (!isString) {
            if (!inputText.substring(charPosition,i).equals("")) {
                test.add(inputText.substring(charPosition, i));
            }
            charPosition = i;
        }
        isString = !isString;
        if (inputChar.length == (i+1) || addPrev) {
            test.set(test.size() - 1, test.get(test.size() - 1)+""+inputText.substring(charPosition,i+1));
            addPrev = false;
            charPosition = i+1;
        }
        return charPosition;
    }
}