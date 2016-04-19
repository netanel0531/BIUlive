package com.example.netanel.biulive;

import java.util.Iterator;
import java.util.TreeMap;

public class DatesManager {


    public static final int CAPTCHA_INPUT = 4;
    public static final int HAS_CAPTCHA = 7;
    public static final int HIDDEN_FIELD = 4;
    public static final int IDENTITY_FIELD = 5;
    public static final int ID_NUMBER_INPUT = 1;
    public static final int INSTITUTE_ID = 1;
    public static final int INSTITUTE_LOGO = 2;
    public static final int INSTITUTE_NAME = 3;
    public static final int NONE = 0;
    public static final int PASSWORD_INPUT = 3;
    public static final int PASSWORD_KEYBOARD_TYPE = 6;
    public static final int SEMESTER_END_1 = 1;
    public static final int SEMESTER_END_2 = 3;
    public static final int SEMESTER_END_3 = 5;
    public static final int SEMESTER_START_1 = 0;
    public static final int SEMESTER_START_2 = 2;
    public static final int SEMESTER_START_3 = 4;

    public static TreeMap<String, TreeMap<String, Integer>> semesterDatesForInstitute(int paramInt)
    {
        TreeMap localTreeMap1 = new TreeMap();
        TreeMap localTreeMap2 = new TreeMap();
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(0));
        localTreeMap2.put("to", Integer.valueOf(1330819200));
        localTreeMap1.put("1_2012", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1330819200));
        localTreeMap2.put("to", Integer.valueOf(1350777600));
        localTreeMap1.put("2_2012", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1350777600));
        localTreeMap2.put("to", Integer.valueOf(1361836800));
        localTreeMap1.put("1_2013", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1361836800));
        localTreeMap2.put("to", Integer.valueOf(1381017600));
        localTreeMap1.put("2_2013", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1381017600));
        localTreeMap2.put("to", Integer.valueOf(1393113600));
        localTreeMap1.put("1_2014", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1393113600));
        localTreeMap2.put("to", Integer.valueOf(1413676800));
        localTreeMap1.put("2_2014", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1413676800));
        localTreeMap2.put("to", Integer.valueOf(1425772800));
        localTreeMap1.put("1_2015", localTreeMap2);
        localTreeMap2 = new TreeMap();
        localTreeMap2.put("from", Integer.valueOf(1425772800));
        localTreeMap2.put("to", Integer.valueOf(Integer.MAX_VALUE));
        localTreeMap1.put("2_2015", localTreeMap2);
        return localTreeMap1;

    }

    public static int semesterNumberForInstituteAndTimestamp(int paramInt, long paramLong)
    {
        long l = paramLong;
        if (paramLong == -1L) {
            l = System.currentTimeMillis();
        }
        int i = (int)(l / 1000L);
        TreeMap localTreeMap1 = semesterDatesForInstitute(paramInt);
        if (localTreeMap1 == null) {
            return -1;
        }
        Iterator localIterator = localTreeMap1.keySet().iterator();
        while (localIterator.hasNext())
        {
            String str = (String)localIterator.next();
            TreeMap localTreeMap2 = (TreeMap)localTreeMap1.get(str);
            if ((i >= ((Integer)localTreeMap2.get("from")).intValue()) && (i < ((Integer)localTreeMap2.get("to")).intValue())) {
                return Integer.parseInt(str.split("_")[0]);
            }
        }
        return -1;
    }

    public static int yearForInstituteAndTimestamp(int paramInt, long paramLong)
    {
        long l = paramLong;
        if (paramLong == -1L) {
            l = System.currentTimeMillis();
        }
        int i = (int)(l / 1000L);
        TreeMap localTreeMap1 = semesterDatesForInstitute(paramInt);
        if (localTreeMap1 == null) {
            return -1;
        }
        Iterator localIterator = localTreeMap1.keySet().iterator();
        while (localIterator.hasNext())
        {
            String str = (String)localIterator.next();
            TreeMap localTreeMap2 = (TreeMap)localTreeMap1.get(str);
            if ((i >= ((Integer)localTreeMap2.get("from")).intValue()) && (i < ((Integer)localTreeMap2.get("to")).intValue())) {
                return Integer.parseInt(str.split("_")[1]);
            }
        }
        return -1;
    }
}
