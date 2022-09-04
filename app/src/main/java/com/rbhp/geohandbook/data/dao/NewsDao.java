package com.rbhp.geohandbook.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rbhp.geohandbook.data.entities.NewsEntity;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("select * from NewsEntity")
    List<NewsEntity> getAll();
    @Insert
    void insertAll(List<NewsEntity> entities);
    @Query("delete from NewsEntity")
    void deleteAll();
}
