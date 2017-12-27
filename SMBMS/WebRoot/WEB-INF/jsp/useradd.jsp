an<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <form id="userForm" name="userForm" enctype="multipart/form-data" method="post" action="${pageContext.request.contextPath }/sys/user/adduser">
				<input type="hidden" name="method" value="add">
                <input type="hidden" name="createdBy" value="${createuser.id }"> 
                <div>
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" value=""> 
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="ruserPassword">确认密码：</label>
                    <input type="password" name="ruserPassword" id="ruserPassword" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label >用户性别：</label>
					<select name="gender" id="gender">
					    <option value="1" selected="selected">男</option>
					    <option value="2">女</option>
					 </select>
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday" name="birthday" 
					readonly="readonly" onclick="WdatePicker();">
					<font color="red"></font>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value=""> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <input name="address" id="address"  value="">
                </div>
                <div>
                    <label >用户角色：</label>
                   <c:if test="${roleList != null }">
	        			<select name="roleId">
	        				<option value="-1">请选择</option>
		        			<c:forEach var="role" items="${roleList}">
		        				<option <c:if test="${role.id == roleId }">selected="selected"</c:if> 
		        					value="${role.id}">${role.roleName}</option>
		        			</c:forEach>
	        			</select>
	        		</c:if>
	        		<font color="red"></font>
                </div>
               	<div>
                    <label for="a_idPicPath" style="float:left;">证件照：</label>
                    <input type="file" name="attachs" id="a_idPicPath"/> 
					<font color="red"></font>
                </div>
                <div>
                    <label for="a_workPicPath" style="float:left;">工作证照片：</label>
                    <input type="file" name="attachs" id="a_workPicPath"/> 
					<font color="red"></font>
                </div>
                <div align="center" style="color: red;">${errorInfo }</div>
                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/useradd.js"></script>