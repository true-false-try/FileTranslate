public class wayFiles {
    public static void main(String[] args) throws Exception {
        ParseFile parseFile = new ParseFile(
                "C:\\Users\\user\\IdeaProjects\\FileTranslate\\src\\main\\java\\files\\QER_Translation.csv",
                "C:\\Users\\user\\IdeaProjects\\FileTranslate\\src\\main\\java\\outputFiles\\Translate.csv"
        );

        parseFile.parseAndWriteToFile(parseFile);
    }
}
