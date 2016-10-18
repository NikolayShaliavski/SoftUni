package IO;

import StaticData.ExceptionMessages;
import StaticData.SessionData;

import java.io.File;
import java.util.LinkedList;

public class IOManager {

    //written in first LAB, changed in third LAB
    public static void traverseDirectory(int depth) {//from first LAB argument was String path

        LinkedList<File> subFolders = new LinkedList<>();
        String path = SessionData.currentPath;
        //here we split the path -> method returns array and we get it length
        int intitalIntdentication = path.split("\\\\").length;// -> third LAB

        File root = new File(path);
        subFolders.add(root);

        while (subFolders.size() != 0) {
            File currentFolder = subFolders.removeFirst();
            //here we again split current path, get the length of the array, subtract the first length(get from the first path)
            int currentIdentation = currentFolder.toString().split("\\\\").length - intitalIntdentication;// -> third LAB
            // -> third LAB
            if (depth - currentIdentation < 0) {//check how deep we are in folders; depends of parameter depth
                break;
            }
            // -> first LAB
            OutputWriter.writeMessageOnNewLine(currentFolder.toString());//printing current folder

            if (currentFolder.listFiles() != null) {

                for (File file : currentFolder.listFiles()) {
                    if (file.isDirectory()) {
                        subFolders.add(file);//if folder is directory, we add it to a List
                    } else {// -> third LAB(else condition was written in third LAB)
                        int indexOLastSlash = file.toString().lastIndexOf("\\");//finding the last slash

                        for (int i = 0; i < indexOLastSlash; i++) {
                            OutputWriter.writeMessage("-");//printing "-" instead the path to file
                        }
                        OutputWriter.writeMessageOnNewLine(file.getName());//printing the file(which isn't a directory)
                    }
                }
            }
        }
    }

    //third LAB
    //method which creates new directory in our project -> not used yet!!!!!!!!!!!!!!!!!
    public static void createDirectoryInCurrentFolder(String name) {
        String path = getCurrentDirectoryPath() + "\\" + name;
        File file = new File(path);
        file.mkdir();
    }

    //only get directory path to our project
    public static String getCurrentDirectoryPath() {
        String currentPath = SessionData.currentPath;
        return currentPath;
    }

    //method to go one directory UP or go into a directory
    //changes current path
    //written in third LAB, not used  yet!!!!!!!!!!
    public static void changeCurrentDirRelativePath(String relativePath) {

        try {
            if (relativePath.equals("..")) {
                //go one directory up
                String currentPath = SessionData.currentPath;
                int indexOfLastSlash = currentPath.lastIndexOf("\\");
                String newPath = currentPath.substring(0, indexOfLastSlash);
                SessionData.currentPath = newPath;
            } else {
                //go to a given directory
                String currentPath = SessionData.currentPath;
                currentPath += "\\" + relativePath;
                changeCurrentDirAbsolute(currentPath);
            }
        } catch (StringIndexOutOfBoundsException strOutBoundsEx) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.INVALID_DESTINATION);
        }
    }

    //method to change absolute path(which is path, used in traverseDirectory() like start point -> start folder; it's our project folder)
    public static void changeCurrentDirAbsolute(String absolutePath) {

        File file = new File(absolutePath);

        if (!file.exists()) {
            OutputWriter.displayException(ExceptionMessages.INVALID_PATH);
            return;
        }
        SessionData.currentPath = absolutePath;
    }
}