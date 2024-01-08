

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.nio.file.Path;
public class Main {
    public static void main(String[] args) throws Exception {
        CharStream is = CharStreams.fromPath(Path.of("src\\main\\java\\examples\\invalid.txt"));

        JavaCodeLexer lexer = new JavaCodeLexer(is);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaCodeParser parser = new JavaCodeParser(tokens);
        ParseTree tree = parser.code();
        ParseTreeWalker walker = new ParseTreeWalker();
        JavaCodeFormatListener formatListener = new JavaCodeFormatListener();
        walker.walk(formatListener, tree);
        System.out.println(formatListener.getTree().getResult());
    }
}
