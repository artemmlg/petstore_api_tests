package io.swagger.petstore.utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class PetStoreUtils {

    public static Faker nameGenerator = new Faker();

    public static int idGenerator() {
        Random ran = new Random();
        String id = String.format("%04d", ran.nextInt(10000));
        return Integer.valueOf(id);
    }
}
