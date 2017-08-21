<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

TD {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}
.error{
	color:red;
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>

<!-- 引入jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	// 检查登录名
	function checkCode() {
		var $user_name = $("#user_name").val();
		if ($user_name.trim() == ""){
			// 如果没有输入用户名
			$("#codeId").addClass("error");
			$("#codeId").html("登录名不能为空！");
		}else{
			var url = "${pageContext.request.contextPath }/user_checkName.action";
			var param = {"user_name":$user_name};
			$.post(url,param,function (data){
				// 判断返回数据
				if (data == "no"){
					$("#codeId").addClass("error");
					$("#codeId").html("登录名已存在！");
				}else{
					$("#codeId").removeClass("error");
					$("#codeId").html("登录名可以使用！");
				}
			});
		}
	}
	
	// 控制表单是否可以提交
	function checkForm(){
		// 先校验是否有错误信息（通过检查是否有class = error）
		checkCode();
		if ($(".error").size() > 0){
			return false;
		}
	}
</script>
</HEAD>
<BODY>
	<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_regist.action" onsubmit="return checkForm()" method=post>

		<DIV id=UpdatePanel1>
			<DIV id=div1
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
			<DIV id=div2
				style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


			<DIV>&nbsp;&nbsp;</DIV>
			<DIV>
				<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
					<TBODY>
						<TR>
							<TD style="HEIGHT: 105px"><IMG src="images/login_1.gif"
								border=0></TD>
						</TR>
						<TR>
							<TD background=images/login_2.jpg height=300>
								<TABLE height=300 cellPadding=0 width=900 border=0>
									<TBODY>
										<TR>
											<TD colSpan=2 height=35></TD>
										</TR>
										<TR>
											<TD width=360></TD>
											<TD>
												<TABLE cellSpacing=0 cellPadding=2 border=0>
													<TBODY>
														<TR>
															<TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
															<TD style="HEIGHT: 28px" width=150>
																<INPUT id="user_name" style="WIDTH: 130px" name="user_name" onblur="checkCode()">
															</TD>
															<TD style="HEIGHT: 28px" width=370>
																<SPAN id="codeId" style="FONT-WEIGHT: bold;"></SPAN>
															</TD>
														</TR>
														<TR>
															<TD style="HEIGHT: 28px">登录密码：</TD>
															<TD style="HEIGHT: 28px">
																<INPUT id="user_password" style="WIDTH: 130px" type=password name="user_password">
															</TD>
														</TR>
														<!-- <TR>
															<TD style="HEIGHT: 28px">用户姓名：</TD>
															<TD style="HEIGHT: 28px">
																<INPUT id="user_name" style="WIDTH: 130px" type="text" name="user_name">
															</TD>
														</TR> -->
														<TR>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
															<TD style="HEIGHT: 18px"></TD>
														</TR>
														<TR>
															<TD></TD>
															<TD><input type="submit" value="注册" /></TD>
														</TR>
													</TBODY>
												</TABLE>
											</TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD><IMG src="images/login_3.jpg" border=0></TD>
						</TR>
					</TBODY>
				</TABLE>
			</DIV>
		</DIV>


	</FORM>
</BODY>
</HTML>


</body>
</html>