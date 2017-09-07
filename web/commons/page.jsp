<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2017/9/7
  Time: 下午2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 分页导航 -->

第一种显示分页方法

第 ${page.currentPageNum}页/共${page.totalPage}页 &nbsp;&nbsp;
<a href=${pageContext.request.contextPath}${page.uri}?num=1>首页</a>
<a href= ${pageContext.request.contextPath}${page.uri}?num=${page.prePageNum}>上一页</a>
<a href=${pageContext.request.contextPath}${page.uri}?num=${page.nextPageNum}>下一页</a>
&nbsp;&nbsp;

<br>

第二种显示分页方法
<br>

<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
    <a href=${pageContext.request.contextPath}${page.uri}?num=${num}>${num}</a>
</c:forEach>

<br>
第三种显示分页方法
<br>

<a href=${pageContext.request.contextPath}${page.uri}?num=${page.nextPageNum}>下一页</a>
<a href=${pageContext.request.contextPath}${page.uri}?num=${page.totalPage}>尾页</a>

<select id="pagenum" onchange="jump(this)">
    <c:forEach begin="1" end="${page.totalPage}" var="num">
        <option value="${num}" ${num==page.currentPageNum?'selected="selected"':''}>${num}</option>
    </c:forEach>
</select>

<br>
第四种显示分页方法
<br>

<input type="text" id="newnum" size="2">
<input type="button" value="跳转" onclick="jump1()">



<script type="text/javascript">
    function jump(selectObj) {

        window.location.href="${pageContext.request.contextPath}${page.uri}?num="+selectObj.value;
    }

    function jump1() {
        var newNum = document.getElementById("newnum").value;

        if (!/^[1-9][0-9]*$/.test(newNum)){
            alert("请输入正确的页码");
            return;
        }

        if (newNum<1 || newNum>${page.totalPage}){
            alert("页码已经超出范围");
            return;
        }
        window.location.href = "${pageContext.request.contextPath}${page.uri}?num="+newNum;

    }

</script>
