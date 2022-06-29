package item5.factorymethod;

import item5.DefaultDictionary;
import item5.Dictionary;


public class DefaultDictionaryFactory implements DictionaryFactory {

    @Override
    public Dictionary getDictionary() {
        return new DefaultDictionary();
    }
}
