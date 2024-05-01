package scoremanager.main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
        Map<String, String> errors = new HashMap<>();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");
		TestListStudentDao tlsDao= new TestListStudentDao();
		StudentDao sDao=new StudentDao();
		String no = "";
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

		no = req.getParameter("no");
		Student student = sDao.get(no);
		List<TestListSubject> tlsub = null;
		if (student == null || teacher.getSchool().equals(student)){
			errors.put("no", "存在しない学生番号です");
			req.setAttribute("class_list", list);
			req.setAttribute("subject_list", sublist);
			req.setAttribute("student", student);
			req.setAttribute("no", no);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
		}else {
		    tlsub = tlsDao.filter(student);
		    req.setAttribute("class_list", list);
		    req.setAttribute("subject_list", sublist);
			req.setAttribute("student", student);
			req.setAttribute("tls", tlsub);
			req.setAttribute("no", no);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
		}
	}
}