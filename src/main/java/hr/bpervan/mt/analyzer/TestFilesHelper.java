package hr.bpervan.mt.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Branimir on 22.6.2015..
 */
public class TestFilesHelper {
    public static final String D1_F1 = "raw_data/dataset1/LogFile_06-06-15_17_37.txt";
    public static final String D1_F2 = "raw_data/dataset1/LogFile_06-06-15_17_38.txt";
    public static final String D1_F3 = "raw_data/dataset1/LogFile_06-06-15_17_39.txt";

    public static final String D2_F1 = "raw_data/dataset2/LogFile_06-06-15_17_59.txt";
    public static final String D2_F2 = "raw_data/dataset2/LogFile_06-06-15_18_00.txt";
    public static final String D2_F3 = "raw_data/dataset2/LogFile_06-06-15_18_01.txt";

    public static final String D3_F1 = "raw_data/dataset3/LogFile_06-06-15_18_01.txt";
    public static final String D3_F2 = "raw_data/dataset3/LogFile_06-06-15_18_02.txt";
    public static final String D3_F3 = "raw_data/dataset3/LogFile_06-06-15_18_02a.txt";

    public static final String D4_F1 = "raw_data/dataset4/LogFile_06-06-15_18_03.txt";
    public static final String D4_F2 = "raw_data/dataset4/LogFile_06-06-15_18_04.txt";
    public static final String D4_F3 = "raw_data/dataset4/LogFile_06-06-15_18_05.txt";

    public static final String D5_F1 = "raw_data/dataset5/LogFile_06-06-15_18_20.txt";
    public static final String D5_F2 = "raw_data/dataset5/LogFile_06-06-15_18_21.txt";
    public static final String D5_F3 = "raw_data/dataset5/LogFile_06-06-15_18_22.txt";

    public static final String D6_F1 = "raw_data/dataset6/LogFile_06-06-15_18_22.txt";
    public static final String D6_F2 = "raw_data/dataset6/LogFile_06-06-15_18_24a.txt";
    public static final String D6_F3 = "raw_data/dataset6/LogFile_06-06-15_18_24b.txt";

    public static final String D7_F1 = "raw_data/dataset7/LogFile_06-06-15_18_26.txt";
    public static final String D7_F2 = "raw_data/dataset7/LogFile_06-06-15_18_27a.txt";
    public static final String D7_F3 = "raw_data/dataset7/LogFile_06-06-15_18_27b.txt";

    public static final String D8_F1 = "raw_data/dataset8/LogFile_06-06-15_18_28.txt";
    public static final String D8_F2 = "raw_data/dataset8/LogFile_06-06-15_18_29.txt";
    public static final String D8_F3 = "raw_data/dataset8/LogFile_06-06-15_18_30.txt";

    public static List<String> filesList(){
        List<String> helperList = new ArrayList<>();

        helperList.add(D1_F1);
        helperList.add(D1_F2);
        helperList.add(D1_F3);

        helperList.add(D2_F1);
        helperList.add(D2_F2);
        helperList.add(D2_F3);

        helperList.add(D3_F1);
        helperList.add(D3_F2);
        helperList.add(D3_F3);

        helperList.add(D4_F1);
        helperList.add(D4_F2);
        helperList.add(D4_F3);

        helperList.add(D5_F1);
        helperList.add(D5_F2);
        helperList.add(D5_F3);

        helperList.add(D6_F1);
        helperList.add(D6_F2);
        helperList.add(D6_F3);

        helperList.add(D7_F1);
        helperList.add(D7_F2);
        helperList.add(D7_F3);

        helperList.add(D8_F1);
        helperList.add(D8_F2);
        helperList.add(D8_F3);

        return helperList;
    }
}
