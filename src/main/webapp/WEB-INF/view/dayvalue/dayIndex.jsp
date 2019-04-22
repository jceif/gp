<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>

<html>
<head>
    <title>data-day-index</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx}assets/css/common/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}assets/css/common/list.css">

</head>
<body>


<form action="/day/index" method="get">
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

                <th>计算收益：
                    <select name="isIncome">
                        <option value="0" <c:if test="${0==isIncome}">selected</c:if>>否</option>
                        <option value="1" <c:if test="${1==isIncome}">selected</c:if>>是</option>
                    </select>
                </th>

                <th>持有：<input type="number" name="limit" value="${limit}">天 </th>
                <th><input type="submit" value="查询">&nbsp; ${allSumRate}</th>
            </tr>

        </table>

        <table style="width: 100%;">
            <tbody>
            <tr>
                <th>index</th>
                <th>code</th>
                <th>inflow</th>
                <th>volume</th>
                <th>amount</th>
                <th>start</th>
                <th>end</th>
                <th>max</th>
                <th>min</th>
                <th>rate</th>
                <th>diff</th>
                <th>dea</th>
                <th>macd</th>
                <th>k</th>
                <th>d</th>
                <th>j</th>
                <th>date</th>
                <th>sumRate</th>
                <th>sumPreRate</th>
                <th>inDateRate</th>
            </tr>

        <%--item 前，itemOld后--%>
            <c:forEach items="${dayValues}" var="item"  step="1"  varStatus="var">
                <%--<c:if test="${ fn:substring(item.companyCode ,0,3)!='300'}">--%>
                    <tr>
                            <%--当前--%>
                        <td>${var.count}</td>
                        <td>
                            <a href="/day/detail?companyCode=${item.companyCode}&count=100"
                               target="_blank">${item.companyCode}</a></td>
                        <td>${item.totalMoney}</td>
                        <td style="
                        <c:choose>
                        <c:when test="${item.volume/10000<2000}">
                                color:green;
                        </c:when>
                                </c:choose>"><fmt:formatNumber type="number"
                                                               value="${item.volume/10000}"
                                                               pattern="0.0"
                                                               maxFractionDigits="1"/></td>
                        <td style="
                        <c:choose>
                        <c:when test="${item.amount/10000>60000 }">
                                color:red;
                        </c:when>
                        <c:when test="${item.amount/10000<2000 }">
                                color:green;
                        </c:when>

                                </c:choose>"><fmt:formatNumber type="number"
                                                               value="${ item.amount/10000}"
                                                               pattern="0.0"
                                                               maxFractionDigits="1"/></td>
                            <%--半小时之前的涨幅--%>
                        <td>${item.startPrice}</td>
                            <%--一小时之前的涨幅--%>
                        <td>${item.endPrice}</td>
                            <%--当前净流入--%>
                        <td>${item.maxPrice}</td>
                        <td>${item.minPrice}</td>
                            <%--涨跌幅度--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.rate>=4.4}">
                                background: red;
                        </c:when>
                        <c:when test="${item.rate>=3}">
                                background: yellow;
                        </c:when>
                        <c:when test="${item.rate<=0}">
                                background: green;
                        </c:when>
                                </c:choose>">${item.rate}%
                        </td>

                        <td>${item.diff}</td>
                        <td>${item.dea}</td>
                        <td>${item.macd}</td>


                        <td style="
                        <c:choose>
                        <c:when test="${item.k>=90}">
                                /*color: red;*/
                        </c:when>
                        <c:when test="${item.k<90 &&  item.k>10}">

                        </c:when>
                        <c:when test="${item.k<=10}">
                                /*color: green;*/
                        </c:when>
                                </c:choose>">${item.k}</td>
                            <%--D线是慢速主干线——数值在80以上为超买，数值在20以下为超卖；--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.d>=80}">
                                /*color: red;*/
                        </c:when>
                        <c:when test="${item.d<80 &&  item.d>20}">

                        </c:when>
                        <c:when test="${item.d<=20}">
                                /*color: green;*/
                        </c:when>
                                </c:choose>">${item.d}</td>
                            <%--当J值大于90，特别是连续5天以上，股价至少会形成短期头部，反之J值小于10时，特别是连续数天以上，股价至少会形成短期底部。--%>
                        <td style="
                        <c:choose>
                        <c:when test="${item.j>=90}">

                        </c:when>
                        <c:when test="${item.j<90 &&  item.d>10}">

                        </c:when>
                        <c:when test="${item.j<=10}">

                        </c:when>
                                </c:choose>">${item.j}</td>

                            <%--kdj 结果分析--%>


                        <td>${item.date}</td>
                        <td style="
                        <c:choose>
                        <c:when test="${item.sumRate<0}">color: red;</c:when></c:choose>">${item.sumRate}</td>
                            <%--<fmt:formatDate value="${item.date}"--%>
                            <%--pattern="yyyy-MM-dd HH:mm:ss"--%>
                            <%--timeZone="0"/>--%>
                                <td style="
                                <c:choose>
                                <c:when test="${item.sumPreRate>-5}">color: red;</c:when></c:choose>">${item.sumPreRate}</td>
                                <c:if test="${isIncome==0 }">
                                    <c:if test="${item.rate<=1.5}">
                                    <td style="color: red;"><fmt:formatNumber type="number" value="${1.5-item.rate}" pattern="0.00" maxFractionDigits="2"/></td>
                                    </c:if>
                                    <c:if test="${item.rate>1.5}">
                                        <td ><fmt:formatNumber type="number" value="${1.5-item.rate}" pattern="0.00" maxFractionDigits="2"/></td>
                                    </c:if>
                                </c:if>
                                <c:if test="${isIncome==1}">
                                    <td style="
                                    <c:choose>
                                    <c:when test="${(item.inDateRate+item.rate)<1.5}">color: red;</c:when></c:choose>">
                                        <fmt:formatNumber type="number" value="${item.inDateRate}" pattern="0.00" maxFractionDigits="2"/>
                                    </td>
                                </c:if>

                    </tr>
                <%--</c:if>--%>
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
