package com.example.favmovies.datos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.favmovies.modelo.Interprete;

import java.util.List;

@Dao
public interface InterpreteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Interprete interprete);

    @Update
    void update(Interprete interprete);

    @Delete
    void delete(Interprete interprete);

    @Query("SELECT * FROM interpretes")
    List<Interprete> getAll();

    @Query("SELECT * FROM interpretes WHERE id = (:interpreteId)")
    Interprete findById(int interpreteId);
}
