package app;

public class ByTheCake {

    private static final String INDEX_PATH =
            "E:\\GitHub\\SoftUni\\03.Web_Fundamentals_2017\\01.Java_Web_Development_Basics" +
            "\\01.Web_Fundamentals_Introdution\\ByTheCake\\src\\main\\resources" +
            "\\static\\html\\index.html";

    public static void main(String[] args) {
        setContent();
        String html = HTMLReader.readHTML(INDEX_PATH);
        System.out.println(html);
    }

    private static void setContent() {
        String content = "Content-type: text/html\n";
        System.out.println(content);
    }
}
