package org.iskcon.icc.bhagavadgitakannada.utilities;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_EIGHT;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_EIGHTEEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_ELEVEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_FIFTEEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_FIVE;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_FOUR;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_FOURTEEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_NINE;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_ONE;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_SEVEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_SEVENTEEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_SIX;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_SIXTEEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_TEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_THIRTEEN;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_THREE;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_TWELVE;
import static org.iskcon.icc.bhagavadgitakannada.utilities.Constants.CHAPTER_TWO;

/**
 * Created by Ankush on 01-05-2017.
 */

public class ChapterUtilities {

    private SparseArray<ArrayList> chapterWiseVerseList = new SparseArray();
    private static ArrayList chapterNames = new ArrayList();

    public ChapterUtilities() {
    }

    public SparseArray<ArrayList> getChapterWiseVerseList() {
        return chapterWiseVerseList;
    }

    public static ArrayList getChapterNames() {
       /** String chapterKey = "CHAPTER";
        List<String> chapterNumbers = new ArrayList<>(Arrays.asList("_ONE","_TWO","_THREE","_FOUR","_FIVE","_SIX","_SEVEN","_EIGHT","_NINE","_TEN","_ELEVEN","_TWELVE","_THIRTEEN","_FOURTEEN","_FIFTEEN","_SIXTEEN","_SEVENTEEN","_EIGHTEEN"));
        for (String chapterNumber : chapterNumbers) {
            String chapterName = chapterKey + chapterNumber;
            chapterNames.add(chapterName);
        } */
        chapterNames.add(CHAPTER_ONE);
        chapterNames.add(CHAPTER_TWO);
        chapterNames.add(CHAPTER_THREE);
        chapterNames.add(CHAPTER_FOUR);
        chapterNames.add(CHAPTER_FIVE);
        chapterNames.add(CHAPTER_SIX);
        chapterNames.add(CHAPTER_SEVEN);
        chapterNames.add(CHAPTER_EIGHT);
        chapterNames.add(CHAPTER_NINE);
        chapterNames.add(CHAPTER_TEN);
        chapterNames.add(CHAPTER_ELEVEN);
        chapterNames.add(CHAPTER_TWELVE);
        chapterNames.add(CHAPTER_THIRTEEN);
        chapterNames.add(CHAPTER_FOURTEEN);
        chapterNames.add(CHAPTER_FIFTEEN);
        chapterNames.add(CHAPTER_SIXTEEN);
        chapterNames.add(CHAPTER_SEVENTEEN);
        chapterNames.add(CHAPTER_EIGHTEEN);
        return chapterNames;
    }
}

