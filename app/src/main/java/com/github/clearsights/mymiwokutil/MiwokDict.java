package com.github.clearsights.mymiwokutil;

import com.github.clearsights.mymiwokstudy.R;

public class MiwokDict {
    // numbers
    private static String[] numbersInEng = new String[]{
            "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten"
    };
    private static String[] numbersInMiwok = new String[]{
            "lutti", "otiiko", "tolookosu", "oyyisa", "massokka",
            "temmokka", "kenekaku", "kawinta", "wo’e", "na’aacha"
    };
    private static int[] numbersImgRes = new int[] {
            R.drawable.number_one,
            R.drawable.number_two,
            R.drawable.number_three,
            R.drawable.number_four,
            R.drawable.number_five,
            R.drawable.number_six,
            R.drawable.number_seven,
            R.drawable.number_eight,
            R.drawable.number_nine,
            R.drawable.number_ten
    };
    private static int[] numbersAudioRes = new int[] {
            R.raw.number_one,
            R.raw.number_two,
            R.raw.number_three,
            R.raw.number_four,
            R.raw.number_five,
            R.raw.number_six,
            R.raw.number_seven,
            R.raw.number_eight,
            R.raw.number_nine,
            R.raw.number_ten
    };

    // family
    private static String[] familyInEng = new String[]{
            "father", "mother", "son", "daughter", "older brother",
            "younger brother", "older sister", "younger sister", "grandmother", "grandfather"
    };
    private static String[] familyInMiwok = new String[]{
            "әpә", "әṭa", "angsi", "tune", "taachi",
            "chalitti", "teṭe", "kolliti", "ama", "paapa"
    };
    private static int[] familyImgRes = new int[] {
            R.drawable.family_father,
            R.drawable.family_mother,
            R.drawable.family_son,
            R.drawable.family_daughter,
            R.drawable.family_older_brother,
            R.drawable.family_younger_brother,
            R.drawable.family_older_sister,
            R.drawable.family_younger_sister,
            R.drawable.family_grandmother,
            R.drawable.family_grandfather
    };
    private static int[] familyAudioRes = new int[] {
            R.raw.family_father,
            R.raw.family_mother,
            R.raw.family_son,
            R.raw.family_daughter,
            R.raw.family_older_brother,
            R.raw.family_younger_brother,
            R.raw.family_older_sister,
            R.raw.family_younger_sister,
            R.raw.family_grandmother,
            R.raw.family_grandfather
    };

    // colors
    private static String[] colorInEng = new String[]{
            "red", "green", "brown", "gray",
            "black", "white", "dusty yellow", "mustard yellow"
    };
    private static String[] colorInMiwok = new String[]{
            "weṭeṭṭi", "chokokki", "ṭakaakki", "ṭopoppi",
            "kululli", "kelelli", "ṭopiisә", "chiwiiṭә"
    };
    private static int[] colorImgRes = new int[] {
            R.drawable.color_red,
            R.drawable.color_green,
            R.drawable.color_brown,
            R.drawable.color_gray,
            R.drawable.color_black,
            R.drawable.color_white,
            R.drawable.color_dusty_yellow,
            R.drawable.color_mustard_yellow,
    };
    private static int[] colorAudioRes = new int[] {
            R.raw.color_red,
            R.raw.color_green,
            R.raw.color_brown,
            R.raw.color_gray,
            R.raw.color_black,
            R.raw.color_white,
            R.raw.color_dusty_yellow,
            R.raw.color_mustard_yellow,
    };

    // Phrases
    private static String[] phrasesInEng = new String[]{
            "Where are you going?",
            "What is your name?",
            "My name is...",
            "How are you feeling?",
            "I’m feeling good.",
            "Are you coming?",
            "Yes, I’m coming.",
            "I’m coming.",
            "Let’s go.",
            "Come here."
    };
    private static String[] phrasesInMiwok = new String[]{
            "minto wuksus",
            "tinnә oyaase'nә",
            "oyaaset...",
            "michәksәs?",
            "kuchi achit",
            "әәnәs'aa?",
            "hәә’ әәnәm",
            "әәnәm",
            "yoowutis",
            "әnni'nem"
    };
    private static int[] phrasesAudioRes = new int[] {
            R.raw.phrase_where_are_you_going,
            R.raw.phrase_what_is_your_name,
            R.raw.phrase_my_name_is,
            R.raw.phrase_how_are_you_feeling,
            R.raw.phrase_im_feeling_good,
            R.raw.phrase_are_you_coming,
            R.raw.phrase_yes_im_coming,
            R.raw.phrase_im_coming,
            R.raw.phrase_lets_go,
            R.raw.phrase_come_here
    };

    public static String[] getNumbersInEng() {
        return numbersInEng;
    }

    public static String[] getNumbersInMiwok() {
        return numbersInMiwok;
    }

    public static int[] getNumbersImgRes() {
        return numbersImgRes;
    }

    public static String[] getFamilyInEng() {
        return familyInEng;
    }

    public static String[] getFamilyInMiwok() {
        return familyInMiwok;
    }

    public static int[] getFamilyImgRes() {
        return familyImgRes;
    }

    public static String[] getColorInEng() {
        return colorInEng;
    }

    public static String[] getColorInMiwok() {
        return colorInMiwok;
    }

    public static int[] getColorImgRes() {
        return colorImgRes;
    }

    public static String[] getPhrasesInEng() {
        return phrasesInEng;
    }

    public static String[] getPhrasesInMiwok() {
        return phrasesInMiwok;
    }

    public static int[] getNumbersAudioRes() {
        return numbersAudioRes;
    }

    public static int[] getFamilyAudioRes() {
        return familyAudioRes;
    }

    public static int[] getColorAudioRes() {
        return colorAudioRes;
    }

    public static int[] getPhrasesAudioRes() {
        return phrasesAudioRes;
    }
}
