package edu.toronto.group0162.dao;
import java.sql.Connection;


public abstract class BaseDao<PK, T> {

    protected final Connection connection;

    public BaseDao(Connection connection) {
        this.connection = connection;
    }

//    public abstract T get(PK pk);
//
//    public abstract void delete(PK pk);
//
//    public abstract T save(T bean);

    // public abstract T update(T bean);
}
