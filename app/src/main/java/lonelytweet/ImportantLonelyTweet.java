package lonelytweet;

import java.util.Date;

import lonelytweet.LonelyTweet;

/**
 * Created by StevenC on 2017-11-01.
 */

public class ImportantLonelyTweet extends LonelyTweet {

    public ImportantLonelyTweet(String text, Date date) {
        super(text, date);
    }

    public String getTweetBody() {
        return tweetBody.toUpperCase();
    }

    public boolean isValid() {
        if (tweetBody.trim().length() == 0
                || tweetBody.trim().length() > 20) {
            return false;
        }

        return true;
    }
}
