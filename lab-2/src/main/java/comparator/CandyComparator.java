package comparator;

import candy.Root;

import java.util.Comparator;

public class CandyComparator implements Comparator<Root.Candy> {
    @Override
    public int compare(Root.Candy c1, Root.Candy c2) {
        return c1.getType().compareTo(c2.getType());
    }
}