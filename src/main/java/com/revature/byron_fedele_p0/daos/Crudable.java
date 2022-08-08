package com.revature.byron_fedele_p0.daos;
import com.revature.byron_fedele_p0.util.customCollections.List;

public interface Crudable<T> { // generic <T> we can use to assign as
    // Generics help insure type saftey


    // Create
    T create(T newObject);

    //Read
    List<T> findAll();
    T findById(String id);

    // Update
    boolean update(T updatedObject);

    //Delete
    boolean delete(String id);

}