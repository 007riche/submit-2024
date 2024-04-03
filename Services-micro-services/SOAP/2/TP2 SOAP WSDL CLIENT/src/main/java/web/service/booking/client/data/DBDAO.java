package web.service.booking.client.data;


import java.util.List;

public interface DBDAO<T> {

    boolean createTable() ;
    List<T> getAll() ;
    boolean add(T object) ;

    boolean update(T object) ;
    List<T> get(String sqlQuery) ;

    List<T> get(String sqlQuery, Object... args);

    boolean dropTable() ;

}
