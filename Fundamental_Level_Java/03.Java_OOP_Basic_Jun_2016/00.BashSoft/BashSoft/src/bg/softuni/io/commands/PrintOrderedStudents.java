package bg.softuni.io.commands;

import bg.softuni.exceptions.InvalidInputException;
import bg.softuni.io.IOManager;
import bg.softuni.io.OutputWriter;
import bg.softuni.judge.Tester;
import bg.softuni.network.DownloadManager;
import bg.softuni.repository.StudentsRepository;
import bg.softuni.staticData.ExceptionMessages;

public class PrintOrderedStudents extends Command {

    public PrintOrderedStudents(String input,
                                String[] data,
                                StudentsRepository repository,
                                Tester tester,
                                IOManager inputOutputManager,
                                DownloadManager downloadManager) {
        super(input, data, repository, tester, inputOutputManager, downloadManager);
    }

    @Override
    public void execute() throws Exception {
        String[] data = this.getData();
        if (data.length != 5) {
            throw new InvalidInputException(this.getInput());
        }

        String courseName = data[1];
        String orderType = data[2].toLowerCase();
        String takeCommand = data[3].toLowerCase();
        String takeQuantity = data[4].toLowerCase();

        this.tryParseParametersForOrder(takeCommand, takeQuantity, courseName, orderType);
    }

    private void tryParseParametersForOrder(
            String takeCommand, String takeQuantity,
            String courseName, String orderType) {
        if (!takeCommand.equals("take")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TAKE_COMMAND);
        }

        if (takeQuantity.equals("all")) {
            this.getRepository().orderAndTake(courseName, orderType);
            return;
        }

        try {
            int studentsToTake = Integer.parseInt(takeQuantity);
            this.getRepository().orderAndTake(courseName, orderType, studentsToTake);
        } catch (NumberFormatException nfe) {
            OutputWriter.displayException(ExceptionMessages.INVALID_TAKE_QUANTITY_PARAMETER);
        }
    }
}
