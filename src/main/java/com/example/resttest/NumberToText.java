package com.example.resttest;

public class NumberToText {

    public static boolean correct;
    private static String result;
    private static final String[][] textDigit ={{"","","",""},
            {"миллиардов ","миллионов ","тысяч ",""},
            {"миллиард ","миллион ","тысяча ",""},
            {"миллиарда ","миллиона ","тысячи ",""},
            {"миллиардов ","миллионов ","тысяч ",""}};

    private static final String[][] allCombination ={ {"","од","дв","три","четыре","пять","шесть","семь","восемь","девять"},
            {"", "десять " ,"двадцать ","тридцать ","сорок ","пятьдесят ","шестьдесят ","семьдесят ","восемьдесят ","девяносто "},
            {"","сто ","двести ","триста ","четыреста ","пятьсот ","шестьсот ","семьсот ","восемьсот ","девятьсот "} };

    private static final String[] numbers10to19 = { "десять ", "одинадцать ", "двенадцать ", "тринадцать ", "четырнадцать ","пятнадцать ",
            "шеснадцать ", "семьнадцать ", "восемьнадцать ", "девятнадцать ", "девятнадцать "} ;



    public static String words(long number) {

        result ="";

        long numberMax = 999999999999L;
        if ( number > numberMax || number<0) {
            correct=false;
            return  result = "Error";
        }
        if (number == 0 ) {
            return  result = "ноль ";
        }


        int billion = (int) (number / 1000000000);
        int million = (int) (number - (billion * 1000000000)) / 1000000;
        int thousand = (int) (number - (billion * 1000000000) - (million * 1000000)) / 1000;
        int lessThousand = (int) (number % 1000);


        result = result + partsOfWords(billion, 0)+ partsOfWords(million, 1)+ partsOfWords(thousand, 2)+ partsOfWords(lessThousand, 3);
        return result;

    }

    private static String partsOfWords(int numericalValue , int index ){

        int hundreds = numericalValue / 100;

        int tens = (numericalValue - (hundreds * 100)) / 10;

        int ones = numericalValue % 10;


        result = "";
        if ( tens == 1 ) result =  allCombination[2] [hundreds] + numbers10to19[ones];
        else result =  allCombination[2] [hundreds] +  allCombination[1][tens] +  allCombination[0] [ones];

        // формируем окончания в единицах
        if (index == 2) {
            if (ones == 1 && tens != 1) result = result + "на ";
            else if (ones == 2 & tens != 1) result = result + "е ";
            if (ones > 1 && tens != 1) result = result + " ";
        }
        else {
            if (ones == 1 && tens != 1) result = result + "ин ";
            if (ones == 2 & tens != 1) {
                result = result + "а ";
            }
            else if (ones != 0 & tens != 1) result = result + " ";
        }

        // Получаем группу числа (тысячи,миллоны ...)
        int indexOfGroup = 0;
        if (numericalValue != 0 ) {
            if (ones == 0 || tens == 1 )
                indexOfGroup = 1;
            else if (ones == 1)
                indexOfGroup = 2;
            else if (ones > 1 & ones < 5)
                indexOfGroup = 3;
            else
                indexOfGroup = 4;
        }
        result = result + textDigit[indexOfGroup][index];
        return result;
    }
}
