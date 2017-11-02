package lonelytweet;

import java.util.Date;

import lonelytweet.LonelyTweet;

/**
 * Created by StevenC on 2017-11-01.
 */

public class NormalLonelyTweet extends LonelyTweet {

    public NormalLonelyTweet(String text, Date date) {
        super(text, date);
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public boolean isValid() {
        if (tweetBody.trim().length() == 0
                || tweetBody.trim().length() > 10) {
            return false;
        }

        return true;
    }
}
