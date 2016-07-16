//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class Problem14_ImportToExcel {
//
//    public static void main(String[] args) {
//
//        String pathToRead = "resources\\StudentData.txt";
//        File excelReport = new File("resources\\ExcelReport.xlsx");
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        XSSFSheet sheet = workbook.createSheet("StudentData");
//
//        Map<String, ArrayList<String>> data = new LinkedHashMap<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(pathToRead));
//             FileOutputStream out = new FileOutputStream(excelReport)) {
//
//            String line = reader.readLine();
//
//            int rowNum = 0;
//            while (!line.equals(null)) {
//                String[] tokens = line.split("[\\s]+");
//                String[] collectedData = new String[8];
//                for (int i = 0; i <= 5; i++) {
//                    collectedData[i] = tokens[i];
//                }
//                String[] gradesArr = new String[4];
//                for (int i = 6; i < 10; i++) {
//                    gradesArr[i - 6] = tokens[i];
//                }
//                String grades = gradesArr.toString();
//                collectedData[6] = grades;
//                collectedData[7] = tokens[10];
//
//
//                Row row = sheet.createRow(rowNum++);
//                int cellNum = 0;
//                for (int i = 0; i < collectedData.length; i++) {
//                    Cell cell = row.createCell(cellNum++);
//                    cell.setCellValue(collectedData[i]);
//                }
//            }
//            workbook.write(out);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
