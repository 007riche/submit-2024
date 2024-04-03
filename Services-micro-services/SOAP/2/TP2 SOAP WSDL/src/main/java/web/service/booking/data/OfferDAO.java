package web.service.booking.data;

import web.service.booking.models.Offer;

import java.util.List;
import java.util.stream.Stream;

public class OfferDAO implements DBDAO<Offer>{

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public List<Offer> getAll()  {
        return null;
    }

    @Override
    public boolean add(Offer object) {
        return false;
    }

    @Override
    public boolean update(Offer object) {
        return false;
    }

    @Override
    public List<Offer> get(String sqlQuery) {
        return null;
    }

    @Override
    public List<Offer> get(String sqlQuery, Object... args) {
        return null;
    }

    @Override
    public boolean dropTableOrDB(String tableOrDB) {
        return false;
    }

//    @Override
//    public boolean dropTable() {
//        return false;
//    }


}
