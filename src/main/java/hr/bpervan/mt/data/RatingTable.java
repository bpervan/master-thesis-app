package hr.bpervan.mt.data;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Branimir on 15.6.2015..
 */
public class RatingTable {
    private Table<Integer, Integer, Double> table;

    public RatingTable(){
        table = HashBasedTable.create();
    }

    public Table<Integer, Integer, Double> getTable() {
        return table;
    }

    public void setTable(Table<Integer, Integer, Double> table) {
        this.table = table;
    }

    public static RatingTable fromCsv(String relativePath){
        RatingTable ratingTable = new RatingTable();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(relativePath);

        StringBuilder sb;

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = null;

            line = bufferedReader.readLine();
            String[] columnIndices = line.split(";");

            while((line = bufferedReader.readLine()) != null){
                line = line.replaceAll(";;", ";0.0;");

                if(line.charAt(line.length() - 1) == ';'){
                    sb = new StringBuilder(line.subSequence(0, line.length() - 1));
                    sb.append(";0.0");
                    line = sb.toString();
                }

                String[] parts = line.split(";");

                int rowKey = Integer.parseInt(parts[0]);

                //System.out.print(rowKey + " ");

                for (int i = 1; i < parts.length; i++) {
                    if(parts[i].equals("")){
                        ratingTable.getTable().put(
                                rowKey,
                                Integer.parseInt(columnIndices[i]),
                                .0
                        );
                        //System.out.print(-1.0 + " ");
                    } else {
                        ratingTable.getTable().put(
                                rowKey,
                                Integer.parseInt(columnIndices[i]),
                                Double.parseDouble(parts[i])
                        );
                        //System.out.print(parts[i] + " ");
                    }
                }
                //System.out.println();
            }

            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return ratingTable;
    }

    public double[][] toArrayTable{
        double[][] retTable = 
        for(Integer column : table.columnKeySet()){
            for(Integer row : table.rowKeySet()) {

            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Table.Cell c : table.cellSet()){
            stringBuilder.append("Row: ");
            stringBuilder.append(c.getRowKey());
            stringBuilder.append("\n");

            stringBuilder.append("Column: ");
            stringBuilder.append(c.getColumnKey());
            stringBuilder.append("\n");

            stringBuilder.append("Data: ");
            stringBuilder.append(c.getValue());
            stringBuilder.append("\n");
            stringBuilder.append("-----------");
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return this.table.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingTable)) return false;

        RatingTable that = (RatingTable) o;

        return this.table.equals(that.getTable());

        //return !(getTable() != null ? !getTable().equals(that.getTable()) : that.getTable() != null);
    }
}
