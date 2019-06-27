package com.example.day01.utils;

import com.example.day01.app.MyApp;
import com.example.day01.bean.ListBean;
import com.example.day01.dao.DaoMaster;
import com.example.day01.dao.ListBeanDao;

import java.util.List;

public class DbUtils {

    private static DbUtils dbUtils;
    private final ListBeanDao dao;

    private DbUtils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getApp(), "ls.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        dao = daoMaster.newSession().getListBeanDao();
    }

    public static DbUtils getDbUtils() {
        if (dbUtils == null) {
            synchronized (DbUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new DbUtils();
                }
            }
        }
        return dbUtils;
    }

    public void insert(ListBean bean) {
        if (has(bean)) {
            return;
        }
        dao.insertOrReplace(bean);
    }

    public void delete(ListBean bean) {
        if (has(bean)) {
            dao.delete(bean);
        }
    }

    public List<ListBean> query() {
        return dao.queryBuilder().list();
    }

    public boolean has(ListBean bean) {
        List<ListBean> list = dao.queryBuilder().where(ListBeanDao.Properties.Title.eq(bean.getTitle()), ListBeanDao.Properties.ChapterName.eq(bean.getChapterName()),
                ListBeanDao.Properties.EnvelopePic.eq(bean.getEnvelopePic())).list();
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
