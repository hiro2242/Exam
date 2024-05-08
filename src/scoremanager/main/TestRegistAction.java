package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{
	public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//ローカル変数の宣言
		HttpSession session =request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		String entYearStr = "";
		String classNumStr = "";
		String subStr = "";
		String noStr = "";

		int entYear = 0;
		String classNum = null;
		Subject subject = null;
		int no = 0;

		SubjectDao subDao = new SubjectDao();
		ClassNumDao cNumDao = new ClassNumDao();
		TestDao testDao = new TestDao();
		List<Test> tests = new ArrayList<>();
		Map<String, String> errors = new HashMap<>();

		//リクエストパラメータ―の取得
		entYearStr = request.getParameter("f1");
		classNumStr = request.getParameter("f2");
		subStr  = request.getParameter("f3");
		noStr = request.getParameter("f4");

		//ラジオリスト
		List<Integer> entYearSet = new ArrayList<>();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}
		List<String> classNumSet = cNumDao.filter(school);
		List<Subject> subSet = subDao.filter(school);
		List<Integer> noSet = new ArrayList<>();
		for (int i = 1; i <= 2; i++) {
			noSet.add(i);
		}

		//型変換
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (classNumStr != null) {
			classNum = classNumStr;
		}
		if (subStr != null) {
			subject = subDao.get(subStr, school);
		}
		if (noStr != null) {
			no = Integer.parseInt(noStr);
		}

		if (entYear != 0 && classNum != null && subject != null && no != 0) {
			tests = testDao.filter(entYear, classNum, subject, no, school);
		}

        //DBへデータ保存 5
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", subject);
		request.setAttribute("f4", no);

        // リクエストにリストをセット
		request.setAttribute("tests", tests);

		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", classNumSet);
		request.setAttribute("sub_set", subSet);
		request.setAttribute("no_set", noSet);

		request.getRequestDispatcher("test_regist.jsp").forward(request, response);
	}
}
