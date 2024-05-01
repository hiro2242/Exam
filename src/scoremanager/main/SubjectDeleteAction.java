package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー
		Subject subject = new Subject();
		SubjectDao subDao = new SubjectDao();
		String cd = req.getParameter("cd");

	    subject = subDao.get(cd, teacher.getSchool());

	    req.setAttribute("subject", subject);
	    req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
	}
}
