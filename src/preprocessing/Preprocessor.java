package preprocessing;

import java.util.List;

public interface Preprocessor {
    List<String> process(String text);
}