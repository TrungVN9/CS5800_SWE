package question5;

/**
 *
 * @author trungvong
 */
public class Folder {
    private String folderName;
    private File[] files;
    private int fileCount;
    private Folder[] subfolders;
    private int subfolderCount;
    private static final int MAX_FILES = 100; // max files for tracking files
    private static final int MAX_SUBFOLDERS = 100; // tracking subfolders

    public Folder(String folderName) {
        this.folderName = folderName;
        this.files = new File[MAX_FILES];
        this.fileCount = 0;
        this.subfolders = new Folder[MAX_SUBFOLDERS];
        this.subfolderCount = 0;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void addFile(File file) {
        if (fileCount < MAX_FILES) {
            files[fileCount++] = file;
        } else {
            System.out.println("Folder is full. Cannot add more files.");
        }
    }

    public File createFile(String name, double size) {
        File newFile = File.createFile(this, name, size);
        if (newFile != null) {
            addFile(newFile);
        }
        return newFile;
    }

    // Remove files
    public void removeFile(String name) {
        for (int i = 0; i < fileCount; i++) {
            if (files[i] != null && files[i].getName().equals(name)) {
                // Shift remaining files left
                for (int j = i; j < fileCount - 1; j++) {
                    files[j] = files[j + 1];
                }
                files[--fileCount] = null;
                System.out.println("File " + name + " removed from " + folderName + ".");
                return;
            }
        }
        System.out.println("File " + name + " not found in " + folderName + ".");
    }

     // Create a sub-folder within this folder
    public Folder createSubFolder(String name) {
        if (subfolderCount >= MAX_SUBFOLDERS) {
            System.out.println("Cannot create more subfolders in " + folderName);
            return null;
        }
        // avoid duplicate names
        for (int i = 0; i < subfolderCount; i++) {
            if (subfolders[i].getFolderName().equals(name)) {
                System.out.println("Subfolder '" + name + "' already exists in " + folderName);
                return subfolders[i];
            }
        }
        Folder f = new Folder(name);
        subfolders[subfolderCount++] = f;
        return f;
    }

    // Remove a subfolder (recursively deletes its contents)
    public void removeFolder(String name) {
        for (int i = 0; i < subfolderCount; i++) {
            if (subfolders[i] != null && subfolders[i].getFolderName().equals(name)) {
                // recursively clean up the folders
                subfolders[i].deleteAll();
                // shift remaining subfolders left
                for (int j = i; j < subfolderCount - 1; j++) {
                    subfolders[j] = subfolders[j + 1];
                }
                subfolders[--subfolderCount] = null;
                System.out.println("Folder '" + name + "' removed from " + folderName + ".");
                return;
            }
        }
        System.out.println("Folder '" + name + "' not found in " + folderName + ".");
    }

    // Recursively delete all files and subfolders inside this folder
    public void deleteAll() {
        // clear files
        for (int i = 0; i < fileCount; i++) {
            files[i] = null;
        }
        fileCount = 0;
        // recursively clear subfolders
        for (int i = 0; i < subfolderCount; i++) {
            if (subfolders[i] != null) {
                subfolders[i].deleteAll();
                subfolders[i] = null;
            }
        }
        subfolderCount = 0;
    }

    // For printing indentation
    public void print() {
        print("");
    }

    // recursive print with indentation for tracking output format
    private void print(String indent) {
        System.out.println(indent + "Folder: " + folderName);
        // print files
        for (int i = 0; i < fileCount; i++) {
            if (files[i] != null) {
                files[i].print(indent + "  ");
            }
        }
        // print subfolders
        for (int i = 0; i < subfolderCount; i++) {
            if (subfolders[i] != null) {
                subfolders[i].print(indent + "  ");
            }
        }
    }
}
