package dinchi.org.moviedatabase;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainScreenActivity extends AppCompatActivity {

    /*
    * API reference
    *
    * 1.    Get Popular movies  -    https://api.themoviedb.org/3/movie/popular?api_key=4d70e983ef0c4a5e94f1e36ebb3e54ba&language=en-US&page=1
    * 2.    Get Top Rated       -    https://api.themoviedb.org/3/movie/top_rated?api_key=4d70e983ef0c4a5e94f1e36ebb3e54ba&language=en-US&page=1
    * 3.    Get Upcoming movies -    https://api.themoviedb.org/3/movie/upcoming?api_key=4d70e983ef0c4a5e94f1e36ebb3e54ba&language=en-US&page=1
    * 4.    Genre list          -   https://api.themoviedb.org/3/genre/movie/list?api_key=4d70e983ef0c4a5e94f1e36ebb3e54ba&language=en-US
    * 5.    Movie list Drama    -   https://api.themoviedb.org/3/genre/18/movies?api_key=4d70e983ef0c4a5e94f1e36ebb3e54ba&language=en-US&include_adult=false&sort_by=created_at.asc
    * 6.    http://www.omdbapi.com/?s=Batman&page=2
    * */

    private RecyclerView recyclerView;
    private ListView listmovies;
    private MovieAdapter movieAdapter;
    private ArrayList<MovieModel> movieList;
    private static final String MOVIE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=4d70e983ef0c4a5e94f1e36ebb3e54ba&language=en-US&page=1";

    InputStream inputStream;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    HttpURLConnection urlConnection;
    StringBuilder builder;
    String TAG = "MainScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

//        recyclerView = (RecyclerView)findViewById(R.id.movie_list_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listmovies = (ListView)findViewById(R.id.listMovies);
        movieList = new ArrayList<>();
        Log.i(TAG,"Before executing the Async task");
        new GetMovies().execute(MOVIE_URL);             //Execute the Async task
    }

    private class GetMovies extends AsyncTask<String,Void,Integer>{

        //This snippet is executed on UI Thread
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG,"onPreExecute");
        }

        //This is executed on Non-UI Thread
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Log.i(TAG,"Inside doInBackground");
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                //read the response from the API
                builder = new StringBuilder();
                String inputLine;
                while((inputLine = bufferedReader.readLine()) != null){
                    builder.append(inputLine);
                }

                return 1;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        //This snippet is executed on UI Thread
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            Log.i(TAG,"Inside onPostExecute");
            if(result == 1){
                parseJSON(builder.toString());
            }
            else {
                Toast.makeText(MainScreenActivity.this,"Failed to fetch the data",Toast.LENGTH_LONG).show();
            }

        }

        private void parseJSON(String httpResponse) {

            try{

            Log.i(TAG,"Response : " + httpResponse);
            //Get the final response to JSON
            String response = builder.toString();
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            for (int i=0;i<jsonArray.length();i++){

                /*
                * Parse the JSON object
                * */
                JSONObject json = jsonArray.getJSONObject(i);
                String title = json.getString("original_title");
                String rating = json.getString("vote_average");
                String desc = json.getString("overview");
                String imageUrl = json.getString("poster_path");
                String genre = "Drama";
                String strYear = json.getString("release_date");

                /*
                * Logic to extract the Year from Date
                * */
                Date releaseDate = dateFormat.parse(strYear);
//                This is deprecated so we need to find an alternative
//                releaseDate.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(releaseDate);
                Log.i(TAG,"Release date " + String.valueOf(calendar.get(Calendar.YEAR)));
                String yearofRelease = String.valueOf(calendar.get(Calendar.YEAR));

                /*
                * Populate the items from JSON into Model object
                * */
                MovieModel movie_item = new MovieModel();
                movie_item.setTitle(title);
                movie_item.setRating(rating);
                movie_item.setSynopsis(desc);
                movie_item.setGenre(genre);
                movie_item.setYear(yearofRelease);
                movie_item.setImage(imageUrl);
                movieList.add(movie_item);
            }

                MovieAdapter adapter = new MovieAdapter(MainScreenActivity.this,movieList);
                listmovies.setAdapter(adapter);
//                recyclerView.setAdapter(adapter);

        } catch (JSONException je) {
            je.printStackTrace();
        }
        catch(ParseException pe){
            pe.printStackTrace();
        }
        }
    }
}
