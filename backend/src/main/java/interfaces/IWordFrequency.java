package interfaces;

public interface IWordFrequency extends Comparable {
    String getWord();

    int getFrequency();

    @Override
    int compareTo(Object object);
}
