package jiho.marvel01.controller;

import jiho.marvel01.domain.Entity.ExcelEntity;
import jiho.marvel01.dto.UserDto;
import jiho.marvel01.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
public class ExcelController {

      private UserService userService;


  @GetMapping("/excel")
  public String main() { // 1
    return "excel";
  }


  @PostMapping("/excel/read")
  public String readExcel(@RequestParam("file") MultipartFile file, Model model, UserDto userDto)
      throws IOException { // 2

    List<ExcelEntity> dataList = new ArrayList<>();

    String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // 3

    if (!extension.equals("xlsx") && !extension.equals("xls")) {
      throw new IOException("엑셀파일만 업로드 해주세요.");
    }

    Workbook workbook = null;

    if (extension.equals("xlsx")) {
      workbook = new XSSFWorkbook(file.getInputStream());
    } else if (extension.equals("xls")) {
      workbook = new HSSFWorkbook(file.getInputStream());
    }

    Sheet worksheet = workbook.getSheetAt(0);

//    int numOfRows = worksheet.getPhysicalNumberOfRows();
//    int numOfCells = 0;
//
//    Row row = null;
//    Cell cell = null;
//
//    String cellName = "";
//
//    Map<String, String>map = null;
//    List<Map<String,String>>result = new ArrayList<Map<String,String>>();

    for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 42

      Row row = worksheet.getRow(i);
      ExcelEntity data = new ExcelEntity();

      data.setNum((int) row.getCell(0).getNumericCellValue());
      data.setEmail(row.getCell(1).getStringCellValue());
      data.setName(row.getCell(2).getStringCellValue());
      data.setCorp(row.getCell(3).getStringCellValue());
      data.setDepartment(row.getCell(4).getStringCellValue());
      data.setRanks(row.getCell(5).getStringCellValue());
      data.setCodes(row.getCell(6).getStringCellValue());
      data.setStatus(row.getCell(7).getStringCellValue());
      data.setI_group((int) row.getCell(8).getNumericCellValue());

      dataList.add(data);
    }

    model.addAttribute("datas", dataList); // 5

    return "/page/excelList";

  }
}
