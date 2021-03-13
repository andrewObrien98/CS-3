public class Generics<E> {

    Generics(){

    }
    public <T> void insert(E elemnt, T word){
        words<T> hello = new words<>(elemnt, word);
    }

    public <T> void print(){

    }

    private class words<T>{
        E element;
        T word;

        words(E e, T t){
            element = e;
            word = t;
        }
    }
}
