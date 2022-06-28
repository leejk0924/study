package item5.singleton;

import item5.DefaultDictionary;
import item5.Dictionary;

import java.util.List;

public class SpellChecker {
    private static final Dictionary dictionary = new DefaultDictionary();

    private SpellChecker() {

    }

    public static final SpellChecker INSTANCE = new SpellChecker();

    public static boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public static List<String> suggestions(String typo) {
        return dictionary.closeWordsTo(typo);
    }
}
