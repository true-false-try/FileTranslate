import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.util.Arrays;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;


public class ParseFile {

    @SuppressWarnings("resource")

    public static void main(String[] args) throws IOException, CsvValidationException {
        Translate translateRequest;
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\user\\IdeaProjects\\FileTranslate\\src\\main\\java\\files\\QER_Translation.csv"));
        String csv = "C:\\Users\\user\\IdeaProjects\\FileTranslate\\src\\main\\java\\outputFiles\\Translate.csv";
        ArrayList<String[]> list = new ArrayList<>();
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {

                //Verifying the read data here
                list.add(Arrays.toString(nextLine).split(";"));

                try {
                    String[] changeFileString = Arrays.toString(nextLine).split(";");
                    for (int i = changeFileString.length; i > changeFileString.length - 1; i--) {
                        translateRequest = new Translate();
                        String request = translateRequest.Post(changeFileString[changeFileString.length - 1].replaceFirst("\"","").replaceFirst("]",""));


                        //System.out.println(changeFileString[changeFileString.length - 1].replaceFirst("\"","").replaceFirst("]",""));

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

    }
}
