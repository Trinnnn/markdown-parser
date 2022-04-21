import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {

    @Test
    public void parselink() throws IOException {
        Path fileName = Path.of("/Users/trinitygao/Documents/GitHub/markdown-parser/test-file.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals("check if output of parse is correct", List.of("https://something.com", "some-thing.html"), links);
        
    }

    @Test
    public void parseWithImageLink() throws IOException{
        Path fileName = Path.of("/Users/trinitygao/Documents/GitHub/markdown-parser/test-file3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(List.of("https://www.google.com/", "https://www.youtube.com/"), links);

    }

    @Test
    public void parseWithFormatLink() throws IOException {
        Path fileName = Path.of("/Users/trinitygao/Documents/GitHub/markdown-parser/test-file2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(List.of("https://www.google.com/", "https://www.youtube.com/"), links);
    }

    @Test
    public void parseWithEmptyFile() throws IOException {
        Path fileName = Path.of("/Users/trinitygao/Documents/GitHub/markdown-parser/test-file1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(List.of(), links);

    }
}
