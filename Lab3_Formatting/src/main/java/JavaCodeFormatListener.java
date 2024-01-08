

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
        join("\n\n");
    }

    @Override
    public void exitPackage(JavaCodeParser.PackageContext ctx){
        addToResult(tree.getChildren().get(0).getResult() + SPACE);
        join(tree.getChildren().stream().skip(1));
    }

    @Override
    public void exitImports(JavaCodeParser.ImportsContext ctx) {
        join("\n");
    }

    @Override
    public void exitImprt(JavaCodeParser.ImprtContext ctx) {
        addToResult(tree.getChildren().get(0).getResult() + SPACE);
        join(tree.getChildren().stream().skip(1));
    }

    @Override
    public  void exitProgrammEntities(JavaCodeParser.ProgrammEntitiesContext ctx) {
        join("\n\n" + mkOffset());
    }

    @Override
    public void exitCodeEntry(JavaCodeParser.CodeEntryContext ctx) {
        join(SPACE);
    }

    @Override
    public void exitDeclaration(JavaCodeParser.DeclarationContext ctx) {
        join(SPACE);
    }

    @Override
    public void exitDeclarationPrefix(JavaCodeParser.DeclarationPrefixContext ctx) {
        join(SPACE);
    }

    @Override
    public void exitFuncDeclarationArg(JavaCodeParser.FuncDeclarationArgContext ctx) {
        join(SPACE);
    }

    @Override
    public void enterInnerCode(JavaCodeParser.InnerCodeContext ctx) {
        level++;
    }

    @Override
    public void exitInnerCode(JavaCodeParser.InnerCodeContext ctx) {
        String offset = "\n" + mkOffset();
        join(offset,offset);
        level --;
    }

    @Override
    public void exitExecutableStatement(JavaCodeParser.ExecutableStatementContext ctx) {
        join(SPACE);
    }

    @Override
    public void exitStatementDeclaration(JavaCodeParser.StatementDeclarationContext ctx) {
        join(SPACE);
    }

    @Override
    public void exitStatementDeclaraitonRest(JavaCodeParser.StatementDeclaraitonRestContext ctx) {
        join(SPACE);
    }

    @Override
    public void exitStatementDeclarationInScope(JavaCodeParser.StatementDeclarationInScopeContext ctx) {
        for(Tree child: tree.getChildren()) {
            addToResult(child.getResult());
            if(";".equals(child.getResult())) {
                addToResult(SPACE);
            }
        }
    }

    @Override
    public void exitSwitchStatement(JavaCodeParser.SwitchStatementContext ctx) {
        join(SPACE);
    }

    @Override
    public void enterSwitchCode(JavaCodeParser.SwitchCodeContext ctx) {
        level++;
    }

    @Override
    public void exitSwitchCode(JavaCodeParser.SwitchCodeContext ctx){
        String offset = "\n" + mkOffset();
        join(offset,offset);
        level--;
    }

    @Override
    public void exitCase(JavaCodeParser.CaseContext ctx) {
        addToResult(tree.getChildren().get(0).getResult() + SPACE);
        join(tree.getChildren().stream().skip(1));
    }

    @Override
    public void exitValue(JavaCodeParser.ValueContext ctx) {
        join(" ");
    }

    @Override
    public void exitDeclareValue(JavaCodeParser.DeclareValueContext ctx) {
        if(ctx.children.size() > 1) {
            addToResult(tree.getChildren().get(0).getResult() + SPACE);
            join(tree.getChildren().stream().skip(1));
        }
    }

    @Override
    public void exitUnoValueKeyWord(JavaCodeParser.UnoValueKeyWordContext ctx) {
        join(" ");
    }

    @Override
    public void exitKeyWordAndTokens(JavaCodeParser.KeyWordAndTokensContext ctx) {
        join(" ");
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        tree = new Tree().buildFromAncestor(tree);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        if(tree.getResult()==null) {
            join();
        }
        tree = tree.getAncestor();
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        switch (node.toString()) {
            case "<EOF>" -> {
            }
            case "," -> addTerminal(","+SPACE);
            case "}" -> addTerminal("\n"+mkOffset() + "}");
            default -> addTerminal(node.toString());
        }
    }







    private void join() {
        join("");
    }

    private void join(String delimiter) {
        join(delimiter, "");
    }

    private void join(String delimiter, String prefix) {
        join(tree.getChildren().stream(), delimiter, prefix);
    }

    private void join(Stream<Tree> treeStream) {
        join(treeStream, "", "");
    }
    private void join(Stream<Tree> treeStream, String delimiter, String prefix) {
        String res = prefix + treeStream.map(Tree::getResult).collect(Collectors.joining(delimiter));
        if(tree.getResult()==null) {
            tree.setResult(res);
        } else {
            addToResult(res);
        }
    }

    private void addTerminal(String s) {
        new Tree(s).buildFromAncestor(tree);
    }

    private void addToResult(String s) {
        tree.setResult((tree.getResult()==null?"": tree.getResult()) + s);
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

        public void setResult(String result) {
            this.result = result;
        }

        public List<Tree> getChildren() {
            return children;
        }

        public Tree getAncestor() {
            return ancestor;
        }

    }
}

