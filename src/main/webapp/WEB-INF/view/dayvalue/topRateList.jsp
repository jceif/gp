<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>topRateList</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">

</head>
<body>


<form action="/day/topRateList" method="get">
    <div style="margin:0 auto; width: 90%;text-align: center;">

        <table style="width: 100%;">
            <tr>
                <th>流入：<input type="number" name="totalMoney1" value="${totalMoney1}">-
               <input type="number" name="totalMoney2" value="${totalMoney2}"></th>
                <th>rate：<input type="text" name="rate" value="${rate}"></th>
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
                <th>验证kdj：
                    <select name="isFilter">
                        <option  <c:if test="${empty isFilter}">selected</c:if> value="">否</option>
                        <option  <c:if test="${!empty isFilter && isFilter=='1'}">selected</c:if> value="1">是</option>
                    </select>
                </th>
                <th><input type="submit" value="查询">&nbsp;${daysRateSum}</th>
            </tr>
        </table>
        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>date</th>
                <th>companyCode</th>
                <th>price</th>
                <th>totalMoney</th>
                <th>volume</th>
                <th>k</th>
                <th>d</th>
                <th>j</th>
                <th>preRate</th>
                <th>rate</th>
                <th>nextRate</th>
                <th>threeRate</th>
                <th>rateSum</th>
            </tr>
        <%--item 前，itemOld后--%>
            <c:forEach items="${listMap}" var="item"  step="1"  varStatus="var">
                    <c:forEach items="${item.value}" var="v" varStatus="status">
                        <tr>
                            <c:if test="${status.index==0}">
                                 <td style="border-bottom: #1b6d85 solid 1px" rowspan="${fn:length(item.value)}">${fn:split(item.key, '_')[0]}</td>
                            </c:if>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>"><c:choose>
                                    <c:when test="${ fn:substring(v.companyCode ,0,3)=='600' or fn:substring(v.companyCode ,0,2)=='60'}">
                                        <a href="http://quote.eastmoney.com/concept/sh${v.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                           target="_blank">${v.companyCode}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="http://quote.eastmoney.com/concept/sz${v.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                           target="_blank">${v.companyCode}</a>
                                    </c:otherwise>
                                </c:choose></td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.endPrice}</td>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.totalMoney}</td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.volume/10000}</td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.preK} / ${v.k}</td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.preD} / ${v.d}</td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.preJ} / ${v.j}</td>

                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>"><span style="<c:if test="${v.preRate<0}">color:green;</c:if>">${v.preRate}%</span></td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>"><a target="_blank"  href="/data/chart?companyCode=${v.companyCode}&date=${v.date}">${v.rate}%</a></td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>"><span style="<c:if test="${v.nextRate<0}">color:red;</c:if>" >${v.nextRate}%</span></td>
                            <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.threeRate}</td>
                            <c:if test="${status.index==0}">
                                <td style="border-bottom: #1b6d85 solid 1px" rowspan="${fn:length(item.value)}">${fn:split(item.key, '_')[1]}</td>
                            </c:if>
                        </tr>
                    </c:forEach>
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
