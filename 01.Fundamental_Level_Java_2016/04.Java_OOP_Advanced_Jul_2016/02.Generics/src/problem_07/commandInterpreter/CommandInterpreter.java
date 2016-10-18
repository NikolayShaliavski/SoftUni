package problem_07.commandInterpreter;

import problem_07.IO.Reader;
import problem_07.IO.Writer;
import problem_07.collections.CustomListImpl;
import problem_07.sorter.Sorter;

import java.io.IOException;

public class CommandInterpreter {

    private Writer writer;
    private Reader reader;
    private CustomListImpl<String> customList;
    private Sorter<String> sorter;

    public CommandInterpreter() throws ClassNotFoundException {
        this.reader = new Reader();
        this.writer = new Writer();
        this.customList = new CustomListImpl();
        this.sorter = new Sorter<>();
    }

    public void executeCommands() throws IOException {
        String command = this.readCommand();
        try {
            while (!command.equals("END")) {
                String[] params = command.split("\\s+");

                switch (params[0]) {
                    case "Add":
                        this.customList.add(params[1]);
                        break;
                    case "Remove":
                        //this.writer.print(this.customList.remove(Integer.valueOf(params[1])).toString());
                        this.customList.remove(Integer.valueOf(params[1]));
                        break;
                    case "Contains":
                        boolean hasFound = this.customList.contains(params[1]);
                        this.writer.print(String.valueOf(hasFound));
                        break;
                    case "Swap":
                        int index1 = Integer.valueOf(params[1]);
                        int index2 = Integer.valueOf(params[2]);
                        this.customList.swap(index1, index2);
                        break;
                    case "Greater":
                        this.writer.print(String.valueOf(this.customList.countGreaterThan(params[1])));
                        break;
                    case "Max":
                        this.writer.print(String.valueOf(this.customList.getMax()));
                        break;
                    case "Min":
                        this.writer.print(String.valueOf(this.customList.getMin()));
                        break;
                    case "Print":
                        //this.writer.printAll(this.customList.toString());
                        //here foreach loop without iterator don't work
                        //but if we iterate over collection in class is we don't need to implement Iterable
                        for (String element : this.customList) {
                            System.out.println(element);
                        }
                        break;
                    case "Sort":
                        this.customList = this.sorter.sort(this.customList);
                        break;
                }
                command = this.readCommand();
            }
        } catch (IndexOutOfBoundsException iex) {
            this.writer.print(iex.getMessage());
        }
    }

    private String readCommand() throws IOException {
        return reader.read();
    }
}
