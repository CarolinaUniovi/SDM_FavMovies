package com.example.favmovies.datos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.favmovies.modelo.InterpretePeliculaCrossRef;

@Dao
public interface InterpretePeliculaCrossRefDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(InterpretePeliculaCrossRef interpretePeliculaCrossRef);
}
