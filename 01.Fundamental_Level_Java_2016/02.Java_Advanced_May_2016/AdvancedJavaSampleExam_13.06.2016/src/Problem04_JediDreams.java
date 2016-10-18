import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem04_JediDreams {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.valueOf(reader.readLine());
        List<String> codeLines = new ArrayList<>();

        for (int i = 0; i < numberOfLines; i++) {
            codeLines.add(reader.readLine());
        }

        TreeMap<String, List<String>> extractedMethods = new TreeMap<>();

        Pattern patternDeclaration = Pattern.compile("(?=static).+?([A-Z]+[A-Za-z]*) *\\( *");
        Pattern patternInner = Pattern.compile(".?([A-Z]+[A-Za-z]*) *\\( *");
        Matcher matcherDeclaration;
        Matcher matcherInner;

        for (int i = 0; i < codeLines.size(); i++) {
                String declaration = codeLines.get(i);
                matcherDeclaration = patternDeclaration.matcher(declaration);
                if (matcherDeclaration.find()) {
                    String method = matcherDeclaration.group(1);
                    if (!extractedMethods.containsKey(method)) {
                        extractedMethods.put(method, new ArrayList<>());
                    }

                    int counter = i + 1;
                    if (counter >= codeLines.size()) {
                        break;
                    }
                    matcherDeclaration = patternDeclaration.matcher(codeLines.get(counter));
                    while (counter < codeLines.size() && !matcherDeclaration.find()) {
                        matcherInner = patternInner.matcher(codeLines.get(counter));
                        while (matcherInner.find()) {
                            extractedMethods.get(method).add(matcherInner.group(1));
                        }
                        counter++;
                        if (counter >= codeLines.size()) {
                            break;
                        }
                        matcherDeclaration = patternDeclaration.matcher(codeLines.get(counter));
                    }
                    i = counter - 1;
                }
        }
        for (List<String> methods : extractedMethods.values()) {
            Collections.sort(methods);
        }
        extractedMethods.entrySet().stream()
                .sorted((v1, v2) -> Integer.compare(v2.getValue().size(), v1.getValue().size()))
                .forEach(method -> {
                    //if (method.getValue().size() > 0) {
                        System.out.printf("%s -> %d -> %s%n", method.getKey(), method.getValue().size()
                                , method.getValue().toString().replace("[", "").replace("]", ""));
//                    } else {
//                        System.out.printf("%s -> None%n");
//                    }
                });



    }
}
