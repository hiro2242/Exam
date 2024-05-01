package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		SubjectDao subDao=new SubjectDao();
		Subject subject = null;
		Teacher teacher = (Teacher)session.getAttribute("user");
		String cd = req.getParameter("cd");

	    subject = subDao.get(cd, teacher.getSchool());
	    subDao.delete(subject);

	    req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}
