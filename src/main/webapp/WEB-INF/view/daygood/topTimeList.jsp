<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>topTimeList</title>
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">
</head>
<body>


<form action="/data//topTimeList" method="get">
    <div style="margin:0 auto; width: 90%;text-align: center;">

        <table style="width: 100%;">
            <tr>
                <th>买入时间：
                    <select name="time">
                        <option value="">请选择</option>
                        <option value="930" <c:if test="${930==time}">selected</c:if>>930</option>
                        <option value="1000" <c:if test="${1000==time}">selected</c:if>>1000</option>
                        <option value="1030" <c:if test="${1030==time}">selected</c:if>>1030</option>
                        <option value="1100" <c:if test="${1100==time}">selected</c:if>>1100</option>
                        <option value="1130" <c:if test="${1130==time}">selected</c:if>>1130</option>
                        <option value="1300" <c:if test="${1300==time}">selected</c:if>>1300</option>
                        <option value="1330" <c:if test="${1330==time}">selected</c:if>>1330</option>
                        <option value="1400" <c:if test="${1400==time}">selected</c:if>>1400</option>
                        <option value="1430" <c:if test="${1430==time}">selected</c:if>>1430</option>
                        <option value="1500" <c:if test="${1500==time}">selected</c:if>>1500</option>
                    </select>
                </th>

                <th>卖出时间：
                    <select name="goTime">
                        <option value="">请选择</option>
                        <option value="930" <c:if test="${930==goTime}">selected</c:if>>930</option>
                        <option value="1000" <c:if test="${1000==goTime}">selected</c:if>>1000</option>
                        <option value="1030" <c:if test="${1030==goTime}">selected</c:if>>1030</option>
                        <option value="1100" <c:if test="${1100==goTime}">selected</c:if>>1100</option>
                        <option value="1130" <c:if test="${1130==goTime}">selected</c:if>>1130</option>
                        <option value="1300" <c:if test="${1300==goTime}">selected</c:if>>1300</option>
                        <option value="1330" <c:if test="${1330==goTime}">selected</c:if>>1330</option>
                        <option value="1400" <c:if test="${1400==goTime}">selected</c:if>>1400</option>
                        <option value="1430" <c:if test="${1430==goTime}">selected</c:if>>1430</option>
                        <option value="1500" <c:if test="${1500==goTime}">selected</c:if>>1500</option>
                    </select>
                </th>

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
                <th><input type="submit" value="查询">&nbsp;sum:${rateSum} | zt:${ztRateSum} | fzt:${fztRateSum}</th>
            </tr>
        </table>

        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>index</th>
                <th>code</th>
                <th>preInflow</th>
                <th>lastInflow</th>
                <th>prePrice</th>
                <th>endPrice</th>
                <th>nextStartPrice</th>
                <th>preK/D/J</th>
                <th>preDiff/Dea/M</th>
                <th>preRate</th>
                <th>endRate</th>
                <th>nextRate</th>
                <th>incomeRate</th>
                <th>threeRate</th>
                <th>date</th>
            </tr>
            <%--item 前，itemOld后--%>
            <c:forEach items="${dayGoodVo1s}" var="item"  step="1"  varStatus="var">
                <tr>
                        <%--当前--%>
                    <td style="<c:if test="${item.preRate>=9.5}">background:yellow;</c:if>">
                    <a href="/day/detail?companyCode=${item.companyCode}&count=100"  target="_blank">${var.count}</a></td>
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
                    <td>${item.preInflow}</td>
                    <td >${item.lastInflow}</td>
                    <td >${item.prePrice}</td>
                    <td style="<c:choose>
                    <c:when test="${item.lastPrice<item.prePrice}">
                            color: red;
                    </c:when>
                    <c:when test="${item.lastPrice>item.prePrice}">
                            color: green;
                    </c:when>
                            </c:choose>">${item.lastPrice}</td>
                    <td >${item.twoStartPrice}</td>
                    <td>
                        <fmt:formatNumber type="number" value="${item.preK}" pattern="0.00" maxFractionDigits="2"/>/
                        <fmt:formatNumber type="number" value="${item.preD}" pattern="0.00" maxFractionDigits="2"/>/
                        <fmt:formatNumber type="number" value="${item.preJ}" pattern="0.00" maxFractionDigits="2"/>
                    </td>
                            <td>
                                <fmt:formatNumber type="number" value="${item.diff}" pattern="0.00" maxFractionDigits="2"/>/
                                <fmt:formatNumber type="number" value="${item.dea}" pattern="0.00" maxFractionDigits="2"/>/
                                <fmt:formatNumber type="number" value="${item.macd}" pattern="0.00" maxFractionDigits="2"/>
                            </td>
                    <td style="<c:if test="${item.preRate>=9.5}">background:yellow;</c:if>">${item.preRate}%</td>
                    <td style="<c:choose>
                    <c:when test="${item.lastRate<item.preRate}">
                            color: red;
                    </c:when>
                    <c:when test="${item.lastRate>item.preRate}">
                            color: green;
                    </c:when>
                            </c:choose>">${item.lastRate}%</td>
                    <td>${item.twoRate}%</td>
                    <td style="<c:choose>
                    <c:when test="${item.incomeRate<0}">
                            color: red;
                    </c:when>
                            </c:choose>">${item.incomeRate}</td>
                    <td>${item.threeRate}%</td>

                    <td><a target="_blank" href="/data/chart?companyCode=${item.companyCode}&date=${item.date}">${item.date}</a></td>
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
