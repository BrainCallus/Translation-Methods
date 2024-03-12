

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JavaCodeFormatListener extends JavaCodeBaseListener {

    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private Tree tree;

    private int level = 0;

    public Tree getTree() {
        return tree;
    }

    @Override
    public void exitCode(JavaCodeParser.CodeContext ctx) {
        mkString("\n\n");
    }

    @Override
    public void exitPackage(JavaCodeParser.PackageContext ctx) {
        addSpaceAfterFirstChild();
    }

    @Override
    public void exitImports(JavaCodeParser.ImportsContext ctx) {
        mkString("\n");
    }

    @Override
    public void exitImprt(JavaCodeParser.ImprtContext ctx) {
        addSpaceAfterFirstChild();
    }

    @Override
    public void exitProgrammEntities(JavaCodeParser.ProgrammEntitiesContext ctx) {
        mkString("\n\n" + mkOffset());
    }

    @Override
    public void exitCodeEntry(JavaCodeParser.CodeEntryContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitDeclaration(JavaCodeParser.DeclarationContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitDeclarationPrefix(JavaCodeParser.DeclarationPrefixContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitFuncDeclarationArg(JavaCodeParser.FuncDeclarationArgContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void enterInnerCode(JavaCodeParser.InnerCodeContext ctx) {
        level++;
    }

    @Override
    public void exitInnerCode(JavaCodeParser.InnerCodeContext ctx) {
        String offset = "\n" + mkOffset();
        mkString(offset, offset);
        level--;
    }

    @Override
    public void exitExecutableStatement(JavaCodeParser.ExecutableStatementContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitStatementDeclaration(JavaCodeParser.StatementDeclarationContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitStatementDeclaraitonRest(JavaCodeParser.StatementDeclaraitonRestContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitStatementDeclarationInScope(JavaCodeParser.StatementDeclarationInScopeContext ctx) {
        for (Tree child : tree.getChildren()) {
            tree.addToResult(child.getResult());
            if (";".equals(child.getResult())) {
                tree.addToResult(SPACE);
            }
        }
    }

    @Override
    public void exitSwitchStatement(JavaCodeParser.SwitchStatementContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void enterSwitchCode(JavaCodeParser.SwitchCodeContext ctx) {
        level++;
    }

    @Override
    public void exitSwitchCode(JavaCodeParser.SwitchCodeContext ctx) {
        String offset = "\n" + mkOffset();
        mkString(offset, offset);
        level--;
    }

    @Override
    public void exitCase(JavaCodeParser.CaseContext ctx) {
        addSpaceAfterFirstChild();
    }

    @Override
    public void exitValue(JavaCodeParser.ValueContext ctx) {
        mkString(SPACE);
    }

    @Override
    public void exitDeclareValue(JavaCodeParser.DeclareValueContext ctx) {
        if (ctx.children.size() > 1) {
            addSpaceAfterFirstChild();
        }
    }

    @Override
    public void exitUnoValueKeyWord(JavaCodeParser.UnoValueKeyWordContext ctx) {
        mkString(" ");
    }

    @Override
    public void exitKeyWordAndTokens(JavaCodeParser.KeyWordAndTokensContext ctx) {
        mkString(" ");
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        tree = new Tree().buildFromAncestor(tree);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        if (tree.getResult() == null) {
            mkString();
        }
        tree = tree.getAncestor();
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        switch (node.toString()) {
            case "<EOF>" -> {
            }
            case "," -> addTerminal("," + SPACE);
            case "}" -> addTerminal("\n" + mkOffset() + "}");
            default -> addTerminal(node.toString());
        }
    }


    private void mkString() {
        mkString("");
    }

    private void mkString(String delimiter) {
        mkString(delimiter, "");
    }

    private void mkString(String delimiter, String prefix) {
        mkString(tree.getChildren().stream(), delimiter, prefix);
    }

    private void mkString(Stream<Tree> treeStream) {
        mkString(treeStream, "", "");
    }

    private void mkString(Stream<Tree> treeStream, String delimiter, String prefix) {
        tree.addToResult(prefix + treeStream.map(Tree::getResult).collect(Collectors.joining(delimiter)));
    }

    private void addSpaceAfterFirstChild() {
        tree.addToResult(tree.getChildren().get(0).getResult() + SPACE);
        mkString(tree.getChildren().stream().skip(1));
    }
    private void addTerminal(String s) {
        new Tree(s).buildFromAncestor(tree);
    }

    private String mkOffset() {
        return TAB.repeat(level);
    }


    public static class Tree {
        private String result;
        private final List<Tree> children;
        private Tree ancestor;

        public Tree() {
            children = new ArrayList<>();
            ancestor = null;
        }

        public Tree(String result) {
            this();
            this.result = result;
        }

        public Tree buildFromAncestor(Tree ancestor) {
            if (ancestor == null) {
                this.ancestor = this;

            } else {
                this.ancestor = ancestor;
                ancestor.getChildren().add(this);
            }
            return this;
        }

        public String getResult() {
            return result;
        }

        public void addToResult(String s) {
            result = (result == null ? "" : result) + s;
        }

        public List<Tree> getChildren() {
            return children;
        }

        public Tree getAncestor() {
            return ancestor;
        }
    }
}

