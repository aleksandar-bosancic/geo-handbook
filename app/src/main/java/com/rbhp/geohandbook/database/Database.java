package com.rbhp.geohandbook.database;

import androidx.room.RoomDatabase;

import com.rbhp.geohandbook.data.NewsData;

@androidx.room.Database(entities = {NewsData.class}, version = 1)
public abstract class Database extends RoomDatabase {
}
