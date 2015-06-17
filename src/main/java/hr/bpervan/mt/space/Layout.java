package hr.bpervan.mt.space;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Branimir on 15.6.2015..
 */
public class Layout {
    private Set<Cell> cellSet;

    public Layout(){
        this.cellSet = new TreeSet<>();
    }

    public static Layout fromCsv(String relativePath){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(relativePath);

        Set<Cell> cellSet;

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                String[] parts = line.split(";");
            }

            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return new Layout();
    }
}
