package pr03_DependencyInversionSkeleton.io;

import pr03_DependencyInversionSkeleton.contracts.Writer;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
