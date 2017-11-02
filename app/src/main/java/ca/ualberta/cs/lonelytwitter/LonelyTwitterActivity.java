package ca.ualberta.cs.lonelytwitter;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import lonelytweet.ImportantLonelyTweet;
import lonelytweet.LonelyTweet;
import lonelytweet.NormalLonelyTweet;

public class LonelyTwitterActivity extends Activity {

	private EditText bodyText;
	private ListView oldTweetsList;

	private List<LonelyTweet> tweets;
	private ArrayAdapter<LonelyTweet> adapter;
	private TweetsFileManager tweetsProvider;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweetsProvider = new TweetsFileManager(this);
		tweets = tweetsProvider.loadTweets();
		adapter = new ArrayAdapter<LonelyTweet>(this, R.layout.list_item,
				tweets);
		oldTweetsList.setAdapter(adapter);
	}

	public void save(View v) {
		String text = bodyText.getText().toString();

		//TODO: use different sub-classes (Normal or Important) based on usage of "*" in the text.
		//Fix: Instead of using hardcoded '*' use the one in @strings resource
		if(text.contains(getResources().getString(R.string.important_identifier))){
			ImportantLonelyTweet importantTweet = new ImportantLonelyTweet(text,new Date());

			if (importantTweet.isValid()) {
				tweets.add(importantTweet);
				adapter.notifyDataSetChanged();

				bodyText.setText("");
				tweetsProvider.saveTweets(tweets);
			} else {
				//Fix: Instead of using hardcoded 'Invalid tweet' use the one in @strings resource
				Toast.makeText(this, getResources().getString(R.string.invalid_tweet), Toast.LENGTH_SHORT).show();
			}
		}else{
			NormalLonelyTweet normalTweet = new NormalLonelyTweet(text,new Date());

			if (normalTweet.isValid()) {
				tweets.add(normalTweet);
				adapter.notifyDataSetChanged();

				bodyText.setText("");
				tweetsProvider.saveTweets(tweets);
			} else {
				//Fix: Instead of using hardcoded 'Invalid tweet' use the one in @strings resource
				Toast.makeText(this, getResources().getString(R.string.invalid_tweet), Toast.LENGTH_SHORT).show();
			}
		}

	}

	public void clear(View v) {
		tweets.clear();
		adapter.notifyDataSetChanged();
		tweetsProvider.saveTweets(tweets);
	}

	public void setTweets(LonelyTweet tweet){
		tweets.add(tweet);
	}

	public List<LonelyTweet> getTweets(){
		return tweets;
	}

}
