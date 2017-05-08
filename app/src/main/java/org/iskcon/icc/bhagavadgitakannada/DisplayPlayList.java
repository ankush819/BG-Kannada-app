package org.iskcon.icc.bhagavadgitakannada;

import android.app.ListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.iskcon.icc.bhagavadgitakannada.utilities.ChapterUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ankush on 01-05-2017.
 */

public class DisplayPlayList extends Activity {

    //private SparseArray<ArrayList> chapterWiseVerseList = new SparseArray();
    private ArrayList chapterNames = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        //ChapterUtilities chapterUtilities = new ChapterUtilities();
        chapterNames = ChapterUtilities.getChapterNames();
        Log.d("My App",Arrays.toString(chapterNames.toArray()));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.playlist_item, chapterNames);
        ListView chapterListView = (ListView) findViewById(R.id.listView);

        chapterListView.setAdapter(arrayAdapter);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO Get the chapter details and send it to the MainActivity page to fetch and start playing
                int chapterIndex = position;
                Intent playChapterIntent = new Intent(getApplicationContext(), MainActivity.class);
                playChapterIntent.putExtra("chapterIndex", String.valueOf(chapterIndex));
                String chapterRawResourceName = "chapter" + String.valueOf(chapterIndex);
                playChapterIntent.putExtra("chapterRawResourceName", chapterRawResourceName);
                startActivity(playChapterIntent);
            }
        });
    }


}
