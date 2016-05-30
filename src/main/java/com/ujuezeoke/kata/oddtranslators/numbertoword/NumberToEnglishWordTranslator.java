package com.ujuezeoke.kata.oddtranslators.numbertoword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

/**
 * Created by ujuezeoke on 30/05/2016.
 */

public class NumberToEnglishWordTranslator {
    private Map<List<Integer>, String> exponentialRangeToSuffix = buildMap();

    public String translate(int number) {
        final int log10 = (int) Math.floor(Math.log10(number));
        if (number >= 0) {

            if (log10 == 0) {
                return retrieveSingleDigit(number);
            }

            if (log10 == 1) {
                return retrieveDoubleDigit(number);
            }

            if (log10 == 2){
                return retrieveTripleDigit(number);
            }

            if (log10 > 2) {
                final int outsideTheGroupsOf3Digits = log10 % 3;
                final int exp = (int) Math.pow(10, (log10 - outsideTheGroupsOf3Digits));
                final int firstDigit = number / exp;
                final int remainingNumber = number - (exp * firstDigit);

                final String translateFirstDigits = translateFirstDigitFrom(outsideTheGroupsOf3Digits, firstDigit);
                return formatFinalResult(
                        translateFirstDigits + " " + retrieveSuffix(log10) + " " + translate(remainingNumber)
                );
            }
        }

        return "zero";
    }

    private String translateFirstDigitFrom(int outsideTheGroupsOf3Digits, int firstDigit) {
        switch (outsideTheGroupsOf3Digits) {
            case 0:
                return retrieveSingleDigit(firstDigit);
            case 1:
                return retrieveDoubleDigit(firstDigit);
            case 2:
                return retrieveTripleDigit(firstDigit);
            default:
                throw new UnsupportedOperationException("Not Yet Implemented");
        }
    }

    private String retrieveTripleDigit(int firstDigit) {
        if (firstDigit % 100 == 0) {
            return retrieveSingleDigit(firstDigit / 100) + " hundred";
        } else {
            final int firstDigitOfTheHundred = (int) (Math.floor(firstDigit / 100));
            return retrieveSingleDigit(firstDigitOfTheHundred) + " hundred " +
                    translate(firstDigit - (firstDigitOfTheHundred * 100));
        }
    }

    private String formatFinalResult(String numberAsString) {
        final String numberWithoutZero = numberAsString.replace("zero", "");

        return numberWithoutZero.trim();
    }

    private String retrieveSuffix(int exponent) {
        return exponentialRangeToSuffix.entrySet()
                .stream()
                .filter(set -> set.getKey().contains(exponent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("10^%d Not yet Implemented", exponent)))
                .getValue();
    }

    private String retrieveSingleDigit(int number) {
        switch (number) {
            case 0:
                return "";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                throw new IllegalArgumentException("Expected a single digit number");
        }


    }

    private String retrieveDoubleDigit(int number) {
        if (number % 10 == 0 && number <= 99) {
            return retrieveMultiplesOf10(number);
        } else if (number < 20 && number > 10) {
            return retrieveNumbersBetween10And20(number);
        }

        return retrieveMultiplesOf10(number - (number % 10)) + " " + retrieveSingleDigit(number % 10);
    }

    private String retrieveNumbersBetween10And20(int number) {
        switch (number) {
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            case 13:
                return "thirteen";
            case 14:
                return "fourteen";
            case 15:
                return "fifteen";
            case 16:
                return "sixteen";
            case 17:
                return "seventeen";
            case 18:
                return "eighteen";
            case 19:
                return "nineteen";
            default:
                throw new IllegalArgumentException("Got '" + number + "' Expected a double digit number from 11 to 19");
        }
    }

    private String retrieveMultiplesOf10(int number) {
        switch (number) {
            case 10:
                return "ten";
            case 20:
                return "twenty";
            case 30:
                return "thirty";
            case 40:
                return "forty";
            case 50:
                return "fifty";
            case 60:
                return "sixty";
            case 70:
                return "seventy";
            case 80:
                return "eighty";
            case 90:
                return "ninety";
            default:
                throw new IllegalArgumentException("Got '" + number + "' Expected a double digit number");
        }
    }

    private Map<List<Integer>, String> buildMap() {
        final Map<List<Integer>, String> numberRangeToLabelMap = new HashMap<>();

        numberRangeToLabelMap.put(range(2, 3).boxed().collect(toList()), "hundred");
        numberRangeToLabelMap.put(range(3, 6).boxed().collect(toList()), "thousand");
        numberRangeToLabelMap.put(range(6, 9).boxed().collect(toList()), "million");
        numberRangeToLabelMap.put(range(9, 12).boxed().collect(toList()), "billion");

        return numberRangeToLabelMap;
    }
}
