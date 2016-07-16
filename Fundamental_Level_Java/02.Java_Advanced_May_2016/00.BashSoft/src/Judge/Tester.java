package Judge;

import StaticData.ExceptionMessages;
import IO.OutputWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//written in third LAB -> this class imitates Judge system
//it compares two files line by line -> testedOutput and expectedOutput
//it creates new file, there we write the result
//if there are mismatches(differences in outputs) -> we write them in this file and print them on the console
//matches(correct output) also
public class Tester {

    //method to compare actual and expected output -> invoked in main
    public static void compareContent(String actualOutput, String expectedOutput) {
        try {
            OutputWriter.writeMessageOnNewLine("Reading files...");
            //creating path for new file for mismatches(the result of testing)
            String mismatchPath = getMismatchPath(expectedOutput);//calling method to create this new filePath

            List<String> actualOutputString = readTextFile(actualOutput);//reading file content for actual output
            List<String> expectedOutputString = readTextFile(expectedOutput);//.......... for expected output and save it in a List

            //invoking compareStrings() -> method which compares file content of two files and write the result in third file mismatch
            boolean mismatch = compareStrings(actualOutputString, expectedOutputString, mismatchPath);

            if (mismatch) {//if there are mismatches -> read mismatch file and print all lines on the console
                List<String> mismatchString = readTextFile(mismatchPath);
                mismatchString.forEach(OutputWriter::writeMessageOnNewLine);
            } else {
                OutputWriter.writeMessageOnNewLine("Files are identical. There are no mismatches.");
            }
        } catch (IOException ioEx) {
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.INVALID_PATH);
        }
    }

    //method to compare file content(invoked by compareContent()) -> returns boolean
    private static boolean compareStrings(List<String> actualOutputString, List<String> expectedOutputString, String mismatchPath) {

        OutputWriter.writeMessageOnNewLine("Comparing files...");
        String output = "";
        boolean isMismatch = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mismatchPath))) {

            if (actualOutputString.size() != expectedOutputString.size()) {//written in fourth LAB; if file lengths are different,
                isMismatch = true;                                          // we have not to continue comparing, so we return
                OutputWriter.writeMessageOnNewLine(ExceptionMessages.INVALID_OUTPUT_LENGTH);
                return isMismatch;
            }
            int maxLength = Math.max(actualOutputString.size(), expectedOutputString.size());

            for (int i = 0; i < maxLength; i++) {
                String actualLine = actualOutputString.get(i);
                String expectedLine = expectedOutputString.get(i);

                if (!actualLine.equals(expectedLine)) {//here we find mismatch -> save it in String variable
                    output = String.format("mismatch- -> expected{%s}, actual{%s}%n", expectedLine, actualLine);
                    isMismatch = true;//change boolean variable, which method returns to true
                } else {
                    output = String.format("line match -> %s%n", actualLine);//else save match in same String variable
                }
                //write every compared line in new file
                writer.write(output);
            }
        } catch (IOException ioEx) {
            isMismatch = true;
            OutputWriter.writeMessageOnNewLine(ExceptionMessages.CANNOT_ACCESS_FILE);
        }
//        catch (IndexOutOfBoundsException outBoundsEx) {// this is wrong -> if we catch exception here, before the exception to be thrown,
//            isMismatch = true;                        //files have been compared to the actualOutputString length, so there are several lines
                                                        //which we will print on the console -> in this case we haven't do this!!!!!!!!
//            IO.OutputWriter.writeMessageOnNewLine(StaticData.ExceptionMessages.INVALID_OUTPUT_LENGTH);
//        }
        return isMismatch;
    }

    //reading file method(invoked by compareContent()) -> reads data from file and saves it in a List
    private static List<String> readTextFile(String filePath) throws IOException {
        List<String> text = new ArrayList<>();
        File file = new File(filePath);

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = null;
        while ((line = reader.readLine()) != null) {//such as line != null -> in condition;
            text.add(line);                         //line = reader.readLine() -> in the end of while loop
        }
        reader.close();
        return text;
    }

    //method to create path for new file where we write the result of comparing
    private static String getMismatchPath(String expectedOutput) {
        int index = expectedOutput.lastIndexOf('\\');
        String directoryPath = expectedOutput.substring(0, index);
        return directoryPath + "\\mismatch.txt";
    }
}
