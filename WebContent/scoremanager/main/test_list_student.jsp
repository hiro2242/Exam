<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4"style="background:#f2f2f2">成績一覧(学年)</h2>
			<form action="TestListSubject.action"method="post">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-4">
						<label class="form-label" for="student-f1-select">入学年度 </label>
						<select class="form-select " id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 ---%>
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select " id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="clist" items="${class_list}">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${clist}">${clist}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-4">
					    <label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select " id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="sublist" items="${subject_list}">
								現在のnumと選択されていたf3が一致していた場合selectedを追記
								<option value="${sublist.cd}" >${sublist.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="mt-2">
						<button class="btn btn-secondary" id="filter-button">絞込み</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1")}</div>
				</div>
			</form>
			<form action="TestListStudent.action" method="post">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-4">
						<label class="form-label" for="student-f4-select">学生番号 </label>
                        <input class="form-control" type="text" id="student-f4-select"
                            name="no" placeholder="学生番号を入力してください" value="${no}" required />
					</div>
					<div class="mt-2 text-warning">${errors.get("no")}</div>
                    <div class="mt-2">
                        <input class="btn btn-secondary" id="filter-button" type="submit" value="検索">
                    </div>
				</div>
			</form>
			<div>氏名:${student.name}(${no})</div>
			<c:choose>
				<c:when test="${tls.size()>0}">
					<table class="table table-hover">
						<tr>
							<th>科目名</th>
							<th>科目コード</th>
							<th>回数</th>
							<th>点数</th>
						</tr>
						<c:forEach var="tls" items="${tls}">
							<tr>
								<td>${tls.subjectName}</td>
								<td>${tls.subjectCd}</td>
								<td>${tls.num}</td>
								<td>${tls.point}</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>学生情報が存在しません</div>
				</c:otherwise>
			</c:choose>
		</section>
	</c:param>
</c:import>

