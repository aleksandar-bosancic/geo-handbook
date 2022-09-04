package com.rbhp.geohandbook.database;

import androidx.room.RoomDatabase;

import com.rbhp.geohandbook.data.dao.NewsDao;
import com.rbhp.geohandbook.data.entities.NewsEntity;

@androidx.room.Database(entities = {NewsEntity.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract NewsDao newsDao();
}
