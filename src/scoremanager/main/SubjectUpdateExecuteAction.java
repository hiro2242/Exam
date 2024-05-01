package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		Subject subject = null;
		SubjectDao subDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();

		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

		subject = subDao.get(cd, teacher.getSchool());

		if (subject != null) {
			subject.setSchool(teacher.getSchool());
			subject.setCd(cd);
			subject.setName(name);
			subDao.save(subject);
		}else {
			errors.put("no", "科目が存在しません");
		}
		request.setAttribute("name", name);
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("cd", cd);
			request.setAttribute("name", name);
			request.getRequestDispatcher("subject_update.jsp");
			return;
		}
		request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
	}
}
