<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="post" action="${pageContext.request.contextPath }/sys/user/userlist.htm">
					<input name="method" value="query" class="input-text" type="hidden">
					 <span>用户名：</span>
					 <input name="userName" class="input-text"	type="text" value="${userName}">
					 
					 <span>用户角色：</span>
	        		<!-- 动态生成 -->
	        		<c:if test="${roleList != null }">
	        			<select name="roleId">
	        				<option value="-1">请选择</option>
		        			<c:forEach var="role" items="${roleList}">
		        				<option <c:if test="${role.id == roleId }">selected="selected"</c:if> 
		        					value="${role.id}">${role.roleName}</option>
		        			</c:forEach>
	        			</select>
	        		</c:if>
					 
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" style="line-height:30px;" type="submit" id="searchbutton">
					 <a style="width:100px;" href="${pageContext.request.contextPath}/sys/user/useradd.htm" >添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th style="text-align:center" width="10%">用户编码</th>
                    <th style="text-align:center" width="20%">用户名称</th>
                    <th style="text-align:center" width="10%">性别</th>
                    <th style="text-align:center" width="10%">密码</th>
                    <th style="text-align:center" width="10%">电话</th>
                    <th style="text-align:center" width="10%">用户角色</th>
                    <th style="text-align:center" width="30%">操作</th>
                </tr>
                   <c:forEach var="user" items="${userPage.pageList }" varStatus="status">
					<tr>
						<td>
						<span>${user.userCode }</span>
						</td>
						<td>
						<span>${user.userName }</span>
						</td>
						<td>
							<span>
								<c:if test="${user.gender==1}">男</c:if>
								<c:if test="${user.gender==2}">女</c:if>
							</span>
						</td>
						<td>
						<span>${user.userPassword}</span>
						</td>
						<td>
						<span>${user.phone}</span>
						</td>
						<td>
							<span>
								${user.role.roleName}
							</span>
						</td>
						<td>
						<span><a class="viewUser" href="javascript:;"  userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="row">
				<div class="col-sm-5">
					<div class="dataTables_info" id="datatable-responsive_info"
						role="status" aria-live="polite">共${userPage.totalCount }条记录
						${userPage.pageIndex }/${userPage.totalPageCount }页</div>
				</div>
				<div class="pagination pagination-small">
					<ul class="pagination">
						<c:if test="${userPage.prevPage()}">
							<li><a href="${pageContext.request.contextPath }/sys/user/userlist.htm?roleId=${roleId}&userName=${userName }&pageIndex=${userPage.pageIndex-1}">&laquo;</a></li>
						</c:if>
						<c:forEach begin="${userPage.pageIndex-2 > 0 ? userPage.pageIndex-2 : 1 }" var="i" step="1"
								 end="${userPage.pageIndex+2 > userPage.getTotalPageCount() ? userPage.getTotalPageCount():userPage.pageIndex+2 }">
							<li <c:if test="${ i == userPage.pageIndex }">class="active"</c:if> >
								<a href="${pageContext.request.contextPath }/sys/user/userlist.htm?roleId=${roleId}&userName=${userName }&pageIndex=${i}">${i}</a>
							</li>
						</c:forEach>	
						<c:if test="${userPage.nextPage()}">
							<li><a href="${pageContext.request.contextPath }/sys/user/userlist.htm?roleId=${roleId}&userName=${userName }&pageIndex=${userPage.pageIndex+1}">&raquo;</a></li>
						</c:if>			
					</ul>
				</div>
			</div>
			<!-- 用户查询 -->
			 <div class="providerAdd">
                <div>
                    <label for="userCode">用户编码：</label>
                    <input type="text" name="userCode" id="userCode" readonly="readonly" value=""> 
                </div>
                <div>
                    <label for="userName">用户名称：</label>
                    <input type="text" name="userName" id="userName" readonly="readonly" value=""> 
                </div>
                <div>
                    <label for="userPassword">用户密码：</label>
                    <input type="password" name="userPassword" id="userPassword" readonly="readonly" value=""> 
                </div>
                <div>
                    <label >用户性别：</label>
					<input name="gender" id="gender" readonly="readonly" value="" />
                </div>
                <div>
                    <label for="birthday">出生日期：</label>
                    <input type="text" Class="Wdate" id="birthday"  name="birthday" 
					readonly="readonly" />
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" readonly="readonly" value=""> 
                </div>
                <div>
                    <label for="address">用户地址：</label>
                   <input name="address" id="address" readonly="readonly" value="">
                </div>
                <div>
                    <label for="creationDate">创建时间：</label>
                   <input name="creationDate" id="creationDate" readonly="readonly" value="">
                </div>
			</div>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/userlist.js"></script>
