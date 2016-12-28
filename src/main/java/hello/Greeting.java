package hello;

import java.util.HashMap;
import java.util.Map;

public class Greeting {

    private final long id;
    private final String content;
    private Map<String, String> data;

    public Map<String, String> getData() {   	
		return data;
	}

	public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
        Map<String, String> map1 = new HashMap<String, String>();
    	map1.put("key1", "value1");
    	map1.put("key2", "value2");
    	data = map1;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
