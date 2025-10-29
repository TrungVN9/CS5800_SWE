package Flyweight;
public class Character {
    private char character;
    private CharacterProperties properties;

    public Character(char c, CharacterProperties properties) {
        this.character = c;
        this.properties = properties;
    }
    
    public char getChar() {
        return character;
    }

    public void setChar(char newChar) {
        this.character = newChar;
    }
    
    public CharacterProperties getProperties() {
        return properties;
    }

    public void setProperties(CharacterProperties newProperties) {
        this.properties = newProperties;
    }
    
    public String display() {
        return String.format("%c %s %s %s", character, properties.getFont(), properties.getColor(), properties.getSize());
    }

}
