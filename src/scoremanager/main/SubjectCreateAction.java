package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction extends Action{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		SubjectDao subDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();
		List<Subject> list = subDao.filter(teacher.getSchool());

		request.setAttribute("subjects", list);
		request.setAttribute("errors", errors);

		request.getRequestDispatcher("subject_create.jsp").forward(request, response);
	}
}
