package models.parsers;

public interface SetterFunction<T, U> {
    public void set(T object, U param);
}
