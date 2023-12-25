package com.example.resttest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TextToNumber {


    public static boolean correct;
    private static final String[][] allCombination = {

            {"", "од", "дв", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"},
            {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"},
            {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"}
    };
    private static final String[][] formsWorld={
            {"один","одна"},
            {"два","две"}
    };
    private static final String[] numbers10to19 = { "десять", "одинадцать", "двенадцать",
            "тринадцать", "четырнадцать","пятнадцать", "шеснадцать", "семьнадцать",
            "восемьнадцать", "девятнадцать", "девятнадцать"} ;

    private static final String[][] textDigit = {{"", "", "", ""},
            {"миллиардов", "миллионов", "тысяч", ""},
            {"миллиард", "миллион", "тысяча", ""},
            {"миллиарда", "миллиона", "тысячи", ""}
            };


    private static ArrayList<String> templist = new ArrayList<>();

    public static Long Convert(String string) {

        correct=true;
        string=string.toLowerCase();
        ArrayList<String> splitString = new ArrayList<String>(Arrays.asList(string.split(" ")));
        ArrayList<String> resultArray=new ArrayList<>();
        System.out.println("Массив  " + splitString.toString());


        if(splitString.size()==1 && splitString.get(0).equals("ноль")){
            return 0L;
        }
        for (int i = 0; i < splitString.size(); i++) {
            if (haveName(0, splitString.get(i))) {
                templist = new ArrayList<>(splitString.subList(0, i));
                splitString = new ArrayList<>(splitString.subList(i + 1, splitString.size()));
                System.out.println("Слова миллиардов" + templist);


                break;
            }

        }

        resultArray.add(groupsNumber(templist));
        templist=new ArrayList<>();
        for (int i = 0; i < splitString.size(); i++) {
            if (haveName(1, splitString.get(i))) {
                templist = new ArrayList<>(splitString.subList(0, i));
                splitString = new ArrayList<>(splitString.subList(i + 1, splitString.size()));
                System.out.println("Слова миллионов" + templist);


                break;
            }
        }
        resultArray.add(groupsNumber(templist));
        templist=new ArrayList<>();
        for (int i = 0; i < splitString.size(); i++) {
            if (haveName(2, splitString.get(i))) {
                templist = new ArrayList<>(splitString.subList(0, i));
                splitString = new ArrayList<>(splitString.subList(i + 1, splitString.size()));
                System.out.println("Слова тысяч" + templist);

                break;
            }
        }
        resultArray.add(groupsNumber(templist));
        System.out.println("Остаток" + splitString);
        resultArray.add(groupsNumber(splitString));



        System.out.println("Остаток" + resultArray);
        addZeros(resultArray);

        System.out.println("Остаток" + resultArray);

        String numberAsString = resultArray.get(0)+resultArray.get(1)+resultArray.get(2)+resultArray.get(3);
        //убрать
        return Long.parseLong(numberAsString);
    }

    public static boolean haveName(int index, String text) {
        for (int i = 0; i < 4; i++) {

            String name = textDigit[i][index];

            if (Objects.equals(name, text)) {
                return true;
            }
        }
        return false;
    }


    private static String groupsNumber(ArrayList<String> groupNumber){

        if(groupNumber.isEmpty())return "000";
        int result=0;
        //проверка и добавление сотен
        if(findInAllCombination(groupNumber.get(0),2)!=-1){
            result+=findInAllCombination(groupNumber.get(0),2)*100;
            if(groupNumber.size()==1)return String.valueOf(result);


            if(findInAllCombination(groupNumber.get(1),1)!=-1 ){//проверка или добавление десятков
                result+=(findInAllCombination(groupNumber.get(1),1)*10);
                if(groupNumber.size()==2)return String.valueOf(result);

                if(findInAllCombination(groupNumber.get(2),0)!=-1){//проверка или добавление единиц
                    //учесть падежи один и два
                    result+=findInAllCombination(groupNumber.get(2),0);
                    return String.valueOf(result);
                }

            } else if (findInAllCombination(groupNumber.get(1),0)!=-1) {//проверка или добавление единиц
                //учесть падежи один и два
                result+=findInAllCombination(groupNumber.get(1),0);
                return String.valueOf(result);

            } else if (findInNumber11to19(groupNumber.get(1))!=-1) {//проверка или добавление "неудобных" числел
                result+=findInNumber11to19(groupNumber.get(1))+10;
                return String.valueOf(result);
            }
        }
        else if(findInAllCombination(groupNumber.get(0),1)!=-1){
            result+=findInAllCombination(groupNumber.get(0),1)*10;
            if(groupNumber.size()==1)return String.valueOf(result);

            //Мейби тут не нужен иф
            if(findInAllCombination(groupNumber.get(1),0)!=-1){
                result+=findInAllCombination(groupNumber.get(1),0);
            }
        } else if (findInNumber11to19(groupNumber.get(0))!=-1) {
            result+=findInNumber11to19(groupNumber.get(0))+10;
            return String.valueOf(result);
        }
        else {
            result+=findInAllCombination(groupNumber.get(0),0);
        }

        return String.valueOf(result);





    }
    private static int findInAllCombination(String text,int digit){
        for(int i=0;i<allCombination[digit].length;i++){
            if(text.equals(allCombination[digit][i])){
                return i;
            }
        }
        if((text.equals(formsWorld[0][0]) || text.equals(formsWorld[0][1]))&&digit==0) return 1;
        if((text.equals(formsWorld[1][0]) || text.equals(formsWorld[1][1]))&&digit==0) return 2;
        return -1;
    }
    private static int findInNumber11to19(String text){
        for(int i=0;i<numbers10to19.length;i++){
            if(text.equals(numbers10to19[i])){
                return i;
            }
        }
        return -1;
    }
    private static ArrayList<String> addZeros(ArrayList<String> numbers){
        for (int i = 0; i < numbers.size(); i++) {
            String originalString = numbers.get(i);
            // Добавление нулей в начало строки, пока длина строки не станет равной 3
            while (originalString.length() < 3) {
                originalString = "0" + originalString;
            }
            numbers.set(i, originalString);
        }
        return numbers;
    }
 /*   static private boolean isCorrect(ArrayList<String> allWords){
        for (String word:allWords) {
            for(int i=0;i<allCombination.length;i++){
            //нверное условие поиска combinatio и have
                if(findInNumber11to19(word)==-1 && findInAllCombination(word,i)==-1 && !haveName(i,word))
                    return false;
            }

        }
        return true;

    }*/
}
