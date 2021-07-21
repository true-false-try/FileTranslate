import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.util.Arrays;
import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;


public class ParseFile {
    /*public static void main(String[] args) throws IOException {
        FileInputStream fileStream = new FileInputStream("C:\\Users\\user\\IdeaProjects\\FileTranslate\\src\\main\\java\\files\\QER_Translation.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
        String str;
        ArrayList<String[]> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile("^[а-яА-я]");


        while ((str = reader.readLine()) != null){
            lines.add(str.split(",|;"));

            System.out.println(Arrays.deepToString(lines.toArray())+"\n");
                }
            }



        }


        *//*for (Object s:
             lines) {
            System.out.println(s.toString());
        }*/
    @SuppressWarnings("resource")


    public static void main(String[] args) throws IOException, CsvValidationException {
        Translate translateRequest = new Translate();
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\user\\IdeaProjects\\FileTranslate\\src\\main\\java\\files\\QER_Translation.csv"));
        ArrayList<String[]> list = new ArrayList<>();



        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                list.add(Arrays.toString(nextLine).split(";"));
                try {
                    String response = translateRequest.Post(Arrays.toString(nextLine));
                    System.out.println(Translate.prettify(response));
                } catch (Exception e) {
                    System.out.println(e);
                }
                /*System.out.println(Arrays.deepToString(list.toArray()));*/
            }
        }

    }
}
