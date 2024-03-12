// Generated from C:/Users/chura/Х#й#я/MT/Lab3_Formatting/src/main/java/JavaCode.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JavaCodeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JavaCodeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode(JavaCodeParser.CodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#package}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackage(JavaCodeParser.PackageContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImports(JavaCodeParser.ImportsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#imprt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImprt(JavaCodeParser.ImprtContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#programmEntities}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgrammEntities(JavaCodeParser.ProgrammEntitiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#codeEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeEntry(JavaCodeParser.CodeEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(JavaCodeParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#declarationBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationBegin(JavaCodeParser.DeclarationBeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#declarationPrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationPrefix(JavaCodeParser.DeclarationPrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#declarationRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationRest(JavaCodeParser.DeclarationRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#codeBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBody(JavaCodeParser.CodeBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#innerCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerCode(JavaCodeParser.InnerCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#executableLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutableLine(JavaCodeParser.ExecutableLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#executableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecutableStatement(JavaCodeParser.ExecutableStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#statementDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDeclaration(JavaCodeParser.StatementDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#statementDeclaraitonRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDeclaraitonRest(JavaCodeParser.StatementDeclaraitonRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#statementDeclarationScope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDeclarationScope(JavaCodeParser.StatementDeclarationScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#statementDeclarationInScope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDeclarationInScope(JavaCodeParser.StatementDeclarationInScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#statementLineEnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementLineEnd(JavaCodeParser.StatementLineEndContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(JavaCodeParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#switchDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchDeclare(JavaCodeParser.SwitchDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#switchBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBody(JavaCodeParser.SwitchBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#switchCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCode(JavaCodeParser.SwitchCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#case}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCase(JavaCodeParser.CaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#default}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefault(JavaCodeParser.DefaultContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#funcDeclarationArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclarationArgs(JavaCodeParser.FuncDeclarationArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#funcDeclarationArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclarationArg(JavaCodeParser.FuncDeclarationArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#argTypeToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgTypeToken(JavaCodeParser.ArgTypeTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#vararg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVararg(JavaCodeParser.VarargContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#declareValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareValue(JavaCodeParser.DeclareValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(JavaCodeParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#unoValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnoValue(JavaCodeParser.UnoValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#unoValueKeyWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnoValueKeyWord(JavaCodeParser.UnoValueKeyWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#unoValueGet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnoValueGet(JavaCodeParser.UnoValueGetContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#justValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJustValue(JavaCodeParser.JustValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#funcToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncToken(JavaCodeParser.FuncTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#funcCalledArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCalledArgs(JavaCodeParser.FuncCalledArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#typeToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeToken(JavaCodeParser.TypeTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#varToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarToken(JavaCodeParser.VarTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#keyWordAndTokens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyWordAndTokens(JavaCodeParser.KeyWordAndTokensContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#typeTokensComma}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeTokensComma(JavaCodeParser.TypeTokensCommaContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#keyWord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyWord(JavaCodeParser.KeyWordContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#unoOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnoOper(JavaCodeParser.UnoOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaCodeParser#oper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOper(JavaCodeParser.OperContext ctx);
}