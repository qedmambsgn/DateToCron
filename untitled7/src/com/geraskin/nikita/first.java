package com.geraskin.nikita;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import com.digdes.school.DatesToCronConvertException;
import com.digdes.school.DatesToCronConverter;

import javax.swing.plaf.IconUIResource;
import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.util.Collections;

public class first implements DatesToCronConverter {
    @Override
    public String convert(List<String> dates) throws DatesToCronConvertException {
        List<GregorianCalendar> datetimeList = new ArrayList<>();
//        ArrayList<TreeSet> arrOfTrees = new ArrayList<TreeSet>();//0-seconds
        HashMap<String, TreeSet<Integer>> trees = new HashMap<>();
        SimpleDateFormat formatToDateHelper = new SimpleDateFormat(DATE_FORMAT);
        formatToDateHelper.setLenient(false);

        List<Integer> daysOfWeekList = new ArrayList<>();//лист для проверки на последний день недели, етс
        List<Integer> daysOfMonthList = new ArrayList<>();//лист для проверки на последний день месяца, етс
        //полный список
        List<Integer> secondsChecker = new ArrayList<>();
        List<Integer> minutesChecker = new ArrayList<>();
        List<Integer> hoursChecker = new ArrayList<>();
        List<Integer> daysChecker = new ArrayList<>();
        List<Integer> monthsChecker = new ArrayList<>();
        List<Integer> daysOfWeekChecker = new ArrayList<>();
        List<Integer> yearsChecker = new ArrayList<>();
        //копия дерева
        List<Integer> secondsCheckerZ = new ArrayList<>();
        List<Integer> minutesCheckerZ = new ArrayList<>();
        List<Integer> hoursCheckerZ = new ArrayList<>();
        List<Integer> daysCheckerZ = new ArrayList<>();
        List<Integer> monthsCheckerZ = new ArrayList<>();
        List<Integer> daysOfWeekCheckerZ = new ArrayList<>();
        List<Integer> yearsCheckerZ = new ArrayList<>();


        trees.put("seconds", new TreeSet<>());
        trees.put("minutes", new TreeSet<>());
        trees.put("hours", new TreeSet<>());
        trees.put("days", new TreeSet<>());
        trees.put("months", new TreeSet<>());
        trees.put("daysOfWeek", new TreeSet<>());
        trees.put("years", new TreeSet<>());
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        for (String date : dates) {
            Date dateA = null;
            try {
                dateA = formatToDateHelper.parse(date);
            } catch (ParseException e) {
                throw new DatesToCronConvertException();
            }
            assert dateA != null;
            calendar.setTime(dateA);
            System.out.println("1");
            //заполняем деревья и листы для проверки на а.п.
            trees.get("days").add(calendar.get(Calendar.DAY_OF_MONTH));
            daysChecker.add(calendar.get(Calendar.DAY_OF_MONTH));

            trees.get("daysOfWeek").add(calendar.get(Calendar.DAY_OF_WEEK));
            daysOfWeekChecker.add(calendar.get(Calendar.DAY_OF_MONTH));

            trees.get("seconds").add(calendar.get(Calendar.SECOND));
            secondsChecker.add(calendar.get(Calendar.DAY_OF_MONTH));

            trees.get("hours").add(calendar.get(Calendar.HOUR_OF_DAY));
            hoursChecker.add(calendar.get(Calendar.DAY_OF_MONTH));

            trees.get("minutes").add(calendar.get(Calendar.MINUTE));
            minutesChecker.add(calendar.get(Calendar.DAY_OF_MONTH));

            trees.get("months").add(calendar.get(Calendar.MONTH));
            monthsChecker.add(calendar.get(Calendar.DAY_OF_MONTH));

            trees.get("years").add(calendar.get(Calendar.YEAR));
            yearsChecker.add(calendar.get(Calendar.DAY_OF_MONTH));


            if (!(daysCheckerZ.contains(calendar.get(Calendar.DAY_OF_MONTH)))) {
                daysCheckerZ.add(calendar.get(Calendar.DAY_OF_MONTH));
            }


            if (!(daysOfWeekCheckerZ.contains(calendar.get(Calendar.DAY_OF_WEEK)))) {
                daysOfWeekCheckerZ.add(calendar.get(Calendar.DAY_OF_WEEK));
            }


            if (!(secondsCheckerZ.contains(calendar.get(Calendar.SECOND)))) {
                secondsCheckerZ.add(calendar.get(Calendar.SECOND));
            }


            if (!(hoursCheckerZ.contains(calendar.get(Calendar.HOUR_OF_DAY)))) {
                hoursCheckerZ.add(calendar.get(Calendar.MINUTE));
            }


            if (!(minutesCheckerZ.contains(calendar.get(Calendar.MINUTE)))) {
                minutesCheckerZ.add(calendar.get(Calendar.MONTH));
            }


            if (!(monthsCheckerZ.contains(calendar.get(Calendar.MONTH)))) {
                monthsCheckerZ.add(calendar.get(Calendar.MONTH));
            }


            if (!(yearsCheckerZ.contains(calendar.get(Calendar.YEAR)))) {
                yearsCheckerZ.add(calendar.get(Calendar.DAY_OF_MONTH));
            }
        }
        StringBuilder cronBuilder = new StringBuilder();
        String cron = "";
        //Обращение к хэшу по адресу "key"
        TreeSet<Integer> secondsFromHash = trees.get("seconds");
        TreeSet<Integer> minutesFromHash = trees.get("minutes");
        TreeSet<Integer> hoursFromHash = trees.get("hours");


        TreeSet<Integer> daysFromHash = trees.get("days");
        TreeSet<Integer> monthsFromHash = trees.get("months");
        TreeSet<Integer> daysOfWeekFromHash = trees.get("daysOfWeek");
        TreeSet<Integer> yearsFromHash = trees.get("years");

        int firstElementArithmeticProgression = 0;
        int secondElementArithmeticProgression = 0;

        int isArithmeticNumber = 0;
        int HelperFirst = 0;
        int HelperSecond = 0;
        Collections.sort(secondsCheckerZ);
        int firstAP = 0;
        int secondAP = 0;


        //секунды
        if (secondsFromHash.size() == 1) {//если везде одинак
            cron += secondsFromHash.first() + " ";
        } else if (secondsCheckerZ.size() != 0) {
            if (secondsCheckerZ.size() == 0) {
                firstAP = 0;
                secondAP = secondsCheckerZ.size();
                while (secondAP < secondsChecker.size()) {
                    if (IsArithmeticProgression(secondsChecker.subList(firstAP, secondAP)) == -1) {
                        isArithmeticNumber = -1;
                    }
                    firstAP += secondsCheckerZ.size();
                    secondAP += secondsCheckerZ.size();
                }
                if (isArithmeticNumber != -1) {
                    isArithmeticNumber = IsArithmeticProgression(secondsCheckerZ);
                    if (60 / secondsFromHash.size() == isArithmeticNumber && (secondsFromHash.size() > 2)) {
                        HelperFirst = secondsFromHash.first();
                        secondsFromHash.pollFirst();
                        cron += "*/ " + secondsFromHash.first() + " ";
                    } else if (secondsFromHash.size() <= 3) {
                        if (secondsFromHash.size() == 2) {
                            cron += HelperFirst + "/" + secondsFromHash.first() + " ";
                        } else {
                            HelperSecond = secondsFromHash.first();
                            secondsFromHash.pollFirst();
                            cron += HelperFirst + "," + HelperSecond + "," + secondsFromHash.first() + " ";
                        }
                    }
                }
            } else cron += "* ";
        }
        System.out.println("seconds");
        //минуты, см комменты у секунд
        //костыль
        if(minutesFromHash.size()==2){
            cron += minutesFromHash.first() + "-" + minutesFromHash.last() + " ";
        }else
        if (minutesFromHash.size() == 1) {//если везде одинак
            cron += minutesFromHash.first() + " ";
        } else if (minutesChecker.size() % minutesCheckerZ.size() == 0) {
            firstAP = 0;
            secondAP = minutesCheckerZ.size();
            while (secondAP < minutesChecker.size()) {
                if (IsArithmeticProgression(minutesChecker.subList(firstAP, secondAP)) == -1) {
                    isArithmeticNumber = -1;
                }
                firstAP += minutesCheckerZ.size();
                secondAP += minutesCheckerZ.size();
            }
            if (isArithmeticNumber != -1) {
                isArithmeticNumber = IsArithmeticProgression(minutesCheckerZ);
                if (60 / minutesFromHash.size() == isArithmeticNumber && (minutesFromHash.size() > 2)) {
                    HelperFirst = minutesFromHash.first();
                    minutesFromHash.pollFirst();
                    cron += "*/ " + minutesFromHash.first() + " ";
                } else if (minutesFromHash.size() <= 2) {
                    if (minutesFromHash.size() == 1) {
                        cron += HelperFirst + "/" + minutesFromHash.first() + " ";
                    } else {
                        HelperSecond = minutesFromHash.first();
                        minutesFromHash.pollFirst();
                        cron += HelperFirst + "," + HelperSecond + "," + minutesFromHash.first() + " ";
                    }
                }
            }
        } else cron += "* ";
        System.out.println("minutes");
        //часы, см комменты у секунд
        if(hoursFromHash.size() == 2){
            cron += hoursFromHash.first() + "/" + hoursFromHash.last() + " ";
        }
        else if (hoursFromHash.size() == 1) {//если везде одинак
            cron += hoursFromHash.first() + " ";
        }
        else if (hoursChecker.size() % hoursCheckerZ.size() == 0) {
            firstAP = 0;
            secondAP = hoursCheckerZ.size();
            System.out.println("hours1");

            while (secondAP < hoursChecker.size()) {
                if (IsArithmeticProgression(hoursChecker.subList(firstAP, secondAP)) == -1) {
                    isArithmeticNumber = -1;
                }
                firstAP += hoursCheckerZ.size();
                secondAP += hoursCheckerZ.size();
            }
            System.out.println("hours1");

            if (isArithmeticNumber != -1) {
                System.out.println("hours1");
                isArithmeticNumber = IsArithmeticProgression(secondsCheckerZ);
                if ((hoursFromHash.size() > 3)) {
                    System.out.println("hours1");
                    HelperFirst = hoursFromHash.first();

                    cron += "*/ " + hoursFromHash.first() + " ";
                } else{
                    System.out.println("hours1");
                    if (hoursFromHash.size() == 2) {
                        cron += HelperFirst + "-" + hoursFromHash.first() + " ";
                    } else {
                        HelperSecond = hoursFromHash.first();
                        hoursFromHash.pollFirst();
                        cron += HelperFirst + "," + HelperSecond + "," + hoursFromHash.first() + " ";
                    }
                }
            }
        } else cron += "* ";
        System.out.println("hours1");
        //дни, см комменты у секунд (добавь обработку месяца)
        System.out.println("hours2");
        if(daysFromHash.size() == 2){
            cron += daysFromHash.first() + "/" + daysFromHash.last() + " ";
        }
        else if (daysFromHash.size() == 1) {//если везде одинак
            cron += daysFromHash.first() + " ";
        } else if (daysChecker.size() % daysCheckerZ.size() == 0) {
            firstAP = 0;
            secondAP = daysCheckerZ.size();
            while (secondAP < daysCheckerZ.size()) {
                if (IsArithmeticProgression(daysChecker.subList(firstAP, secondAP)) == -1) {
                    isArithmeticNumber = -1;
                }
                firstAP += daysCheckerZ.size();
                secondAP += daysCheckerZ.size();
            }
            if (isArithmeticNumber != -1) {
                isArithmeticNumber = IsArithmeticProgression(secondsCheckerZ);
                if ((daysFromHash.size() > 2)) {
                    HelperFirst = daysFromHash.first();
                    daysFromHash.pollFirst();
                    cron += "*/ " + daysFromHash.first() + " ";
                } else if (daysFromHash.size() <= 3) {
                    if (daysFromHash.size() == 2) {
                        cron += HelperFirst + "/" + daysFromHash.first() + " ";
                    } else {
                        HelperSecond = daysFromHash.first();
                        daysFromHash.pollFirst();
                        cron += HelperFirst + "," + HelperSecond + "," + daysFromHash.first() + " ";
                    }
                }
            }
        } else cron += "* ";
        //месяца, см комменты у секунд
        System.out.println("days1");
        if (monthsFromHash.size() == 1) {//если везде одинак
            cron += monthsFromHash.first() + " ";
        } else if (monthsChecker.size() % monthsCheckerZ.size() == 0) {
            firstAP = 0;
            secondAP = monthsCheckerZ.size();
            while (secondAP < monthsChecker.size()) {
                if (IsArithmeticProgression(monthsChecker.subList(firstAP, secondAP)) == -1) {
                    isArithmeticNumber = -1;
                }
                firstAP += monthsCheckerZ.size();
                secondAP += monthsCheckerZ.size();
            }
            if (isArithmeticNumber != -1) {
                isArithmeticNumber = IsArithmeticProgression(monthsCheckerZ);
                if (12 / monthsFromHash.size() == isArithmeticNumber && (monthsFromHash.size() > 2)) {
                    HelperFirst = monthsFromHash.first();
                    monthsFromHash.pollFirst();
                    cron += "*/ " + monthsFromHash.first() + " ";
                } else if (monthsFromHash.size() <= 3) {
                    if (monthsFromHash.size() == 2) {
                        cron += HelperFirst + "/" + monthsFromHash.first() + " ";
                    } else {
                        HelperSecond = monthsFromHash.first();
                        monthsFromHash.pollFirst();
                        cron += HelperFirst + "," + HelperSecond + "," + monthsFromHash.first() + " ";
                    }
                }
            }
        } else cron += "* ";
        //*************************************************************************************************************
        // Работает с днями недели
        if (daysOfWeekFromHash.size() == 1) {
            cron += dayToString(daysOfWeekFromHash.first());
        } else if (daysOfWeekFromHash.size() > 1) {
            cron += "* ";
        } else if (daysOfWeekFromHash.size() > 7) {
            cron += dayToString(daysOfWeekFromHash.first()) + "-" + dayToString(daysOfWeekFromHash.last());
        } else {
            throw new DatesToCronConvertException();
        }

        return cron;
    }

