//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            if (markdown.indexOf("(", currentIndex) == -1 || markdown.indexOf(")", currentIndex) == -1 || 
            markdown.indexOf("[", currentIndex) == -1 || markdown.indexOf("]", currentIndex) == -1) {
                break;
            }

            int openBracket = markdown.indexOf("[", currentIndex);

            if (openBracket >= 1 && (markdown.charAt(openBracket - 1) == '!' || markdown.charAt(openBracket - 1) == '`')) {

                int closeBracket = markdown.indexOf("]", openBracket);
                int openParen = markdown.indexOf("(", closeBracket);
                int closeParen = markdown.indexOf(")", openParen);

                currentIndex = closeParen + 1;
            }
            else {
                int closeBracket = markdown.indexOf("]", openBracket);
                int openParen = markdown.indexOf("(", closeBracket);
                int closeParen = markdown.indexOf(")", openParen);

                if (markdown.indexOf("\n", openBracket) == -1 || 
                markdown.indexOf("\n", openBracket) >= closeParen ) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                    currentIndex = closeParen + 1;
                }
                else {
                    currentIndex = markdown.indexOf("\n", openBracket);
                }
            }
        }

        if (toReturn.size() == 0) {
            System.out.println("The file has no links. Return empty Array: ");
            return toReturn;
        }

        return toReturn;
        
    }

    public static void main(String[] args) throws IOException {
        if (args[0].equals("test-files/")) {
            File dir = new File("test-files/");
            if (dir.isDirectory()) {
                File[] directoryList = dir.listFiles();
                for (File child: directoryList) {
                    Path childpath = child.toPath();
                    String contents = Files.readString(childpath);
                    ArrayList<String> links = getLinks(contents);
                    System.out.println(links);
                }
            }
        }
        else {
            Path fileName = Path.of(args[0]);
            String contents = Files.readString(fileName);
            ArrayList<String> links = getLinks(contents);
            System.out.println(links);
        }
    }
}