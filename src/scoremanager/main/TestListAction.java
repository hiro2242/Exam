package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		List<String> list = cNumDao.filter(teacher.getSchool());

		List<Subject> sublist = subDao.filter(teacher.getSchool());

		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("class_list", list);
		req.setAttribute("subject_list", sublist);
		req.getRequestDispatcher("test_list.jsp").forward(req, res);

	}
}
