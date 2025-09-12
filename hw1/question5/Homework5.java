
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

        // 'app' folder with nested subfolders
        Folder app = source_file.createSubFolder("app");
        Folder config = app.createSubFolder("config");
        Folder controllers = app.createSubFolder("controllers");
        Folder library = app.createSubFolder("library");
        Folder migrations = app.createSubFolder("migrations");
        Folder models = app.createSubFolder("models");
        Folder views = app.createSubFolder("views");

        // other subfolders under Source Files
        Folder cache = source_file.createSubFolder("cache");
        Folder public_folder = source_file.createSubFolder("public");

        // files in public folder
        public_folder.createFile(".htaccess", 0.5);
        public_folder.createFile("index.php", 1.2);
        public_folder.createFile("router.php", 1.0);

        // Create a subfolder in php_demo1
        Folder include_path = php_demo1.createSubFolder("Include Path");
        Folder remote_files = php_demo1.createSubFolder("Remote Files");

        // Print full structure
        System.out.println("--- 1. Full folder structure ---");
        php_demo1.print();

        // recursive deletion: remove 'app' folder from Source Files
        System.out.println();
        System.out.println("Deleting 'app' folder from Source Files (should remove its subfolders)...");
        source_file.removeFolder("app");

        // Print structure after deletion
        System.out.println();
        System.out.println("--- 2. Structure after deleting 'app' ---");
        php_demo1.print();

        // Delete the entire public folder and print out the full structure
        System.out.println();
        System.out.println("Deleting 'public' folder from Source Files (should remove its files)...");
        source_file.removeFolder("public");
        System.out.println();
        System.out.println("--- 3. Structure after deleting 'public' ---");
        php_demo1.print();

    }
}
