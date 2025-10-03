package eclipse_plugin_test.parts.util;

public final class JSONHelper {
	public static String prettyPrintJson(String json) {
	    StringBuilder pretty = new StringBuilder();
	    int indent = 0;
	    boolean inString = false;
	    for (char c : json.toCharArray()) {
	        switch (c) {
	            case '{', '[' -> {
	                pretty.append(c).append('\n');
	                indent++;
	                pretty.append("  ".repeat(indent));
	            }
	            case '}', ']' -> {
	                pretty.append('\n');
	                indent--;
	                pretty.append("  ".repeat(indent)).append(c);
	            }
	            case ',' -> {
	                pretty.append(c).append('\n');
	                pretty.append("  ".repeat(indent));
	            }
	            case ':' -> {
	            	pretty.append(c);
	            	if (!inString) {
	            		pretty.append(' ');
	            	}
	            }
	            case '"' -> {
	            	pretty.append(c);
	            	inString = !inString;
	            }
	            default -> pretty.append(c);
	        }
	    }
	    return pretty.toString();
	}
}
