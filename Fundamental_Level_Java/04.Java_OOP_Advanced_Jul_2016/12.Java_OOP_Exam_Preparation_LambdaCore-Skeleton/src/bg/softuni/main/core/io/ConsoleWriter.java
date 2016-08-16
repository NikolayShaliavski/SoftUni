package bg.softuni.main.core.io;

import bg.softuni.main.contracts.coreContracts.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
