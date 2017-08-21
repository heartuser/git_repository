<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
	
	$(function (){
		var url = "${pageContext.request.contextPath }/dict_findByCode.action";
		var params = {"dict_type_code" : "006"};
		$.post(url,params,function(data){
			$(data).each(function(i, n){// i是下标值，n是迭代产生的对象
				// alert(n.dict_item_name);
				$("#levelId").append("<option value='" + n.dict_id + "'>" + n.dict_item_name + "</option>");
			});
		},"json");
		var params = {"dict_type_code" : "002"};
		$.post(url,params,function(data){
			$(data).each(function(i, n){
				$("#sourceId").append("<option value='" + n.dict_id + "'>" + n.dict_item_name + "</option>");
			});
		},"json");
	});
	
	function to_page(page){
		if(page){
			$("#pageCode").val(page);
		}
		document.customerForm.submit();
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByPage.action"
		method=post>
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD><INPUT class=textbox id="cust_name"
														style="WIDTH: 80px" maxLength=50 name="cust_name"></TD>
														
													<TD>客户级别：</TD>
													<td>
														<select name="cust_level.dict_id" id="levelId">
															<option value="">-请选择-</option>
														</select>
													</td>
													
													<TD>客户来源：</TD>
													<td>
														<select name="cust_source.dict_id" id="sourceId">
															<option value="">-请选择-</option>
														</select>
													</td>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
														
												</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>联系人</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${page.beanList }" var="customer">
												<TR
													style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
													<TD>${customer.cust_name }</TD>
													<TD>${customer.cust_level.dict_item_name }</TD>
													<TD>${customer.cust_source.dict_item_name }</TD>
													<TD>${customer.cust_linkman }</TD>
													<TD>${customer.cust_phone }</TD>
													<TD>${customer.cust_mobile }</TD>
													<TD>
													<a href="${pageContext.request.contextPath }/customerServlet?method=edit&custId=${customer.cust_id}">修改</a>
													&nbsp;&nbsp;
													<a href="${pageContext.request.contextPath }/customerServlet?method=delete&custId=${customer.cust_id}">删除</a>
													</TD>
												</TR>
												
												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${page.totalCount}</B>]条记录,共[<B>${page.totalPage}</B>]页
												,每页显示
												<select name="pageSize">
													<option value="2" <c:if test="${page.pageSize==2 }">selected</c:if>>2</option>
													<option value="3" <c:if test="${page.pageSize==3 }">selected</c:if>>3</option>
												</select>
												条 
												[<A href="javascript:void(0)" onclick="to_page('${page.pageCode-1}')">前一页</A>]
												<b>${page.pageCode}</b>
												[<A href="javascript:void(0)" onclick="to_page('${page.pageCode+1}')">后一页</A>] 
												到
												<input type="text" size="3" id="pageCode" name="pageCode" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
