package com.tecnocampus.blackjack.utilities;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public static int MIN_PASS_LENTGH=8;
    public static int MIN_PASS_NUMERIC_CHAR=1;
    public static int MAX_PLAYING_MATCHES=3;
    public static int TARGET_PLAYING_SCORE=21;
}
