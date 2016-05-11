package demo.chips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity {

    private String bestMovies[] = new String[]{"The Shawshank Redemption", "The Godfather", "The Godfather: Part II", "The Dark Knight", "Schindler's List",
            "12 Angry Men", "Pulp Fiction", "The Lord of the Rings: The Return of the King", "The Good, the Bad and the Ugly", "Fight Club",
            "The Lord of the Rings: The Fellowship of the Ring", "Star Wars: Episode V - The Empire Strikes Back", "Forrest Gump", "Inception",
            "The Lord of the Rings: The Two Towers"};

    private TextView tvBestMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBestMovies = (TextView) findViewById(R.id.tv_best_movies);
        tvBestMovies.setOnClickListener(new ClickListener());
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            tvBestMovies.setText(R.string.best_movies);
            tvBestMovies.setOnClickListener(null);
            LinearLayout layoutContainer = (LinearLayout) findViewById(R.id.ll_container); //Container layout with vertical orientation
            inflateLayout(layoutContainer, new ArrayList<>(Arrays.asList(bestMovies))); //inflating container layout with chips
        }
    }
}
