//package io.github.haskaqwerty.filmlibrary.dao;
//
//import lombok.Getter;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//@Getter
//class SimpleRepository <T>{
//    private String url="jdbc:postgresql://localhost:5432/postgres";
//    private String username="postgresuser";
//    private String password="postgres";
//    static String sqlExpression="SELECT * FROM movies";
//    static ArrayList<String> bufferList = new ArrayList<String>();
//
//    public ArrayList<T> connectDb(String classtype) {
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(sqlExpression);
//            while (resultSet.next()) {
//                StringBuffer buffer = new StringBuffer();
//                T classTypeObject = new T(resultSet.getObject(i,T));
//                for (int i = 1; i < 5; i++) {
//                    buffer.append(resultSet.getString(i) + " ");
//
//                }
//                bufferList.add(buffer.toString());
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return bufferList;
//    }
//
//}
//
