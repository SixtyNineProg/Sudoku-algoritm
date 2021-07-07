package main;

public class DuplicateEncoder {
    static String encode(String word){
        System.out.println(word);
        word = word.toLowerCase();
        StringBuilder newWorld = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char[] masWord = word.toCharArray();
            char ch = masWord[i];
            boolean duplicate = false;
            for (int j = 0; j < word.length(); j++) {
                if (j == i) continue;
                if (ch == masWord[j]) {
                    duplicate = true;
                    break;
                }
            }
            if (duplicate) {
                newWorld.append(')');
            } else newWorld.append('(');
        }
        return newWorld.toString();
    }
}
