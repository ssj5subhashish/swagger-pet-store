package util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Randomizer {

    private final static Pattern PATTERN = Pattern.compile("\\$\\{[^\\$\\}]+\\}");

    private final Map<String, String> values;
    private final Random random;

    public Randomizer() {
        this.values = new HashMap<>();
        this.random = new Random();
    }

    public String get(String key) {
        if (key == null || !PATTERN.matcher(key).find()) {
            return key;
        } else {
            return transform(key);
        }
    }

    private String transform(String key) {
        String r = key;
        Matcher m = PATTERN.matcher(key);
        while (m.find()) {
            String k = m.group();
            if (k.equals("${currentDateISO}")) {
                r = r.replace(k, Utils.getCurrentFormattedDateISO());
                continue;
            }
            if (!values.containsKey(k)) {
                values.put(k, Integer.toString(random.nextInt(Integer.MAX_VALUE)));
            }
            r = r.replace(k, values.get(k));
        }
        return r;
    }

    @Override
    public String toString() {
        return "Randomizer{" + "values=" + values + '}';
    }
}
