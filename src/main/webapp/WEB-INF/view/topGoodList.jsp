<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>${daysRateSum}</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">

</head>
<body>


<form action="/data/topList" method="get">
    <div style="margin:0 auto; width: 90%;text-align: center;">

        <table style="width: 100%;">
            <tr>
                <th>开始日期：
                    <select name="dateStart">
                        <option value="">请选择</option>
                        <c:forEach items="${dateStarts}" var="item" varStatus="var">
                            <option value="${item}"
                                    <c:if test="${item==dateStart}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>
                <th>结束日期：
                    <select name="dateEnd">
                        <option value="">请选择</option>
                        <c:forEach items="${dateEnds}" var="item" varStatus="var">
                            <option value="${item}" <c:if test="${item==dateEnd}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>
                <th><input type="submit" value="查询"></th>
            </tr>
        </table>

        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>date</th>
                <th>rateSum</th>
                <th>companyCode</th>
                <th>mainMoney</th>
                <th>rate</th>
                <th>time</th>
            </tr>
        <%--item 前，itemOld后--%>
            <c:forEach items="${listMap}" var="item"  step="1"  varStatus="var">
                <tr>
                    <td rowspan="${fn:length(item.value)+1}">${fn:split(item.key, '_')[0]}</td>
                    <td rowspan="${fn:length(item.value)+1}">${fn:split(item.key, '_')[1]}</td>
                    <c:forEach items="${item.value}" var="v" >
                        <tr>
                            <td>${v.companyCode}</td>
                            <td >${v.mainMoney}</td>
                            <td >${v.rate}%</td>
                            <td >${v.time}</td>
                        </tr>
                    </c:forEach>

                </tr>
            </c:forEach>
            </tbody>
        </table><!-- /.table -->




    </div>








</form>


<script type="text/javascript"
        src="${ctx}assets/js/common/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${ctx}assets/js/common/bootstrap.min.js"></script>

</body>
</html>
