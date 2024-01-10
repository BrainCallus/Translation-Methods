// Generated from C:/Users/chura/Х#й#я/MT/Lab3_Formatting/src/main/java/JavaCode.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaCodeParser}.
 */
public interface JavaCodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(JavaCodeParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(JavaCodeParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#package}.
	 * @param ctx the parse tree
	 */
	void enterPackage(JavaCodeParser.PackageContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#package}.
	 * @param ctx the parse tree
	 */
	void exitPackage(JavaCodeParser.PackageContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#imports}.
	 * @param ctx the parse tree
	 */
	void enterImports(JavaCodeParser.ImportsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#imports}.
	 * @param ctx the parse tree
	 */
	void exitImports(JavaCodeParser.ImportsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#imprt}.
	 * @param ctx the parse tree
	 */
	void enterImprt(JavaCodeParser.ImprtContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#imprt}.
	 * @param ctx the parse tree
	 */
	void exitImprt(JavaCodeParser.ImprtContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#programmEntities}.
	 * @param ctx the parse tree
	 */
	void enterProgrammEntities(JavaCodeParser.ProgrammEntitiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#programmEntities}.
	 * @param ctx the parse tree
	 */
	void exitProgrammEntities(JavaCodeParser.ProgrammEntitiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#codeEntry}.
	 * @param ctx the parse tree
	 */
	void enterCodeEntry(JavaCodeParser.CodeEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#codeEntry}.
	 * @param ctx the parse tree
	 */
	void exitCodeEntry(JavaCodeParser.CodeEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(JavaCodeParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(JavaCodeParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#declarationBegin}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationBegin(JavaCodeParser.DeclarationBeginContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#declarationBegin}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationBegin(JavaCodeParser.DeclarationBeginContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#declarationPrefix}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationPrefix(JavaCodeParser.DeclarationPrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#declarationPrefix}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationPrefix(JavaCodeParser.DeclarationPrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#declarationRest}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationRest(JavaCodeParser.DeclarationRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#declarationRest}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationRest(JavaCodeParser.DeclarationRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#codeBody}.
	 * @param ctx the parse tree
	 */
	void enterCodeBody(JavaCodeParser.CodeBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#codeBody}.
	 * @param ctx the parse tree
	 */
	void exitCodeBody(JavaCodeParser.CodeBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#innerCode}.
	 * @param ctx the parse tree
	 */
	void enterInnerCode(JavaCodeParser.InnerCodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#innerCode}.
	 * @param ctx the parse tree
	 */
	void exitInnerCode(JavaCodeParser.InnerCodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#executableLine}.
	 * @param ctx the parse tree
	 */
	void enterExecutableLine(JavaCodeParser.ExecutableLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#executableLine}.
	 * @param ctx the parse tree
	 */
	void exitExecutableLine(JavaCodeParser.ExecutableLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#executableStatement}.
	 * @param ctx the parse tree
	 */
	void enterExecutableStatement(JavaCodeParser.ExecutableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#executableStatement}.
	 * @param ctx the parse tree
	 */
	void exitExecutableStatement(JavaCodeParser.ExecutableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#statementDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStatementDeclaration(JavaCodeParser.StatementDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#statementDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStatementDeclaration(JavaCodeParser.StatementDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#statementDeclaraitonRest}.
	 * @param ctx the parse tree
	 */
	void enterStatementDeclaraitonRest(JavaCodeParser.StatementDeclaraitonRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#statementDeclaraitonRest}.
	 * @param ctx the parse tree
	 */
	void exitStatementDeclaraitonRest(JavaCodeParser.StatementDeclaraitonRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#statementDeclarationScope}.
	 * @param ctx the parse tree
	 */
	void enterStatementDeclarationScope(JavaCodeParser.StatementDeclarationScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#statementDeclarationScope}.
	 * @param ctx the parse tree
	 */
	void exitStatementDeclarationScope(JavaCodeParser.StatementDeclarationScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#statementDeclarationInScope}.
	 * @param ctx the parse tree
	 */
	void enterStatementDeclarationInScope(JavaCodeParser.StatementDeclarationInScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#statementDeclarationInScope}.
	 * @param ctx the parse tree
	 */
	void exitStatementDeclarationInScope(JavaCodeParser.StatementDeclarationInScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#statementLineEnd}.
	 * @param ctx the parse tree
	 */
	void enterStatementLineEnd(JavaCodeParser.StatementLineEndContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#statementLineEnd}.
	 * @param ctx the parse tree
	 */
	void exitStatementLineEnd(JavaCodeParser.StatementLineEndContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(JavaCodeParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(JavaCodeParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#switchDeclare}.
	 * @param ctx the parse tree
	 */
	void enterSwitchDeclare(JavaCodeParser.SwitchDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#switchDeclare}.
	 * @param ctx the parse tree
	 */
	void exitSwitchDeclare(JavaCodeParser.SwitchDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#switchBody}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBody(JavaCodeParser.SwitchBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#switchBody}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBody(JavaCodeParser.SwitchBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#switchCode}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCode(JavaCodeParser.SwitchCodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#switchCode}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCode(JavaCodeParser.SwitchCodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#case}.
	 * @param ctx the parse tree
	 */
	void enterCase(JavaCodeParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#case}.
	 * @param ctx the parse tree
	 */
	void exitCase(JavaCodeParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#default}.
	 * @param ctx the parse tree
	 */
	void enterDefault(JavaCodeParser.DefaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#default}.
	 * @param ctx the parse tree
	 */
	void exitDefault(JavaCodeParser.DefaultContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#funcDeclarationArgs}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclarationArgs(JavaCodeParser.FuncDeclarationArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#funcDeclarationArgs}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclarationArgs(JavaCodeParser.FuncDeclarationArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#funcDeclarationArg}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclarationArg(JavaCodeParser.FuncDeclarationArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#funcDeclarationArg}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclarationArg(JavaCodeParser.FuncDeclarationArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#argTypeToken}.
	 * @param ctx the parse tree
	 */
	void enterArgTypeToken(JavaCodeParser.ArgTypeTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#argTypeToken}.
	 * @param ctx the parse tree
	 */
	void exitArgTypeToken(JavaCodeParser.ArgTypeTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#vararg}.
	 * @param ctx the parse tree
	 */
	void enterVararg(JavaCodeParser.VarargContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#vararg}.
	 * @param ctx the parse tree
	 */
	void exitVararg(JavaCodeParser.VarargContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#declareValue}.
	 * @param ctx the parse tree
	 */
	void enterDeclareValue(JavaCodeParser.DeclareValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#declareValue}.
	 * @param ctx the parse tree
	 */
	void exitDeclareValue(JavaCodeParser.DeclareValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(JavaCodeParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(JavaCodeParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#unoValue}.
	 * @param ctx the parse tree
	 */
	void enterUnoValue(JavaCodeParser.UnoValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#unoValue}.
	 * @param ctx the parse tree
	 */
	void exitUnoValue(JavaCodeParser.UnoValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#unoValueKeyWord}.
	 * @param ctx the parse tree
	 */
	void enterUnoValueKeyWord(JavaCodeParser.UnoValueKeyWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#unoValueKeyWord}.
	 * @param ctx the parse tree
	 */
	void exitUnoValueKeyWord(JavaCodeParser.UnoValueKeyWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#unoValueGet}.
	 * @param ctx the parse tree
	 */
	void enterUnoValueGet(JavaCodeParser.UnoValueGetContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#unoValueGet}.
	 * @param ctx the parse tree
	 */
	void exitUnoValueGet(JavaCodeParser.UnoValueGetContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#justValue}.
	 * @param ctx the parse tree
	 */
	void enterJustValue(JavaCodeParser.JustValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#justValue}.
	 * @param ctx the parse tree
	 */
	void exitJustValue(JavaCodeParser.JustValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#funcToken}.
	 * @param ctx the parse tree
	 */
	void enterFuncToken(JavaCodeParser.FuncTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#funcToken}.
	 * @param ctx the parse tree
	 */
	void exitFuncToken(JavaCodeParser.FuncTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#funcCalledArgs}.
	 * @param ctx the parse tree
	 */
	void enterFuncCalledArgs(JavaCodeParser.FuncCalledArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#funcCalledArgs}.
	 * @param ctx the parse tree
	 */
	void exitFuncCalledArgs(JavaCodeParser.FuncCalledArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#generic}.
	 * @param ctx the parse tree
	 */
	void enterGeneric(JavaCodeParser.GenericContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#generic}.
	 * @param ctx the parse tree
	 */
	void exitGeneric(JavaCodeParser.GenericContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#genericToken}.
	 * @param ctx the parse tree
	 */
	void enterGenericToken(JavaCodeParser.GenericTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#genericToken}.
	 * @param ctx the parse tree
	 */
	void exitGenericToken(JavaCodeParser.GenericTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#typeToken}.
	 * @param ctx the parse tree
	 */
	void enterTypeToken(JavaCodeParser.TypeTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#typeToken}.
	 * @param ctx the parse tree
	 */
	void exitTypeToken(JavaCodeParser.TypeTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#varToken}.
	 * @param ctx the parse tree
	 */
	void enterVarToken(JavaCodeParser.VarTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#varToken}.
	 * @param ctx the parse tree
	 */
	void exitVarToken(JavaCodeParser.VarTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#keyWordAndTokens}.
	 * @param ctx the parse tree
	 */
	void enterKeyWordAndTokens(JavaCodeParser.KeyWordAndTokensContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#keyWordAndTokens}.
	 * @param ctx the parse tree
	 */
	void exitKeyWordAndTokens(JavaCodeParser.KeyWordAndTokensContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#typeTokensComma}.
	 * @param ctx the parse tree
	 */
	void enterTypeTokensComma(JavaCodeParser.TypeTokensCommaContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#typeTokensComma}.
	 * @param ctx the parse tree
	 */
	void exitTypeTokensComma(JavaCodeParser.TypeTokensCommaContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#keyWord}.
	 * @param ctx the parse tree
	 */
	void enterKeyWord(JavaCodeParser.KeyWordContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#keyWord}.
	 * @param ctx the parse tree
	 */
	void exitKeyWord(JavaCodeParser.KeyWordContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#unoOper}.
	 * @param ctx the parse tree
	 */
	void enterUnoOper(JavaCodeParser.UnoOperContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#unoOper}.
	 * @param ctx the parse tree
	 */
	void exitUnoOper(JavaCodeParser.UnoOperContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaCodeParser#oper}.
	 * @param ctx the parse tree
	 */
	void enterOper(JavaCodeParser.OperContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaCodeParser#oper}.
	 * @param ctx the parse tree
	 */
	void exitOper(JavaCodeParser.OperContext ctx);
}