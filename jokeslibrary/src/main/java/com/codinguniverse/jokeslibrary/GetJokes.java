package com.codinguniverse.jokeslibrary;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GetJokes {
    Random random = new Random();
    public Joke getJokeRandomly() {
        return new Joke(pickRandomJoke());
    }

    String pickRandomJoke() {
        List<String> jokes = Arrays
                .asList("I threw a boomerang a few year ago. I now live in constant fear.",
                        "You don't need a parachute to go to skydiving. You need a parachute to go skydiving twice.",
                        "Someone stolen my mood ring, I don't know how I feel about that.",
                        "You are not completely useless, you can always serve as a bad example.",
                        "I broke my finger last week. On the other hand, I'm okay.",
                        "Working on a mirror factory is something I can totally see myself doing.",
                        "How does moses make his coffee? \n \n Hebrews it.",
                        "Why do cow wear bells? \n\n because there their horns don't work.");


        int randomIndex = random.nextInt(jokes.size());
        return jokes.get(randomIndex);

    }
}
