import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;

public class DocumentTest {
    @Test
    public void testMemoryOptimization() {
        CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
        Document doc = new Document(factory);

        // Provide 10 chars with same style
        for (int i = 0; i < 10; i++) {
            doc.addCharacter('A', "Arial", "Red", 12);
        }
        assertEquals(1, factory.getPropertiesSize(), "Flyweight factory should reuse same CharacterProperties");
    }

    @Test
    public void testAddCharacterAndSaveLoad() throws IOException {
        CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
        Document doc = new Document(factory);
        doc.addCharacter('A', "Arial", "Red", 12);
        doc.addCharacter('B', "Calibri", "Blue", 14);

        File tempFile = File.createTempFile("testDoc", ".txt");
        doc.saveToFile(tempFile.getAbsolutePath());
        assertTrue(tempFile.exists(), "File should be created after saving");

        System.out.println("Saved content:");
        doc.loadFromFile(tempFile.getAbsolutePath());

        assertEquals(2, factory.getPropertiesSize(), "Factory should have two unique properties");
        tempFile.deleteOnExit();
    }

    @Test
    public void testEditCharacterOutOfBoundsThrowsError() {
        CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
        Document doc = new Document(factory);
        doc.addCharacter('A', "Arial", "Red", 12);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            doc.editCharacter(5, 'X', "Verdana", "Blue", 14);
        });

        assertTrue(exception.getMessage().contains("Invalid character index"));
    }

    @Test
    public void testEditCharacterSuccessfully() {
        CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
        Document doc = new Document(factory);

        doc.addCharacter('A', "Arial", "Red", 12);
        doc.addCharacter('B', "Calibri", "Blue", 14);

        // Edit 2nd character
        doc.editCharacter(1, 'Z', "Verdana", "Black", 16);

        assertEquals(2, doc.getCharacterCount());
        assertEquals(3, factory.getPropertiesSize(), "Factory should contain three unique property sets after edit");
    }

}