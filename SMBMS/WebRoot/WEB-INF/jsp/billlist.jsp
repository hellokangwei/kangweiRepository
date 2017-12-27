<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>订单管理页面</span>
       </div>
       <div class="search">
       <form method="post" action="${pageContext.request.contextPath }/sys/bill/billlist.htm">
			<input name="method" value="query" class="input-text" type="hidden">
			<span>商品名称：</span>
			<input name="productName" type="text" value="${productName }">
			 
			<span>供应商：</span>
			<select name="provderId" style="width:200px;">
				<c:if test="${providerList != null }">
				   <option value="-1">-- 请选择  --</option>
				   <c:forEach var="provider" items="${providerList}">
				   		<option <c:if test="${provider.id == provderId }">selected="selected"</c:if>
				   		value="${provider.id}">${provider.proName}</option>
				   </c:forEach>
				</c:if>
       		</select>
			 
			<span>是否付款：</span>
			<select name="isPayment">
				<option value="-1">--请选择--</option>
				<option value="1" ${isPayment == 1 ? "selected=\"selected\"":"" }>未付款</option>
				<option value="2" ${isPayment == 2 ? "selected=\"selected\"":"" }>已付款</option>
       		</select>
			
			 <input	value="查 询"  style="line-height:30px;" type="submit" id="searchbutton">
			 <a style="width:100px;" href="${pageContext.request.contextPath }/sys/bill/addbill.htm">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
              <th style="text-align:center" width="10%">订单编码</th>
              <th style="text-align:center" width="20%">商品名称</th>
              <th style="text-align:center" width="20%">供应商</th>
              <th style="text-align:center" width="10%">订单金额</th>
              <th style="text-align:center" width="10%">是否付款</th>
              <th style="text-align:center" width="10%">创建时间</th>
              <th style="text-align:center" width="20%">操作</th>
          </tr>
          <c:forEach var="bill" items="${billPage.pageList }" varStatus="status">
				<tr>
					<td>
					<span>${bill.billCode }</span>
					</td>
					<td>
					<span>${bill.productName }</span>
					</td>
					<td>
					<span>${bill.provider.proName}</span>
					</td>
					<td>
					<span>${bill.totalPrice}</span>
					</td>
					<td>
					<span>
						<c:if test="${bill.isPayment == 1}">未付款</c:if>
						<c:if test="${bill.isPayment == 2}">已付款</c:if>
					</span>
					</td>
					<td>
					<span>
					<fmt:formatDate value="${bill.creationDate}" pattern="yyyy-MM-dd"/>
					</span>
					</td>
					<td>
					<span><a class="viewBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
					</td>
				</tr>
			</c:forEach>
      </table>
      <div class="row">
		<div class="col-sm-5">
			<div class="dataTables_info" id="datatable-responsive_info"
				role="status" aria-live="polite">共${billPage.totalCount }条记录
				${billPage.pageIndex }/${billPage.totalPageCount }页</div>
		</div>
		<div class="pagination pagination-small">
			<ul class="pagination">
				<c:if test="${billPage.prevPage()}">
					<li><a href="${pageContext.request.contextPath }/sys/bill/billlist.htm?isPayment=${isPayment }&provderId=${provderId}&productName=${productName }&pageIndex=${billPage.pageIndex-1}">&laquo;</a></li>
				</c:if>
				<c:forEach begin="${billPage.pageIndex-2 > 0 ? billPage.pageIndex-2 : 1 }" var="i" step="1"
						 end="${billPage.pageIndex+2 > billPage.getTotalPageCount() ? billPage.getTotalPageCount():billPage.pageIndex+2 }">
					<li <c:if test="${ i == billPage.pageIndex }">class="active"</c:if> >
						<a href="${pageContext.request.contextPath }/sys/bill/billlist.htm?isPayment=${isPayment }&provderId=${provderId}&productName=${productName }&pageIndex=${i}">${i}</a>
					</li>
				</c:forEach>	
				<c:if test="${billPage.nextPage()}">
					<li><a href="${pageContext.request.contextPath }/sys/bill/billlist.htm?isPayment=${isPayment }&provderId=${provderId}&productName=${productName }&pageIndex=${billPage.pageIndex+1}">&raquo;</a></li>
				</c:if>			
			</ul>
		</div>
	  </div>
  </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/billlist.js"></script>