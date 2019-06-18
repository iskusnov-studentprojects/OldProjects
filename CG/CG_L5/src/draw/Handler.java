package draw;

/**
 * Created by Sergey on 16.01.2017.
 */
public interface Handler<T,R> {
    R process(T value) throws Exception;
}
