package HTTPServer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jphoenix on 8/4/16.
 */
public class InputParser {
    private HashMap params;

    public InputParser() {
        this.params = new HashMap();
    }

    public Map parse(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder input = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            input.append(line);
            if (line.isEmpty()) break;
            input.append("\r\n");
        }
        return populateParams(input.toString());
    }

    private Map populateParams(String header) throws IOException {
        if (!header.isEmpty()) {
            String[] lines = header.split("\r\n");
            String[] line = lines[0].split(" ");
            params.put("action", line[0]);
            params.put("path", line[1]);
            params.put("scheme", line[2]);
        }
        return params;
    }
}