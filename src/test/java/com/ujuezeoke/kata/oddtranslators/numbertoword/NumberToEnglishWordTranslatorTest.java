package com.ujuezeoke.kata.oddtranslators.numbertoword;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ujuezeoke on 30/05/2016.
 */
public class NumberToEnglishWordTranslatorTest {

    private NumberToEnglishWordTranslator underTest = new NumberToEnglishWordTranslator();

    @Test
    public void translateZero() {
        assertThat(underTest.translate(0), is("zero"));
    }

    @Test
    public void translateSingleDigits() {
        assertThat(underTest.translate(1), is("one"));
        assertThat(underTest.translate(2), is("two"));
        assertThat(underTest.translate(3), is("three"));
        assertThat(underTest.translate(4), is("four"));
        assertThat(underTest.translate(5), is("five"));
        assertThat(underTest.translate(6), is("six"));
        assertThat(underTest.translate(7), is("seven"));
        assertThat(underTest.translate(8), is("eight"));
        assertThat(underTest.translate(9), is("nine"));
    }

    @Test
    public void translate2digitMultiplesOf10() {
        assertThat(underTest.translate(10), is("ten"));
        assertThat(underTest.translate(20), is("twenty"));
        assertThat(underTest.translate(30), is("thirty"));
        assertThat(underTest.translate(40), is("forty"));
        assertThat(underTest.translate(50), is("fifty"));
        assertThat(underTest.translate(60), is("sixty"));
        assertThat(underTest.translate(70), is("seventy"));
        assertThat(underTest.translate(80), is("eighty"));
        assertThat(underTest.translate(90), is("ninety"));
    }

    @Test
    public void translate2digitsNoneMultiplesOf10Below20() {
        assertThat(underTest.translate(11), is("eleven"));
        assertThat(underTest.translate(12), is("twelve"));
        assertThat(underTest.translate(13), is("thirteen"));
        assertThat(underTest.translate(14), is("fourteen"));
        assertThat(underTest.translate(15), is("fifteen"));
        assertThat(underTest.translate(16), is("sixteen"));
        assertThat(underTest.translate(17), is("seventeen"));
        assertThat(underTest.translate(18), is("eighteen"));
        assertThat(underTest.translate(19), is("nineteen"));
    }

    @Test
    public void translate2DigitsAbove19() {
        assertThat(underTest.translate(21), is("twenty one"));
        assertThat(underTest.translate(39), is("thirty nine"));
        assertThat(underTest.translate(45), is("forty five"));
        assertThat(underTest.translate(57), is("fifty seven"));
        assertThat(underTest.translate(66), is("sixty six"));
        assertThat(underTest.translate(72), is("seventy two"));
        assertThat(underTest.translate(84), is("eighty four"));
        assertThat(underTest.translate(93), is("ninety three"));
    }

    @Test
    public void translateHundreds() {
        assertThat(underTest.translate(100), is("one hundred"));
        assertThat(underTest.translate(200), is("two hundred"));
        assertThat(underTest.translate(300), is("three hundred"));
        assertThat(underTest.translate(900), is("nine hundred"));
    }

    @Test
    public void translateHundredsNoneMultiplesOf10() {
        assertThat(underTest.translate(101), is("one hundred one"));
        assertThat(underTest.translate(220), is("two hundred twenty"));
        assertThat(underTest.translate(348), is("three hundred forty eight"));
        assertThat(underTest.translate(912), is("nine hundred twelve"));
    }

    @Test
    public void translateThousands() {
        assertThat(underTest.translate(5000), is("five thousand"));
        assertThat(underTest.translate(7002), is("seven thousand two"));
        assertThat(underTest.translate(2300), is("two thousand three hundred"));
         assertThat(underTest.translate(8210), is("eight thousand two hundred ten"));
        assertThat(underTest.translate(4234), is("four thousand two hundred thirty four"));
    }

    @Test
    public void tensOfThousands() {
        assertThat(underTest.translate(15000), is("fifteen thousand"));
        assertThat(underTest.translate(17002), is("seventeen thousand two"));
        assertThat(underTest.translate(23000), is("twenty three thousand"));
        assertThat(underTest.translate(18210), is("eighteen thousand two hundred ten"));
        assertThat(underTest.translate(94234), is("ninety four thousand two hundred thirty four"));
    }

    @Test
    public void hundredOfThousands() {
        assertThat(underTest.translate(115000), is("one hundred fifteen thousand"));
        assertThat(underTest.translate(115845), is("one hundred fifteen thousand eight hundred forty five"));
    }

    @Test
    public void worksUptoMaximumInteger() {
        assertThat(underTest.translate(Integer.MAX_VALUE),
                is("two billion one hundred forty seven million " +
                        "four hundred eighty three thousand six hundred forty seven"));
    }
}
