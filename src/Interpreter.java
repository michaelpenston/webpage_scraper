import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter {

    private String[] findHref(String[] lines, String regex) {
        ArrayList<String> urls = new ArrayList<String>();

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String textOutput = matcher.group();
                urls.add(textOutput);
            }
        }
        return urls.toArray(String[]::new);
    }

    public String[] findUrls(String[] lines) {
        return this.findHref(lines, "href=(\"|')(http:|https:)([^\"']*)(\"|')");
    }

    public String[] findEmails(String[] lines) {
        return this.findHref(lines, "href=(\"|')(mailto:)([^\"']*)(\"|')");
    }

    public String[] findName(String[] lines) {
        return this.findHref(lines, "property=\"name\"([^\"']*)<");
    }
}
