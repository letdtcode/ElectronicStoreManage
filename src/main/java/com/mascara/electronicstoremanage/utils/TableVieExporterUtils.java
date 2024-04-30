package com.mascara.electronicstoremanage.utils;

import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 30/04/2024
 * Time      : 5:20 CH
 * Filename  : TableVieExporterUtils
 */
public class TableVieExporterUtils {
    private CellStyle cellStyleFormatNumber = null;
    private static FileChooser fileChooser = null;

    private static TableVieExporterUtils instance = null;

    public static TableVieExporterUtils getInstance() {
        if (instance == null)
            instance = new TableVieExporterUtils();
        return instance;
    }

    public List<CategoryCreateRequest> importCategory(Window window) {

        if (fileChooser == null)
            fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        fileChooser.setTitle("Chọn file excel để import");

        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            List<CategoryCreateRequest> categoryCreateRequests = new LinkedList<>();
            InputStream inputStream = null;
            Workbook workbook = null;
            try {
                inputStream = new FileInputStream(file.getPath());
                workbook = getWorkbook(inputStream, file.getPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0)
                    continue;

                Iterator<Cell> cellIterator = nextRow.cellIterator();

                CategoryCreateRequest request = CategoryCreateRequest.builder().build();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    Object cellValue = getCellValue(cell);
                    if (cellValue == null || cellValue.toString().isEmpty())
                        continue;
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 1:
                            request.setCategoryName((String) getCellValue(cell));
                            break;
                        default:
                            break;
                    }
                }
                categoryCreateRequests.add(request);
            }
            try {
                workbook.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return categoryCreateRequests;
        }
        return null;
    }

    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }
        return cellValue;
    }

    public boolean exportExcel(TableView tableView) {
        boolean result = true;
        if (fileChooser == null)
            fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        fileChooser.setTitle("Chọn đường dẫn lưu file Excel");

        File file = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        if (file != null) {
            //        Create work book
            Workbook workbook = getWorkbook(file.getPath());

            //        Create sheet
            Sheet sheet = workbook.createSheet("Sheet 1");
            ObservableList<TableColumn<?, ?>> columns = tableView.getColumns();

            if (columns.size() == 0)
                result = false;

            //        Write header
            int rowIndex = 0;
            writeHeader(sheet, rowIndex, columns);

            //        Write data
            ObservableList<?> items = tableView.getItems();
            if (items.size() == 0)
                result = false;
            writeContent(items, columns, sheet);

            //        Auto resize column width
            int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
            autoSizeColumn(sheet, numberOfColumn);

            //        Create file excel
            createOutputFile(workbook, file.getPath());
        } else result = false;
        return result;
    }

    private void createOutputFile(Workbook workbook, String excelFilePath) {
        try {
            OutputStream os = new FileOutputStream(excelFilePath);
            workbook.write(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void autoSizeColumn(Sheet sheet, int numberOfColumn) {
        for (int columnIndex = 0; columnIndex < numberOfColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    private void writeContent(ObservableList<?> items, ObservableList<TableColumn<?, ?>> columns, Sheet sheet) {
        short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");

        Workbook workbook = sheet.getWorkbook();
        cellStyleFormatNumber = workbook.createCellStyle();
        cellStyleFormatNumber.setDataFormat(format);

        for (int rowIndex = 0; rowIndex < items.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            for (int colIndex = 0; colIndex < columns.size(); colIndex++) {
                TableColumn<?, ?> column = columns.get(colIndex);
                Object cellValue = column.getCellData(rowIndex);
                Cell cell = null;
                if (cellValue != null) {
                    if (cellValue instanceof Number) {
                        cell = row.createCell(colIndex, CellType.NUMERIC);
                        cell.setCellStyle(cellStyleFormatNumber);
                        cell.setCellValue(((Number) cellValue).doubleValue());
                    } else {
                        cell = row.createCell(colIndex, CellType.STRING);
                        cell.setCellValue(cellValue.toString());
                    }
                }
            }
        }
    }

    private void writeHeader(Sheet sheet, int rowIndex, ObservableList<TableColumn<?, ?>> columns) {
        CellStyle cellStyle = createStyleForHeader(sheet);
        Row row = sheet.createRow(rowIndex);

        int numColumn = columns.size();
        Cell cell = null;
        for (int i = 0; i < numColumn; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columns.get(i).getText());
        }
    }

    private CellStyle createStyleForHeader(Sheet sheet) {
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }


    private Workbook getWorkbook(String excelFilePath) {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx"))
            workbook = new XSSFWorkbook();
        else if (excelFilePath.endsWith("xls"))
            workbook = new HSSFWorkbook();
        else
            throw new IllegalArgumentException("The specified file is not Excel file");
        return workbook;
    }

    private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else throw new IllegalArgumentException("The specified file is not Excel file");
        return workbook;
    }
}
