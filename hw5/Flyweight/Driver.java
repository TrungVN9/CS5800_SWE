package Flyweight;
public class Driver{
    public static void main(String[] args){
        try {
            CharacterPropertiesFactory factory = new CharacterPropertiesFactory();
            Document document = new Document(factory);

            document.addCharacter('H', "Arial", "Red", 12);
            document.addCharacter('e', "Calibri", "Blue", 14);
            document.addCharacter('l', "Verdana", "Black", 16);
            document.addCharacter('l', "Arial", "Red", 12);
            document.addCharacter('o', "Calibri", "Blue", 14);
            document.addCharacter('C', "Calibri", "Blue", 14);
            document.addCharacter('S', "Calibri", "Blue", 14);
            document.addCharacter('5', "Arial", "Red", 12);
            document.addCharacter('8', "Arial", "Red", 12);
            document.addCharacter('0', "Arial", "Red", 12);
            document.addCharacter('0', "Verdana", "Black", 16);

            System.out.println("Original Document:");
            document.displayDocument();

            System.out.println("\nEditing 2nd character (index 1)...");
            document.editCharacter(1, 'E', "Verdana", "Black", 16);

            System.out.println("\nDocument After Edit:");
            document.displayDocument();

            document.saveToFile("edited_output.txt");
            System.out.println("\nDocument saved to edited_output.txt");

            document.loadFromFile("edited_output.txt");
            System.out.println("\nDocument loaded from edited_output.txt:");
            document.displayDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}