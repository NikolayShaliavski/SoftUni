package IO;

import Judge.Tester;
import Network.DownloadManager;
import Repository.StudentsRepository;
import StaticData.ExceptionMessages;
import StaticData.SessionData;

import java.awt.*;
import java.io.File;

public class CommandInterpreter {

    //written in fifth LAB
    public static void interpretCommand(String input) {
        String[] data = input.split("\\s+");
        String command = data[0];

        switch (command) {
            case "open":
                tryOpenFile(input, data);
                break;
            case "mkdir":
                tryCreateDirectory(input, data);
                break;
            case "ls":
                tryTraverseFolders(input, data);
                break;
            case "cmp":
                tryCompareFiles(input, data);
                break;
            case "changeDirRel":
                tryChangeRelativePath(input, data);
                break;
            case "changeDirAbs":
                tryChangeAbsolutePath(input, data);
                break;
            case "readDb":
                tryReadDataBaseFromFile(input, data);
                break;
            case "show":
                tryShowWantedCourse(input, data);
                break;
            case "filter":
                tryPrintFilteredStudents(input, data);
                break;
            case "order":
                tryPrintOrderedStudents(input, data);
                break;
            case "download"://written in 9 LAB
                tryDownloadFile(input, data);
                break;
            case "downloadAsynch"://written in 9 LAB
                tryDownloadFileOnNewThread(input, data);
                break;
            case "help":
                getHelp();
                break;
            default:
                displayInvalidCommandMessage(input);
                break;
        }
    }

    private static void tryOpenFile(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String fileName = data[1];
        String filePath = SessionData.currentPath + "\\" + fileName;
        File file = new File(filePath);
        try {
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            OutputWriter.displayException(ExceptionMessages.CANNOT_ACCESS_FILE);
        }
    }

    private static void tryCreateDirectory(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String folderName = data[1];
        IOManager.createDirectoryInCurrentFolder(folderName);
    }

    private static void tryTraverseFolders(String input, String[] data) {
        if (data.length != 1 && data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        if (data.length == 1) {
            IOManager.traverseDirectory(0);
        } else if (data.length == 2) {
            try {
                int depth = Integer.parseInt(data[1]);
                IOManager.traverseDirectory(depth);
            } catch (NumberFormatException nfex) {
                displayInvalidCommandMessage(input);
            }
        }
    }

    private static void tryCompareFiles(String input, String[] data) {
        if (data.length != 3) {
            displayInvalidCommandMessage(input);
            return;
        }
        String firstPath = data[1];
        String secondPath = data[2];
        Tester.compareContent(firstPath, secondPath);
    }

    private static void tryChangeRelativePath(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String relativePath = data[1];
        IOManager.changeCurrentDirRelativePath(relativePath);
    }

    private static void tryChangeAbsolutePath(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String absolutePath = data[1];
        IOManager.changeCurrentDirAbsolute(absolutePath);
    }

    private static void tryReadDataBaseFromFile(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }
        String fileName = data[1];
        StudentsRepository.initializeData(fileName);
    }

    private static void tryShowWantedCourse(String input, String[] data) {
        if (data.length != 2 && data.length != 3) {
            displayInvalidCommandMessage(input);
            return;
        }
        if (data.length == 2) {
            String courseName = data[1];
            StudentsRepository.getStudentsByCourse(courseName);
        } else if (data.length == 3) {
            String courseName = data[1];
            String studentName = data[2];
            StudentsRepository.getStudentMarksInCourse(courseName, studentName);
        }
    }

    private static void tryPrintFilteredStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
            return;
        }
        String course = data[1];
        String filter = data[2];

        if (data.length == 3) {
            StudentsRepository.printFilteredStudents(course, filter, null);
            return;
        }
        Integer numberOfStudents = Integer.valueOf(data[3]);
        if (data.length == 4) {
            StudentsRepository.printFilteredStudents(course, filter, numberOfStudents);
        }
    }

    private static void tryPrintOrderedStudents(String input, String[] data) {
        if (data.length != 3 && data.length != 4) {
            displayInvalidCommandMessage(input);
            return;
        }
        String course = data[1];
        String compareType = data[2];

        if (data.length == 3) {
            StudentsRepository.printOrderedStudents(course, compareType, null);
            return;
        }
        Integer numberOfStudents = Integer.valueOf(data[3]);
        if (data.length == 4) {
            StudentsRepository.printOrderedStudents(course, compareType, numberOfStudents);
        }
    }

    private static void tryDownloadFile(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileUrl = data[1];
        DownloadManager.download(fileUrl);
    }

    private static void tryDownloadFileOnNewThread(String input, String[] data) {
        if (data.length != 2) {
            displayInvalidCommandMessage(input);
            return;
        }

        String fileUrl = data[1];
        DownloadManager.downloadOnNewThread(fileUrl);
    }
    private static void getHelp() {
        OutputWriter.writeMessageOnNewLine("open - open file");
        OutputWriter.writeMessageOnNewLine("mkdir path - make directory");
        OutputWriter.writeMessageOnNewLine("ls depth - traverse directory");
        OutputWriter.writeMessageOnNewLine("cmp path1 path2 - compare two files");
        OutputWriter.writeMessageOnNewLine("changeDirRel relativePath - change directory");
        OutputWriter.writeMessageOnNewLine("changeDirAbs absolutePath - change directory");
        OutputWriter.writeMessageOnNewLine("readDb path - read students data base");
        OutputWriter.writeMessageOnNewLine("show courseName(studentName) - shows info about given course and student(student may be omitted)");
        OutputWriter.writeMessageOnNewLine("filterExcellent - filter excellent students (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("filterExcellent path - filter excellent students (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("filterAverage - filter average students (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("filterAverage path - filter average students (the output is written in a file)");
        OutputWriter.writeMessageOnNewLine("filterPoor - filter low grade students (the output is on the console)");
        OutputWriter.writeMessageOnNewLine("filterPoor path - filter low grade students (the output is written in a file)");
        OutputWriter.writeMessageOnNewLine("order - sort students in increasing order (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("order path - sort students in increasing order (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("decOrder - sort students in decreasing order (the output is written on the console)");
        OutputWriter.writeMessageOnNewLine("decOrder path - sort students in decreasing order (the output is written in a given path)");
        OutputWriter.writeMessageOnNewLine("download pathOfFile - download file (saved in current directory)");
        OutputWriter.writeMessageOnNewLine("downloadAsync path - download file asynchronously (save in the current directory)");
        OutputWriter.writeMessageOnNewLine("help - get help");
        OutputWriter.writeEmptyLine();
    }

    private static void displayInvalidCommandMessage(String input) {
        String output = String.format("The command '%s' is invalid.", input);
        OutputWriter.writeMessageOnNewLine(output);
    }
}
