package eclipse_plugin_test.parts.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JSONHelperTest {
	@Test public void testPrettyPrint() throws Exception {
		String test_json = "[{\"id\":1,\"name\":\"Outdated dependency\",\"severity\":\"medium\",\"updatedAt\":\"2025-08-20T10:15:00Z\"},{\"id\":2,\""
				+ "name\":\"SQL injection risk\",\"severity\":\"high\",\"updatedAt\":"
				+ "\"2025-08-21T08:03:00Z\"},{\"id\":3,\"name\":\"Weak TLS settings\",\""
				+ "severity\":\"low\",\"updatedAt\":\"2025-08-22T12:45:00Z\"}]";
		String prettyfied_json = JSONHelper.prettyPrintJson(test_json);
		String expected_json = "[\n"
				+ "  {\n"
				+ "    \"id\": 1,\n"
				+ "    \"name\": \"Outdated dependency\",\n"
				+ "    \"severity\": \"medium\",\n"
				+ "    \"updatedAt\": \"2025-08-20T10:15:00Z\"\n"
				+ "  },\n"
				+ "  {\n"
				+ "    \"id\": 2,\n"
				+ "    \"name\": \"SQL injection risk\",\n"
				+ "    \"severity\": \"high\",\n"
				+ "    \"updatedAt\": \"2025-08-21T08:03:00Z\"\n"
				+ "  },\n"
				+ "  {\n"
				+ "    \"id\": 3,\n"
				+ "    \"name\": \"Weak TLS settings\",\n"
				+ "    \"severity\": \"low\",\n"
				+ "    \"updatedAt\": \"2025-08-22T12:45:00Z\"\n"
				+ "  }\n"
				+ "]";
		
		assertEquals(expected_json, prettyfied_json);
	}
}
