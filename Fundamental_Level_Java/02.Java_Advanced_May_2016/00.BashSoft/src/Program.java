import IO.InputReader;

public class Program {

    public static void main(String[] args) {

        //first LAB

        //IO.IOManager manager = new IO.IOManager();
        //manager.traverseDirectory(3);//from first LAB parameter, given to the method was String -> "C:\\Program Files (x86)"
                                     //now we give Integer as parameter to method, which traverse our project, not Program Files
                                     //this Integer is depth of traversing -> if is 1, we look in all first folders, not deeper,
                                     //if is 2 -> in all first and second folders etc. For every folder we print files in it.
                                     //path to directory from which we will start traversing we can change from IO.IOManager ->
                                     //changeCurrentDirAbsolute()
                                     //to go one folder up use IO.IOManager -> changeCurrentDirRelativePath()
        //second LAB

//        Repository.StudentsRepository repository = new Repository.StudentsRepository();
//        repository.intializeData(data.txt);
//        repository.getStudentsByCourse("Java_fundamentals");
//        IO.OutputWriter.writeMessageOnNewLine("############################################################################");
//        repository.getStudentMarksInCourse("C#_advanced", "Lila");
//
//        //third LAB
//
//        String test1path = "resources\\files\\02_LinesOutMy.txt";
//        String test2path = "resources\\files\\02_LinesOut.txt";
//        Judge.Tester.compareContent(test1path, test2path);
//
//        IO.IOManager.createDirectoryInCurrentFolder("pesho");

        //fourth LAB

//        IO.IOManager ioManager = new IO.IOManager();
//        ioManager.changeCurrentDirAbsolute("E:");
//        ioManager.traverseDirectory(2);

        //fifth && sixth LAB
        InputReader inputReader = new InputReader();
        inputReader.readCommands();
    }
}
