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

public class SubjectUpdateAction extends Action {
	public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		SubjectDao subDao = new SubjectDao();
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		Map<String, String> errors = new HashMap<>();
		String cd = request.getParameter("cd");
		Subject subject = subDao.get(cd, teacher.getSchool());
		if (subject != null) {
			request.setAttribute("cd", subject.getCd());
			request.setAttribute("name", subject.getName());
			request.setAttribute("school_cd", subject.getSchool());
		}else{
			errors.put("no", "科目が存在していません");
			request.setAttribute("errors", errors);
		}
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);
	}

}
