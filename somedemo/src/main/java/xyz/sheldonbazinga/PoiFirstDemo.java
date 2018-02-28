package xyz.sheldonbazinga;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class PoiFirstDemo {
    private static final String demoFileDirStr = "E:\\gitRepo\\personalRepo\\someDemoPengYang\\somedemo\\demofiles";

    public static void outputFile(Workbook wb, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\" + fileName + ".xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newHSSF() {
        Workbook wb1 = new HSSFWorkbook();

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\workbook.xls")) {
            wb1.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void newXSSF() {
        Workbook wb2 = new XSSFWorkbook();

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\workbook.xlsx")) {
            wb2.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void newSheet() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet();
        Sheet sheet2 = wb.createSheet("second sheet");

        String safeName = WorkbookUtil.createSafeSheetName("[Johnny's tests*?]");
        Sheet sheet3 = wb.createSheet(safeName);

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\sheet.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newCells() {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();

        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue(createHelper.createRichTextString("This is a string"));
        row.createCell(3).setCellValue(true);

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\cells.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newDateCells() {
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();

        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue(new Date());

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
        cell = row.createCell(1);
        cell.setCellValue(new Date());
        cell.setCellStyle(cellStyle);

        cell = row.createCell(2);
        cell.setCellValue(Calendar.getInstance());
        cell.setCellStyle(cellStyle);

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\datecells.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void diffTypeCells() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue(1.1);
        row.createCell(1).setCellValue(new Date());
        row.createCell(2).setCellValue(Calendar.getInstance());
        row.createCell(3).setCellValue("a string");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellType(CellType.ERROR);

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\difftypecells.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void varAlignOpt() {
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(2);
        row.setHeightInPoints(30);

        createCell(wb, row, 0, HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        createCell(wb, row, 1, HorizontalAlignment.CENTER_SELECTION, VerticalAlignment.BOTTOM);
        createCell(wb, row, 2, HorizontalAlignment.FILL, VerticalAlignment.CENTER);
        createCell(wb, row, 3, HorizontalAlignment.GENERAL, VerticalAlignment.CENTER);
        createCell(wb, row, 4, HorizontalAlignment.JUSTIFY, VerticalAlignment.JUSTIFY);
        createCell(wb, row, 5, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
        createCell(wb, row, 6, HorizontalAlignment.RIGHT, VerticalAlignment.TOP);

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\hssf-align.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createCell(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign) {
        Cell cell = row.createCell(column);
        cell.setCellValue("Align It");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }

    public void workingWithBorders() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);

        Cell cell = row.createCell(1);
        cell.setCellValue(4);

        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\hssf-borders.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fillsAndColors() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);

        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        style.setFillPattern(FillPatternType.BIG_SPOTS);
        Cell cell = row.createCell(1);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell = row.createCell(2);
        cell.setCellValue("X");
        cell.setCellStyle(style);

        cell = row.createCell(0);
        cell.setCellValue("X");

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\fills-colors.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeCells() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("This is a test of merging");

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\mergeCells.xls")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void workingWithFonts() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow(1);

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 24);
        font.setFontName("Courier New");
        font.setItalic(true);
        font.setStrikeout(true);

        CellStyle style = wb.createCellStyle();
        style.setFont(font);

        Cell cell = row.createCell(1);
        cell.setCellValue("This is a test of fonts");
        cell.setCellStyle(style);

        String fileName = "workingWithFonts";
        outputFile(wb, fileName);
    }

    public void customColorsHSSF() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Default Palette");

        HSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIME.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());
        style.setFont(font);

        cell.setCellStyle(style);

        String fileName = "default_palette";
        outputFile(wb, fileName);

        cell.setCellValue("Modified Palette");

        HSSFPalette palette = wb.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.RED.getIndex(), (byte) 153, (byte) 0, (byte) 0);
        palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.LIME.getIndex(), (byte) 255, (byte) 204, (byte) 102);

        fileName = "modified_palette";
        outputFile(wb, fileName);
    }

    public void customColorsXSSF() {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("custom XSSF colors");

        XSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 0, 128)));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        try (FileOutputStream fileOut = new FileOutputStream(demoFileDirStr + "\\xssf_colors.xlsx")) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useNewlinesInCells() {
        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(2);
        cell.setCellValue("Use \n with word wrap on to create a new line");

        CellStyle cs = wb.createCellStyle();
        cs.setWrapText(true);
        cell.setCellStyle(cs);

        row.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));
        sheet.autoSizeColumn(2);

        String fileName = "newlines";
        outputFile(wb, fileName);
    }


    public void dataFormats() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        CellStyle style;
        DataFormat format = wb.createDataFormat();
        Row row;
        Cell cell;
        int rowNum = 0;
        int colNum = 0;

        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(11111111111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("0.0"));
        cell.setCellStyle(style);

        row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum);
        cell.setCellValue(11111111111.25);
        style = wb.createCellStyle();
        style.setDataFormat(format.getFormat("#,##0.0000"));
        cell.setCellStyle(style);

        //test fit sheet
        PrintSetup ps = sheet.getPrintSetup();

        sheet.setAutobreaks(true);

        ps.setFitHeight((short) 1);
        ps.setFitWidth((short) 1);

        String fileName = "data_formats";
        outputFile(wb, fileName);
    }

    public void fitSheet() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        PrintSetup ps = sheet.getPrintSetup();

        sheet.setAutobreaks(true);

        ps.setFitHeight((short) 1);
        ps.setFitWidth((short) 1);

        String fileName = "fit_sheet";
        outputFile(wb, fileName);
    }

    public void setPageNumbers() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        Footer footer = sheet.getFooter();

        footer.setRight("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());

        String fileName = "page_numbers";
        outputFile(wb, fileName);
    }

    public void dropDownListsHSSF() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Data Validation");

        CellRangeAddressList addressList = new CellRangeAddressList(
                0, 65535, 0, 0);
        DVConstraint dvConstraint = DVConstraint.createExplicitListConstraint(
                new String[]{"10", "20", "30"});
        DataValidation dataValidation = new HSSFDataValidation
                (addressList, dvConstraint);
        dataValidation.setSuppressDropDownArrow(false);
        sheet.addValidationData(dataValidation);

        String fileName = "dropdown_lists";
        outputFile(wb, fileName);
    }

    public void choiceCell() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("format sheet");
        Sheet sheet1 = wb.createSheet("sheet1");

        Row row = sheet.createRow(1);

        Cell cell = row.createCell(1);
        cell.setCellValue(4);

        sheet1.setSelected(true);

        String fileName = "sheet_selected";
        outputFile(wb, fileName);
    }


}
