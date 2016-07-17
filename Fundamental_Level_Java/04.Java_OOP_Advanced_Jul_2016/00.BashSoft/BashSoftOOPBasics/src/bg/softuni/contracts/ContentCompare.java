package bg.softuni.contracts;

import java.io.IOException;

public interface ContentCompare {

    void compareContent(String actualOutput, String expectedOutput) throws IOException;
}
