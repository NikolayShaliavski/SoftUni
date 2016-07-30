package problem_01_Logger.core.IO;

import problem_01_Logger.contracs.File;

public class FileWriter implements File {

    private StringBuilder builder;

    public FileWriter() {
        this.builder = new StringBuilder();
    }

    @Override
    public void write(String text) {
        this.builder.append(text);
    }

    @Override
    public int getSize() {
        int asciiValue = 0;
        for (int i = 0; i < this.builder.length(); i++) {
            Character currentChar = builder.charAt(i);
            if (Character.isLetter(currentChar)) {
                asciiValue += currentChar;
            }
        }
        return asciiValue;
    }
}

