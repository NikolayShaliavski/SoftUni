package bg.softuni.io;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.commands.*;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;

import java.io.IOException;

public class CommandInterpreter {

    private Tester tester;
    private StudentsRepository repository;
    private DownloadManager downloadManager;
    private IOManager inputOutputManager;

    public CommandInterpreter(Tester tester,
                              StudentsRepository repository,
                              DownloadManager downloadManager,
                              IOManager inputOutputManager) {
        this.tester = tester;
        this.repository = repository;
        this.downloadManager = downloadManager;
        this.inputOutputManager = inputOutputManager;
    }

    public void interpretCommand(String input) throws IOException {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Command command = parseCommands(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }
    }

    private Command parseCommands(String input, String[] data, String command) throws IOException {
        switch (command) {
            case "open":
                return new OpenFileCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "mkdir":
                return new MakeDirectoryCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "ls":
                return new TraverseFoldersCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "cmp":
                return new CompareFilesCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "cdrel":
                return new ChangeRelativePathCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "cdabs":
                return new ChangeAbsolutePathCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "readdb":
                return new ReadDatabaseCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "help":
                return new GetHelpCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "show":
                return new ShowCourseCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "filter":
                return new PrintFilteredStudents
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "order":
                return new PrintOrderedStudents
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "download":
                return new DownloadFileCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "downloadasynch":
                return new DownloadAsynchCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            case "dropdb":
                return new DropDatabaseCommand
                        (input, data, this.repository, this.tester, this.inputOutputManager, this.downloadManager);
            default:
                throw new InvalidInputException(input);
        }
    }

//    private void tryDropDb(String command, String[] data) {
//        if (data.length != 1) {
//            this.displayInvalidCommandMessage(command);
//            return;
//        }
//        this.repository.unloadData();
//        OutputWriter.writeMessageOnNewLine("Database dropped!");
//    }
//
//    private void tryDownloadFile(String command, String[] data) {
//        if (data.length != 2) {
//            displayInvalidCommandMessage(command);
//            return;
//        }
//
//        String fileUrl = data[1];
//        this.downloadManager.download(fileUrl);
//    }
//
//    private void tryDownloadFileOnNewThread(String command, String[] data) {
//        if (data.length != 2) {
//            displayInvalidCommandMessage(command);
//            return;
//        }
//
//        String fileUrl = data[1];
//        this.downloadManager.downloadOnNewThread(fileUrl);
//    }
//
//    private void tryPrintFilteredStudents(String input, String[] data) {
//        if (data.length != 5) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String course = data[1];
//        String filter = data[2].toLowerCase();
//        String takeCommand = data[3].toLowerCase();
//        String takeQuantity = data[4].toLowerCase();
//
//        this.tryParseParametersForFilter(takeCommand, takeQuantity, course, filter);
//    }
//
//    private void tryParseParametersForFilter(
//            String takeCommand, String takeQuantity,
//            String courseName, String filter) {
//        if (!takeCommand.equals("take")) {
//            throw new IllegalArgumentException(ExceptionMessages.INVALID_TAKE_COMMAND);
//        }
//
//        if (takeQuantity.equals("all")) {
//            this.repository.filterAndTake(courseName, filter);
//            return;
//        }
//
//        try {
//            int studentsToTake = Integer.parseInt(takeQuantity);
//            this.repository.filterAndTake(courseName, filter, studentsToTake);
//        } catch (NumberFormatException nfe) {
//            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
//        }
//    }
//
//    private void tryPrintOrderedStudents(String input, String[] data) {
//        if (data.length != 5) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String courseName = data[1];
//        String orderType = data[2].toLowerCase();
//        String takeCommand = data[3].toLowerCase();
//        String takeQuantity = data[4].toLowerCase();
//
////        StudentsRepository.printOrderedStudents(courseName, modifier, numberOfStudents);
//        this.tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
//    }
//
//    private void tryParseParametersForOrder(
//            String takeCommand, String takeQuantity,
//            String courseName, String orderType) {
//        if (!takeCommand.equals("take")) {
//            throw new IllegalArgumentException(ExceptionMessages.INVALID_TAKE_COMMAND);
//        }
//
//        if (takeQuantity.equals("all")) {
//            this.repository.orderAndTake(courseName, orderType);
//            return;
//        }
//
//        try {
//            int studentsToTake = Integer.parseInt(takeQuantity);
//            this.repository.orderAndTake(courseName, orderType, studentsToTake);
//        } catch (NumberFormatException nfe) {
//            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
//        }
//    }
//
//    private void tryShowWantedCourse(String input, String[] data) {
//        if (data.length != 2 && data.length != 3) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        if (data.length == 2) {
//            String courseName = data[1];
//            this.repository.getStudentsByCourse(courseName);
//        }
//
//        if (data.length == 3) {
//            String courseName = data[1];
//            String userName = data[2];
//            this.repository.getStudentMarkInCourse(courseName, userName);
//        }
//    }
//
//    private void tryOpenFile(String input, String[] data) throws IOException {
//        if (data.length != 2) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String fileName = data[1];
//        String filePath = SessionData.currentPath + "\\" + fileName;
//        File file = new File(filePath);
//        Desktop.getDesktop().open(file);
//    }
//
//    private void tryCompareFiles(String input, String[] data) throws IOException {
//        if (data.length != 3) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String firstPath = data[1];
//        String secondPath = data[2];
//        this.tester.compareContent(firstPath, secondPath);
//    }
//
//    private void tryGetHelp(String input, String[] data) {
//        if (data.length != 1) {
//            this.displayInvalidCommandMessage(input);
//            return;
//        }
//
//        this.displayHelp();
//    }
//
//    private void tryReadDatabaseFromFile(String input, String[] data) throws IOException {
//        if (data.length != 2) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String fileName = data[1];
//        this.repository.loadData(fileName);
//    }
//
//    private void tryChangeAbsolutePath(String input, String[] data) throws IOException {
//        if (data.length != 2) {
//            this.displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String absolutePath = data[1];
//        this.inputOutputManager.changeCurrentDirAbsolute(absolutePath);
//    }
//
//    private void tryChangeRelativePath(String input, String[] data) throws IOException {
//        if (data.length != 2) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String relativePath = data[1];
//        this.inputOutputManager.changeCurrentDirRelativePath(relativePath);
//    }
//
//    private void tryTraverseFolders(String input, String[] data) {
//        if (data.length != 1 && data.length != 2) {
//            this.displayInvalidCommandMessage(input);
//            return;
//        }
//
//        if (data.length == 1) {
//            this.inputOutputManager.traverseDirectory(0);
//        }
//
//        if (data.length == 2) {
//            this.inputOutputManager.traverseDirectory(Integer.valueOf(data[1]));
//        }
//    }
//
//    private void tryCreateDirectory(String input, String[] data) {
//        if (data.length != 2) {
//            displayInvalidCommandMessage(input);
//            return;
//        }
//
//        String folderName = data[1];
//        this.inputOutputManager.createDirectoryInCurrentFolder(folderName);
//    }
//
//    private void displayInvalidCommandMessage(String input) {
//        String output = String.format("The command '%s' is invalid", input);
//        OutputWriter.displayException(output);
//    }
//
//    private void displayHelp() {
//        StringBuilder helpBuilder = new StringBuilder();
//        helpBuilder.append("make directory - mkdir nameOfFolder")
//                .append(System.lineSeparator());
//        helpBuilder.append("traverse directory - ls depth")
//                .append(System.lineSeparator());
//        helpBuilder.append("comparing files - cmp absolutePath1 absolutePath2")
//                .append(System.lineSeparator());
//        helpBuilder.append("change directory - cdRel relativePath or \"..\" for level up")
//                .append(System.lineSeparator());
//        helpBuilder.append("change directory - cdAbs absolutePath")
//                .append(System.lineSeparator());
//        helpBuilder.append("read students data base - readDb fileName")
//                .append(System.lineSeparator());
//        helpBuilder.append("filter students - filter {courseName} excellent/average/poor take 2/5/all")
//                .append(System.lineSeparator());
//        helpBuilder.append("order students - order {courseName} ascending/descending take 20/10/all")
//                .append(System.lineSeparator());
//        helpBuilder.append("download file - download URL (saved in current directory)")
//                .append(System.lineSeparator());
//        helpBuilder.append("download file on new thread - downloadAsynch URL (saved in the current directory)")
//                .append(System.lineSeparator());
//        helpBuilder.append("get help – help")
//                .append(System.lineSeparator());
//        OutputWriter.writeMessage(helpBuilder.toString());
//        OutputWriter.writeEmptyLine();
//    }
}
