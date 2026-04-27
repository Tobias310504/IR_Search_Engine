package tolerant;

import core.InvertedIndex;
import java.util.Set;

public class WildcardSearchStrategy implements TolerantSearchStrategy {

    @Override
    public Set<Integer> search(String query, InvertedIndex index) {
        // TODO:
        // 1. Cek posisi "*"
        // 2. Ambil prefix/suffix sesuai kebutuhan
        // 3. Cocokkan query dengan vocabulary
        // 4. Gabungkan posting list dari term yang cocok
        throw new UnsupportedOperationException("TODO");
    }
}