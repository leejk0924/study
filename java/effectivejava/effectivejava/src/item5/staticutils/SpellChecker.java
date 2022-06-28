package item5.staticutils;


import item5.DefaultDictionary;
import item5.Dictionary;

import java.util.List;

public class SpellChecker {
    // 자원을 직접 명시
    private static final Dictionary dictionary = new DefaultDictionary();

    private SpellChecker() {

    }

    public static boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public static List<String> suggestions(String typo) {
        return dictionary.closeWordsTo(typo);
    }
}
