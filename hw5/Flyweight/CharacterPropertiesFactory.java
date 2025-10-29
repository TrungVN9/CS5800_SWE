package Flyweight;
import java.util.HashMap;
import java.util.Map;

public class CharacterPropertiesFactory {
    private final Map<String, CharacterProperties> properties = new HashMap<>();

    public CharacterProperties getCharacterProperties(String font, String color, int size) {
        String key = font + '-' + color + '-' + size;
        if (!properties.containsKey(key)) {
            properties.put(key, new CharacterProperties(font, color, size));
        }
        return properties.get(key);
    }

    public int getPropertiesSize() {
        return properties.size();
    }
}