package br.imd.ufrn.sistema.csv;

import org.apache.commons.csv.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSV {

  public static void write(String filename, Writeable writeable) {
    try (CSVPrinter printer = new CSVPrinter(new FileWriter(filename), CSVFormat.EXCEL)) {
      for (String[] row: writeable.getRows()){
        printer.printRecord((Object[]) row);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void read(String filename, Readable readable) throws IOException {
    File file = new File(filename);
    CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL.withHeader(readable.getHeader()));
    for (CSVRecord csvRecord : parser) {
     readable.add(csvRecord.toMap());
    }
  }

}
