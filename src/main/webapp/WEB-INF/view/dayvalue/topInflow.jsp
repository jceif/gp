<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>data-day-detail</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">

</head>
<body>


<form action="/day/topInflowList" method="get">


    <div style="margin:0 auto; width: 90%;text-align: center;">
        <table style="width: 100%;">
            <tr>
                <th>开始日期：
                    <select name="dateStart">
                        <option value="">请选择</option>
                        <c:forEach items="${formatDates}" var="item" varStatus="var">
                            <option value="${item}"
                                    <c:if test="${item==dateStart}">selected</c:if>>${item}</option>
                        </c:forEach>
                    </select>
                </th>
                <th>结束日期：
                    <select name="dateEnd">
                        <option value="">请选择</option>
                        <c:forEach items="${formatDates1}" var="item" varStatus="var">
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
                <th>index</th>
                <th>name</th>
                <th>code</th>
                <th>inflow</th>
                <th>rate ${dateCurrent}</th>
                <th>date</th>
            </tr>
            <c:forEach items="${list}" var="item" varStatus="var">
                <tr>
                    <td>${var.index+1}</td>
                    <td><a href="/day/chart?companyCode=${item.companyCode}&date=${dateEnd}" target="_blank">${item.companyCode}</a></td>
                    <td>
                        <c:choose>
                            <c:when test="${ fn:substring(item.companyCode ,0,3)=='600' or fn:substring(item.companyCode ,0,2)=='60'}">
                                <a href="http://quote.eastmoney.com/concept/sh${item.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                   target="_blank">${item.companyCode}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="http://quote.eastmoney.com/concept/sz${item.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                   target="_blank">${item.companyCode}</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${item.inflow}</td>
                    <td>${item.rate}</td>
                    <td>${item.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</form>

</body>
</html>
