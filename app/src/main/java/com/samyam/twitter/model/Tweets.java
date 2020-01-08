package com.samyam.twitter.model;

public class Tweets {
    private String tweet, image;

    public Tweets(String tweet, String image) {
        this.tweet = tweet;
        this.image = image;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
