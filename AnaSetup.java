import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

import java.io.File;
import java.io.IOException;

public class AnaSetup {
	public static void launch(){
	
	}
	
	//st_date, en_date must be in format mm-dd-yyyy
	public static void fetchSess(String st_date, String en_date){
		String TB_id = "UA-84848653-1"; //Tracking ID for Test Taker
		Get apiQuery = analytics.data().ga()
			    .get(TB_id,                  // Table Id.
			        st_date,              // Start date.
			        en_date,              // End date.
			        "ga:sessions")             // Metrics -- for this particular method, sessions
	}
}


