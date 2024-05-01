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

public class SubjectCreateExecuteAction extends Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Map<String, String> errors = new HashMap<>();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

		SubjectDao subDao = new SubjectDao();
		Subject subject = subDao.get(cd, teacher.getSchool());

		if (cd.length() == 3) {
			if (subject == null) {
				subject = new Subject();
				subject.setSchool(teacher.getSchool());
				subject.setCd(cd);
				subject.setName(name);
				subDao.save(subject);
			}else {
				errors.put("chohuku", "科目コードが重複しています");
			}
		}else {
			errors.put("moji", "科目コードは3文字で入力してください");
		}
		request.setAttribute("cd", cd);
		request.setAttribute("name", name);

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("cd", cd);
			request.setAttribute("name", name);
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);;
			return;
		}
		request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);;
	}
}
