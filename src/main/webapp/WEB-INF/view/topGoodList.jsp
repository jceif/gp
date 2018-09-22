<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>${twoRateSum}|${oneRateSum}</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <style>
        td {
            padding: 6px 5px;
            background-color: #fff;
            border: 1px solid #ddd;
            min-width: 10px;
            text-align: center;
        }

        th {
            border: 1px solid #ddd;
            height: 45px;
            background: azure;
            padding: 8px 6px;
            text-align: center;
        }

        input {
            height: 30px;
            padding: 3px 5px;
            margin: 3px 5px;
            min-width: 100px;
        }

        select {
            height: 30px;
            padding: 3px 5px;
            margin: 3px 5px;
            min-width: 100px;
        }
    </style>
</head>
<body>


<form action="/data/topList" method="get">
    <div style="margin:0 auto; width: 90%;text-align: center;">

        <table style="width: 100%;">
            <tr>
                <th>查询时间：
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
                    </select>
                </th>

                <th>净流入：<input type="number" name="inflow"
                               value="${inflow}">
                </td>

                <th>rate：<input type="number" name="rate"
                               value="${rate}">
                </td>
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
                <th>index</th>
                <th>code</th>
                <th>preTime</th>
                <th>preInflow</th>
                <th>prePrice</th>
                <th>preRate</th>
                <th>lastInflow</th>
                <th>lastPrice</th>
                <th>lastRate</th>
                <th>twoRate</th>
                <th>twoStartPrice</th>
                <th>twoEndPrice</th>
                <th>date</th>
            </tr>
        <%--item 前，itemOld后--%>
            <c:forEach items="${dayGoodVo1s}" var="item"  step="1"  varStatus="var">
            <tr>
                    <%--当前--%>
                <td >${var.count}</td>
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
                <td>${item.preTime}</td>
                <td>${item.preInflow}</td>
                <td>${item.prePrice}</td>
                <td>${item.preRate}%</td>
                <td >${item.lastInflow}</td>
                <td style="<c:choose>
                <c:when test="${item.lastPrice>item.prePrice}">
                        color: red;
                </c:when>
                <c:when test="${item.lastPrice<item.prePrice}">
                        color: green;
                </c:when>
                        </c:choose>">${item.lastPrice}</td>
                <td>${item.lastRate}%</td>
                <td>${item.twoRate}%</td>
                <td >${item.twoStartPrice}</td>
                <td style="<c:choose>
                <c:when test="${item.twoEndPrice>item.prePrice}">
                        background: red;
                </c:when>
                <c:when test="${item.twoEndPrice<item.prePrice}">
                        background: green;
                </c:when>
                        </c:choose>">${item.twoEndPrice}</td>
                <td>${item.date}</td>
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
