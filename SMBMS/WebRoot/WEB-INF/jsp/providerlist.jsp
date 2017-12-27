<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面</span>
        </div>
        <div class="search">
        	<form method="post" action="${pageContext.request.contextPath }/sys/pro/providerlist.htm">
				<input name="method" value="query" type="hidden">
				<span>供应商编码：</span>
				<input name="proCode" type="text" value="${proCode }">
				
				<span>供应商名称：</span>
				<input name=proName type="text" value="${proName }">
				
				<input value="查 询" style="line-height:30px;" type="submit" id="searchbutton">
				<a style="width:130px;" href="${pageContext.request.contextPath }/sys/pro/provideradd.htm">添加供应商</a>
			</form>
        </div>
        <!--供应商操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th style="text-align:center" width="10%">供应商编码</th>
                <th style="text-align:center" width="20%">供应商名称</th>
                <th style="text-align:center" width="10%">联系人</th>
                <th style="text-align:center" width="10%">联系电话</th>
                <th style="text-align:center" width="10%">传真</th>
                <th style="text-align:center" width="10%">创建时间</th>
                <th style="text-align:center" width="30%">操作</th>
            </tr>
            <c:forEach var="provider" items="${proPage.pageList }" varStatus="status">
				<tr>
					<td>
					<span>${provider.proCode }</span>
					</td>
					<td>
					<span>${provider.proName }</span>
					</td>
					<td>
					<span>${provider.proContact}</span>
					</td>
					<td>
					<span>${provider.proPhone}</span>
					</td>
					<td>
					<span>${provider.proFax}</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${provider.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteProvider" href="javascript:;" proid=${provider.id } proname=${provider.proName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
        </table>

		<div class="row">
				<div class="col-sm-5">
					<div class="dataTables_info" id="datatable-responsive_info"
						role="status" aria-live="polite">共${proPage.totalCount }条记录
						${proPage.pageIndex }/${proPage.totalPageCount }页</div>
				</div>
				<div class="pagination pagination-small">
					<ul class="pagination">
						<c:if test="${proPage.prevPage()}">
							<li><a href="${pageContext.request.contextPath }/sys/pro/providerlist.htm?proCode=${proCode }&proName=${proName }&pageIndex=${proPage.pageIndex-1}">&laquo;</a></li>
						</c:if>
						<c:forEach begin="${proPage.pageIndex-2 > 0 ? proPage.pageIndex-2 : 1 }" var="i" step="1"
								 end="${proPage.pageIndex+2 > proPage.getTotalPageCount() ? proPage.getTotalPageCount():proPage.pageIndex+2 }">
							<li <c:if test="${ i == proPage.pageIndex }">class="active"</c:if> >
								<a href="${pageContext.request.contextPath }/sys/pro/providerlist.htm?proCode=${proCode }&proName=${proName }&pageIndex=${i}">${i}</a>
							</li>
						</c:forEach>	
						<c:if test="${proPage.nextPage()}">
							<li><a href="${pageContext.request.contextPath }/sys/pro/providerlist.htm?proCode=${proCode }&proName=${proName }&pageIndex=${proPage.pageIndex+1}">&raquo;</a></li>
						</c:if>			
					</ul>
				</div>
			</div>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该供应商吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/providerlist.js"></script>
