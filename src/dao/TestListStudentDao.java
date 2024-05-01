package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListSubject;

public class TestListStudentDao extends Dao{
	private String baseSql = "select name, cd, no, point from subject join test on subject.cd = test.subject_cd where student_no = ?";

    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        // リストを初期化
        List<TestListSubject> list = new ArrayList<>();

        try {
            // リザルトセットを全権走査
            while (rSet.next()) {
                // 学生インスタンスを初期化
                TestListSubject tlsub = new TestListSubject();
                // 学生インスタンスに検索結果をセット
                tlsub.setSubjectName(rSet.getString("name"));
                tlsub.setSubjectCd(rSet.getString("cd"));
                tlsub.setNum(rSet.getInt("no"));
                tlsub.setPoint(rSet.getInt("point"));
                list.add(tlsub);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return list;
    }


	public List<TestListSubject> filter(Student student) throws Exception {
        // リストを初期化
        List<TestListSubject> list = new ArrayList<>();
        // コネクションを確立
        Connection connection = getConnection();
        // プリペアードステートメント
        PreparedStatement statement = null;
        // リザルトセット
        ResultSet rSet = null;
        // SQL文の条件;

        // SQL文の在学フラグ

        try {
            // プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql);
            // プリペアードステートメントに学校コードをバインド
            statement.setString(1, student.getNo());
            // プリペアードステートメントを実行
            rSet = statement.executeQuery();
            // リストへの格納処理を実行
            list = postFilter(rSet);
        } catch (Exception e) {
            throw e;
        } finally {
            // プリペアードステートメントを閉じる
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションを閉じる
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }


}
