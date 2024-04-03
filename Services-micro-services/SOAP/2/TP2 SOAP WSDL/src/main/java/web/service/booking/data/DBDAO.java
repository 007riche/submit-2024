package web.service.booking.data;

import web.service.booking.models.Booking;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DBDAO<T> {

    boolean createTable() ;
    List<T> getAll() ;
    boolean add(T object) ;

    boolean update(T object) ;
    List<T> get(String sqlQuery) ;

    List<T> get(String sqlQuery, Object... args);

    boolean dropTableOrDB(String tableOrDB) ;

}
