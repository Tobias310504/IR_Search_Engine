package booleanquery;

import core.InvertedIndex;
import java.util.Set;

public interface QueryExpression {
    Set<Integer> evaluate(InvertedIndex index, Set<Integer> allDocumentIds);
}