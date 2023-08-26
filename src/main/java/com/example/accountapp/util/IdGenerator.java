package com.example.accountapp.util;

import java.util.Random;

public class IdGenerator {
    private final static long maxID = 5000000L;
    public static long generateID(){
        Random random = new Random();
        long number = random.nextLong();

        return number;
    }
}
