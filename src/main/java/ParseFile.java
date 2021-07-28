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
                            CSVWriter.NO_QUOTE_CHARACTER,
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
                            String ukr =  "\"" + translate.substring(76, translate.length()-15) + "\"";
                            //Part fix to exception with incorrect translate
                            if (ukr.equals("\"\": \"Your Client (working) ---> Gateway (working) ---> API \"")){
                                translate = translateRequest.Post(changeFileString[changeFileString.length - 1].replaceFirst("\"","").replaceFirst("]","").replace("\"","'"));
                                ukr =  "\"" + translate.substring(76, translate.length()-15).replace("'","\"") + "\"";
                            }

                            String conc1 = "";
                            for (String s:
                                    changeFileString) {
                                conc1 += s.concat(";").trim();
                            }
                            String[]toWrite = conc1.replace(changeFileString[changeFileString.length - 1], ukr).split(";");

                            String conc = "";

                            for (String s:
                                    toWrite) {
                                conc += s.concat(";").trim();

                            }

                            StringBuilder stringBuilder = new StringBuilder(conc);
                            stringBuilder.delete(0,1);
                            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

                            String[] outString = stringBuilder.toString().split(";\"(?=\"([А-Я]))|;");

                            writer.writeNext(outString);
                            System.out.println(count + "    ----    " + Arrays.toString(outString));

                            count++;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
            System.out.println("Translate finished");
            reader.close();
            writer.close();

        }

    }
