/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package question5;
/**
 *
 * @author trungvong
 */
public class Homework5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Build folder tree
        Folder php_demo1 = new Folder("php_demo1");
        Folder source_file = php_demo1.createSubFolder("Source Files");
        Folder phalcon = source_file.createSubFolder("Phalcon");

        // 'config' folder with nested subfolders
        Folder app = source_file.createSubFolder("app");
        Folder config = app.createSubFolder("config");
        Folder controllers = app.createSubFolder("controllers");
        Folder library = app.createSubFolder("library");
        Folder models = app.createSubFolder("models");
        Folder views = app.createSubFolder("views");

        // other subfolders under Source Files
        Folder cache = source_file.createSubFolder("cache");
        Folder public_folder = source_file.createSubFolder("public");

        // files in public folder
        public_folder.createFile(".htaccess", 0.5);
        public_folder.createFile("index.php", 1.2);
        public_folder.createFile("router.php", 1.0);

        // Print full structure
        System.out.println("--- Full folder structure ---");
        php_demo1.print();

        // Demonstrate recursive deletion: remove 'config' (app) from Source Files
        System.out.println();
        System.out.println("Deleting 'config' folder from Source Files (should remove its subfolders)...");
        source_file.removeFolder("app");

        // Print structure after deletion
        System.out.println();
        System.out.println("--- Structure after deleting 'config' ---");
        php_demo1.print();
    }
}