    @Override
    public String getImplementationInfo() {
        return "Гераскин Никита " + getClass().getSimpleName() + " " + getClass().getPackage().getName()
                + " " + "https://github.com/qedmambsgn/DateToCron";
    }

    private String dayToString(int day) {
        String result = null;
        switch (day) {
            case 2:
                result = "MON";
                break;
            case 3:
                result = "TUE";
                break;
            case 4:
                result = "WED";
                break;
            case 5:
                result = "THU";
                break;
            case 6:
                result = "FRI";
                break;
            case 7:
                result = "SUT";
                break;
            case 8:
                result = "SUN";
                break;
            default:
                break;
        }
        return result;
    }

    public int IsArithmeticProgression(List<Integer> listForCheck) throws DatesToCronConvertException {
        for (int i = 1; i < listForCheck.size() - 1; i++) {
            if (listForCheck.get(i) - listForCheck.get(i - 1) != listForCheck.get(i + 1) - listForCheck.get(i)) {
//                System.out.println(i + "vivod -1");
                return -1;
            }
        }
        return (listForCheck.get(1) - listForCheck.get(0));
    }

    public static void main(String[] args) throws DatesToCronConvertException {
        //не добавил работы с last, etc.
        //не добавил оптимальной работы с днями недели
        //при необходимости могу обновить код/объяснить как именно это реализовать (с использованием Date)
        //подобное реализовывал в тестовом для frontend вашей школы, но работая с одной датой, а не массивом
        //также в программе есть лишние переменные, словарь деревьев был прописан для первоначального способа решения, который оказался неверен
        //также прогрессия должна быть написана по принципу сравнения элементов, если во входном файле подаётся 8899, тогда функция прогрессии
        //должна принимать 4 числа и идти по 2 итераторам:
        //1) от начала листа до /2
        //2)/2+1
        //и сравнивать эти элементы
        //аналогично для 6, 8, etc.
        //причина всего выше перечисленного - ожидал меньше случаев, которые нужно рассмотреть.

        List<String> dateListFirst = new ArrayList<>();
        List<String> dateListSecond = new ArrayList<>();


        dateListFirst.add("2022-01-25T08:00:00");
        dateListFirst.add("2022-01-25T08:30:00");
        dateListFirst.add("2022-01-25T09:00:00");
        dateListFirst.add("2022-01-25T09:30:00");
        dateListFirst.add("2022-01-26T08:00:00");
        dateListFirst.add("2022-01-26T08:30:00");
        dateListFirst.add("2022-01-26T09:00:00");
        dateListFirst.add("2022-01-26T09:30:00");

        dateListSecond.add("2022-01-24T19:53:00");
        dateListSecond.add("2022-01-24T19:54:00");
        dateListSecond.add("2022-01-24T19:55:00");
        dateListSecond.add("2022-01-24T19:56:00");
        dateListSecond.add("2022-01-24T19:57:00");
        dateListSecond.add("2022-01-24T19:58:00");
        dateListSecond.add("2022-01-24T19:59:00");
        dateListSecond.add("2022-01-24T20:00:00");
        dateListSecond.add("2022-01-24T20:01:00");
        dateListSecond.add("2022-01-24T20:02:00");


        first testMyFunctions = new first();
        System.out.println("cron = " + testMyFunctions.convert(dateListFirst));
        System.out.println("cron = " + testMyFunctions.convert(dateListSecond));
    }
}