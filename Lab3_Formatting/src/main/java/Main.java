import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream text = CharStreams.fromPath(Path.of("src\\main\\java\\examples\\arraysGet.txt"));

        JavaCodeFormatListener formatListener = new JavaCodeFormatListener();
        (new ParseTreeWalker()).walk(formatListener,
                (new JavaCodeParser(new CommonTokenStream(new JavaCodeLexer(text)))).code());
        System.out.println(formatListener.getTree().getResult());
    }
}
