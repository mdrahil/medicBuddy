package com.cricbuzz.medicbuddy.repository.repos;

import android.arch.lifecycle.LiveData;

import com.cricbuzz.medicbuddy.db.AlarmsDao;
import com.cricbuzz.medicbuddy.db.AppDb;
import com.cricbuzz.medicbuddy.models.Alarms;
import com.cricbuzz.medicbuddy.repository.common.AppExecutors;
import com.cricbuzz.medicbuddy.ui.report.ReportQuery;

import java.util.List;

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

    public LiveData<List<Alarms>> loadReport(ReportQuery query) {
        return alarmsDao.loadAlarms();
    }
}
