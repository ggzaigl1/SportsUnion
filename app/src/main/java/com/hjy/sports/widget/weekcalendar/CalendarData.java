package com.hjy.sports.widget.weekcalendar;

public class CalendarData {

    public int year;
    public int month;
    public int day;
    public int week = -1;
    public String weeks;
    public boolean isSelect = false;
    public boolean isNextMonthDay = false;//是否是下个月中的日期
    public boolean isLastMonthDay = false;//是否是上个月中的日期

    public CalendarData() {
    }

    public CalendarData(int year, int month, int day, String week) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weeks = week;
    }

    public CalendarData(int year, int month, int day, String week,boolean isSelect) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weeks = week;
        this.isSelect =isSelect;
    }

    public boolean isSameDay(CalendarData data) {
        return (data.day == day && data.month == month && data.year == year);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "CalendarData{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", week=" + week +
                ", isNextMonthDay=" + isNextMonthDay +
                ", isLastMonthDay=" + isLastMonthDay +
                '}';
    }
}
