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

                <th>开始时间：
                    <select name="timeStart">
                        <option value="">请选择</option>
                        <option value="1100" <c:if test="${1100==timeStart}">selected</c:if>>${1100}</option>
                        <option value="1130" <c:if test="${1130==timeStart}">selected</c:if>>${1130}</option>
                        <option value="1300" <c:if test="${1300==timeStart}">selected</c:if>>${1300}</option>
                        <option value="1330" <c:if test="${1330==timeStart}">selected</c:if>>${1330}</option>
                        <option value="1400" <c:if test="${1400==timeStart}">selected</c:if>>${1400}</option>
                        <option value="1430" <c:if test="${1430==timeStart}">selected</c:if>>${1430}</option>
                    </select>
                </th>
                <th>结束时间：
                    <select name="timeEnd">
                        <option value="">请选择</option>
                        <option value="1100" <c:if test="${1100==timeEnd}">selected</c:if>>${1100}</option>
                        <option value="1130" <c:if test="${1130==timeEnd}">selected</c:if>>${1130}</option>
                        <option value="1300" <c:if test="${1300==timeEnd}">selected</c:if>>${1300}</option>
                        <option value="1330" <c:if test="${1330==timeEnd}">selected</c:if>>${1330}</option>
                        <option value="1400" <c:if test="${1400==timeEnd}">selected</c:if>>${1400}</option>
                        <option value="1430" <c:if test="${1430==timeEnd}">selected</c:if>>${1430}</option>

                    </select>
                </th>
                <th><input type="submit" value="查询"></th>
            </tr>
        </table>

        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>date</th>
                <th>companyCode</th>
                <th>preMoney</th>
                <th>nextMoney</th>
                <th>preRate</th>
                <th>endRate</th>
                <th>nextRate</th>
                <th>time</th>
                <th>rateSum</th>
            </tr>
        <%--item 前，itemOld后--%>
            <c:forEach items="${listMap}" var="item"  step="1"  varStatus="var">
                    <c:forEach items="${item.value}" var="v" varStatus="status">
                        <tr>
                            <c:if test="${status.index==0}">
                                 <td style="border-bottom: #1b6d85 solid 1px" rowspan="${fn:length(item.value)}">${fn:split(item.key, '_')[0]}</td>
                            </c:if>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">    <c:choose>
                                    <c:when test="${ fn:substring(v.companyCode ,0,3)=='600' or fn:substring(v.companyCode ,0,2)=='60'}">
                                        <a href="http://quote.eastmoney.com/concept/sh${v.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                           target="_blank">${v.companyCode}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="http://quote.eastmoney.com/concept/sz${v.companyCode}.html?from=classic&eventcode=Web_quote_entrance2"
                                           target="_blank">${v.companyCode}</a>
                                    </c:otherwise>
                                </c:choose></td>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.mainMoney}</td>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.endMainMoney}</td>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.rate}%</td>
                               <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.endRate}%</td>
                               <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.nextRate}%</td>
                                <td style="<c:if test="${status.index==(fn:length(item.value)-1)}">border-bottom: #1b6d85 solid 1px</c:if>">${v.time}</td>
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
