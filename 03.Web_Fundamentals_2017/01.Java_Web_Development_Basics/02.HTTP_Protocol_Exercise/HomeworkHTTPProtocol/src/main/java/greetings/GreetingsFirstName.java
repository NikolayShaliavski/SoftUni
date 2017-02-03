package greetings;

import utils.WebUtils;

public class GreetingsFirstName {

    private static final String GREETINS_FIRST_NAME_HTML =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
                    "\\02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                        "resources\\static\\html\\greetings\\greetings-first-name.html";

    private static final String FILE_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics\\" +
                    "02.HTTP_Protocol_Exercise\\HomeworkHTTPProtocol\\src\\main\\" +
                    "resources\\static\\html\\greetings\\files\\userdata.txt";


    public static void main(String[] args) {
        setContent();
        String html = WebUtils.readHTML(GREETINS_FIRST_NAME_HTML);
        System.out.println(html);

        //saveFirstName();
        //createNewFile();
    }

//    private static void createNewFile() {
//        File file = new File(FILE_PATH);
//        try {
//            Files.deleteIfExists(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        file = new File(FILE_PATH);
//    }

//    private static void saveFirstName() {
//        Map<String, String> params = WebUtils.getParameters();
//
//        String firstName = params.get("first-name");
//        File file = new File(FILE_PATH);
//        GreetingsFileWriter.append(firstName);
//        System.out.println(firstName);
//    }

    private static void setContent() {
        System.out.println("Content-Type: text/html\n");
    }
}
