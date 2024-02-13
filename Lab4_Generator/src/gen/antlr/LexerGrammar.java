// Generated from C:/Users/chura/Х#й#я/MT/Lab4_Generator/src/main/java/antlr/LexerGrammar.g4 by ANTLR 4.13.1
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class LexerGrammar extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR=1, HEADER=2, RETURNS=3, COMMA=4, EQUALS=5, COLON=6, OR=7, REGEX=8, 
		SEMICOLON=9, SPACES=10, OP=11, CP=12, FUNCTIONAL_ARROW=13, SKIP_=14, LOWER_CASE_INTEDEFICATION=15, 
		UPPER_CASE_INTEDEFICATION=16, OPENP=17, CODE_OPENP=18, CODE=19, CLOSEP=20;
	public static final int
		JAVA_CODE=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "JAVA_CODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GRAMMAR", "HEADER", "RETURNS", "COMMA", "EQUALS", "COLON", "OR", "REGEX", 
			"SEMICOLON", "SPACES", "OP", "CP", "FUNCTIONAL_ARROW", "SKIP_", "LOWER_CASE_INTEDEFICATION", 
			"UPPER_CASE_INTEDEFICATION", "OPENP", "CODE_OPENP", "CODE", "CLOSEP"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'grammar'", "'@header'", "'returns'", "','", "'='", "':'", "'|'", 
			null, "';'", null, "'['", "']'", "'->'", "'skip'", null, null, null, 
			null, null, "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GRAMMAR", "HEADER", "RETURNS", "COMMA", "EQUALS", "COLON", "OR", 
			"REGEX", "SEMICOLON", "SPACES", "OP", "CP", "FUNCTIONAL_ARROW", "SKIP_", 
			"LOWER_CASE_INTEDEFICATION", "UPPER_CASE_INTEDEFICATION", "OPENP", "CODE_OPENP", 
			"CODE", "CLOSEP"
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


	public LexerGrammar(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LexerGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0014\u0087\u0006\uffff\uffff\u0006\uffff\uffff\u0002\u0000"+
		"\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003"+
		"\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006"+
		"\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002"+
		"\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002"+
		"\u000e\u0007\u000e\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002"+
		"\u0011\u0007\u0011\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0005\u0007M\b\u0007\n\u0007"+
		"\f\u0007P\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0004"+
		"\tW\b\t\u000b\t\f\tX\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0005\u000ek\b\u000e\n\u000e\f\u000en\t\u000e"+
		"\u0001\u000f\u0001\u000f\u0005\u000fr\b\u000f\n\u000f\f\u000fu\t\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0004\u0012\u0080\b\u0012\u000b\u0012"+
		"\f\u0012\u0081\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0000\u0000"+
		"\u0014\u0002\u0001\u0004\u0002\u0006\u0003\b\u0004\n\u0005\f\u0006\u000e"+
		"\u0007\u0010\b\u0012\t\u0014\n\u0016\u000b\u0018\f\u001a\r\u001c\u000e"+
		"\u001e\u000f \u0010\"\u0011$\u0012&\u0013(\u0014\u0002\u0000\u0001\u0006"+
		"\u0001\u0000\'\'\u0003\u0000\t\n\r\r  \u0001\u0000az\u0004\u000009AZ_"+
		"_az\u0001\u0000AZ\u0002\u0000{{}}\u008a\u0000\u0002\u0001\u0000\u0000"+
		"\u0000\u0000\u0004\u0001\u0000\u0000\u0000\u0000\u0006\u0001\u0000\u0000"+
		"\u0000\u0000\b\u0001\u0000\u0000\u0000\u0000\n\u0001\u0000\u0000\u0000"+
		"\u0000\f\u0001\u0000\u0000\u0000\u0000\u000e\u0001\u0000\u0000\u0000\u0000"+
		"\u0010\u0001\u0000\u0000\u0000\u0000\u0012\u0001\u0000\u0000\u0000\u0000"+
		"\u0014\u0001\u0000\u0000\u0000\u0000\u0016\u0001\u0000\u0000\u0000\u0000"+
		"\u0018\u0001\u0000\u0000\u0000\u0000\u001a\u0001\u0000\u0000\u0000\u0000"+
		"\u001c\u0001\u0000\u0000\u0000\u0000\u001e\u0001\u0000\u0000\u0000\u0000"+
		" \u0001\u0000\u0000\u0000\u0000\"\u0001\u0000\u0000\u0000\u0001$\u0001"+
		"\u0000\u0000\u0000\u0001&\u0001\u0000\u0000\u0000\u0001(\u0001\u0000\u0000"+
		"\u0000\u0002*\u0001\u0000\u0000\u0000\u00042\u0001\u0000\u0000\u0000\u0006"+
		":\u0001\u0000\u0000\u0000\bB\u0001\u0000\u0000\u0000\nD\u0001\u0000\u0000"+
		"\u0000\fF\u0001\u0000\u0000\u0000\u000eH\u0001\u0000\u0000\u0000\u0010"+
		"J\u0001\u0000\u0000\u0000\u0012S\u0001\u0000\u0000\u0000\u0014V\u0001"+
		"\u0000\u0000\u0000\u0016\\\u0001\u0000\u0000\u0000\u0018^\u0001\u0000"+
		"\u0000\u0000\u001a`\u0001\u0000\u0000\u0000\u001cc\u0001\u0000\u0000\u0000"+
		"\u001eh\u0001\u0000\u0000\u0000 o\u0001\u0000\u0000\u0000\"v\u0001\u0000"+
		"\u0000\u0000$z\u0001\u0000\u0000\u0000&\u007f\u0001\u0000\u0000\u0000"+
		"(\u0083\u0001\u0000\u0000\u0000*+\u0005g\u0000\u0000+,\u0005r\u0000\u0000"+
		",-\u0005a\u0000\u0000-.\u0005m\u0000\u0000./\u0005m\u0000\u0000/0\u0005"+
		"a\u0000\u000001\u0005r\u0000\u00001\u0003\u0001\u0000\u0000\u000023\u0005"+
		"@\u0000\u000034\u0005h\u0000\u000045\u0005e\u0000\u000056\u0005a\u0000"+
		"\u000067\u0005d\u0000\u000078\u0005e\u0000\u000089\u0005r\u0000\u0000"+
		"9\u0005\u0001\u0000\u0000\u0000:;\u0005r\u0000\u0000;<\u0005e\u0000\u0000"+
		"<=\u0005t\u0000\u0000=>\u0005u\u0000\u0000>?\u0005r\u0000\u0000?@\u0005"+
		"n\u0000\u0000@A\u0005s\u0000\u0000A\u0007\u0001\u0000\u0000\u0000BC\u0005"+
		",\u0000\u0000C\t\u0001\u0000\u0000\u0000DE\u0005=\u0000\u0000E\u000b\u0001"+
		"\u0000\u0000\u0000FG\u0005:\u0000\u0000G\r\u0001\u0000\u0000\u0000HI\u0005"+
		"|\u0000\u0000I\u000f\u0001\u0000\u0000\u0000JN\u0005\'\u0000\u0000KM\b"+
		"\u0000\u0000\u0000LK\u0001\u0000\u0000\u0000MP\u0001\u0000\u0000\u0000"+
		"NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OQ\u0001\u0000\u0000"+
		"\u0000PN\u0001\u0000\u0000\u0000QR\u0005\'\u0000\u0000R\u0011\u0001\u0000"+
		"\u0000\u0000ST\u0005;\u0000\u0000T\u0013\u0001\u0000\u0000\u0000UW\u0007"+
		"\u0001\u0000\u0000VU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000"+
		"\u0000Z[\u0006\t\u0000\u0000[\u0015\u0001\u0000\u0000\u0000\\]\u0005["+
		"\u0000\u0000]\u0017\u0001\u0000\u0000\u0000^_\u0005]\u0000\u0000_\u0019"+
		"\u0001\u0000\u0000\u0000`a\u0005-\u0000\u0000ab\u0005>\u0000\u0000b\u001b"+
		"\u0001\u0000\u0000\u0000cd\u0005s\u0000\u0000de\u0005k\u0000\u0000ef\u0005"+
		"i\u0000\u0000fg\u0005p\u0000\u0000g\u001d\u0001\u0000\u0000\u0000hl\u0007"+
		"\u0002\u0000\u0000ik\u0007\u0003\u0000\u0000ji\u0001\u0000\u0000\u0000"+
		"kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000"+
		"\u0000m\u001f\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000os\u0007"+
		"\u0004\u0000\u0000pr\u0007\u0003\u0000\u0000qp\u0001\u0000\u0000\u0000"+
		"ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000"+
		"\u0000t!\u0001\u0000\u0000\u0000us\u0001\u0000\u0000\u0000vw\u0005{\u0000"+
		"\u0000wx\u0001\u0000\u0000\u0000xy\u0006\u0010\u0001\u0000y#\u0001\u0000"+
		"\u0000\u0000z{\u0005{\u0000\u0000{|\u0001\u0000\u0000\u0000|}\u0006\u0011"+
		"\u0001\u0000}%\u0001\u0000\u0000\u0000~\u0080\b\u0005\u0000\u0000\u007f"+
		"~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u007f"+
		"\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\'\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0005}\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0086\u0006\u0013\u0002\u0000\u0086)\u0001\u0000\u0000"+
		"\u0000\u0007\u0000\u0001NXls\u0081\u0003\u0006\u0000\u0000\u0005\u0001"+
		"\u0000\u0004\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}