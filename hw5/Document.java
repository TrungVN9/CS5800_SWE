import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Document {
    private final List<Character> characters = new ArrayList<>();
    private final CharacterPropertiesFactory factory;

    public Document(CharacterPropertiesFactory factory) {
        this.factory = factory;
    }

    public void addCharacter(char c, String font, String color, int size) {
        CharacterProperties properties = factory.getCharacterProperties(font, color, size);
        characters.add(new Character(c, properties));
    }
    
    public void editCharacter(int index, char newChar, String newFont, String newColor, int newSize) {
        if (index < 0 || index >= characters.size()) {
            throw new IndexOutOfBoundsException("Invalid character index!");
        }

        CharacterProperties newProperties = factory.getCharacterProperties(newFont, newColor, newSize);
        Character character = characters.get(index);
        character.setChar(newChar);
        character.setProperties(newProperties);
    }

    public void displayDocument() {
        for (Character ch : characters) {
            System.out.println(ch.display());
        }
    }

    public int getCharacterCount() {
        return characters.size();
    }

    public void saveToFile(String fileName) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (Character ch: characters){
                writer.write(ch.display() + '\n');
            }
        }
    }

    public void loadFromFile(String fileName) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }
}
