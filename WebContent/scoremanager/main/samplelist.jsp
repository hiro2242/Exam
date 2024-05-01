<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
   <c:param name="scripts"></c:param>
   <c:param name="content">
      <section class="me-4">
         <div>氏名:${student.name}(${student.no})</div>
         <table>
            <tr class="table teble-hover">
               <th>科目名</th>
               <th>科目コード</th>
               <th>回数</th>
               <th>点数</th>
            </tr>
            <c:forEach var="student" items="${tls}">
               <tr>
                  <td>${student.subjectName}</td>
                  <td>${student.subjectCd}</td>
                  <td>${student.num}</td>
                  <td>${student.point}</td>
               </tr>
               <br>
            </c:forEach>
         </table>
      </section>
   </c:param>
</c:import>