package main;

import java.sql.*;
import java.util.Scanner;

public class crudApplication {
    private static Connection con = null;
    private static Statement state = null;
    private static ResultSet rs = null;

    /**
     * FUNCTION :: 데이터베이스 연결
     */
    public static void db_connection(String type) {
        String url = "jdbc:mysql://localhost:3306/javaCRUD?serverTimezone=UTC";
        String userName = "root";
        String password = "1234";

        try {
            con = DriverManager.getConnection(url, userName, password);
            state = con.createStatement();
            System.out.println(type + " Success!");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * FUNCTION :: 데이터 db 삽입
     */
    public static void insert() {

        Scanner sc = new Scanner(System.in);
        String text;

        System.out.print("# 내용\n>>");
        text = sc.next();

        String query = "insert into javaCRUD(content) values('"+text+"')";
        /**
         * LINE :: 데이터베이스 연결
         */
        db_connection("Insert");

        try {
            state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            /**
             * LINE :: 데이터베이스 연결해제
             */
            if(rs != null) rs.close();
            if(state != null) state.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * FUNCTION :: 데이터베이스 db 조회
     */
    public static void select() {
        String query = "select * from javaCRUD";
        /**
         * LINE :: 데이터베이스 연결
         */
        db_connection("select");

        try {
            rs = state.executeQuery(query);

            while (rs.next()) {
                int idx = rs.getInt("idx");
                String content = rs.getString("content");

                System.out.println("idx: " + idx + ", 내용: " + content);
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            /**
             * LINE :: 데이터베이스 연결해제
             */
            if(rs != null) rs.close();
            if(state != null) state.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * FUNCTION :: 데이터베이스 db 수정
     */
    public static void update() {
        Scanner sc = new Scanner(System.in);
        int idx;

        System.out.print("# idx\n>>");
        idx = sc.nextInt();

        String text;

        System.out.print("# 내용\n>>");
        text = sc.next();

        String query = "update javaCRUD set content = '" + text + "' where idx = " + idx;
        /**
         * LINE :: 데이터베이스 연결
         */
        db_connection("update");

        try {
            state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            /**
             * LINE :: 데이터베이스 연결해제
             */
            if(rs != null) rs.close();
            if(state != null) state.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * FUNCTION :: 데이터베이스 db 삭제
     */
    public static void delete() {
        Scanner sc = new Scanner(System.in);
        int idx;

        System.out.print("# idx\n>>");
        idx = sc.nextInt();

        /**
         * LINE :: 데이터베이스 연결
         */
        String query = "delete from javaCRUD where idx =" + idx;
        db_connection("delete");

        try {
            state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            /**
             * LINE :: 데이터베이스 연결해제
             */
            if(rs != null) rs.close();
            if(state != null) state.close();
            if(con != null) con.close();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * FUNCTION :: 프로그램 메인 메소드
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int chkNum;

        while(true) {
            System.out.println("------- JAVA CRUD -------");
            System.out.println("[1] - Insert(등록)");
            System.out.println("[2] - Select(조회)");
            System.out.println("[3] - Update(수정)");
            System.out.println("[4] - Delete(삭제)");
            System.out.println("[5] - 프로그램 종료");
            System.out.println("-------------------------");
            System.out.print("입력 : ");

            chkNum = sc.nextInt();

            switch(chkNum) {
                case 1: // Insert - 추가
                    insert();
                    break;
                case 2: // Select - 조회
                    select();
                    break;
                case 3: // Update - 수정
                    update();
                    break;
                case 4: // Delete - 삭제
                    delete();
                    break;
                default: // Exit - 프로그램 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
            }
        }
    }
}
