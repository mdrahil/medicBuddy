package com.cricbuzz.medicbuddy.repository.repos;

import com.cricbuzz.medicbuddy.db.AlarmsDao;
import com.cricbuzz.medicbuddy.db.AppDb;
import com.cricbuzz.medicbuddy.repository.common.AppExecutors;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReportRepository {

    private AppDb db;
    private AppExecutors executors;
    private AlarmsDao alarmsDao;


    @Inject
    public ReportRepository(AppDb db, AlarmsDao alarmsDao, AppExecutors executors) {
        this.db = db;
        this.executors = executors;
        this.alarmsDao = alarmsDao;
    }

}
