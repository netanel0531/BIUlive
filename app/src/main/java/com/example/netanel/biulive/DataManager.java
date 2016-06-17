package com.example.netanel.biulive;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.EncodingUtils;

public class DataManager {


    public static String average = "";
    public static Boolean finishLoading = false;
    private static Boolean finishScheduale = false;
    private static Boolean finishGrades = false;
    public static void loadAll() {

        final RequestParams params = new RequestParams();
        params.put("yearValue", "%FA%F9%F2%22%E5");
        params.put("yearSelected", "40");
        ServerRequests.get("HourScheduale.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);

                Pattern pattern = Pattern.compile("\\d\\d\\-\\d\\d\\d\\-\\d\\d");
                Matcher matcher = pattern.matcher(res);
                List<String> listMatches = new ArrayList<String>();
                String current = "";
                while (matcher.find()) {
                    current = matcher.group();
                    listMatches.add(current);
                }
                ArrayList<String> c = new ArrayList<String>();
                for (Course course: MainActivity.courses) {
                    c.add(course.getCourseNumber());
                }
                for (String s : listMatches) {
                    if (!c.contains(s)) {
                        c.add(s);
                        MainActivity.courses.add(new Course(s));
                    }
                }
                /*
                for (String s : listMatches) {
                    MainActivity.courses.add(new Course(s));
                }*/


                for (final Course course : MainActivity.courses) {

                    final String[] parts = course.getCourseNumber().split("-");

                    RequestParams courseTimeParams = new RequestParams();

                    String url = "CourseInfo.jsp?year=%FA%F9%F2%22%E5";
                    url += "&CourseId1=" + parts[0];
                    url += "&CourseId2=" + parts[1];
                    url += "&CourseGrup=" + parts[2];
                    url += "&sum=";
                    ServerRequests.post(url,null, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess ( int statusCode, Header[] headers,byte[] responseBody){
                            String res = EncodingUtils.getString(responseBody,"iso-8859-8");
                            String response = res.replaceAll("\\s","");
                            int len = "<tdalign=center>".length();
                            int semeterIndex = response.indexOf("<tdalign=center>")+len;
                            int dayIndex = response.indexOf("<tdalign=center>", semeterIndex+1)+len;
                            int hoursIndex = response.indexOf("<tdalign=center>", dayIndex+1)+len;
                            int buildingIndex = response.indexOf("<tdalign=center>", hoursIndex+1)+len;
                            int builidngEndIndex = response.indexOf("<", buildingIndex);
                            int classroomIndex = response.indexOf("<tdalign=center>", builidngEndIndex+1)+len;
                            int classroomEndIndex = response.indexOf("<", classroomIndex);
                            int len2 = "xx:xx-xx:xx".length();
                            String courseNum = parts[0]+"-"+parts[1]+"-"+parts[2];

                            course.setCourseBuilding(response.substring(buildingIndex, builidngEndIndex));
                            course.setCourseClass(response.substring(classroomIndex, classroomEndIndex));

                            course.setCourseDay(response.substring(dayIndex, dayIndex+1));
                            course.setCourseStartHour(response.substring(hoursIndex, hoursIndex + 5));
                            course.setCourseEndHour(response.substring(hoursIndex + 6, hoursIndex + 11));
                            course.setCourseSemester(response.substring(semeterIndex, semeterIndex+1) );

                        }

                        @Override
                        public void onFailure ( int statusCode, Header[] headers,
                                                byte[] responseBody, Throwable error){
                            //       textView.append("failed\n");
                        }

                    });
                    HashMap<String, String> paramMap = new HashMap<>();
                    // String[] ids = couresNum.split("-");
                    paramMap.put("CourseId1", parts[0]);
                    paramMap.put("CourseId2", parts[1]);
                    paramMap.put("CourseGrup", parts[2]);
                    RequestParams params = new RequestParams(paramMap);
                    ServerRequests.post("FinalGradeInfo.jsp", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String res = EncodingUtils.getString(responseBody,"iso-8859-8");
                            res = res.replaceAll("\\s","");
                /*
                if (res.contains("<pdir=rtlalign=right>")) {
                    return;
                }
                */
                            Pattern pattern = Pattern.compile("\\d\\d\\d\\d\\/\\d\\d\\/\\d\\d");
                            Matcher matcher = pattern.matcher(res);
                            List<String> listMatches = new ArrayList<String>();
                            while (matcher.find()) {
                                listMatches.add(matcher.group());
                            }
                            int listSize = listMatches.size();
                            if (listSize < 1) {
                                return;
                            }
                            int lastGradeIndex = res.indexOf(listMatches.get(listSize-1));
                            int len = "<tdalign=center>".length();
                            int len3 = "xxxx/xx/xx".length();
                            int i = 0;
                            int index = lastGradeIndex+len+len3;
                            String grade = "";
                            while (res.charAt(index+i) != '<') {
                                grade += res.substring(index + i, index + i + 1);
                                i++;
                            }
                            if (grade.length() > 1) {
                                course.setCourseGrade(Integer.parseInt(grade));
                            }
                            //textView.append(grade+"\n");

                        }


                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            course.setCourseGrade(3);
                        }
                    });
                    retrieveGradeComponents(course);
                }

            }

            @Override
            public void onFailure ( int statusCode, Header[] headers,byte[] responseBody, Throwable
                    error){
                //  textView.append("not working");
            }
            @Override
            public void onFinish() {
                retrieveExams();

            }
        });
        retrieveAverage();
    }
    public static void retrieveAllGrades() {

    }
    public static void retrieveAverage() {
        //TODO timer
        ServerRequests.get("memutsaBeynaim.jsp",null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);
                Document doc = Jsoup.parse(res);
                Elements elms = doc.getElementsByTag("span");

                average = elms.get(1).text();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                average = "not working";
            }
        });
    }
    public static void retrieveCourseFinalGrade() {


        for (final Course course: MainActivity.courses) {
            String couresNum = course.getCourseNumber();
            final int[] gradeInt = new int[1];
            HashMap<String, String> paramMap = new HashMap<>();
            String[] ids = couresNum.split("-");
            paramMap.put("CourseId1", ids[0]);
            paramMap.put("CourseId2", ids[1]);
            paramMap.put("CourseGrup", ids[2]);
            RequestParams params = new RequestParams(paramMap);
            ServerRequests.post("FinalGradeInfo.jsp", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String res = EncodingUtils.getString(responseBody,"iso-8859-8");
                    res = res.replaceAll("\\s","");
                /*
                if (res.contains("<pdir=rtlalign=right>")) {
                    return;
                }
                */
                    Pattern pattern = Pattern.compile("\\d\\d\\d\\d\\/\\d\\d\\/\\d\\d");
                    Matcher matcher = pattern.matcher(res);
                    List<String> listMatches = new ArrayList<String>();
                    while (matcher.find()) {
                        listMatches.add(matcher.group());
                    }
                    int listSize = listMatches.size();
                    if (listSize < 1) {
                        return;
                    }
                    int lastGradeIndex = res.indexOf(listMatches.get(listSize-1));
                    int len = "<tdalign=center>".length();
                    int len3 = "xxxx/xx/xx".length();
                    int i = 0;
                    int index = lastGradeIndex+len+len3;
                    String grade = "";
                    while (res.charAt(index+i) != '<') {
                        grade += res.substring(index + i, index + i + 1);
                        i++;
                    }
                    if (grade.length() > 1) {
                        course.setCourseGrade(Integer.parseInt(grade));
                    }
                    //textView.append(grade+"\n");

                }


                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    course.setCourseGrade(3);
                }
            });
        }
    }

    public static void retrieveCourseGrades() {

    }

    public static void retrieveSchedule() {
        final RequestParams params = new RequestParams();
        params.put("yearValue", "%FA%F9%F2%22%E5");
        params.put("yearSelected", "40");
        ServerRequests.get("HourScheduale.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = new String(responseBody);

                Pattern pattern = Pattern.compile("\\d\\d\\-\\d\\d\\d\\-\\d\\d");
                Matcher matcher = pattern.matcher(res);
                List<String> listMatches = new ArrayList<String>();
                String current = "";
                while (matcher.find()) {
                    current = matcher.group();
                    listMatches.add(current);
                }

                ArrayList<String> c = new ArrayList<String>();
                for (Course course: MainActivity.courses) {
                    c.add(course.getCourseNumber());
                }
                for (String s : listMatches) {
                    if (!c.contains(s)) {
                        c.add(s);
                        MainActivity.courses.add(new Course(s));
                    }
                }

                for (final Course course : MainActivity.courses) {

                    final String[] parts = course.getCourseNumber().split("-");

                    RequestParams courseTimeParams = new RequestParams();

                    String url = "CourseInfo.jsp?year=%FA%F9%F2%22%E5";
                    url += "&CourseId1=" + parts[0];
                    url += "&CourseId2=" + parts[1];
                    url += "&CourseGrup=" + parts[2];
                    url += "&sum=";
                    ServerRequests.post(url,null, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess ( int statusCode, Header[] headers,byte[] responseBody){
                            String res = EncodingUtils.getString(responseBody,"iso-8859-8");
                            String response = res.replaceAll("\\s","");
                            int len = "<tdalign=center>".length();
                            int semeterIndex = response.indexOf("<tdalign=center>")+len;
                            int dayIndex = response.indexOf("<tdalign=center>", semeterIndex+1)+len;
                            int hoursIndex = response.indexOf("<tdalign=center>", dayIndex+1)+len;
                            int buildingIndex = response.indexOf("<tdalign=center>", hoursIndex+1)+len;
                            int builidngEndIndex = response.indexOf("<", buildingIndex);
                            int classroomIndex = response.indexOf("<tdalign=center>", builidngEndIndex+1)+len;
                            int classroomEndIndex = response.indexOf("<", classroomIndex);
                            int len2 = "xx:xx-xx:xx".length();
                            String courseNum = parts[0]+"-"+parts[1]+"-"+parts[2];

                            course.setCourseBuilding(response.substring(buildingIndex, builidngEndIndex));
                            course.setCourseClass(response.substring(classroomIndex, classroomEndIndex));


                            course.setCourseDay(response.substring(dayIndex, dayIndex+1));
                            course.setCourseStartHour(response.substring(hoursIndex, hoursIndex + 5));
                            course.setCourseEndHour(response.substring(hoursIndex + 6, hoursIndex + 11));
                            course.setCourseSemester(response.substring(semeterIndex, semeterIndex+1) );
/*
                            textView.append(course.getCourseNumber()+":\n");
                            textView.append("semester = " + course.getCourseSemester() + "\n");
                            textView.append("day = " + course.getCourseDay() + "\n");
                            textView.append("hours = " + course.getCourseStartHour() + " - "
                                    + course.getCourseEndHour() + "\n");
                            textView.append("building = " + course.getCourseBuilding() + "\n");
                            textView.append("class = " + course.getCourseClass() + "\n");
*/
                        }

                        @Override
                        public void onFailure ( int statusCode, Header[] headers,
                                                byte[] responseBody, Throwable error){
                            //       textView.append("failed\n");
                        }
                    });
                }

            }

            @Override
            public void onFailure ( int statusCode, Header[] headers,byte[] responseBody, Throwable
                    error){
                //  textView.append("not working");
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void retrieveExams() {
        HashMap<String, String> paramMap1 = new HashMap<>();
        paramMap1.put("year", Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - 1940));
        RequestParams params = new RequestParams(paramMap1);
        ServerRequests.post("ExamDates.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = EncodingUtils.getString(responseBody, "iso-8859-8");
                res = res.replaceAll("\\s","");
                res = res.replaceAll("&nbsp;","");
                String courseNum;
                String courseName;
                String moedADate;
                String moedATime;
                String moedBDate;
                String moedBTime;
                String semester = "";

                String tableStartSign = "height=2";
                int tableStart = 0;
                while (res.indexOf(tableStartSign, tableStart+1) > -1) {
                    tableStart = res.indexOf(tableStartSign, tableStart+1);
                }
                String rawStart = "<tr>";
                String cellStart = "<tdalign=center>";
                String cellEnd = "</td>";
                String tableEnd = "</table>";
                String passed = "<fontcolor=\"D18700\">";


                int tableEndIndex = res.indexOf(tableEnd, tableStart + 1);
                int nextRaw = res.indexOf(rawStart, tableStart + 1);
                int nextCell = res.indexOf(cellStart, nextRaw);

                int curInfoStart = 0;
                int curInfoEnd = 0;
                System.out.println(nextRaw);

                while (tableEndIndex > nextRaw) {
                    System.out.println();

                    //first cell
                    curInfoStart = res.indexOf(cellStart, nextRaw) + cellStart.length();
                    /*
                    //check if first cell as a semester info
                    if (res.charAt(curInfoStart) != '&') {
                        semester = "" + res.charAt(curInfoStart);
                    }
                    System.out.println("Semester: " + semester);
                    */

                    //course number cell
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    courseNum = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("course num: " + res.substring(curInfoStart, curInfoEnd));

                    //course name cell
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    courseName = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("course name: " + res.substring(curInfoStart, curInfoEnd));

                    //course lecturer
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    System.out.println("lacturer: " + res.substring(curInfoStart, curInfoEnd));

                    //first test date
                    /*
                     * date of course. may contain the string:<fontcolor="D18700"> if the date passed.
                     * so TODO add a check for this addition.
                     */
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, nextCell) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, nextCell);
                    if (res.charAt(curInfoStart) == '<') {
                        curInfoStart += passed.length();
                    }
                    moedADate = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed A date: " + res.substring(curInfoStart, curInfoEnd));

                    //first test time
                    //TODO as above
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, curInfoEnd) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, curInfoStart);
                    if (res.charAt(curInfoStart) == '<') {
                        curInfoStart += passed.length();
                    }
                    moedATime = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed A hour: " + res.substring(curInfoStart, curInfoEnd));


                    //second test date
                    //TODO as above
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, curInfoEnd) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, curInfoStart);
                    if (res.charAt(curInfoStart) == '<') {
                        curInfoStart += passed.length();
                    }
                    moedBDate = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed B date: " + res.substring(curInfoStart, curInfoEnd));

                    //second test time
                    //TODO as above
                    nextCell = res.indexOf(cellStart, curInfoStart);
                    curInfoStart = res.indexOf(cellStart, curInfoEnd) + cellStart.length();
                    curInfoEnd = res.indexOf(cellEnd, curInfoStart);
                    if (res.charAt(curInfoStart) == '<') {
                        curInfoStart += passed.length();
                    }
                    moedBTime = res.substring(curInfoStart, curInfoEnd);
                    System.out.println("moed B hour: " + res.substring(curInfoStart, curInfoEnd));


                    if (res.indexOf(cellStart, curInfoStart) > -1) {
                        nextRaw = res.indexOf(rawStart, nextCell);
                        nextRaw = res.indexOf(rawStart, nextRaw);
                    } else {
                        nextRaw = tableEndIndex + 1;
                    }

                    for (Course course: MainActivity.courses) {
                        if (course.getCourseNumber().equals(courseNum)) {
                            course.setCourseName(courseName);
                            course.setMoedA(moedADate, moedATime, "א");
                            course.setMoedB(moedBDate, moedBTime, "ב");
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private static void retrieveGradeComponents(final Course course) {
        String courseNum = course.getCourseNumber();
        HashMap<String, String> paramMap = new HashMap<>();
        String[] ids = courseNum.split("-");
        paramMap.put("CourseId1", ids[0]);
        paramMap.put("CourseId2", ids[1]);
        paramMap.put("CourseGrup", ids[2]);
        RequestParams params = new RequestParams(paramMap);
        ServerRequests.get("ziunBchina.jsp", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String res = EncodingUtils.getString(responseBody, "iso-8859-8");
                res = res.replaceAll("\\s","");
                int head = res.indexOf("<thalign=\"right\"width=");
                int precTd = res.indexOf("<td", head);
                String[] tasks = res.split("<thvalign=bottomalign=centercolspan=8>");
                int i;
                String text;
                for (i = 2; i < tasks.length-1; i++) {
                    text = tasks[i];

                    //get Matala
                    int firstTd = text.indexOf("<td");
                    while (text.charAt(firstTd)!='>') {
                        firstTd++;
                    }
                    firstTd++;
                    String matala = "";
                    while (text.charAt(firstTd) != '<') {
                        matala += text.substring(firstTd, firstTd + 1);
                        firstTd++;
                    }

                    //the percentage info
                    precTd = text.indexOf("<tddir", firstTd);
                    precTd += "<tddir=\"ltr\"align=\"right\">".length();
                    String prec = "";
                    while (text.charAt(precTd) != '<') {
                        prec += text.substring(precTd, precTd+1);
                        precTd++;
                    }

                    //the Moed info
                    firstTd = text.indexOf("<td>", precTd);
                    firstTd += "<td>".length();
                    String moed = "";
                    while (text.charAt(firstTd) != '<') {
                        moed += text.substring(firstTd, firstTd+1);
                        firstTd++;
                    }

                    //the Grade info
                    precTd = text.indexOf("<TDalign", firstTd);
                    precTd += "<TDalign=\"right\"dir=\"ltr\">".length();
                    String grade = "";
                    while (text.charAt(precTd) != '<') {
                        grade += text.substring(precTd, precTd+1);
                        precTd++;
                    }

                    //the Date info
                    precTd = text.indexOf("<TDalign", precTd);
                    precTd += "<TDalign=\"right\"dir=\"ltr\">".length();
                    String date = "";
                    while (text.charAt(precTd) != '<') {
                        date += text.substring(precTd, precTd+1);
                        precTd++;
                    }
                    GradeComponent component = new GradeComponent();
                    component.setType(matala);
                    component.setDate(date);
                    component.setGrade(grade);
                    component.setMoed(moed);
                    component.setPrecents(prec);
                    course.getGradeComponents().add(component);
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
            @Override
            public void onFinish() {
                /*
                course.setListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = new GradesFragment().getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.add(R.id.gradeCompFragment,
                                GradeComponentsFragment.newInstance(course.getCourseNumber(), ""));
                        ft.addToBackStack("menu");
                        ft.commit();
                    }
                });*/
            }
        });
    }
}
