import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Problem05_GetFolderSize {

    public static void main(String[] args) throws FileNotFoundException {

        String pathToFolder = "files\\Problem_05";
        File file = new File(pathToFolder);
        //Queue<File> subFolders = new ArrayDeque<>();
        //subFolders.add(file);
        long allBytes = 0;
//traversing whole TestFolder include directories and gatting bytes for each file in it
//        while (subFolders.size() != 0) {
//            File currentFolder = subFolders.poll();
//
//            if (currentFolder.listFiles() != null) {
//
//                for (File subFile : currentFolder.listFiles()) {
//                    if (subFile.isDirectory()) {
//                        subFolders.add(subFile);
//                    } else {
//                        allBytes += subFile.length();
//                    }
//                }
//            }
//        }
//        double bytesInMBytes = allBytes / 1024.0 / 1024.0;
//        System.out.println(bytesInMBytes);

        //getting bytes only for files in TestFolder, if we find directory, we ignore it
        for (File subFile : file.listFiles()) {
            if (subFile.isFile()) {
                allBytes += subFile.length();
            }
        }
        double bytesInMbytes = allBytes / 1024.0 / 1024.0;
        System.out.println(bytesInMbytes);
    }
}
