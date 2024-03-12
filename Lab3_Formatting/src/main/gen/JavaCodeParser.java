// Generated from C:/Users/chura/Х#й#я/MT/Lab3_Formatting/src/main/java/JavaCode.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class JavaCodeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, KEY_package=11, KEY_import=12, KEY_switch=13, KEY_case=14, KEY_default=15, 
		KEY_STATEMENT=16, KEY_STATEMENT_REST=17, KEYWORD=18, STRING=19, NUMBER=20, 
		NAME=21, Langle=22, Rangle=23, Question=24, Colon=25, Star=26, UNO_OPER=27, 
		OPER=28, Comma=29, SPACE=30;
	public static final int
		RULE_code = 0, RULE_package = 1, RULE_imports = 2, RULE_imprt = 3, RULE_programmEntities = 4, 
		RULE_codeEntry = 5, RULE_declaration = 6, RULE_declarationBegin = 7, RULE_declarationPrefix = 8, 
		RULE_declarationRest = 9, RULE_codeBody = 10, RULE_innerCode = 11, RULE_executableLine = 12, 
		RULE_executableStatement = 13, RULE_statementDeclaration = 14, RULE_statementDeclaraitonRest = 15, 
		RULE_statementDeclarationScope = 16, RULE_statementDeclarationInScope = 17, 
		RULE_statementLineEnd = 18, RULE_switchStatement = 19, RULE_switchDeclare = 20, 
		RULE_switchBody = 21, RULE_switchCode = 22, RULE_case = 23, RULE_default = 24, 
		RULE_funcDeclarationArgs = 25, RULE_funcDeclarationArg = 26, RULE_argTypeToken = 27, 
		RULE_vararg = 28, RULE_declareValue = 29, RULE_value = 30, RULE_unoValue = 31, 
		RULE_unoValueKeyWord = 32, RULE_unoValueGet = 33, RULE_justValue = 34, 
		RULE_funcToken = 35, RULE_funcCalledArgs = 36, RULE_typeToken = 37, RULE_varToken = 38, 
		RULE_keyWordAndTokens = 39, RULE_typeTokensComma = 40, RULE_keyWord = 41, 
		RULE_unoOper = 42, RULE_oper = 43;
	private static String[] makeRuleNames() {
		return new String[] {
			"code", "package", "imports", "imprt", "programmEntities", "codeEntry", 
			"declaration", "declarationBegin", "declarationPrefix", "declarationRest", 
			"codeBody", "innerCode", "executableLine", "executableStatement", "statementDeclaration", 
			"statementDeclaraitonRest", "statementDeclarationScope", "statementDeclarationInScope", 
			"statementLineEnd", "switchStatement", "switchDeclare", "switchBody", 
			"switchCode", "case", "default", "funcDeclarationArgs", "funcDeclarationArg", 
			"argTypeToken", "vararg", "declareValue", "value", "unoValue", "unoValueKeyWord", 
			"unoValueGet", "justValue", "funcToken", "funcCalledArgs", "typeToken", 
			"varToken", "keyWordAndTokens", "typeTokensComma", "keyWord", "unoOper", 
			"oper"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'.'", "'('", "')'", "'{'", "'}'", "'while'", "'...'", "'['", 
			"']'", "'package'", "'import'", "'switch'", "'case'", "'default'", null, 
			null, null, null, null, null, "'<'", "'>'", "'?'", "':'", "'*'", null, 
			null, "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "KEY_package", 
			"KEY_import", "KEY_switch", "KEY_case", "KEY_default", "KEY_STATEMENT", 
			"KEY_STATEMENT_REST", "KEYWORD", "STRING", "NUMBER", "NAME", "Langle", 
			"Rangle", "Question", "Colon", "Star", "UNO_OPER", "OPER", "Comma", "SPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JavaCode.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JavaCodeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CodeContext extends ParserRuleContext {
		public ProgrammEntitiesContext programmEntities() {
			return getRuleContext(ProgrammEntitiesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(JavaCodeParser.EOF, 0); }
		public PackageContext package_() {
			return getRuleContext(PackageContext.class,0);
		}
		public ImportsContext imports() {
			return getRuleContext(ImportsContext.class,0);
		}
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_code);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KEY_package) {
				{
				setState(88);
				package_();
				}
			}

			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KEY_import) {
				{
				setState(91);
				imports();
				}
			}

			setState(94);
			programmEntities();
			setState(95);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageContext extends ParserRuleContext {
		public TerminalNode KEY_package() { return getToken(JavaCodeParser.KEY_package, 0); }
		public TypeTokenContext typeToken() {
			return getRuleContext(TypeTokenContext.class,0);
		}
		public PackageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_package; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterPackage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitPackage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitPackage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageContext package_() throws RecognitionException {
		PackageContext _localctx = new PackageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_package);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(KEY_package);
			setState(98);
			typeToken();
			setState(99);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportsContext extends ParserRuleContext {
		public List<ImprtContext> imprt() {
			return getRuleContexts(ImprtContext.class);
		}
		public ImprtContext imprt(int i) {
			return getRuleContext(ImprtContext.class,i);
		}
		public ImportsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imports; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterImports(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitImports(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitImports(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportsContext imports() throws RecognitionException {
		ImportsContext _localctx = new ImportsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imports);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				imprt();
				}
				}
				setState(104); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==KEY_import );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImprtContext extends ParserRuleContext {
		public TerminalNode KEY_import() { return getToken(JavaCodeParser.KEY_import, 0); }
		public TypeTokenContext typeToken() {
			return getRuleContext(TypeTokenContext.class,0);
		}
		public TerminalNode Star() { return getToken(JavaCodeParser.Star, 0); }
		public ImprtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imprt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterImprt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitImprt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitImprt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImprtContext imprt() throws RecognitionException {
		ImprtContext _localctx = new ImprtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_imprt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(KEY_import);
			setState(107);
			typeToken();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(108);
				match(T__1);
				setState(109);
				match(Star);
				}
			}

			setState(112);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgrammEntitiesContext extends ParserRuleContext {
		public List<CodeEntryContext> codeEntry() {
			return getRuleContexts(CodeEntryContext.class);
		}
		public CodeEntryContext codeEntry(int i) {
			return getRuleContext(CodeEntryContext.class,i);
		}
		public ProgrammEntitiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programmEntities; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterProgrammEntities(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitProgrammEntities(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitProgrammEntities(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgrammEntitiesContext programmEntities() throws RecognitionException {
		ProgrammEntitiesContext _localctx = new ProgrammEntitiesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_programmEntities);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(114);
				codeEntry();
				}
				}
				setState(117); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==KEYWORD || _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CodeEntryContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public CodeBodyContext codeBody() {
			return getRuleContext(CodeBodyContext.class,0);
		}
		public CodeEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterCodeEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitCodeEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitCodeEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeEntryContext codeEntry() throws RecognitionException {
		CodeEntryContext _localctx = new CodeEntryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_codeEntry);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			declaration();
			setState(120);
			codeBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationBeginContext declarationBegin() {
			return getRuleContext(DeclarationBeginContext.class,0);
		}
		public DeclarationRestContext declarationRest() {
			return getRuleContext(DeclarationRestContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			declarationBegin();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KEYWORD) {
				{
				setState(123);
				declarationRest();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationBeginContext extends ParserRuleContext {
		public DeclarationPrefixContext declarationPrefix() {
			return getRuleContext(DeclarationPrefixContext.class,0);
		}
		public FuncDeclarationArgsContext funcDeclarationArgs() {
			return getRuleContext(FuncDeclarationArgsContext.class,0);
		}
		public DeclarationBeginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationBegin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterDeclarationBegin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitDeclarationBegin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitDeclarationBegin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationBeginContext declarationBegin() throws RecognitionException {
		DeclarationBeginContext _localctx = new DeclarationBeginContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_declarationBegin);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			declarationPrefix();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(127);
				match(T__2);
				setState(128);
				funcDeclarationArgs();
				setState(129);
				match(T__3);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationPrefixContext extends ParserRuleContext {
		public List<KeyWordContext> keyWord() {
			return getRuleContexts(KeyWordContext.class);
		}
		public KeyWordContext keyWord(int i) {
			return getRuleContext(KeyWordContext.class,i);
		}
		public List<TypeTokenContext> typeToken() {
			return getRuleContexts(TypeTokenContext.class);
		}
		public TypeTokenContext typeToken(int i) {
			return getRuleContext(TypeTokenContext.class,i);
		}
		public DeclarationPrefixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationPrefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterDeclarationPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitDeclarationPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitDeclarationPrefix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationPrefixContext declarationPrefix() throws RecognitionException {
		DeclarationPrefixContext _localctx = new DeclarationPrefixContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declarationPrefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(135); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(135);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case KEYWORD:
						{
						setState(133);
						keyWord();
						}
						break;
					case NAME:
						{
						setState(134);
						typeToken();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(137); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationRestContext extends ParserRuleContext {
		public List<KeyWordAndTokensContext> keyWordAndTokens() {
			return getRuleContexts(KeyWordAndTokensContext.class);
		}
		public KeyWordAndTokensContext keyWordAndTokens(int i) {
			return getRuleContext(KeyWordAndTokensContext.class,i);
		}
		public DeclarationRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterDeclarationRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitDeclarationRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitDeclarationRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationRestContext declarationRest() throws RecognitionException {
		DeclarationRestContext _localctx = new DeclarationRestContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_declarationRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(139);
				keyWordAndTokens();
				}
				}
				setState(142); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==KEYWORD );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CodeBodyContext extends ParserRuleContext {
		public InnerCodeContext innerCode() {
			return getRuleContext(InnerCodeContext.class,0);
		}
		public CodeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterCodeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitCodeBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitCodeBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeBodyContext codeBody() throws RecognitionException {
		CodeBodyContext _localctx = new CodeBodyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_codeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__4);
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 536682506L) != 0)) {
				{
				setState(145);
				innerCode();
				}
			}

			setState(148);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InnerCodeContext extends ParserRuleContext {
		public ProgrammEntitiesContext programmEntities() {
			return getRuleContext(ProgrammEntitiesContext.class,0);
		}
		public List<ExecutableLineContext> executableLine() {
			return getRuleContexts(ExecutableLineContext.class);
		}
		public ExecutableLineContext executableLine(int i) {
			return getRuleContext(ExecutableLineContext.class,i);
		}
		public List<ExecutableStatementContext> executableStatement() {
			return getRuleContexts(ExecutableStatementContext.class);
		}
		public ExecutableStatementContext executableStatement(int i) {
			return getRuleContext(ExecutableStatementContext.class,i);
		}
		public List<SwitchStatementContext> switchStatement() {
			return getRuleContexts(SwitchStatementContext.class);
		}
		public SwitchStatementContext switchStatement(int i) {
			return getRuleContext(SwitchStatementContext.class,i);
		}
		public InnerCodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerCode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterInnerCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitInnerCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitInnerCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InnerCodeContext innerCode() throws RecognitionException {
		InnerCodeContext _localctx = new InnerCodeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_innerCode);
		int _la;
		try {
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				programmEntities();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(154);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__0:
					case T__2:
					case KEYWORD:
					case STRING:
					case NUMBER:
					case NAME:
					case Langle:
					case Rangle:
					case Question:
					case Colon:
					case Star:
					case UNO_OPER:
					case OPER:
						{
						setState(151);
						executableLine();
						}
						break;
					case KEY_STATEMENT:
						{
						setState(152);
						executableStatement();
						}
						break;
					case KEY_switch:
						{
						setState(153);
						switchStatement();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(156); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 536682506L) != 0) );
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExecutableLineContext extends ParserRuleContext {
		public KeyWordContext keyWord() {
			return getRuleContext(KeyWordContext.class,0);
		}
		public DeclareValueContext declareValue() {
			return getRuleContext(DeclareValueContext.class,0);
		}
		public ExecutableLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_executableLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterExecutableLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitExecutableLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitExecutableLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExecutableLineContext executableLine() throws RecognitionException {
		ExecutableLineContext _localctx = new ExecutableLineContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_executableLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(160);
				keyWord();
				}
				break;
			case 2:
				{
				setState(161);
				declareValue();
				}
				break;
			}
			setState(164);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExecutableStatementContext extends ParserRuleContext {
		public StatementDeclarationContext statementDeclaration() {
			return getRuleContext(StatementDeclarationContext.class,0);
		}
		public List<CodeBodyContext> codeBody() {
			return getRuleContexts(CodeBodyContext.class);
		}
		public CodeBodyContext codeBody(int i) {
			return getRuleContext(CodeBodyContext.class,i);
		}
		public List<StatementDeclaraitonRestContext> statementDeclaraitonRest() {
			return getRuleContexts(StatementDeclaraitonRestContext.class);
		}
		public StatementDeclaraitonRestContext statementDeclaraitonRest(int i) {
			return getRuleContext(StatementDeclaraitonRestContext.class,i);
		}
		public StatementLineEndContext statementLineEnd() {
			return getRuleContext(StatementLineEndContext.class,0);
		}
		public ExecutableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_executableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterExecutableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitExecutableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitExecutableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExecutableStatementContext executableStatement() throws RecognitionException {
		ExecutableStatementContext _localctx = new ExecutableStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_executableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			statementDeclaration();
			setState(167);
			codeBody();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEY_STATEMENT_REST) {
				{
				{
				setState(168);
				statementDeclaraitonRest();
				setState(169);
				codeBody();
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(176);
				statementLineEnd();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> KEY_STATEMENT() { return getTokens(JavaCodeParser.KEY_STATEMENT); }
		public TerminalNode KEY_STATEMENT(int i) {
			return getToken(JavaCodeParser.KEY_STATEMENT, i);
		}
		public StatementDeclarationScopeContext statementDeclarationScope() {
			return getRuleContext(StatementDeclarationScopeContext.class,0);
		}
		public StatementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterStatementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitStatementDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitStatementDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementDeclarationContext statementDeclaration() throws RecognitionException {
		StatementDeclarationContext _localctx = new StatementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statementDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(179);
				match(KEY_STATEMENT);
				}
				}
				setState(182); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==KEY_STATEMENT );
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(184);
				statementDeclarationScope();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementDeclaraitonRestContext extends ParserRuleContext {
		public TerminalNode KEY_STATEMENT_REST() { return getToken(JavaCodeParser.KEY_STATEMENT_REST, 0); }
		public List<TerminalNode> KEY_STATEMENT() { return getTokens(JavaCodeParser.KEY_STATEMENT); }
		public TerminalNode KEY_STATEMENT(int i) {
			return getToken(JavaCodeParser.KEY_STATEMENT, i);
		}
		public StatementDeclarationScopeContext statementDeclarationScope() {
			return getRuleContext(StatementDeclarationScopeContext.class,0);
		}
		public StatementDeclaraitonRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementDeclaraitonRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterStatementDeclaraitonRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitStatementDeclaraitonRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitStatementDeclaraitonRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementDeclaraitonRestContext statementDeclaraitonRest() throws RecognitionException {
		StatementDeclaraitonRestContext _localctx = new StatementDeclaraitonRestContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statementDeclaraitonRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(KEY_STATEMENT_REST);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEY_STATEMENT) {
				{
				{
				setState(188);
				match(KEY_STATEMENT);
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(194);
				statementDeclarationScope();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementDeclarationScopeContext extends ParserRuleContext {
		public StatementDeclarationInScopeContext statementDeclarationInScope() {
			return getRuleContext(StatementDeclarationInScopeContext.class,0);
		}
		public StatementDeclarationScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementDeclarationScope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterStatementDeclarationScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitStatementDeclarationScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitStatementDeclarationScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementDeclarationScopeContext statementDeclarationScope() throws RecognitionException {
		StatementDeclarationScopeContext _localctx = new StatementDeclarationScopeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statementDeclarationScope);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(197);
			match(T__2);
			setState(198);
			statementDeclarationInScope();
			setState(199);
			match(T__3);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementDeclarationInScopeContext extends ParserRuleContext {
		public List<DeclareValueContext> declareValue() {
			return getRuleContexts(DeclareValueContext.class);
		}
		public DeclareValueContext declareValue(int i) {
			return getRuleContext(DeclareValueContext.class,i);
		}
		public StatementDeclarationInScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementDeclarationInScope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterStatementDeclarationInScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitStatementDeclarationInScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitStatementDeclarationInScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementDeclarationInScopeContext statementDeclarationInScope() throws RecognitionException {
		StatementDeclarationInScopeContext _localctx = new StatementDeclarationInScopeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statementDeclarationInScope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			declareValue();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(202);
				match(T__0);
				setState(203);
				declareValue();
				}
				}
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementLineEndContext extends ParserRuleContext {
		public StatementLineEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementLineEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterStatementLineEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitStatementLineEnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitStatementLineEnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementLineEndContext statementLineEnd() throws RecognitionException {
		StatementLineEndContext _localctx = new StatementLineEndContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_statementLineEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(T__6);
			setState(210);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode KEY_switch() { return getToken(JavaCodeParser.KEY_switch, 0); }
		public SwitchDeclareContext switchDeclare() {
			return getRuleContext(SwitchDeclareContext.class,0);
		}
		public SwitchBodyContext switchBody() {
			return getRuleContext(SwitchBodyContext.class,0);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_switchStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(KEY_switch);
			setState(213);
			switchDeclare();
			setState(214);
			switchBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchDeclareContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public SwitchDeclareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchDeclare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterSwitchDeclare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitSwitchDeclare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitSwitchDeclare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchDeclareContext switchDeclare() throws RecognitionException {
		SwitchDeclareContext _localctx = new SwitchDeclareContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_switchDeclare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__2);
			setState(217);
			value();
			setState(218);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchBodyContext extends ParserRuleContext {
		public SwitchCodeContext switchCode() {
			return getRuleContext(SwitchCodeContext.class,0);
		}
		public SwitchBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterSwitchBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitSwitchBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitSwitchBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchBodyContext switchBody() throws RecognitionException {
		SwitchBodyContext _localctx = new SwitchBodyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_switchBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(T__4);
			setState(221);
			switchCode();
			setState(222);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchCodeContext extends ParserRuleContext {
		public List<CaseContext> case_() {
			return getRuleContexts(CaseContext.class);
		}
		public CaseContext case_(int i) {
			return getRuleContext(CaseContext.class,i);
		}
		public DefaultContext default_() {
			return getRuleContext(DefaultContext.class,0);
		}
		public SwitchCodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchCode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterSwitchCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitSwitchCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitSwitchCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchCodeContext switchCode() throws RecognitionException {
		SwitchCodeContext _localctx = new SwitchCodeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_switchCode);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(224);
					case_();
					}
					} 
				}
				setState(229);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KEY_default) {
				{
				setState(230);
				default_();
				}
			}

			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEY_case) {
				{
				{
				setState(233);
				case_();
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseContext extends ParserRuleContext {
		public TerminalNode KEY_case() { return getToken(JavaCodeParser.KEY_case, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode Colon() { return getToken(JavaCodeParser.Colon, 0); }
		public InnerCodeContext innerCode() {
			return getRuleContext(InnerCodeContext.class,0);
		}
		public CaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseContext case_() throws RecognitionException {
		CaseContext _localctx = new CaseContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_case);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(KEY_case);
			setState(240);
			value();
			setState(241);
			match(Colon);
			setState(242);
			innerCode();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultContext extends ParserRuleContext {
		public TerminalNode KEY_default() { return getToken(JavaCodeParser.KEY_default, 0); }
		public TerminalNode Colon() { return getToken(JavaCodeParser.Colon, 0); }
		public InnerCodeContext innerCode() {
			return getRuleContext(InnerCodeContext.class,0);
		}
		public DefaultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_default; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterDefault(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitDefault(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitDefault(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultContext default_() throws RecognitionException {
		DefaultContext _localctx = new DefaultContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_default);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(KEY_default);
			setState(245);
			match(Colon);
			setState(246);
			innerCode();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDeclarationArgsContext extends ParserRuleContext {
		public List<FuncDeclarationArgContext> funcDeclarationArg() {
			return getRuleContexts(FuncDeclarationArgContext.class);
		}
		public FuncDeclarationArgContext funcDeclarationArg(int i) {
			return getRuleContext(FuncDeclarationArgContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaCodeParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaCodeParser.Comma, i);
		}
		public FuncDeclarationArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclarationArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterFuncDeclarationArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitFuncDeclarationArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitFuncDeclarationArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclarationArgsContext funcDeclarationArgs() throws RecognitionException {
		FuncDeclarationArgsContext _localctx = new FuncDeclarationArgsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_funcDeclarationArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KEYWORD || _la==NAME) {
				{
				setState(248);
				funcDeclarationArg();
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(249);
					match(Comma);
					setState(250);
					funcDeclarationArg();
					}
					}
					setState(255);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDeclarationArgContext extends ParserRuleContext {
		public ArgTypeTokenContext argTypeToken() {
			return getRuleContext(ArgTypeTokenContext.class,0);
		}
		public VarTokenContext varToken() {
			return getRuleContext(VarTokenContext.class,0);
		}
		public List<KeyWordContext> keyWord() {
			return getRuleContexts(KeyWordContext.class);
		}
		public KeyWordContext keyWord(int i) {
			return getRuleContext(KeyWordContext.class,i);
		}
		public FuncDeclarationArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDeclarationArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterFuncDeclarationArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitFuncDeclarationArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitFuncDeclarationArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclarationArgContext funcDeclarationArg() throws RecognitionException {
		FuncDeclarationArgContext _localctx = new FuncDeclarationArgContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_funcDeclarationArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEYWORD) {
				{
				{
				setState(258);
				keyWord();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(264);
			argTypeToken();
			setState(265);
			varToken();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgTypeTokenContext extends ParserRuleContext {
		public TypeTokenContext typeToken() {
			return getRuleContext(TypeTokenContext.class,0);
		}
		public VarargContext vararg() {
			return getRuleContext(VarargContext.class,0);
		}
		public ArgTypeTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argTypeToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterArgTypeToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitArgTypeToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitArgTypeToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgTypeTokenContext argTypeToken() throws RecognitionException {
		ArgTypeTokenContext _localctx = new ArgTypeTokenContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_argTypeToken);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			typeToken();
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(268);
				vararg();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarargContext extends ParserRuleContext {
		public VarargContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vararg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterVararg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitVararg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitVararg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarargContext vararg() throws RecognitionException {
		VarargContext _localctx = new VarargContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_vararg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(271);
			match(T__7);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclareValueContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaCodeParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaCodeParser.Comma, i);
		}
		public DeclareValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterDeclareValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitDeclareValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitDeclareValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareValueContext declareValue() throws RecognitionException {
		DeclareValueContext _localctx = new DeclareValueContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_declareValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			value();
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 536608776L) != 0)) {
				{
				setState(274);
				value();
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(275);
					match(Comma);
					setState(276);
					value();
					}
					}
					setState(281);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public List<UnoValueContext> unoValue() {
			return getRuleContexts(UnoValueContext.class);
		}
		public UnoValueContext unoValue(int i) {
			return getRuleContext(UnoValueContext.class,i);
		}
		public List<OperContext> oper() {
			return getRuleContexts(OperContext.class);
		}
		public OperContext oper(int i) {
			return getRuleContext(OperContext.class,i);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_value);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 398458880L) != 0)) {
				{
				{
				setState(284);
				oper();
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(290);
			unoValue();
			setState(300);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(292); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(291);
						oper();
						}
						}
						setState(294); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 398458880L) != 0) );
					setState(296);
					unoValue();
					}
					} 
				}
				setState(302);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			setState(306);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(303);
					oper();
					}
					} 
				}
				setState(308);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnoValueContext extends ParserRuleContext {
		public UnoValueKeyWordContext unoValueKeyWord() {
			return getRuleContext(UnoValueKeyWordContext.class,0);
		}
		public List<UnoOperContext> unoOper() {
			return getRuleContexts(UnoOperContext.class);
		}
		public UnoOperContext unoOper(int i) {
			return getRuleContext(UnoOperContext.class,i);
		}
		public UnoValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unoValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterUnoValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitUnoValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitUnoValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnoValueContext unoValue() throws RecognitionException {
		UnoValueContext _localctx = new UnoValueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_unoValue);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==UNO_OPER) {
				{
				{
				setState(309);
				unoOper();
				}
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(315);
			unoValueKeyWord();
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(316);
					unoOper();
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnoValueKeyWordContext extends ParserRuleContext {
		public UnoValueGetContext unoValueGet() {
			return getRuleContext(UnoValueGetContext.class,0);
		}
		public List<KeyWordContext> keyWord() {
			return getRuleContexts(KeyWordContext.class);
		}
		public KeyWordContext keyWord(int i) {
			return getRuleContext(KeyWordContext.class,i);
		}
		public UnoValueKeyWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unoValueKeyWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterUnoValueKeyWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitUnoValueKeyWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitUnoValueKeyWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnoValueKeyWordContext unoValueKeyWord() throws RecognitionException {
		UnoValueKeyWordContext _localctx = new UnoValueKeyWordContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_unoValueKeyWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KEYWORD) {
				{
				{
				setState(322);
				keyWord();
				}
				}
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(328);
			unoValueGet();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnoValueGetContext extends ParserRuleContext {
		public JustValueContext justValue() {
			return getRuleContext(JustValueContext.class,0);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<UnoValueContext> unoValue() {
			return getRuleContexts(UnoValueContext.class);
		}
		public UnoValueContext unoValue(int i) {
			return getRuleContext(UnoValueContext.class,i);
		}
		public UnoValueGetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unoValueGet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterUnoValueGet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitUnoValueGet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitUnoValueGet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnoValueGetContext unoValueGet() throws RecognitionException {
		UnoValueGetContext _localctx = new UnoValueGetContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_unoValueGet);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			justValue();
			setState(337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(331);
				match(T__8);
				setState(332);
				value();
				setState(333);
				match(T__9);
				}
				}
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(344);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(340);
					match(T__1);
					setState(341);
					unoValue();
					}
					} 
				}
				setState(346);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JustValueContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(JavaCodeParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(JavaCodeParser.STRING, 0); }
		public FuncTokenContext funcToken() {
			return getRuleContext(FuncTokenContext.class,0);
		}
		public JustValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_justValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterJustValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitJustValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitJustValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JustValueContext justValue() throws RecognitionException {
		JustValueContext _localctx = new JustValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_justValue);
		try {
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				match(T__2);
				setState(348);
				value();
				setState(349);
				match(T__3);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				match(NUMBER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(352);
				match(STRING);
				}
				break;
			case NAME:
				enterOuterAlt(_localctx, 4);
				{
				setState(353);
				funcToken();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncTokenContext extends ParserRuleContext {
		public TypeTokenContext typeToken() {
			return getRuleContext(TypeTokenContext.class,0);
		}
		public FuncCalledArgsContext funcCalledArgs() {
			return getRuleContext(FuncCalledArgsContext.class,0);
		}
		public FuncTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterFuncToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitFuncToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitFuncToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncTokenContext funcToken() throws RecognitionException {
		FuncTokenContext _localctx = new FuncTokenContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_funcToken);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			typeToken();
			setState(358);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(357);
				funcCalledArgs();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncCalledArgsContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaCodeParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaCodeParser.Comma, i);
		}
		public FuncCalledArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCalledArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterFuncCalledArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitFuncCalledArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitFuncCalledArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCalledArgsContext funcCalledArgs() throws RecognitionException {
		FuncCalledArgsContext _localctx = new FuncCalledArgsContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_funcCalledArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(T__2);
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 536608776L) != 0)) {
				{
				setState(361);
				value();
				setState(366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(362);
					match(Comma);
					setState(363);
					value();
					}
					}
					setState(368);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(371);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeTokenContext extends ParserRuleContext {
		public VarTokenContext varToken() {
			return getRuleContext(VarTokenContext.class,0);
		}
		public TypeTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterTypeToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitTypeToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitTypeToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTokenContext typeToken() throws RecognitionException {
		TypeTokenContext _localctx = new TypeTokenContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_typeToken);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			varToken();
			setState(378);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(374);
					match(T__8);
					setState(375);
					match(T__9);
					}
					} 
				}
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarTokenContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(JavaCodeParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(JavaCodeParser.NAME, i);
		}
		public VarTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterVarToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitVarToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitVarToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTokenContext varToken() throws RecognitionException {
		VarTokenContext _localctx = new VarTokenContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_varToken);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(NAME);
			setState(386);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(382);
					match(T__1);
					setState(383);
					match(NAME);
					}
					} 
				}
				setState(388);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyWordAndTokensContext extends ParserRuleContext {
		public KeyWordContext keyWord() {
			return getRuleContext(KeyWordContext.class,0);
		}
		public TypeTokensCommaContext typeTokensComma() {
			return getRuleContext(TypeTokensCommaContext.class,0);
		}
		public KeyWordAndTokensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyWordAndTokens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterKeyWordAndTokens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitKeyWordAndTokens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitKeyWordAndTokens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyWordAndTokensContext keyWordAndTokens() throws RecognitionException {
		KeyWordAndTokensContext _localctx = new KeyWordAndTokensContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_keyWordAndTokens);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
			keyWord();
			setState(390);
			typeTokensComma();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeTokensCommaContext extends ParserRuleContext {
		public List<TypeTokenContext> typeToken() {
			return getRuleContexts(TypeTokenContext.class);
		}
		public TypeTokenContext typeToken(int i) {
			return getRuleContext(TypeTokenContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(JavaCodeParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(JavaCodeParser.Comma, i);
		}
		public TypeTokensCommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTokensComma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterTypeTokensComma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitTypeTokensComma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitTypeTokensComma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeTokensCommaContext typeTokensComma() throws RecognitionException {
		TypeTokensCommaContext _localctx = new TypeTokensCommaContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_typeTokensComma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			typeToken();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(393);
				match(Comma);
				setState(394);
				typeToken();
				}
				}
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyWordContext extends ParserRuleContext {
		public TerminalNode KEYWORD() { return getToken(JavaCodeParser.KEYWORD, 0); }
		public KeyWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterKeyWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitKeyWord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitKeyWord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyWordContext keyWord() throws RecognitionException {
		KeyWordContext _localctx = new KeyWordContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_keyWord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(KEYWORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnoOperContext extends ParserRuleContext {
		public TerminalNode UNO_OPER() { return getToken(JavaCodeParser.UNO_OPER, 0); }
		public UnoOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unoOper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterUnoOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitUnoOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitUnoOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnoOperContext unoOper() throws RecognitionException {
		UnoOperContext _localctx = new UnoOperContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_unoOper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			match(UNO_OPER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperContext extends ParserRuleContext {
		public TerminalNode OPER() { return getToken(JavaCodeParser.OPER, 0); }
		public TerminalNode Langle() { return getToken(JavaCodeParser.Langle, 0); }
		public TerminalNode Rangle() { return getToken(JavaCodeParser.Rangle, 0); }
		public TerminalNode Question() { return getToken(JavaCodeParser.Question, 0); }
		public TerminalNode Colon() { return getToken(JavaCodeParser.Colon, 0); }
		public TerminalNode Star() { return getToken(JavaCodeParser.Star, 0); }
		public OperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).enterOper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaCodeListener ) ((JavaCodeListener)listener).exitOper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaCodeVisitor ) return ((JavaCodeVisitor<? extends T>)visitor).visitOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperContext oper() throws RecognitionException {
		OperContext _localctx = new OperContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_oper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 398458880L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001e\u0197\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0001\u0000"+
		"\u0003\u0000Z\b\u0000\u0001\u0000\u0003\u0000]\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0004\u0002g\b\u0002\u000b\u0002\f\u0002h\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003o\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0004\u0004t\b\u0004\u000b\u0004\f\u0004u\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0003\u0006}\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0084"+
		"\b\u0007\u0001\b\u0001\b\u0004\b\u0088\b\b\u000b\b\f\b\u0089\u0001\t\u0004"+
		"\t\u008d\b\t\u000b\t\f\t\u008e\u0001\n\u0001\n\u0003\n\u0093\b\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b"+
		"\u009b\b\u000b\u000b\u000b\f\u000b\u009c\u0003\u000b\u009f\b\u000b\u0001"+
		"\f\u0001\f\u0003\f\u00a3\b\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u00ac\b\r\n\r\f\r\u00af\t\r\u0001\r\u0003\r\u00b2\b"+
		"\r\u0001\u000e\u0004\u000e\u00b5\b\u000e\u000b\u000e\f\u000e\u00b6\u0001"+
		"\u000e\u0003\u000e\u00ba\b\u000e\u0001\u000f\u0001\u000f\u0005\u000f\u00be"+
		"\b\u000f\n\u000f\f\u000f\u00c1\t\u000f\u0001\u000f\u0003\u000f\u00c4\b"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u00cd\b\u0011\n\u0011\f\u0011\u00d0\t\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0005\u0016\u00e2\b\u0016"+
		"\n\u0016\f\u0016\u00e5\t\u0016\u0001\u0016\u0003\u0016\u00e8\b\u0016\u0001"+
		"\u0016\u0005\u0016\u00eb\b\u0016\n\u0016\f\u0016\u00ee\t\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019"+
		"\u00fc\b\u0019\n\u0019\f\u0019\u00ff\t\u0019\u0003\u0019\u0101\b\u0019"+
		"\u0001\u001a\u0005\u001a\u0104\b\u001a\n\u001a\f\u001a\u0107\t\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0003\u001b\u010e"+
		"\b\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0005\u001d\u0116\b\u001d\n\u001d\f\u001d\u0119\t\u001d\u0003\u001d"+
		"\u011b\b\u001d\u0001\u001e\u0005\u001e\u011e\b\u001e\n\u001e\f\u001e\u0121"+
		"\t\u001e\u0001\u001e\u0001\u001e\u0004\u001e\u0125\b\u001e\u000b\u001e"+
		"\f\u001e\u0126\u0001\u001e\u0001\u001e\u0005\u001e\u012b\b\u001e\n\u001e"+
		"\f\u001e\u012e\t\u001e\u0001\u001e\u0005\u001e\u0131\b\u001e\n\u001e\f"+
		"\u001e\u0134\t\u001e\u0001\u001f\u0005\u001f\u0137\b\u001f\n\u001f\f\u001f"+
		"\u013a\t\u001f\u0001\u001f\u0001\u001f\u0005\u001f\u013e\b\u001f\n\u001f"+
		"\f\u001f\u0141\t\u001f\u0001 \u0005 \u0144\b \n \f \u0147\t \u0001 \u0001"+
		" \u0001!\u0001!\u0001!\u0001!\u0001!\u0005!\u0150\b!\n!\f!\u0153\t!\u0001"+
		"!\u0001!\u0005!\u0157\b!\n!\f!\u015a\t!\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0003\"\u0163\b\"\u0001#\u0001#\u0003#\u0167"+
		"\b#\u0001$\u0001$\u0001$\u0001$\u0005$\u016d\b$\n$\f$\u0170\t$\u0003$"+
		"\u0172\b$\u0001$\u0001$\u0001%\u0001%\u0001%\u0005%\u0179\b%\n%\f%\u017c"+
		"\t%\u0001&\u0001&\u0001&\u0005&\u0181\b&\n&\f&\u0184\t&\u0001\'\u0001"+
		"\'\u0001\'\u0001(\u0001(\u0001(\u0005(\u018c\b(\n(\f(\u018f\t(\u0001)"+
		"\u0001)\u0001*\u0001*\u0001+\u0001+\u0001+\u0000\u0000,\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>@BDFHJLNPRTV\u0000\u0001\u0002\u0000\u0016\u001a\u001c\u001c"+
		"\u019d\u0000Y\u0001\u0000\u0000\u0000\u0002a\u0001\u0000\u0000\u0000\u0004"+
		"f\u0001\u0000\u0000\u0000\u0006j\u0001\u0000\u0000\u0000\bs\u0001\u0000"+
		"\u0000\u0000\nw\u0001\u0000\u0000\u0000\fz\u0001\u0000\u0000\u0000\u000e"+
		"~\u0001\u0000\u0000\u0000\u0010\u0087\u0001\u0000\u0000\u0000\u0012\u008c"+
		"\u0001\u0000\u0000\u0000\u0014\u0090\u0001\u0000\u0000\u0000\u0016\u009e"+
		"\u0001\u0000\u0000\u0000\u0018\u00a2\u0001\u0000\u0000\u0000\u001a\u00a6"+
		"\u0001\u0000\u0000\u0000\u001c\u00b4\u0001\u0000\u0000\u0000\u001e\u00bb"+
		"\u0001\u0000\u0000\u0000 \u00c5\u0001\u0000\u0000\u0000\"\u00c9\u0001"+
		"\u0000\u0000\u0000$\u00d1\u0001\u0000\u0000\u0000&\u00d4\u0001\u0000\u0000"+
		"\u0000(\u00d8\u0001\u0000\u0000\u0000*\u00dc\u0001\u0000\u0000\u0000,"+
		"\u00e3\u0001\u0000\u0000\u0000.\u00ef\u0001\u0000\u0000\u00000\u00f4\u0001"+
		"\u0000\u0000\u00002\u0100\u0001\u0000\u0000\u00004\u0105\u0001\u0000\u0000"+
		"\u00006\u010b\u0001\u0000\u0000\u00008\u010f\u0001\u0000\u0000\u0000:"+
		"\u0111\u0001\u0000\u0000\u0000<\u011f\u0001\u0000\u0000\u0000>\u0138\u0001"+
		"\u0000\u0000\u0000@\u0145\u0001\u0000\u0000\u0000B\u014a\u0001\u0000\u0000"+
		"\u0000D\u0162\u0001\u0000\u0000\u0000F\u0164\u0001\u0000\u0000\u0000H"+
		"\u0168\u0001\u0000\u0000\u0000J\u0175\u0001\u0000\u0000\u0000L\u017d\u0001"+
		"\u0000\u0000\u0000N\u0185\u0001\u0000\u0000\u0000P\u0188\u0001\u0000\u0000"+
		"\u0000R\u0190\u0001\u0000\u0000\u0000T\u0192\u0001\u0000\u0000\u0000V"+
		"\u0194\u0001\u0000\u0000\u0000XZ\u0003\u0002\u0001\u0000YX\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000\u0000\u0000[]\u0003"+
		"\u0004\u0002\u0000\\[\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000"+
		"]^\u0001\u0000\u0000\u0000^_\u0003\b\u0004\u0000_`\u0005\u0000\u0000\u0001"+
		"`\u0001\u0001\u0000\u0000\u0000ab\u0005\u000b\u0000\u0000bc\u0003J%\u0000"+
		"cd\u0005\u0001\u0000\u0000d\u0003\u0001\u0000\u0000\u0000eg\u0003\u0006"+
		"\u0003\u0000fe\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hf\u0001"+
		"\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000i\u0005\u0001\u0000\u0000"+
		"\u0000jk\u0005\f\u0000\u0000kn\u0003J%\u0000lm\u0005\u0002\u0000\u0000"+
		"mo\u0005\u001a\u0000\u0000nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000"+
		"\u0000op\u0001\u0000\u0000\u0000pq\u0005\u0001\u0000\u0000q\u0007\u0001"+
		"\u0000\u0000\u0000rt\u0003\n\u0005\u0000sr\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"v\t\u0001\u0000\u0000\u0000wx\u0003\f\u0006\u0000xy\u0003\u0014\n\u0000"+
		"y\u000b\u0001\u0000\u0000\u0000z|\u0003\u000e\u0007\u0000{}\u0003\u0012"+
		"\t\u0000|{\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\r\u0001"+
		"\u0000\u0000\u0000~\u0083\u0003\u0010\b\u0000\u007f\u0080\u0005\u0003"+
		"\u0000\u0000\u0080\u0081\u00032\u0019\u0000\u0081\u0082\u0005\u0004\u0000"+
		"\u0000\u0082\u0084\u0001\u0000\u0000\u0000\u0083\u007f\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u000f\u0001\u0000\u0000"+
		"\u0000\u0085\u0088\u0003R)\u0000\u0086\u0088\u0003J%\u0000\u0087\u0085"+
		"\u0001\u0000\u0000\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0088\u0089"+
		"\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u008a"+
		"\u0001\u0000\u0000\u0000\u008a\u0011\u0001\u0000\u0000\u0000\u008b\u008d"+
		"\u0003N\'\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u008e\u0001"+
		"\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001"+
		"\u0000\u0000\u0000\u008f\u0013\u0001\u0000\u0000\u0000\u0090\u0092\u0005"+
		"\u0005\u0000\u0000\u0091\u0093\u0003\u0016\u000b\u0000\u0092\u0091\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0005\u0006\u0000\u0000\u0095\u0015\u0001"+
		"\u0000\u0000\u0000\u0096\u009f\u0003\b\u0004\u0000\u0097\u009b\u0003\u0018"+
		"\f\u0000\u0098\u009b\u0003\u001a\r\u0000\u0099\u009b\u0003&\u0013\u0000"+
		"\u009a\u0097\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000"+
		"\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000"+
		"\u009c\u009a\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000"+
		"\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u0096\u0001\u0000\u0000\u0000"+
		"\u009e\u009a\u0001\u0000\u0000\u0000\u009f\u0017\u0001\u0000\u0000\u0000"+
		"\u00a0\u00a3\u0003R)\u0000\u00a1\u00a3\u0003:\u001d\u0000\u00a2\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\u0005\u0001\u0000\u0000\u00a5\u0019\u0001\u0000\u0000\u0000\u00a6\u00a7"+
		"\u0003\u001c\u000e\u0000\u00a7\u00ad\u0003\u0014\n\u0000\u00a8\u00a9\u0003"+
		"\u001e\u000f\u0000\u00a9\u00aa\u0003\u0014\n\u0000\u00aa\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ab\u00a8\u0001\u0000\u0000\u0000\u00ac\u00af\u0001\u0000"+
		"\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000"+
		"\u0000\u0000\u00ae\u00b1\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b2\u0003$\u0012\u0000\u00b1\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u001b\u0001\u0000\u0000"+
		"\u0000\u00b3\u00b5\u0005\u0010\u0000\u0000\u00b4\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b8\u00ba\u0003 \u0010\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u001d\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bf\u0005\u0011\u0000\u0000\u00bc\u00be\u0005\u0010\u0000\u0000"+
		"\u00bd\u00bc\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c4\u0003 \u0010\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c4\u001f\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\u0005\u0003\u0000\u0000\u00c6\u00c7\u0003\"\u0011\u0000\u00c7\u00c8"+
		"\u0005\u0004\u0000\u0000\u00c8!\u0001\u0000\u0000\u0000\u00c9\u00ce\u0003"+
		":\u001d\u0000\u00ca\u00cb\u0005\u0001\u0000\u0000\u00cb\u00cd\u0003:\u001d"+
		"\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cd\u00d0\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000"+
		"\u0000\u00cf#\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000"+
		"\u00d1\u00d2\u0005\u0007\u0000\u0000\u00d2\u00d3\u0005\u0001\u0000\u0000"+
		"\u00d3%\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005\r\u0000\u0000\u00d5"+
		"\u00d6\u0003(\u0014\u0000\u00d6\u00d7\u0003*\u0015\u0000\u00d7\'\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0005\u0003\u0000\u0000\u00d9\u00da\u0003"+
		"<\u001e\u0000\u00da\u00db\u0005\u0004\u0000\u0000\u00db)\u0001\u0000\u0000"+
		"\u0000\u00dc\u00dd\u0005\u0005\u0000\u0000\u00dd\u00de\u0003,\u0016\u0000"+
		"\u00de\u00df\u0005\u0006\u0000\u0000\u00df+\u0001\u0000\u0000\u0000\u00e0"+
		"\u00e2\u0003.\u0017\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e7\u0001\u0000\u0000\u0000\u00e5\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e8\u00030\u0018\u0000\u00e7\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ec\u0001"+
		"\u0000\u0000\u0000\u00e9\u00eb\u0003.\u0017\u0000\u00ea\u00e9\u0001\u0000"+
		"\u0000\u0000\u00eb\u00ee\u0001\u0000\u0000\u0000\u00ec\u00ea\u0001\u0000"+
		"\u0000\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed-\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u000e\u0000"+
		"\u0000\u00f0\u00f1\u0003<\u001e\u0000\u00f1\u00f2\u0005\u0019\u0000\u0000"+
		"\u00f2\u00f3\u0003\u0016\u000b\u0000\u00f3/\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f5\u0005\u000f\u0000\u0000\u00f5\u00f6\u0005\u0019\u0000\u0000\u00f6"+
		"\u00f7\u0003\u0016\u000b\u0000\u00f71\u0001\u0000\u0000\u0000\u00f8\u00fd"+
		"\u00034\u001a\u0000\u00f9\u00fa\u0005\u001d\u0000\u0000\u00fa\u00fc\u0003"+
		"4\u001a\u0000\u00fb\u00f9\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000"+
		"\u0000\u0000\u00fd\u00fb\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000"+
		"\u0000\u0000\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000"+
		"\u0000\u0000\u0100\u00f8\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000"+
		"\u0000\u0000\u01013\u0001\u0000\u0000\u0000\u0102\u0104\u0003R)\u0000"+
		"\u0103\u0102\u0001\u0000\u0000\u0000\u0104\u0107\u0001\u0000\u0000\u0000"+
		"\u0105\u0103\u0001\u0000\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000"+
		"\u0106\u0108\u0001\u0000\u0000\u0000\u0107\u0105\u0001\u0000\u0000\u0000"+
		"\u0108\u0109\u00036\u001b\u0000\u0109\u010a\u0003L&\u0000\u010a5\u0001"+
		"\u0000\u0000\u0000\u010b\u010d\u0003J%\u0000\u010c\u010e\u00038\u001c"+
		"\u0000\u010d\u010c\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000"+
		"\u0000\u010e7\u0001\u0000\u0000\u0000\u010f\u0110\u0005\b\u0000\u0000"+
		"\u01109\u0001\u0000\u0000\u0000\u0111\u011a\u0003<\u001e\u0000\u0112\u0117"+
		"\u0003<\u001e\u0000\u0113\u0114\u0005\u001d\u0000\u0000\u0114\u0116\u0003"+
		"<\u001e\u0000\u0115\u0113\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000"+
		"\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000"+
		"\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000"+
		"\u0000\u0000\u011a\u0112\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000"+
		"\u0000\u0000\u011b;\u0001\u0000\u0000\u0000\u011c\u011e\u0003V+\u0000"+
		"\u011d\u011c\u0001\u0000\u0000\u0000\u011e\u0121\u0001\u0000\u0000\u0000"+
		"\u011f\u011d\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000"+
		"\u0120\u0122\u0001\u0000\u0000\u0000\u0121\u011f\u0001\u0000\u0000\u0000"+
		"\u0122\u012c\u0003>\u001f\u0000\u0123\u0125\u0003V+\u0000\u0124\u0123"+
		"\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0124"+
		"\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0128"+
		"\u0001\u0000\u0000\u0000\u0128\u0129\u0003>\u001f\u0000\u0129\u012b\u0001"+
		"\u0000\u0000\u0000\u012a\u0124\u0001\u0000\u0000\u0000\u012b\u012e\u0001"+
		"\u0000\u0000\u0000\u012c\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001"+
		"\u0000\u0000\u0000\u012d\u0132\u0001\u0000\u0000\u0000\u012e\u012c\u0001"+
		"\u0000\u0000\u0000\u012f\u0131\u0003V+\u0000\u0130\u012f\u0001\u0000\u0000"+
		"\u0000\u0131\u0134\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000"+
		"\u0000\u0132\u0133\u0001\u0000\u0000\u0000\u0133=\u0001\u0000\u0000\u0000"+
		"\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0137\u0003T*\u0000\u0136\u0135"+
		"\u0001\u0000\u0000\u0000\u0137\u013a\u0001\u0000\u0000\u0000\u0138\u0136"+
		"\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013b"+
		"\u0001\u0000\u0000\u0000\u013a\u0138\u0001\u0000\u0000\u0000\u013b\u013f"+
		"\u0003@ \u0000\u013c\u013e\u0003T*\u0000\u013d\u013c\u0001\u0000\u0000"+
		"\u0000\u013e\u0141\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000"+
		"\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140?\u0001\u0000\u0000\u0000"+
		"\u0141\u013f\u0001\u0000\u0000\u0000\u0142\u0144\u0003R)\u0000\u0143\u0142"+
		"\u0001\u0000\u0000\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145\u0143"+
		"\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0148"+
		"\u0001\u0000\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148\u0149"+
		"\u0003B!\u0000\u0149A\u0001\u0000\u0000\u0000\u014a\u0151\u0003D\"\u0000"+
		"\u014b\u014c\u0005\t\u0000\u0000\u014c\u014d\u0003<\u001e\u0000\u014d"+
		"\u014e\u0005\n\u0000\u0000\u014e\u0150\u0001\u0000\u0000\u0000\u014f\u014b"+
		"\u0001\u0000\u0000\u0000\u0150\u0153\u0001\u0000\u0000\u0000\u0151\u014f"+
		"\u0001\u0000\u0000\u0000\u0151\u0152\u0001\u0000\u0000\u0000\u0152\u0158"+
		"\u0001\u0000\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0154\u0155"+
		"\u0005\u0002\u0000\u0000\u0155\u0157\u0003>\u001f\u0000\u0156\u0154\u0001"+
		"\u0000\u0000\u0000\u0157\u015a\u0001\u0000\u0000\u0000\u0158\u0156\u0001"+
		"\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159C\u0001\u0000"+
		"\u0000\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u015c\u0005\u0003"+
		"\u0000\u0000\u015c\u015d\u0003<\u001e\u0000\u015d\u015e\u0005\u0004\u0000"+
		"\u0000\u015e\u0163\u0001\u0000\u0000\u0000\u015f\u0163\u0005\u0014\u0000"+
		"\u0000\u0160\u0163\u0005\u0013\u0000\u0000\u0161\u0163\u0003F#\u0000\u0162"+
		"\u015b\u0001\u0000\u0000\u0000\u0162\u015f\u0001\u0000\u0000\u0000\u0162"+
		"\u0160\u0001\u0000\u0000\u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0163"+
		"E\u0001\u0000\u0000\u0000\u0164\u0166\u0003J%\u0000\u0165\u0167\u0003"+
		"H$\u0000\u0166\u0165\u0001\u0000\u0000\u0000\u0166\u0167\u0001\u0000\u0000"+
		"\u0000\u0167G\u0001\u0000\u0000\u0000\u0168\u0171\u0005\u0003\u0000\u0000"+
		"\u0169\u016e\u0003<\u001e\u0000\u016a\u016b\u0005\u001d\u0000\u0000\u016b"+
		"\u016d\u0003<\u001e\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016d\u0170"+
		"\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e\u016f"+
		"\u0001\u0000\u0000\u0000\u016f\u0172\u0001\u0000\u0000\u0000\u0170\u016e"+
		"\u0001\u0000\u0000\u0000\u0171\u0169\u0001\u0000\u0000\u0000\u0171\u0172"+
		"\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174"+
		"\u0005\u0004\u0000\u0000\u0174I\u0001\u0000\u0000\u0000\u0175\u017a\u0003"+
		"L&\u0000\u0176\u0177\u0005\t\u0000\u0000\u0177\u0179\u0005\n\u0000\u0000"+
		"\u0178\u0176\u0001\u0000\u0000\u0000\u0179\u017c\u0001\u0000\u0000\u0000"+
		"\u017a\u0178\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000"+
		"\u017bK\u0001\u0000\u0000\u0000\u017c\u017a\u0001\u0000\u0000\u0000\u017d"+
		"\u0182\u0005\u0015\u0000\u0000\u017e\u017f\u0005\u0002\u0000\u0000\u017f"+
		"\u0181\u0005\u0015\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0181"+
		"\u0184\u0001\u0000\u0000\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0182"+
		"\u0183\u0001\u0000\u0000\u0000\u0183M\u0001\u0000\u0000\u0000\u0184\u0182"+
		"\u0001\u0000\u0000\u0000\u0185\u0186\u0003R)\u0000\u0186\u0187\u0003P"+
		"(\u0000\u0187O\u0001\u0000\u0000\u0000\u0188\u018d\u0003J%\u0000\u0189"+
		"\u018a\u0005\u001d\u0000\u0000\u018a\u018c\u0003J%\u0000\u018b\u0189\u0001"+
		"\u0000\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d\u018b\u0001"+
		"\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018eQ\u0001\u0000"+
		"\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u0012"+
		"\u0000\u0000\u0191S\u0001\u0000\u0000\u0000\u0192\u0193\u0005\u001b\u0000"+
		"\u0000\u0193U\u0001\u0000\u0000\u0000\u0194\u0195\u0007\u0000\u0000\u0000"+
		"\u0195W\u0001\u0000\u0000\u0000/Y\\hnu|\u0083\u0087\u0089\u008e\u0092"+
		"\u009a\u009c\u009e\u00a2\u00ad\u00b1\u00b6\u00b9\u00bf\u00c3\u00ce\u00e3"+
		"\u00e7\u00ec\u00fd\u0100\u0105\u010d\u0117\u011a\u011f\u0126\u012c\u0132"+
		"\u0138\u013f\u0145\u0151\u0158\u0162\u0166\u016e\u0171\u017a\u0182\u018d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}