import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.util.Arrays;
import java.io.*;



public class ParseFile {
    @SuppressWarnings("resource")
    Translate translateRequest;
    CSVReader reader;
    String csv;
    ParseFile(String readFrom, String writeTo) throws Exception{
        reader = new CSVReader(new FileReader(readFrom));
        csv = writeTo;
    }

        public void parseAndWriteToFile(ParseFile parseFile) throws IOException, CsvValidationException {
        int count = 1;
            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(csv),
                            ';',
                            CSVWriter.NO_QUOTE_CHARACTER,
                            '#',
                            CSVWriter.DEFAULT_LINE_END);
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    try {
                        String[] changeFileString = Arrays.toString(nextLine).split(";");


                        //This step describe last elements from array and we enter their and translate with help class Translate.
                        for (int i = changeFileString.length; i > changeFileString.length - 1; i--) {
                            translateRequest = new Translate();
                            String translate = translateRequest.Post(changeFileString[changeFileString.length - 1].replaceFirst("\"","").replaceFirst("]",""));

                            //Clear ukr language

                            //System.out.println(Arrays.toString(changeFileString));
                            String ukr =  "\"" + translate.substring(76, translate.length()-15) + "\"";


                            String[]toWrite = Arrays.toString(changeFileString).replace(changeFileString[changeFileString.length - 1], ukr).split(",");

                            //System.out.println(count +"  ----  " + Arrays.toString(toWrite));


                            String conc = "";

                            for (String s:
                                    toWrite) {
                                conc += s.concat(";").trim();

                            }

                           StringBuffer stringBuffer = new StringBuffer(conc);
                            stringBuffer.delete(0,2);
                            stringBuffer.delete(stringBuffer.length() - 2, stringBuffer.length());

                            writer.writeNext(stringBuffer.toString().split(";"));

                            System.out.println(count +"  ----  " + stringBuffer);

                            count++;

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
            reader.close();
            writer.close();

        }

    }
