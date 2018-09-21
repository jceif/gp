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


<form action="/day/topList" method="get">
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
                <th>index</th>
                <th>code</th>
                <th>preEnd</th>
                <th>nextStart</th>
                <th>nextMax</th>
                <th>nextEnd</th>
                <th>preRate</th>
                <th>nextRate</th>
                <th>threeRate</th>
                <th>threeMaxPrice</th>
                <th>threeEndPrice</th>
                <th>preInflow</th>
                <th>nextInflow</th>
                <th>date</th>
            </tr>

        <%--item 前，itemOld后--%>
            <c:forEach items="${dayValues}" var="item"  step="1"  varStatus="var">
            <tr>
                    <%--当前--%>
                <td style="<c:choose>
                        <c:when test="${item.nextRate>0}">
                            color: red;
                        </c:when>
                        <c:when test="${item.nextMaxPrice>item.preEndPrice}">

                        </c:when>
                <c:when test="${item.nextMaxPrice<item.preEndPrice}">
                        color: green;
                        </c:when>
                        </c:choose>">${var.count}</td>
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
                            <c:if test="${item.isNew==1}"><span style="color: red">n</span></c:if>
                        </td>
                <td>${item.preEndPrice}</td>
                <td>${item.nextStartPrice}</td>
                <td>${item.nextMaxPrice}</td>
                <td>${item.nextEndPrice}</td>
                <td>${item.preRate}</td>
                <td  style="<c:choose>
                <c:when test="${item.nextRate>0}">
                        background: red;
                </c:when>
                <c:when test="${item.nextRate<0}">
                        background: green;
                </c:when>
                        </c:choose>">${item.nextRate}</td>
                <td  style="<c:choose>
                <c:when test="${item.threeRate>0}">
                        color: red;
                </c:when>
                <c:when test="${item.threeRate<0}">
                        color: green;
                </c:when>
                        </c:choose>">${item.threeRate}</td>
                <td>${item.threeMaxPrice}</td>
                <td>${item.threeEndPrice}</td>
                <td >${item.preTotalMoney}</td>
                <td >${item.nextTotalMoney}</td>
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